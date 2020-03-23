package com.pigorv.springcloud.orders;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestClientException;

import static java.util.stream.Collectors.toList;

@RequestMapping
@RestController
public class OrdersController {

    @Autowired
    UserClient userClient;

    @Autowired
    ProductClient productClient;

    @Autowired
    NotificationClient notificationClient;

    @Autowired
    DiscoveryClient discoveryClient;

    private List<Order> orderList = new ArrayList<>();

    @GetMapping
    public String health() {
        return "OK";
    }

    @PostMapping
    public ResponseEntity<Order> createNewOrder(@RequestBody Order order) {
        ServiceInstance usersInfo = discoveryClient.getInstances("users").get(0);
        String hostName = usersInfo.getHost();
        int port = usersInfo.getPort();

        try {
            userClient.getUserByUsername(order.getUserName());
        } catch (RestClientException e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        try {
            productClient.updateProductQuantity(order.getProduct());

        } catch (RestClientException e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        orderList.add(order);
        notificationClient.addNotification(order.getUserName());

        return new ResponseEntity<>(order, HttpStatus.CREATED);
    }

    @GetMapping("/users/{userName}")
    public List<String> getProductsForUser(@PathVariable String userName) {
        return orderList.stream()
                .filter(order -> userName.equals(order.getUserName()))
                .map(Order::getProduct)
                .collect(toList());
    }
}
