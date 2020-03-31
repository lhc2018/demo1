package com.example;

import com.example.apitest.controller.AuthenticationController;
import com.example.apitest.controller.TestController;
import com.example.apitest.entity.KeepTokenAliveReq;
import com.example.apitest.entity.ResultRsp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.validation.BindingResult;
import org.springframework.web.client.RestTemplate;

/**
 * @program: demo
 * @description:
 * @author: liying
 * @create: 2020-03-27 13:38:59
 **/
@SpringBootApplication
@EnableScheduling
public class DemoApplication /*implements CommandLineRunner*/{

    @Autowired
    private AuthenticationController controller;

    public static void main(String[] args){
        SpringApplication.run(DemoApplication.class,args);
       /* AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
        context.register(TestController.class);
        context.refresh();
        TestController t = context.getBean(TestController.class);
        System.out.println("##t#:"+t);*/
    }

    @Bean
    public RestTemplate restTemplate(){
        return new RestTemplateBuilder().build();

    }


    @Scheduled(fixedDelay = 90000L)
    public void scheduleTask(){
        System.out.println("续约一次:");
        KeepTokenAliveReq req = null;

    }

    /*@Override
    public void run(String... args) throws Exception {
        System.out.println("执行一次:");
    }*/
}
