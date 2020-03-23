package com.pigorv.springcloud.orders;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient("users")
public interface UserClient {

    @GetMapping("/{username}")
    UserDto getUserByUsername(@PathVariable String username);
}
