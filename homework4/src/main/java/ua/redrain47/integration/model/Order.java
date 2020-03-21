package ua.redrain47.integration.model;

import lombok.Data;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Data
public class Order implements Serializable {

    private static final long serialVersionUID = 1L;

    private List<OrderItem> orderItems = new ArrayList<OrderItem>();

    private int number;

    public Order(int number) {
        this.number = number;
    }

    public void addItem(DeliveryType deliveryType) {
        this.orderItems.add(new OrderItem(this.number, deliveryType));
    }
}
