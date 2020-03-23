package com.pigorv.springcloud.orders;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;

@FeignClient("products")
public interface ProductClient {

    @PutMapping("{productName}")
    void updateProductQuantity(@PathVariable String productName);
}
