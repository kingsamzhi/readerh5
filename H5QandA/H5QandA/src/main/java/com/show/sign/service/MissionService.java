package com.show.sign.service;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.google.gson.Gson;
import com.qcloud.cos.utils.StringUtils;
import com.show.sign.entity.*;
import com.show.sign.mapper.*;
import com.show.sign.mapper.MissionMapper;
import com.show.sign.pojo.*;
import com.show.sign.pojo.Mission;
import io.netty.util.internal.StringUtil;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tk.mybatis.mapper.entity.Example;

import javax.annotation.Resource;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

@Service
public class MissionService {
    @Resource
    private RedisService redisService;

    @Resource
    private MissionMapper missionMapper;

    @Resource
    private StudentMapper studentMapper;

    @Resource
    private StuopenMapper stuopenMapper;

    @Resource
    private MissiondetailMapper missiondetailMapper;
    @Resource
    private QuestionsMapper questionsMapper;
    @Resource
    private OptionsMapper optionsMapper;

    @Resource
    private MarkconfigMapper markconfigMapper;

    @Resource
    private SignrecordMapper signrecordMapper;

    @Resource
    private StumedalMapper stumedalMapper;
    @Resource
    private MedalMapper metalMapper;
    @Resource
    private BukaMapper bukaMapper;

    SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    SimpleDateFormat sdfdate=new SimpleDateFormat("yyyy-MM-dd");
    SimpleDateFormat sdftime=new SimpleDateFormat("HH:mm:ss");

    /**
     * 计算两个日期之间相差的天数
     * @param smdate 较小的时间
     * @param bdate  较大的时间
     * @return 相差天数
     * @throws ParseException
     */
    public static int daysBetween(Date smdate,Date bdate) throws ParseException
    {
        SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
        smdate=sdf.parse(sdf.format(smdate));
        bdate=sdf.parse(sdf.format(bdate));
        Calendar cal = Calendar.getInstance();
        cal.setTime(smdate);
        long time1 = cal.getTimeInMillis();
        cal.setTime(bdate);
        long time2 = cal.getTimeInMillis();
        long between_days=(time2-time1)/(1000*3600*24);

        return Integer.parseInt(String.valueOf(between_days));
    }

        /**
     　　 *字符串的日期格式的计算
     　　 */
    public static int daysBetween(String smdate,String bdate) throws ParseException{
        SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
        Calendar cal = Calendar.getInstance();
        cal.setTime(sdf.parse(smdate));
        long time1 = cal.getTimeInMillis();
        cal.setTime(sdf.parse(bdate));
        long time2 = cal.getTimeInMillis();
        long between_days=(time2-time1)/(1000*3600*24);

        return Integer.parseInt(String.valueOf(between_days));
    }


