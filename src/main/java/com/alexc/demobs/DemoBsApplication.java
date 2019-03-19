package com.alexc.demobs;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.core.Ordered;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.Arrays;

@SpringBootApplication
public class DemoBsApplication implements WebMvcConfigurer {

    public static void main(String[] args) {
        ApplicationContext ctx =  SpringApplication.run(DemoBsApplication.class, args);
        // printSpringBootBeans(ctx);
    }

    private static void printSpringBootBeans(ApplicationContext ctx) {
        String[] beanNames = ctx.getBeanDefinitionNames();
        Arrays.sort(beanNames);
        for (String beanName : beanNames) {
            System.out.println(beanName);
        }
    }

    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/login").setViewName("custom-login");
        registry.setOrder(Ordered.HIGHEST_PRECEDENCE);
    }

}
