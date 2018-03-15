package com.example.eurekaconsumer.controller;

import com.example.eurekaconsumer.model.User;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import java.util.HashMap;
import java.util.Map;

@RestController
@Configuration
public class ConsumerController {

    @Bean
    @LoadBalanced
    public RestTemplate getRestTemplate(){
        return  new RestTemplate();
    }

    @GetMapping("/consumer")
    public String  test(){
        User user = new User();
        user.setName("consumer");
        ResponseEntity<String> entity = getRestTemplate().postForEntity("http://EUREKA-PROVIDER/provider",user,String.class);
        return entity.getBody();
    }
}
