package com.show.sign.mapper;

import com.show.sign.pojo.Student;
import tk.mybatis.mapper.annotation.RegisterMapper;
import tk.mybatis.mapper.common.Mapper;

@RegisterMapper
public interface StudentMapper extends Mapper<Student> {
    

}
