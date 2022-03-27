package com.lin.springcloud.service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Component
@FeignClient(value = "CLOUD-PROVIDER-HYSTRIX-PAYMENT",fallback = PaymentFallbackService.class)
public interface PaymentHystrixService {

    @GetMapping("/payment/hystrix/ok/{id}")
     String getPaymentInfoOK(@PathVariable("id")Integer id);

    @GetMapping("/payment/hystrix/timeout/{id}")
    String getPaymentInfoTimeout(@PathVariable("id")Integer id);
}
