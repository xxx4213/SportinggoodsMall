package com.shop.demo.config;


import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;


/**
 * @ProjectName: jk-a-parent
 * @Package: com.jiakai.config
 * @Description:
 * @Author: HCQ
 * @CreateDate: 2018/12/14 16:17
 * @UpdateRemark:
 * @Version: 1.0
 */
@Configuration
public class WebConfiguration extends WebMvcConfigurationSupport {

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**");
    }


    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {

        String path = System.getProperty("user.dir") + "\\src\\main\\resources\\upload\\";
        //--Swagger2----------------
        registry.addResourceHandler("doc.html").addResourceLocations("classpath:/META-INF/resources/");
        registry.addResourceHandler("/webjars/**").addResourceLocations("classpath:/META-INF/resources/webjars/");
        //--Swagger2----------------
        registry.addResourceHandler("/upload/**").addResourceLocations("file:" + path);
        super.addResourceHandlers(registry);
    }

    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/").setViewName("forward:/index.html");
        super.addViewControllers(registry);
    }


}
