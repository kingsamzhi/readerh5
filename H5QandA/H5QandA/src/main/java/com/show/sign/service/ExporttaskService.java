package com.show.sign.service;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.show.sign.entity.ExcelModel;
import com.show.sign.mapper.ExporttaskMapper;
import com.show.sign.mapper.OptionsMapper;
import com.show.sign.mapper.QuestionsMapper;
import com.show.sign.pojo.Exporttask;
import com.show.sign.pojo.Options;
import com.show.sign.pojo.Questions;
import com.show.sign.utils.ExcelUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tk.mybatis.mapper.entity.Example;

import javax.annotation.Resource;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Service
public class ExporttaskService {
    @Resource
    private RedisService redisService;

    @Resource
    private ExporttaskMapper exportTaskMapper;

    @Resource
    private OptionsMapper optionsMapper;

    @Resource
    private QuestionsMapper questionsMapper;


    public PageInfo<Exporttask> getExporttasks(String groupid, Integer flag,Integer page, Integer limit){

            Example example = Example.builder(Exporttask.class).build();
            Example.Criteria c=example.createCriteria();

            if(groupid!=null&&!groupid.equals("")) {
                c.andEqualTo("groupid",  groupid);
            }
            if(flag!=null&&!flag.equals("")) {
                c.andEqualTo("flag",  flag);
            }
            Page<Exporttask> p = PageHelper.startPage(page, limit);
            List<Exporttask> datas = exportTaskMapper.selectByExample(example);
            PageInfo pageInfo = new PageInfo<>(datas);
            return pageInfo;
    }

