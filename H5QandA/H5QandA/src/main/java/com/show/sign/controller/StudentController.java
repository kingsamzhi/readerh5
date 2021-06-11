package com.show.sign.controller;

import com.github.pagehelper.PageInfo;
import com.show.sign.pojo.Student;
import com.show.sign.pojo.Stumedal;
import com.show.sign.pojo.Stumedaldetail;
import com.show.sign.service.SilkbagService;
import com.show.sign.service.StudentService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/student")
public class StudentController {

    @Resource
    private StudentService studentService ;

    @Resource
    private SilkbagService silkbagService ;

    @CrossOrigin(origins = "*", maxAge = 3600)
    @RequestMapping(value = "/getStudents")
    public @ResponseBody Map<String, Object> getStudents(Integer schoolid,Integer teacherid,Integer name,String bgrade,String bclass, @RequestParam(defaultValue="1") Integer page, @RequestParam(defaultValue="100")Integer limit){
        PageInfo<Student> users = studentService.getDatas(schoolid,teacherid,name,bgrade,bclass, page, limit);
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
     * 修改管理员用户
     *
     * @param student
     * @return
     */
    @CrossOrigin(origins = "*", maxAge = 3600)
    @RequestMapping(value = "/editStudent")
    public @ResponseBody
    Map<String, Object> editStudents(Student student) {
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
    @RequestMapping(value = "/addStudent")
    public @ResponseBody
    Map<String, Object> addStudents(Student student) {
        Map<String, Object> map = new HashMap<String, Object>();
        int n = studentService.addStudent(student);
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
     * @param id
     * @return
     */
    @CrossOrigin(origins = "*", maxAge = 3600)
    @RequestMapping(value = "/getStudentById")
    public @ResponseBody
    Map<String, Object> getStudentById(Integer id) {
        Map<String, Object> map = new HashMap<String, Object>();
        //查询用户
        Student Student = studentService.getStudentById(id);
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
     * @param id
     * @return
     */
    @CrossOrigin(origins = "*", maxAge = 3600)
    @RequestMapping("/deleteStudent")
    public @ResponseBody
    Map<String, Object> deleteStudent(Integer id) {
        Map<String, Object> map = new HashMap<String, Object>();
        int n = studentService.deleteStudent(id);
        if (n > 0) {
            map.put("code", "0");
            map.put("msg", "删除成功！");
            map.put("count", n);
            map.put("data", id);
            return map;
        } else {
            map.put("code", "1");
            map.put("msg", "删除失败！");
            map.put("count", n);
            map.put("data", id);
            return map;
        }
    }


    @CrossOrigin(origins = "*", maxAge = 3600)
    @RequestMapping(value = "/addMetalsShipment")
    public @ResponseBody Map<String, Object> addMetalsShipment(Integer id,String name,
                                                               String phone,String address,String shipmentcompany,String shipmentnum){

        Integer result = silkbagService.addMetalsShipment(id,name,phone,address,shipmentcompany,shipmentnum);
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
    public @ResponseBody Map<String, Object> getMetalsShipment(Integer stuid,Integer flag,@RequestParam(defaultValue="1") Integer page, @RequestParam(defaultValue="100")Integer limit){

        PageInfo<Stumedaldetail> result = silkbagService.getMetalsShipment(stuid,flag,page,limit);
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

    @CrossOrigin(origins = "*", maxAge = 3600)
    @RequestMapping(value = "/getStuMetalByid")
    public @ResponseBody Map<String, Object> getStuMetalByid(Integer id){

        Stumedal result = silkbagService.getStuMetalByid(id);
        Map<String, Object> map = new HashMap<String, Object>();
        if (result != null) {

            map.put("code", "0");
            map.put("msg", "查找成功！");
            map.put("count", 1);
            map.put("data", result);



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