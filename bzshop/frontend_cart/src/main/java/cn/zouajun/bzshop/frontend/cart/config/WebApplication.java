package cn.zouajun.bzshop.frontend.cart.config;

import cn.zouajun.bzshop.frontend.cart.interceptor.UserLoginInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;


/*
* 拦截器配置类
* */
@Configuration
public class WebApplication implements WebMvcConfigurer {

    @Autowired
    private UserLoginInterceptor userLoginInterceptor;

    /*
    * 注册拦截器
    * */
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        InterceptorRegistration registration = registry.addInterceptor(userLoginInterceptor);
        registration.addPathPatterns("/cart/goSettlement/**");
    }
}
