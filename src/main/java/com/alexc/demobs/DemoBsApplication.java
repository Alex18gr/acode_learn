package com.alexc.demobs;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.core.Ordered;
import org.springframework.stereotype.Controller;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@SpringBootApplication
@Controller
public class DemoBsApplication extends WebMvcConfigurerAdapter {

    public static void main(String[] args) {
        SpringApplication.run(DemoBsApplication.class, args);
    }

    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/login").setViewName("custom-login");
        registry.setOrder(Ordered.HIGHEST_PRECEDENCE);
    }
}
