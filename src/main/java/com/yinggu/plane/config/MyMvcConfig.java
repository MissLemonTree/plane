package com.yinggu.plane.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @ClassName MyMvcConfig
 * @Description TODO
 * @Author wangyichen
 * Date 2022/4/16 14:25
 * Version 1.0
 */
@Configuration
public class MyMvcConfig implements WebMvcConfigurer {

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new LoginHandler())
                .addPathPatterns("/order/orderList")
                .excludePathPatterns("/AlipayFaceToFaceController/ZFBcallback")
                .excludePathPatterns("/flight/index")
                .excludePathPatterns("/flight/login")
                .excludePathPatterns("/flight/user/login")
                .excludePathPatterns("/flight/phone/index")
                .excludePathPatterns("/flight/phone/login")
                .excludePathPatterns("/flight/phone/check")
                .excludePathPatterns("/flight/flightList");
    }
}
