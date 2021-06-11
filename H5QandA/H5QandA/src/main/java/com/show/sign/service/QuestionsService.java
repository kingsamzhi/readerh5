package com.show.sign.service;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.show.sign.entity.ExcelModel;
import com.show.sign.entity.QuestionsReq;
import com.show.sign.entity.WX;
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
import java.util.LinkedList;
import java.util.List;

@Service
public class QuestionsService {
    @Resource
    private RedisService redisService;

    @Resource
    private OptionsMapper optionsMapper;

    @Resource
    private QuestionsMapper questionsMapper;


    public PageInfo<Questions> getQuestions(String groupid, Integer flag,Integer page, Integer limit){

            Example example = Example.builder(Questions.class).build();
            Example.Criteria c=example.createCriteria();

            if(groupid!=null&&!groupid.equals("")) {
                c.andEqualTo("groupid",  groupid);
            }
            if(flag!=null&&!flag.equals("")) {
                c.andEqualTo("flag",  flag);
            }
            Page<Questions> p = PageHelper.startPage(page, limit);
            List<Questions> datas = questionsMapper.selectByExample(example);
            PageInfo pageInfo = new PageInfo<>(datas);
            return pageInfo;
    }


    public PageInfo<QuestionsReq> getQuestionsWithOptions(String groupid, Integer flag,Integer page, Integer limit){

        Example example = Example.builder(Questions.class).build();
        Example.Criteria c=example.createCriteria();

        if(groupid!=null&&!groupid.equals("")) {
            c.andEqualTo("groupid",  groupid);
        }
        if(flag!=null&&!flag.equals("")) {
            c.andEqualTo("flag",  flag);
        }
        Page<Questions> p = PageHelper.startPage(page, limit);
        List<Questions> datas = questionsMapper.selectByExample(example);
        PageInfo pageInfo1 = new PageInfo<>(datas);

        List<QuestionsReq> reqs=new LinkedList<QuestionsReq>();
        for(Questions question:datas){
            QuestionsReq req=new QuestionsReq();
            req.setFlag(question.getFlag());
            req.setGroupid(question.getGroupid());
            req.setAnswers(question.getAnswers());
            req.setLevels(question.getLevels());
            req.setId(question.getId());
            req.setOwner(question.getOwner());
            req.setTitle(question.getTitle());
            req.setType(question.getType());
            req.setShowcount(question.getShowcount());
            req.setSucccount(question.getSucccount());

            Example e2 = Example.builder(Options.class).build();
            Example.Criteria c2=e2.createCriteria();

            if(groupid!=null&&!groupid.equals("")) {
                c2.andEqualTo("groupid",  groupid);
            }
            c2.andEqualTo("questionid",  question.getId());
            e2.setOrderByClause(" rand() ");

            List<Options> options = optionsMapper.selectByExample(e2);
            req.setOptions(options);

            reqs.add(req);
        }

        PageInfo pageInfo = new PageInfo<>(reqs);
        pageInfo.setTotal(pageInfo1.getTotal());
        return pageInfo;
    }


    public int update(Integer id,Integer type){
        Questions question=questionsMapper.selectByPrimaryKey(id);
        if(type==0)
            question.setShowcount(question.getShowcount()+1);
        else if(type==1)
            question.setSucccount(question.getSucccount()+1);

        //如果题目正确次数超过，则flag=-1;
        if(question.getSucccount()>= WX.expirenum){
            question.setFlag(-1);
        }

        return questionsMapper.updateByPrimaryKey(question);
    }


}
