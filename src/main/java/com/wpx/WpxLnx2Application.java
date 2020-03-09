package com.wpx;

import com.wpx.ACommonAPI.Constants.BeanAoto.CommonMvcDo;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.MultipartConfigFactory;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.util.unit.DataSize;
import org.springframework.web.servlet.DispatcherServlet;

import javax.servlet.MultipartConfigElement;

@SpringBootApplication
@MapperScan("com.wpx.mapper")
@EnableScheduling  //开启定时任务支持
public class WpxLnx2Application {

    public static void main(String[] args) {
        SpringApplication.run(WpxLnx2Application.class, args);
    }

    @Bean
    public MultipartConfigElement multipartConfigElement() {
        MultipartConfigFactory factory = new MultipartConfigFactory();
        //允许上传的文件最大值
        factory.setMaxFileSize(DataSize.parse("50MB")); //KB,MB
        /// 设置总上传数据总大小
        factory.setMaxRequestSize(DataSize.parse("50MB"));
        return factory.createMultipartConfig();
    }
}