    //获取题目。检查今天是否做过，未做过就创建一条任务。做过的话，检查是否做完，如果做完就看是否可以再做一次
    //返回 一条题目
    @Transactional
    public AnswerResult getMissions(String token, Integer stuid,String replacedate){

        AnswerResult missionResult=new AnswerResult();
        String today=sdfdate.format(new Date());
        if(replacedate!=null&&!StringUtils.isNullOrEmpty(replacedate)){
            int between=-1;
            try {
                between = MissionService.daysBetween(replacedate, today);
            }catch(Exception ex){
                ex.printStackTrace();
            }
            if(between<=0||between>7){
                missionResult.setCode("1");
                missionResult.setCount(0);
                missionResult.setDate(sdfdate.format(new Date()));
                missionResult.setTime(sdftime.format(new Date()));
                missionResult.setNowtimes(0);
                missionResult.setNum(0);
                missionResult.setMissiondetail(null);
                missionResult.setMission(null);
                missionResult.setShowcount(0);
                missionResult.setSucccount(0);
                missionResult.setMsg("补卡失败，补卡时间已超过7天");
                return missionResult;
            }

            //查一下是否能补卡

            List<Buka> bukas=bukaMapper.get7BukaBystuid(stuid);

            if(bukas!=null&&bukas.size()>=2){
                //判断能否补卡
                boolean accept=false;
                for(Buka buka:bukas){
                    if(buka.getDatime().equals(replacedate)){//如果补卡记录已经存在
                        accept=true;
                        break;
                    }
                    if(!accept){
                        missionResult.setCode("1");
                        missionResult.setCount(0);
                        missionResult.setDate(sdfdate.format(new Date()));
                        missionResult.setTime(sdftime.format(new Date()));
                        missionResult.setNowtimes(0);
                        missionResult.setNum(0);
                        missionResult.setMissiondetail(null);
                        missionResult.setMission(null);
                        missionResult.setShowcount(0);
                        missionResult.setSucccount(0);
                        missionResult.setMsg("补卡失败，7天内补卡次数已经超过2次！");
                        return missionResult;
                    }
                }
            }
        }


            /*
            1.判断是否token和stuid是合法的
            * */
            String openid=redisService.getValue(token);
            //获取openid并获取学生信息，验证是否是绑定的openid
            Example stuexample = Example.builder(Stuopen.class).build();
            Example.Criteria stuc=stuexample.createCriteria();
            stuc.andEqualTo("stuid",stuid);//当前这个学生
            stuc.andEqualTo("openid",openid);
            List<Stuopen> stus=stuopenMapper.selectByExample(stuexample);

            if(openid==null||stus==null||stus.size()<=0){
                missionResult.setCode("1");
                missionResult.setCount(0);
                missionResult.setDate(sdfdate.format(new Date()));
                missionResult.setTime(sdftime.format(new Date()));
                missionResult.setNowtimes(0);
                missionResult.setNum(0);
                missionResult.setMissiondetail(null);
                missionResult.setMission(null);
                missionResult.setShowcount(0);
                missionResult.setSucccount(0);
                missionResult.setMsg("找不到该学生信息，或token无效");
                return missionResult;
            }

            /*
            * 2.获得学生信息
            * */
            Stuopen stu=stus.get(0);

            Student stinfo=studentMapper.selectByPrimaryKey(stuid);
            if(stinfo==null){
                missionResult.setCode("1");
                missionResult.setCount(0);
                missionResult.setDate(sdfdate.format(new Date()));
                missionResult.setTime(sdftime.format(new Date()));
                missionResult.setNowtimes(0);
                missionResult.setNum(0);
                missionResult.setMissiondetail(null);
                missionResult.setMission(null);
                missionResult.setShowcount(0);
                missionResult.setSucccount(0);
                missionResult.setMsg("找不到该学生信息，或token无效");
                return missionResult;
            }

            //更新token
            if(openid.equals("oeGfF6NUGBv1LdgPCGyQPERuxueg")){
                redisService.setValue(token,openid);
            }else {
                redisService.setValueTime(token, openid, WX.expire);
            }

            /*
            * 3.查找或创建mission
            * */
            Example example = Example.builder(Mission.class).build();
            Example.Criteria c=example.createCriteria();
            c.andEqualTo("stuid",stuid);//当前这个学生
            if(replacedate!=null&& StringUtils.isNullOrEmpty(replacedate)){
                c.andEqualTo("startdate", replacedate);//当前日期
            }else {
                c.andEqualTo("startdate", sdfdate.format(new Date()));//当前日期
            }
            List<Mission> missions = missionMapper.selectByExample(example);
            int finishmissioncount=0;//已经完成mission数目
            int missionid=0;//需要创建question的missionid
            Mission nowmission=null;
            if(missions!=null&&missions.size()>0){
                for(Mission mission:missions){
                    if(mission.getTotalnum().equals(mission.getFinishnum())){//已经完成题目=总数
                        finishmissioncount++;
                    }else{//如果不相同，证明还未完成。
                        missionid=mission.getId();
                        nowmission=mission;
                    }
                }
            }

            if(replacedate!=null&& StringUtils.isNullOrEmpty(replacedate)){
                c.andEqualTo("startdate", replacedate);//当前日期
                if(finishmissioncount>=1){
                    missionResult.setCode("1");
                    missionResult.setCount(0);
                    missionResult.setDate(sdfdate.format(new Date()));
                    missionResult.setTime(sdftime.format(new Date()));
                    missionResult.setNowtimes(0);
                    missionResult.setNum(0);
                    missionResult.setMissiondetail(null);
                    missionResult.setMission(null);
                    missionResult.setShowcount(0);
                    missionResult.setSucccount(0);
                    missionResult.setMsg(replacedate+"已经完成"+finishmissioncount+"次,补卡完成！");
                    return missionResult;
                }
            }

            //4.如果finish任务已经有3条。
            if(finishmissioncount>=3){
                missionResult.setCode("1");
                missionResult.setCount(0);
                missionResult.setDate(sdfdate.format(new Date()));
                missionResult.setTime(sdftime.format(new Date()));
                missionResult.setNowtimes(0);
                missionResult.setNum(0);
                missionResult.setMissiondetail(null);
                missionResult.setMission(null);
                missionResult.setShowcount(0);
                missionResult.setSucccount(0);
                missionResult.setMsg("今天已经完成"+finishmissioncount+"次");
                return missionResult;
            }else{
                if(missionid==0){//无任务或有任务已完成，新开任务
                    nowmission=new Mission();
                    nowmission.setStuid(stuid);
                    if(replacedate!=null&& StringUtils.isNullOrEmpty(replacedate)) {
                        nowmission.setStartdate(replacedate);
                    }else{
                        nowmission.setStartdate(sdfdate.format(new Date()));
                    }
                    nowmission.setStarttime(sdftime.format(new Date()));
                    nowmission.setCorrectnum(0);
                    nowmission.setFinishnum(0);
                    nowmission.setMark(0);
                    nowmission.setLevels(stinfo.getLevels());
                    nowmission.setTotalnum(WX.questionNumInMission);
                    nowmission.setSignflag(0);
                    nowmission.setTimes(0);
                    missionMapper.insert(nowmission);
                }

                /**
                *
                *5.看看有没有未答的题目：missionDetail
                *
                * */
                Example msexample = Example.builder(Missiondetail.class).build();
                Example.Criteria msc=msexample.createCriteria();
                msc.andEqualTo("missionid",nowmission.getId());
                //msc.andIsNull("stuanswer");
                List<Missiondetail> details=missiondetailMapper.selectByExample(msexample);
                List<Integer> ids=new ArrayList();//所有ids
                Missiondetail notfinishdetails=null;//正在执行的detail
                if(details!=null&&details.size()>0){
                    for(Missiondetail detail:details){
                        if(detail.getStuanswer()==null&&(detail.getSturesult()==null||detail.getSturesult()==0)){
                            if(detail.getCreatenum()>=WX.limitnum){
                                //设置超时及超出尝试次数
                                return submitMission(token,detail.getId(),"刷题次数超过"+WX.limitnum+"次！");
                            }else {
                                notfinishdetails = details.get(0);
                            }
                        }
                        ids.add(detail.getQuestionid());
                    }
                }
                //6.新找一条问题
                int showcount=0;
                int succcount=0;

                Example qe = Example.builder(Questions.class).build();
                Example.Criteria qc=qe.createCriteria();
                qc.andEqualTo("levels",stinfo.getLevels());
                qc.andEqualTo("flag",0);
                if(ids!=null&&ids.size()>0) {
                    qc.andNotIn("id", ids);
                }
                qe.setOrderByClause(" rand() ");
                Page<Questions> p = PageHelper.startPage(0, 1);
                List<Questions> pquestions = questionsMapper.selectByExample(qe);
                PageInfo pageInfo = new PageInfo<Questions>(pquestions);
                if(pquestions.size()>=1){
                    Questions ques=pquestions.get(0);
                    //找option
                    Example eq2 = Example.builder(Options.class).build();
                    Example.Criteria cq2=eq2.createCriteria();
                    cq2.andEqualTo("questionid",  ques.getId());
                    eq2.setOrderByClause(" rand() ");
                    List<Options> options = optionsMapper.selectByExample(eq2);

                    String[] answersarray=null;
                    if(ques.getAnswers().contains(".")) {
                        answersarray= ques.getAnswers().split("\\.");
                    }else{
                        answersarray=new String[1];
                        answersarray[0]=ques.getAnswers();
                    }
                    //设置随机排序答案
                    String optinfo="";
                    String answerinfo="";
                    char index = 'A';
                    if(options!=null){
                        for(Options ops:options){
                            optinfo+= index+".|."+ops.getContentx()+"~|~";
                            if(answersarray!=null&&answersarray.length>0) {
                                for (String nums : answersarray) {
                                    if (ops.getId().equals(Integer.parseInt(nums))) {
                                        answerinfo+=index;
                                    }
                                }
                            }
                            index++;
                        }
                    }


                    //7.missionDetail写入
                    if(notfinishdetails==null){
                        notfinishdetails=new Missiondetail();
                    }
                    notfinishdetails.setQuestionid(ques.getId());
                    notfinishdetails.setAnswer(answerinfo);
                    notfinishdetails.setOptions(optinfo);
                    notfinishdetails.setTitle(ques.getTitle());
                    notfinishdetails.setMissionid(nowmission.getId());
                    notfinishdetails.setType(ques.getType());
                    showcount=ques.getShowcount();
                    succcount=ques.getSucccount();
                    if(notfinishdetails.getCreatenum()==null)
                        notfinishdetails.setCreatenum(1);
                    else
                        notfinishdetails.setCreatenum(notfinishdetails.getCreatenum()+1);
                    notfinishdetails.setCreatetime(sdfdate.format(new Date())+" "+sdftime.format(new Date()));
                    notfinishdetails.setLevel(ques.getLevels());
                    notfinishdetails.setStuanswer(null);
                    notfinishdetails.setSturesult(0);
                    notfinishdetails.setAnswertime(null);

                    if(notfinishdetails.getId()!=null){
                        missiondetailMapper.updateByPrimaryKey(notfinishdetails);
                    }else{
                        missiondetailMapper.insert(notfinishdetails);
                    }
                }else{
                    //如果找不到题目
                    missionResult.setMission(nowmission);
                    missionResult.setCode("0");
                    missionResult.setCount(1);
                    missionResult.setDate(sdfdate.format(new Date()));
                    missionResult.setTime(sdftime.format(new Date()));
                    missionResult.setNowtimes(details.size());//出题几多次？
                    missionResult.setNum(details.size()+1);//当前第几题？
                    missionResult.setMissiondetail(notfinishdetails);//?
                    missionResult.setShowcount(0);
                    missionResult.setSucccount(0);
                    missionResult.setMsg("获取题目失败，题库不够题目，请联系管理员！");
                    return missionResult;
                }

                //8.更新出题次数
                QuestionRedisReq req=new QuestionRedisReq(notfinishdetails.getQuestionid(), 0);
                questionAddnum(req);

                //9.设置返回数据
                missionResult.setMission(nowmission);
                missionResult.setCode("0");
                missionResult.setCount(1);
                missionResult.setDate(sdfdate.format(new Date()));
                missionResult.setTime(sdftime.format(new Date()));
                missionResult.setNowtimes(details.size());//出题几多次？
                missionResult.setNum(details.size()+1);//当前第几题？
                missionResult.setShowcount(showcount);
                missionResult.setSucccount(succcount);
                notfinishdetails.setAnswer("");
                missionResult.setMissiondetail(notfinishdetails);//?
                missionResult.setMsg("获取题目成功");
            }
            return missionResult;
    }




