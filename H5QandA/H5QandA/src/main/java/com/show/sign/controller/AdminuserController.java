package com.show.sign.controller;

import com.github.pagehelper.PageInfo;
import com.show.sign.pojo.Adminuser;
import com.show.sign.service.AdminuserService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/adminuser")
public class AdminuserController {

    @Resource
    private AdminuserService adminuserService ;

    @CrossOrigin(origins = "*", maxAge = 3600)
    @RequestMapping(value = "/getUsers")
    public @ResponseBody Map<String, Object> test(String name, @RequestParam(defaultValue="1") Integer page, @RequestParam(defaultValue="100")Integer limit){
        PageInfo<Adminuser> users = adminuserService.getUsers(name, page, limit);
        Map<String, Object> map = new HashMap<String, Object>();
        if (users != null) {
            map.put("code", "0");
            map.put("msg", "操作成功！");
            map.put("count", users.getTotal());
            map.put("data", users.getList());
            return map;
        } else {
            map.put("code", "1");
            map.put("msg", "找不到记录！");
            map.put("count", "0");
            map.put("data", null);
            return map;
        }
    }


    /**
     * 登录
     *
     * @param admin
     * @return
     */
    @CrossOrigin(origins = "*", maxAge = 3600)
    @RequestMapping(value = "/login")
    public @ResponseBody
    Map<String, Object> login(Adminuser admin) {
        Map<String, Object> map = new HashMap<String, Object>();
        //查询用户
        String token = adminuserService.login(admin);

        if (token != null) {
            map.put("code", "0");
            map.put("msg", "登录成功！");
            map.put("count", "1");
            map.put("data", admin);
            map.put("token", token);
            return map;
        } else {
            map.put("code", "1");
            map.put("msg", "登录失败！");
            map.put("count", "0");
            map.put("data", admin);
            return map;
        }
    }

    /**
     * 管理员用户列表
     *
     * @param name  null
     * @param page
     * @param limit
     * @return
     */
    @CrossOrigin(origins = "*", maxAge = 3600)
    @RequestMapping(value = "/getAdmins")
    public @ResponseBody Map<String, Object> getAdmins(String name, Integer page, Integer limit) {
        Map<String, Object> map = new HashMap<String, Object>();
        //获得列表
        PageInfo<Adminuser> admins = adminuserService.getAdmins(name, page, limit);
        if (admins != null) {
            map.put("code", "0");
            map.put("msg", "操作成功！");
            map.put("count", admins.getTotal());
            map.put("data", admins.getList());
            return map;
        } else {
            map.put("code", "1");
            map.put("msg", "找不到记录！");
            map.put("count", "0");
            map.put("data", null);
            return map;
        }
    }

    /**
     * 修改管理员用户
     *
     * @param admin
     * @return
     */
    @CrossOrigin(origins = "*", maxAge = 3600)
    @RequestMapping(value = "/editAdmin")
    public @ResponseBody
    Map<String, Object> editAdmins(Adminuser admin) {
        Map<String, Object> map = new HashMap<String, Object>();
        int n = adminuserService.editAdmin(admin);
        if (n > 0) {
            map.put("code", 0);
            map.put("msg", "修改信息成功！");
            map.put("count", n);
            map.put("data", admin);
            return map;
        } else {
            map.put("code", 1);
            map.put("msg", "修改信息失败！");
            map.put("count", n);
            map.put("data", admin);
            return map;
        }
    }

    /**
     * 添加管理员
     *
     * @param admin
     * @return
     */
    @CrossOrigin(origins = "*", maxAge = 3600)
    @RequestMapping(value = "/addAdmin")
    public @ResponseBody
    Map<String, Object> addAdmins(Adminuser admin) {
        Map<String, Object> map = new HashMap<String, Object>();
        int n = adminuserService.addAdmin(admin);
        if (n > 0) {
            map.put("code", 0);
            map.put("msg", "管理员添加成功！");
            map.put("count", n);
            map.put("data", admin);
            return map;
        } else {
            map.put("code", 1);
            map.put("msg", "管理员添加失败！");
            map.put("count", n);
            map.put("data", admin);
            return map;
        }
    }

    /**
     * 根据账号查询用户
     *
     * @param username
     * @return
     */
    @CrossOrigin(origins = "*", maxAge = 3600)
    @RequestMapping(value = "/getAdminByUsername")
    public @ResponseBody
    Map<String, Object> getAdminByUsername(String username) {
        Map<String, Object> map = new HashMap<String, Object>();
        //查询用户
        Adminuser Adminuser = adminuserService.getAdminByUsername(username);
        if (Adminuser != null) {
            map.put("code", "0");
            map.put("msg", "查询成功！");
            map.put("count", "1");
            map.put("data", Adminuser);
            return map;
        } else {
            map.put("code", "1");
            map.put("msg", "找不到该用户，请重新查询！");
            map.put("count", "0");
            map.put("data", Adminuser);
            return map;
        }
    }



    /**
     * 删除用户
     *
     * @param admin
     * @return
     */
    @CrossOrigin(origins = "*", maxAge = 3600)
    @RequestMapping("/deleteAdmin")
    public @ResponseBody
    Map<String, Object> deleteAdmin(Adminuser admin) {
        Map<String, Object> map = new HashMap<String, Object>();
        int n = adminuserService.deleteAdmin(admin);
        if (n > 0) {
            map.put("code", "0");
            map.put("msg", "删除成功！");
            map.put("count", n);
            map.put("data", admin);
            return map;
        } else {
            map.put("code", "1");
            map.put("msg", "删除失败！");
            map.put("count", n);
            map.put("data", admin);
            return map;
        }
    }

}