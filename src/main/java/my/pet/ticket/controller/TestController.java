package my.pet.ticket.controller;

import my.pet.ticket.config.dto.AppProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController("/test")
public class TestController {

    @Autowired
    AppProperties appProperties;

    @GetMapping("/user")
    public void test(){
        System.out.println(appProperties.getDatabaseUrl());
    }
}
