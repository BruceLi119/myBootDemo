package com.asiaInfo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;

/**
 * @description: 从pulsar的topic中获取日志，生成文件后上传到sftp服务器
 * @author: li.shuGuang
 * @dateTime: 2021/4/9 17:06
 */
@ComponentScan(basePackages = "com.asiaInfo.demo.*")
@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class})
public class SpringbootDemoApplication  {
    public static void main(String[] args) {
        SpringApplication.run(SpringbootDemoApplication.class);
    }

}
