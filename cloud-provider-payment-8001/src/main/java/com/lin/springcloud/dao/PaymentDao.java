package com.lin.springcloud.dao;

import com.lin.springcloud.entity.Payment;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface  PaymentDao {

    int create(Payment payment);

    Payment getPaymentById(@Param("id")Long id);
}
