package com.lin.springcloud.controller;

import com.lin.springcloud.service.PaymentHystrixService;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class OrderHystrixController {

    @Autowired
    private PaymentHystrixService paymentHystrixService;

    @GetMapping("/consumer/payment/hystrix/ok/{id}")
    public String getPaymentInfoOK(@PathVariable("id")Integer id){
        return paymentHystrixService.getPaymentInfoOK(id);
    }

    @GetMapping("/consumer/payment/hystrix/timeout/{id}")
    @HystrixCommand(fallbackMethod = "paymentTimeoutMethod",commandProperties = {
            @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds",value = "1500")
    })
    public String getPaymentInfoTimeout(@PathVariable("id")Integer id){
        return paymentHystrixService.getPaymentInfoTimeout(id);
    }

    public String paymentTimeoutMethod(Integer id){
        return "我是消费者 1.5秒就死掉调用自己的方法";
    }
}