    @Transactional
    public AnswerResult submitMission(String token,Integer id,String answer){

        AnswerResult answerResult=new AnswerResult();

        if(token==null||id==null||answer==null){
            answerResult.setMission(null);
            answerResult.setCode("1");
            answerResult.setCount(0);
            answerResult.setDate(sdfdate.format(new Date()));
            answerResult.setTime(sdftime.format(new Date()));
            answerResult.setNowtimes(0);//出题几多次？
            answerResult.setNum(0);//当前第几题？
            answerResult.setMissiondetail(null);//?
            answerResult.setMsg("缺少参数，请选择答案！");
            return answerResult;
        }




        String openid=redisService.getValue(token);
        if(openid.equals("oeGfF6NUGBv1LdgPCGyQPERuxueg")){
            redisService.setValue(token,openid);
        }else {
            redisService.setValueTime(token, openid, WX.expire);
        }
        Missiondetail detail=missiondetailMapper.selectByPrimaryKey(id);
        if(detail==null||detail.getStuanswer()!=null){
            answerResult.setMission(null);
            answerResult.setCode("1");
            answerResult.setCount(0);
            answerResult.setDate(sdfdate.format(new Date()));
            answerResult.setTime(sdftime.format(new Date()));
            answerResult.setNowtimes(0);//出题几多次？
            answerResult.setNum(0);//当前第几题？
            answerResult.setMissiondetail(null);//?
            answerResult.setMsg("没有找到题目，或题目已经提交！");
            return answerResult;
        }

        if(detail.getStuanswer()!=null&&detail.getSturesult()!=0){
            answerResult.setMission(null);
            answerResult.setCode("1");
            answerResult.setCount(0);
            answerResult.setDate(sdfdate.format(new Date()));
            answerResult.setTime(sdftime.format(new Date()));
            answerResult.setNowtimes(0);//出题几多次？
            answerResult.setNum(0);//当前第几题？
            answerResult.setMissiondetail(null);//?
            answerResult.setMsg("题目之前已经提交了！");
            return answerResult;
        }


        Mission mission=missionMapper.selectByPrimaryKey(detail.getMissionid());
        if(mission==null){
            answerResult.setMission(null);
            answerResult.setCode("1");
            answerResult.setCount(0);
            answerResult.setDate(sdfdate.format(new Date()));
            answerResult.setTime(sdftime.format(new Date()));
            answerResult.setNowtimes(0);//出题几多次？
            answerResult.setNum(0);//当前第几题？
            answerResult.setMissiondetail(detail);//?
            answerResult.setMsg("没有找到任务！");
            return answerResult;
        }

        Example stuexample = Example.builder(Stuopen.class).build();
        Example.Criteria stuc=stuexample.createCriteria();
        stuc.andEqualTo("openid",openid);
        List<Stuopen> stus=stuopenMapper.selectByExample(stuexample);
        boolean checkuser=false;
        for(Stuopen r:stus){
            if(r.getStuid().equals(mission.getStuid())){
                checkuser=true;
            }
        }

        if(!checkuser){
            answerResult.setMission(mission);
            answerResult.setCode("1");
            answerResult.setCount(0);
            answerResult.setDate(sdfdate.format(new Date()));
            answerResult.setTime(sdftime.format(new Date()));
            answerResult.setNowtimes(0);//出题几多次？
            answerResult.setNum(0);//当前对几题？
            answerResult.setMissiondetail(null);//?
            answerResult.setMsg("提交失败，没有token或用户信息错误！");
            return answerResult;
        }
        mission.setFinishnum(mission.getFinishnum()+1);

        detail.setStuanswer(answer);
        detail.setAnswertime(sdf.format(new Date()));
        try {
            Calendar cal = Calendar.getInstance();
            cal.setTime(sdf.parse(detail.getCreatetime()));
            long starttime = cal.getTimeInMillis();
            cal.setTime(sdf.parse(detail.getAnswertime()));
            long endtime=cal.getTimeInMillis();
            int times=(int)((endtime-starttime)/1000);

            if(times>WX.ANSWER_LIMIT){
                answer="回答超时！";
            }

            detail.setTimes(times);
            mission.setTimes(mission.getTimes()+times);
        }catch(Exception ex){
            ex.printStackTrace();
        }

        if(!StringUtil.isNullOrEmpty(answer)&&answer.equals(detail.getAnswer())){
            detail.setSturesult(1);
            mission.setMark(mission.getMark()+WX.mark);
            //增加问题答对数
            QuestionRedisReq req=new QuestionRedisReq(detail.getQuestionid(), 1);
            questionAddnum(req);
            mission.setCorrectnum(mission.getCorrectnum()+1);
        }else {
            detail.setSturesult(-1);

        }

        if(mission.getTotalnum().equals(mission.getFinishnum())){
            //设置打卡成功
            mission.setSignflag(1);
            mission.setEndtime(sdf.format(new Date()));

            Example exrecord=Example.builder(Signrecord.class).build();
            Example.Criteria crecord=exrecord.createCriteria();
            crecord.andEqualTo("stuid",mission.getStuid());
            crecord.andEqualTo("signdate",mission.getStartdate());
            int count=signrecordMapper.selectCountByExample(exrecord);
            if(count==0) {
                Signrecord signrecord = new Signrecord();
                signrecord.setStuid(mission.getStuid());
                signrecord.setFlag(0);
                signrecord.setSigndate(mission.getStartdate());
                signrecordMapper.insert(signrecord);
            }

            //设置阅读之星
            setGroup(mission.getStuid());
        }

        missiondetailMapper.updateByPrimaryKey(detail);
        missionMapper.updateByPrimaryKey(mission);

        if(!mission.getStartdate().equals(sdfdate.format(new Date()))) {
            Example bukaexample= Example.builder(Buka.class).build();
            Example.Criteria bukac=bukaexample.createCriteria();
            bukac.andEqualTo("stuid",mission.getStuid());
            bukac.andEqualTo("datime",mission.getStartdate());
            int bukacount= bukaMapper.selectCountByExample(bukaexample);
            if(bukacount<=0) {
                Buka buka = new Buka();
                buka.setStuid(mission.getStuid());
                buka.setDatime(mission.getStartdate());
                bukaMapper.insert(buka);
            }
        }

        //setmarkconfig
        Example example=Example.builder(Markconfig.class).build();
        Example.Criteria msc=example.createCriteria();
        msc.andLessThanOrEqualTo("markstart",mission.getMark());
        msc.andGreaterThanOrEqualTo("markend",mission.getMark());
        List<Markconfig> mcs=markconfigMapper.selectByExample(example);
        if(mcs!=null&&mcs.size()>0){
            Markconfig mc=mcs.get(0);
            answerResult.setMarkconfig(mc);
        }

        answerResult.setMission(mission);
        answerResult.setCode("0");
        answerResult.setCount(1);
        answerResult.setDate(sdfdate.format(new Date()));
        answerResult.setTime(sdftime.format(new Date()));
        answerResult.setNowtimes(mission.getFinishnum());//出题几多次？
        answerResult.setNum(mission.getFinishnum());//当前第几题？
        answerResult.setMissiondetail(detail);//?
        answerResult.setMsg("提交成功");


        return answerResult;
    }

