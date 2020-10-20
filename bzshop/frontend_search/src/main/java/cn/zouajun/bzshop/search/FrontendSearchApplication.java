package cn.zouajun.bzshop.search;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;


@SpringBootApplication
@EnableDiscoveryClient
@MapperScan("cn.zouajun.bzshop.mapper")
public class FrontendSearchApplication {
    public static void main(String[] args) {
        SpringApplication.run(FrontendSearchApplication.class,args);
    }
}
