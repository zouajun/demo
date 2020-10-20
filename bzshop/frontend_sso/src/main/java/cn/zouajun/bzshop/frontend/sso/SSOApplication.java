package cn.zouajun.bzshop.frontend.sso;


import com.codingapi.txlcn.tc.config.EnableDistributedTransaction;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableDiscoveryClient
@EnableFeignClients
@MapperScan("cn.zouajun.bzshop.mapper")
@EnableDistributedTransaction
public class SSOApplication {

    public static void main(String[] args) {
        SpringApplication.run(SSOApplication.class,args);
    }
}
