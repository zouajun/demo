package cn.zouajun.bzshop.frontend.cart.interceptor;

import cn.zouajun.bzshop.frontend.cart.service.UserCheckService;
import cn.zouajun.bzshop.pojo.TbUser;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component
public class UserLoginInterceptor implements HandlerInterceptor {

    @Autowired
    private UserCheckService userCheckService;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        //对用户的token做判断
        String token = request.getParameter("token");
        if (StringUtils.isBlank(token)){//没有token，未登录
            return false;
        }
        //如果用户token不为空则校验用户信息在redis中是否失效
        TbUser tbUser = userCheckService.checkUserToken(token);
        if (tbUser==null){//用户信息已失效
            return false;
        }
        return true;
    }
}
