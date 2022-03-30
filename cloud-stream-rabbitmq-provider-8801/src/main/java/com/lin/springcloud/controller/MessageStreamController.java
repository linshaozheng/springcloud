package com.lin.springcloud.controller;

import com.lin.springcloud.service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
public class MessageStreamController {

    @Resource
    private MessageService messageService;

    @GetMapping("/sendMessage")
    public String getSend(){
        return messageService.send();
    }
}
