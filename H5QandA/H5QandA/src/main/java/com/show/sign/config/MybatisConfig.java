package com.show.sign.config;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.context.annotation.Configuration;
import tk.mybatis.spring.annotation.MapperScan;

/**
 * @Author Terrell She
 * @Date 2020/5/27 17:19
 * @Email terrell.she
 * @Description
 */
@MapperScan("com.show.sign.mapper")
@Configuration
public class MybatisConfig {

    /**
     *  fix : No MyBatis mapper was found in '[xx.mapper]' package. Please check your configuration
     */
    @Mapper
    public interface NoWarnMapper {
    }
}
