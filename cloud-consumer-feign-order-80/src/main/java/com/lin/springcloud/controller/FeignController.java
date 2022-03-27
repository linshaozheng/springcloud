package com.lin.springcloud.controller;

import com.lin.springcloud.entity.CommonResult;
import com.lin.springcloud.entity.Payment;
import com.lin.springcloud.services.PaymentFeignService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class FeignController {

    @Autowired
    private PaymentFeignService paymentFeignService;

    @GetMapping("/consumer/payment/get/{id}")
    public CommonResult<Payment> getPaymentById(@PathVariable("id")Long id){
        return paymentFeignService.getPaymentById(id);
    }

    @GetMapping("/consumer/payment/feign/timeout")
    public String getServerPortTimeOut(){
        return paymentFeignService.getServerPortTimeOut();
    }
}
