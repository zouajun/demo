package cn.zzouajun.bzshop.content;

import com.codingapi.txlcn.tc.config.EnableDistributedTransaction;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
@EnableDistributedTransaction
@MapperScan("cn.zouajun.bzshop.mapper")
public class CommonContentApplication {

    public static void main(String[] args) {
        SpringApplication.run(CommonContentApplication.class,args);
    }
}
