package com.show.sign.controller;

import com.github.pagehelper.PageInfo;
import com.show.sign.pojo.*;
import com.show.sign.service.SilkbagService;
import com.show.sign.service.StudentService;
import io.netty.util.internal.StringUtil;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/children")
public class ChildrenController {

    @Resource
    private StudentService studentService ;

    @Resource
    private SilkbagService silkbagService ;


    @CrossOrigin(origins = "*", maxAge = 3600)
    @RequestMapping(value = "/addDefaultchild")
    public @ResponseBody Map<String, Object> addDefaultchild(String token,Integer stuid){

        Studentdetail studentdetail = studentService.addDefaultchild(token,stuid);
        Map<String, Object> map = new HashMap<String, Object>();
        if (studentdetail != null) {
            map.put("code", "0");
            map.put("msg", "操作成功！");
            map.put("count", 1);
            map.put("data", studentdetail);
            return map;
        } else {
            map.put("code", "1");
            map.put("msg", "操作失败！");
            map.put("count", "0");
            map.put("data", null);
            return map;
        }
    }


    @CrossOrigin(origins = "*", maxAge = 3600)
    @RequestMapping(value = "/getDefaultchild")
    public @ResponseBody Map<String, Object> getDefaultchild(String token){

        Studentdetail studentdetail = studentService.getDefaultchild(token);
        Map<String, Object> map = new HashMap<String, Object>();
        if (studentdetail != null) {
            map.put("code", "0");
            map.put("msg", "操作成功！");
            map.put("count", 1);
            map.put("data", studentdetail);
            return map;
        } else {
            map.put("code", "1");
            map.put("msg", "找不到记录！");
            map.put("count", "0");
            map.put("data", null);
            return map;
        }
    }




