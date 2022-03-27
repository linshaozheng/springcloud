package com.lin.springcloud.controller;

import com.lin.springcloud.service.PaymentService;
import com.netflix.hystrix.contrib.javanica.annotation.DefaultProperties;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
@DefaultProperties(defaultFallback = "payment_Global_FallbackMethod")
public class PaymentController {
    @Value("${server.port}")
    private String serverPort;

    @Autowired
    private PaymentService paymentService;

    @GetMapping("/payment/hystrix/ok/{id}")
    public String getPaymentInfoOK(@PathVariable("id")Integer id){
         String result =  paymentService.paymentInfo_OK(id);
         return  result;
    }

    @GetMapping("/payment/hystrix/timeout/{id}")
    @HystrixCommand
    public String getPaymentInfoTimeout(@PathVariable("id")Integer id){
        String result = paymentService.paymentInfo_Timeout(id);
        return result;
    }

    public String payment_Global_FallbackMethod(){
        return "全局异常处理方法";
    }

    @GetMapping("/payment/circuit/{id}")
    public String getCircuitBreakerId(@PathVariable("id")Integer id){
        return paymentService.paymentCircuitBreaker(id);
    }
}