    @Transactional
    public void exportTasks(){

        Example example = Example.builder(Exporttask.class).build();
        Example.Criteria c=example.createCriteria();
        c.andEqualTo("flag",  0);
        List<Exporttask> datas = exportTaskMapper.selectByExample(example);
        if(datas!=null)
        for(Exporttask task:datas){
            try {
                File file=new File(task.getPath());
                List<ExcelModel> excelModels = ExcelUtils.importExcel(file, ExcelModel.class);
                //导入数据
                if(excelModels!=null&&excelModels.size()>0) {
                    int totalq=0;
                    String answererror="编号：";
                    for (ExcelModel em : excelModels) {
                        if(em.getAnswer()==null){
                            answererror+=em.getId();
                            continue;
                        }
                        Questions question = new Questions();
                        question.setGroupid(task.getGroupid());
                        question.setFlag(0);
                        question.setTitle(em.getTitle());
                        question.setType(em.getType());
                        question.setAnswers(em.getAnswer());
                        question.setOwner(em.getOwner());
                        question.setLevels(task.getType());
                        question.setShowcount(0);
                        question.setSucccount(0);
                        questionsMapper.insert(question);
                        int questionid = question.getId();
                        String answer="";

                        if (em.getOptionA() != null && !em.getOptionA().trim().equals("")) {

                            Options options = new Options();
                            options.setGroupid(task.getGroupid());
                            options.setQuestionid(questionid);
                            options.setFlag(0);
                            options.setContentx(em.getOptionA());
                            optionsMapper.insert(options);
                            if(em.getAnswer().contains("A")){
                                answer+=options.getId()+".";
                            }
                        }
                        if (em.getOptionB() != null && !em.getOptionB().trim().equals("")) {
                            Options options = new Options();
                            options.setGroupid(task.getGroupid());
                            options.setQuestionid(questionid);
                            options.setFlag(0);
                            options.setContentx(em.getOptionB());
                            optionsMapper.insert(options);
                            if(em.getAnswer().contains("B")){
                                answer+=options.getId()+".";
                            }
                        }
                        if (em.getOptionC() != null && !em.getOptionC().trim().equals("")) {
                            Options options = new Options();
                            options.setGroupid(task.getGroupid());
                            options.setQuestionid(questionid);
                            options.setFlag(0);
                            options.setContentx(em.getOptionC());
                            optionsMapper.insert(options);
                            if(em.getAnswer().contains("C")){
                                answer+=options.getId()+".";
                            }
                        }
                        if (em.getOptionD() != null && !em.getOptionD().trim().equals("")) {
                            Options options = new Options();
                            options.setGroupid(task.getGroupid());
                            options.setQuestionid(questionid);
                            options.setFlag(0);
                            options.setContentx(em.getOptionD());
                            optionsMapper.insert(options);
                            if(em.getAnswer().contains("D")){
                                answer+=options.getId()+".";
                            }
                        }
                        if (em.getOptionE() != null && !em.getOptionE().trim().equals("")) {
                            Options options = new Options();
                            options.setGroupid(task.getGroupid());
                            options.setQuestionid(questionid);
                            options.setFlag(0);
                            options.setContentx(em.getOptionE());
                            optionsMapper.insert(options);
                            if(em.getAnswer().contains("E")){
                                answer+=options.getId()+".";
                            }
                        }
                        if (em.getOptionF() != null && !em.getOptionF().trim().equals("")) {
                            Options options = new Options();
                            options.setGroupid(task.getGroupid());
                            options.setQuestionid(questionid);
                            options.setFlag(0);
                            options.setContentx(em.getOptionF());
                            optionsMapper.insert(options);
                            if(em.getAnswer().contains("F")){
                                answer+=options.getId()+".";
                            }
                        }
                        if (em.getOptionG() != null && !em.getOptionG().trim().equals("")) {
                            Options options = new Options();
                            options.setGroupid(task.getGroupid());
                            options.setQuestionid(questionid);
                            options.setFlag(0);
                            options.setContentx(em.getOptionG());
                            optionsMapper.insert(options);
                            if(em.getAnswer().contains("G")){
                                answer+=options.getId()+".";
                            }
                        }
                        if (em.getOptionH() != null && !em.getOptionH().trim().equals("")) {
                            Options options = new Options();
                            options.setGroupid(task.getGroupid());
                            options.setQuestionid(questionid);
                            options.setFlag(0);
                            options.setContentx(em.getOptionH());
                            optionsMapper.insert(options);
                            if(em.getAnswer().contains("H")){
                                answer+=options.getId()+".";
                            }
                        }
                        if (em.getOptionI() != null && !em.getOptionI().trim().equals("")) {
                            Options options = new Options();
                            options.setGroupid(task.getGroupid());
                            options.setQuestionid(questionid);
                            options.setFlag(0);
                            options.setContentx(em.getOptionI());
                            optionsMapper.insert(options);
                            if(em.getAnswer().contains("I")){
                                answer+=options.getId()+".";
                            }
                        }
                        if (em.getOptionJ() != null && !em.getOptionJ().trim().equals("")) {
                            Options options = new Options();
                            options.setGroupid(task.getGroupid());
                            options.setQuestionid(questionid);
                            options.setFlag(0);
                            options.setContentx(em.getOptionJ());
                            optionsMapper.insert(options);
                            if(em.getAnswer().contains("J")){
                                answer+=options.getId()+".";
                            }
                        }
                        answer=answer.substring(0,answer.length()-1);
                        question.setAnswers(answer);
                        questionsMapper.updateByPrimaryKey(question);
                        totalq++;
                    }

                    task.setInfo("找到"+excelModels.size()+"条记录,导入"+totalq+"条记录；"+answererror+"答案为空，无法导入！;");
                    task.setTotalcount(totalq);
                    task.setFlag(1);

                }else{
                    task.setInfo("Excel文件找不到记录！");
                    task.setTotalcount(0);
                    task.setFlag(-1);
                }
                //更新task状态



            }catch(Exception ex){
                ex.printStackTrace();
                task.setInfo(ex.toString());
                task.setFlag(-1);
            }

            SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            task.setFinishtime(sdf.format(new Date()));
            exportTaskMapper.updateByPrimaryKey(task);
            
        }

    }

    public int editExporttasks(Exporttask admin) {
        return exportTaskMapper.updateByPrimaryKey(admin);
    }

    public int addExporttasks(Exporttask admin) {
        return exportTaskMapper.insert(admin);
    }

    public Exporttask getExporttasksById(Integer id) {
        return exportTaskMapper.selectByPrimaryKey(id);
    }

 

    public int deleteExporttasks(Integer id) {
        return exportTaskMapper.deleteByPrimaryKey(id);
    }
    public int cleanExporttasks(Integer id) {

        Exporttask task=exportTaskMapper.selectByPrimaryKey(id);
        String groupid=task.getGroupid();

        Example example = Example.builder(Questions.class).build();
        Example.Criteria c=example.createCriteria();
        if(groupid!=null&&!groupid.equals("")) {
            c.andEqualTo("groupid",  groupid);
        }
        questionsMapper.deleteByExample(example);

        Example example2 = Example.builder(Options.class).build();
        Example.Criteria c2=example2.createCriteria();
        if(groupid!=null&&!groupid.equals("")) {
            c2.andEqualTo("groupid",  groupid);
        }
        optionsMapper.deleteByExample(example2);

        return exportTaskMapper.deleteByPrimaryKey(id);
    }

}
