package ua.redrain47.integration.model;

import lombok.Data;

import java.io.Serializable;

@Data
public class Package implements Serializable {

    private static final long serialVersionUID = 1L;

    private DeliveryType deliveryType;

    private int orderNumber;

    public Package(int orderNumber, DeliveryType deliveryType) {
        this.orderNumber = orderNumber;
        this.deliveryType = deliveryType;
    }

    @Override
    public String toString() {
        switch (deliveryType) {
            case DTH:
                return "delivery to home";
            case DTS:
                return "delivery to store";
            case TRANSFER:
                return "transfer delivery";
            default:
                return "";
        }
    }

}
