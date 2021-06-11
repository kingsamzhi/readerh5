package com.show.sign.service;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.show.sign.mapper.AdminuserMapper;
import com.show.sign.pojo.Adminuser;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import javax.annotation.Resource;
import java.util.List;
import java.util.Locale;
import java.util.UUID;

@Service
public class AdminuserService {
    @Resource
    private RedisService redisService;

    @Resource
    private AdminuserMapper testUserMapper;

    public PageInfo<Adminuser> getUsers(String name, Integer page, Integer limit){

            Example example = Example.builder(Adminuser.class).build();
            Example.Criteria c=example.createCriteria();

            if(name!=null&&!name.equals("")){
                c.andLike("name","%"+name+"%");
            }
            Page<Adminuser> p = PageHelper.startPage(page, limit);
            List<Adminuser> datas = testUserMapper.selectByExample(example);
            PageInfo pageInfo = new PageInfo<>(datas);
            return pageInfo;

    }

    public String login(Adminuser admin) {
        Adminuser user = testUserMapper.selectByPrimaryKey(admin.getUsername());
        if(admin.getPassword()!=null&&user!=null&&user.getPassword()!=null){
            if(admin.getPassword().equals(user.getPassword())){
                String token= UUID.randomUUID().toString().replace("-", "").toUpperCase();
                redisService.setValueTime(token,user.getUsername(),1200L);
                return token;
            }
        }
        return null;
    }

    public PageInfo<Adminuser> getAdmins(String name, Integer page, Integer limit) {
        Example example = Example.builder(Adminuser.class).build();
        Example.Criteria c=example.createCriteria();
        if(name!=null&&!name.equals("")) {
            c.andLike("username", "%" + name + "%");

        }
        Page<Adminuser> p = PageHelper.startPage(page, limit);
        List<Adminuser> datas = this.testUserMapper.selectByExample(example);

        return p.toPageInfo();

    }
    public int getAdminsCount(String name) {
        Example example = Example.builder(Adminuser.class).build();
        Example.Criteria c=example.createCriteria();
        if(name!=null&&!name.equals("")) {
            c.andLike("username", "%" + name + "%");
        }
        return testUserMapper.selectCountByExample(example);
    }

    public int editAdmin(Adminuser admin) {
        return testUserMapper.updateByPrimaryKey(admin);
    }

    public int addAdmin(Adminuser admin) {
        return testUserMapper.insert(admin);
    }

    public Adminuser getAdminByUsername(String username) {
        return testUserMapper.selectByPrimaryKey(username);
    }

    public Adminuser showAdminByUsername(String username) {
        return testUserMapper.selectByPrimaryKey(username);
    }

    public int deleteAdmin(Adminuser admin) {
        return testUserMapper.deleteByPrimaryKey(admin.getUsername());
    }
}