    @CrossOrigin(origins = "*", maxAge = 3600)
    @RequestMapping(value = "/getChildrens")
    public @ResponseBody Map<String, Object> getChildrens(String token,Integer flag){

        List<Studentdetail> users = studentService.getChildrens(token,flag);
        Map<String, Object> map = new HashMap<String, Object>();
        if (users != null) {
            map.put("code", "0");
            map.put("msg", "操作成功！");
            map.put("count", users.size());
            map.put("data", users);
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
     * @param student
     * @return
     */
    @CrossOrigin(origins = "*", maxAge = 3600)
    @RequestMapping(value = "/editChildren")
    public @ResponseBody
    Map<String, Object> editChildren(Student student) {
        Map<String, Object> map = new HashMap<String, Object>();
        int n = studentService.editStudent(student);
        if (n > 0) {
            map.put("code", "0");
            map.put("msg", "修改信息成功！");
            map.put("count", n);
            map.put("data", student);
            return map;
        } else {
            map.put("code", "1");
            map.put("msg", "修改信息失败！");
            map.put("count", n);
            map.put("data", student);
            return map;
        }
    }

    /**
     * 添加管理员
     *
     * @param student
     * @return
     */
    @CrossOrigin(origins = "*", maxAge = 3600)
    @RequestMapping(value = "/addChildren")
    public @ResponseBody
    Map<String, Object> addChildren(Student student,String token) {
        Map<String, Object> map = new HashMap<String, Object>();

        if(StringUtil.isNullOrEmpty(token)){
            map.put("code", "1");
            map.put("msg", "添加信息失败，请先登录！");
            map.put("count", 0);
            map.put("data", student);
            return map;
        }
        if(StringUtil.isNullOrEmpty(student.getBgrade())||StringUtil.isNullOrEmpty(student.getBclass())||StringUtil.isNullOrEmpty(student.getName())||StringUtil.isNullOrEmpty(student.getSex())||student.getSchoolid()==null||student.getTeacherid()==null){
            map.put("code", "1");
            map.put("msg", "添加信息失败，信息有空值！");
            map.put("count", 0);
            map.put("data", student);
            return map;
        }
        int n = studentService.addStudent(student,token);
        if (n > 0) {
            map.put("code", "0");
            map.put("msg", "添加信息成功！");
            map.put("count", n);
            map.put("data", student);
            return map;
        } else {
            map.put("code", "1");
            map.put("msg", "添加信息失败！");
            map.put("count", n);
            map.put("data", student);
            return map;
        }
    }

    /**
     * 根据id查询
     *
     * @param token,stuid
     * @return
     */
    @CrossOrigin(origins = "*", maxAge = 3600)
    @RequestMapping(value = "/getChildrenById")
    public @ResponseBody
    Map<String, Object> getChildrenById(String token,Integer stuid) {
        Map<String, Object> map = new HashMap<String, Object>();
        //查询用户
        Studentdetail Student = studentService.getStudentByIdwithToken(token,stuid);
        if (Student != null) {
            map.put("code", "0");
            map.put("msg", "查询成功！");
            map.put("count", "1");
            map.put("data", Student);
            return map;
        } else {
            map.put("code", "1");
            map.put("msg", "找不到数据，请重新查询！");
            map.put("count", "0");
            map.put("data", Student);
            return map;
        }
    }



    /**
     * 删除
     *
     * @param token,stuid
     * @return
     */
    @CrossOrigin(origins = "*", maxAge = 3600)
    @RequestMapping("/unbandChildren")
    public @ResponseBody
    Map<String, Object> unbandChildren(String token,Integer stuid) {
        Map<String, Object> map = new HashMap<String, Object>();
        int n = studentService.deleteStudent(token,stuid);
        if (n > 0) {
            map.put("code", "0");
            map.put("msg", "解绑成功！");
            map.put("count", n);
            map.put("data", stuid);
            return map;
        } else {
            map.put("code", "1");
            map.put("msg", "解绑失败！");
            map.put("count", n);
            map.put("data", stuid);
            return map;
        }
    }

    @CrossOrigin(origins = "*", maxAge = 3600)
    @RequestMapping(value = "/useSilkbag")
    public @ResponseBody Map<String, Object> useSilkbag(String token,Integer stuid,Integer id){

        Silkbag silkbag = silkbagService.useSilkbag(token,stuid,id);
        Map<String, Object> map = new HashMap<String, Object>();
        if (silkbag !=null) {
            map.put("code", "0");
            map.put("msg", "使用锦囊成功！");
            map.put("count", 1);
            map.put("data", silkbag);
            return map;
        } else {
            map.put("code", "1");
            map.put("msg", "使用失败！锦囊不存在或已被使用！");
            map.put("count", "0");
            map.put("data", null);
            return map;
        }
    }

    @CrossOrigin(origins = "*", maxAge = 3600)
    @RequestMapping(value = "/getSilkbag")
    public @ResponseBody Map<String, Object> getSilkbag(String token,Integer stuid){

        Silkbag silkbag = silkbagService.getSilkbag(token,stuid);
        Map<String, Object> map = new HashMap<String, Object>();
        if (silkbag != null) {
            map.put("code", "0");
            map.put("msg", "领取成功！");
            map.put("count", 1);
            map.put("data", silkbag);
            return map;
        } else {
            map.put("code", "1");
            map.put("msg", "领取失败！还有锦囊未使用或今天已领取锦囊");
            map.put("count", "0");
            map.put("data", null);
            return map;
        }
    }

    @CrossOrigin(origins = "*", maxAge = 3600)
    @RequestMapping(value = "/getSilkbagByStuid")
    public @ResponseBody Map<String, Object> getSilkbagByStuid(String token,Integer stuid){

        Silkbag silkbag = silkbagService.getSilkbagByStuid(token,stuid);
        Map<String, Object> map = new HashMap<String, Object>();
        if (silkbag != null) {
            map.put("code", "0");
            map.put("msg", "已有锦囊！");
            map.put("count", 1);
            map.put("data", silkbag);
            return map;
        } else {
            map.put("code", "1");
            map.put("msg", "未有锦囊，可以领取！");
            map.put("count", "0");
            map.put("data", null);
            return map;
        }
    }


    @CrossOrigin(origins = "*", maxAge = 3600)
    @RequestMapping(value = "/getMetals")
    public @ResponseBody Map<String, Object> getMetals(String token,Integer stuid,@RequestParam(defaultValue="1") Integer page, @RequestParam(defaultValue="100")Integer limit){

        PageInfo<Stumedaldetail> stumedaldetails = silkbagService.getMetals(token,stuid,page,limit);
        Map<String, Object> map = new HashMap<String, Object>();
        if (stumedaldetails != null) {
            map.put("code", "0");
            map.put("msg", "查询成功！");
            map.put("count", stumedaldetails.getTotal());
            map.put("data", stumedaldetails.getList());
            return map;
        } else {
            map.put("code", "1");
            map.put("msg", "查询失败！没有记录");
            map.put("count", "0");
            map.put("data", null);
            return map;
        }
    }

    @CrossOrigin(origins = "*", maxAge = 3600)
    @RequestMapping(value = "/addMetalsShipment")
    public @ResponseBody Map<String, Object> addMetalsShipment(String token,Integer stuid,Integer id,String name,
            String phone,String address){

        Integer result = silkbagService.addMetalsShipment(token,stuid,id,name,phone,address);
        Map<String, Object> map = new HashMap<String, Object>();
        if (result != null) {
            if(result.equals(1)) {
                map.put("code", "0");
                map.put("msg", "申请发货成功！");
                map.put("count", 1);
                map.put("data", result);
            }else{
                map.put("code", "0");
                map.put("msg", "已经发货了！");
                map.put("count", 0);
                map.put("data", result);
            }
            return map;
        } else {
            map.put("code", "1");
            map.put("msg", "用户信息错误申请发货失败！");
            map.put("count", 0);
            map.put("data", 0);
            return map;
        }
    }

    @CrossOrigin(origins = "*", maxAge = 3600)
    @RequestMapping(value = "/getMetalsShipment")
    public @ResponseBody Map<String, Object> getMetalsShipment(String token,Integer stuid,Integer flag,@RequestParam(defaultValue="1") Integer page, @RequestParam(defaultValue="100")Integer limit){

        PageInfo<Stumedaldetail> result = silkbagService.getMetalsShipment(token,stuid,flag,page,limit);
        Map<String, Object> map = new HashMap<String, Object>();
        if (result != null) {

                map.put("code", "0");
                map.put("msg", "查找成功！");
                map.put("count", result.getTotal());
                map.put("data", result.getList());



            return map;
        } else {
            map.put("code", "0");
            map.put("msg", "查找失败！");
            map.put("count", 0);
            map.put("data", null);
            return map;
        }
    }


}