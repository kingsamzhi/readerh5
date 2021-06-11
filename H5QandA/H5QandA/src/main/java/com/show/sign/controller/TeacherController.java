package com.show.sign.controller;

import com.github.pagehelper.PageInfo;
import com.show.sign.pojo.Schoolteacher;
import com.show.sign.pojo.Teacher;
import com.show.sign.service.TeacherService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/teacher")
public class TeacherController {

    @Resource
    private TeacherService teacherService ;

    @CrossOrigin(origins = "*", maxAge = 3600)
    @RequestMapping(value = "/getTeachers")
    public @ResponseBody Map<String, Object> getTeachers(Integer schoolid,String name,String code,Integer flag, @RequestParam(defaultValue="1") Integer page, @RequestParam(defaultValue="100")Integer limit){
        PageInfo<Schoolteacher> users = teacherService.getDatas(schoolid,name,code, flag, page, limit);
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

    @CrossOrigin(origins = "*", maxAge = 3600)
    @RequestMapping(value = "/getTeacherDetailById")
    public @ResponseBody Map<String, Object> getTeacherDetailById(Integer id){
        Schoolteacher users = teacherService.getTeacherDetailById(id);
        Map<String, Object> map = new HashMap<String, Object>();
        if (users != null) {
            map.put("code", "0");
            map.put("msg", "操作成功！");
            map.put("count", 1);
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
     * @param teacher
     * @return
     */
    @CrossOrigin(origins = "*", maxAge = 3600)
    @RequestMapping(value = "/editTeacher")
    public @ResponseBody
    Map<String, Object> editTeachers(Teacher teacher) {
        Map<String, Object> map = new HashMap<String, Object>();
        int n = teacherService.editTeacher(teacher);
        if (n > 0) {
            map.put("code", "0");
            map.put("msg", "修改信息成功！");
            map.put("count", n);
            map.put("data", teacher);
            return map;
        } else {
            map.put("code", "1");
            map.put("msg", "修改信息失败！");
            map.put("count", n);
            map.put("data", teacher);
            return map;
        }
    }

    /**
     * 添加管理员
     *
     * @param teacher
     * @return
     */
    @CrossOrigin(origins = "*", maxAge = 3600)
    @RequestMapping(value = "/addTeacher")
    public @ResponseBody
    Map<String, Object> addTeachers(Teacher teacher) {
        Map<String, Object> map = new HashMap<String, Object>();
        int n = teacherService.addTeacher(teacher);
        if (n > 0) {
            map.put("code", "0");
            map.put("msg", "添加信息成功！");
            map.put("count", n);
            map.put("data", teacher);
            return map;
        } else {
            map.put("code", "1");
            map.put("msg", "添加信息失败！");
            map.put("count", n);
            map.put("data", teacher);
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
    @RequestMapping(value = "/getTeacherById")
    public @ResponseBody
    Map<String, Object> getTeacherById(Integer id) {
        Map<String, Object> map = new HashMap<String, Object>();
        //查询用户
        Teacher Teacher = teacherService.getTeacherById(id);
        if (Teacher != null) {
            map.put("code", "0");
            map.put("msg", "查询成功！");
            map.put("count", "1");
            map.put("data", Teacher);
            return map;
        } else {
            map.put("code", "1");
            map.put("msg", "找不到数据，请重新查询！");
            map.put("count", "0");
            map.put("data", Teacher);
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
    @RequestMapping("/deleteTeacher")
    public @ResponseBody
    Map<String, Object> deleteTeacher(Integer id) {
        Map<String, Object> map = new HashMap<String, Object>();
        int n = teacherService.deleteTeacher(id);
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

}