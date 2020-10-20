package cn.zouajun.bzshop.zuul.filter;

import com.google.common.util.concurrent.RateLimiter;
import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;
import org.springframework.cloud.netflix.zuul.filters.support.FilterConstants;
import org.springframework.stereotype.Component;

/*
* 限流器
* */
@Component
public class RateLimitFilter extends ZuulFilter {

    //创建令牌桶
    //RateLimiter.create(n)每秒生成n个令牌
    //n数值越大代表请求数量越多
    private static final RateLimiter RATE_LIMITER = RateLimiter.create(1);

    /*
    * 过滤类型，执行前
    * */
    @Override
    public String filterType() {
        return FilterConstants.PRE_TYPE;
    }

    /*
    * 过滤器优先级
    * 限流器的优先级应为最高
    * */
    @Override
    public int filterOrder() {
        return FilterConstants.SERVLET_DETECTION_FILTER_ORDER-1;
    }

    @Override
    public boolean shouldFilter() {
        return true;
    }

    @Override
    public Object run() throws ZuulException {
        System.out.println("filter");
        //是否能从令牌桶中获得令牌
        if (!RATE_LIMITER.tryAcquire()){
            //未获得令牌
            RequestContext requestContext = RequestContext.getCurrentContext();
            requestContext.setSendZuulResponse(false);
            requestContext.setResponseStatusCode(429);
            System.out.println("限流");
        }
        return null;
    }
}
