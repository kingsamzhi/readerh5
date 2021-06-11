package com.show.sign;

import com.twelvemonkeys.servlet.image.IIOProviderContextListener;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import tk.mybatis.spring.annotation.MapperScan;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;

@SuppressWarnings("deprecation")
@SpringBootApplication
@MapperScan(basePackages="com.show.sign.mapper")
public class SignServerApplication extends SpringBootServletInitializer {

    public static void main(String[] args) {
        SpringApplication.run(SignServerApplication.class, args);
    }
    // 当SpringBoot项目打成war包发布时,需要继承SpringBootServletInitializer接口实现该方法
    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
        return builder.sources(SignServerApplication.class);
    }

    @Override
    public void onStartup(ServletContext servletContext) throws ServletException {
        servletContext.addListener(IIOProviderContextListener.class);
        super.onStartup(servletContext);
    }
}