    public String questionAddnum(QuestionRedisReq qq){
        Gson gson=new Gson();
        RedisRequire res=new RedisRequire();
        String data=gson.toJson(qq);
        String requireid= UUID.randomUUID().toString();
        res.setRequireid(requireid);
        res.setData(data);
        String requiredata=gson.toJson(res);
        String listname=WX.updatequestion;
        if(redisService.lpush(listname, requiredata,WX.timeout)){//发送成功
            String resultx=redisService.rpop(requireid,WX.timeout);//阻塞key
            return resultx;
        }else{
            return null;
        }
    }

    //分组变成阅读之星
    public void setGroup(Integer stuid){

        Calendar c = Calendar.getInstance();
        c.setTime(new Date());
        c.add(Calendar.DATE, -7);         //利用Calendar实现Date日期+1天
        Date sDate = c.getTime();
        String date=sdf.format(sDate);
        Example e= Example.builder(Signrecord.class).build();
        Example.Criteria ce=e.createCriteria();
        ce.andEqualTo("stuid",stuid);
        ce.andGreaterThan("signdate",date);
        ce.andEqualTo("flag",0);
        int total=signrecordMapper.selectCountByExample(e);
        if(total==7){
            //最近打卡满7天
            Signrecord record=new Signrecord();
            record.setFlag(1);
            String groupid=UUID.randomUUID().toString();
            record.setGroupid(groupid);
            int totle=signrecordMapper.updateByExampleSelective(record,e);
            String now=sdf.format(new Date());

            Example meexample= Example.builder(Medal.class).build();
            Example.Criteria mec=meexample.createCriteria();
            mec.andLessThan("starttime",now);
            mec.andGreaterThan("endtime",now);
            List<Medal> metals=metalMapper.selectByExample(mec);
            if(metals!=null&&metals.size()>0){
                Stumedal stumedal=new Stumedal();
                stumedal.setStuid(stuid);
                stumedal.setGroupid(groupid);
                stumedal.setGettime(now);
                stumedal.setMedalid(metals.get(0).getId());
                stumedalMapper.insert(stumedal);
            }

        }

    }

    public static void main(String[] args) throws ParseException {
        // TODO Auto-generated method stub
        SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date d1=sdf.parse("2012-09-08 10:10:10");
        Date d2=sdf.parse("2012-09-15 00:00:00");
        System.out.println(daysBetween(d1,d2));

        System.out.println(daysBetween("2012-09-08 10:10:10","2012-09-15 00:00:00"));
    }

    public int editMission(Mission mission) {
        return missionMapper.updateByPrimaryKey(mission);
    }

    public int addMission(Mission mission) {
        return missionMapper.insert(mission);
    }

    public Mission getMissionById(Integer id) {
        return missionMapper.selectByPrimaryKey(id);
    }

    public int deleteMission(Integer id) {
        return missionMapper.deleteByPrimaryKey(id);
    }
}
