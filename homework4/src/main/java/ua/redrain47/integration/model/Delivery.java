package ua.redrain47.integration.model;

import java.io.Serializable;
import java.util.List;

public class Delivery implements Serializable {

    private static final long serialVersionUID = 1L;

    private static final String SEPARATOR = "-----------------------";

    private List<Package> deliveredPackages;

    private int orderNumber;

    public Delivery() {
    }

    public Delivery(List<Package> deliveredPackages) {
        assert (deliveredPackages.size() > 0);
        this.deliveredPackages = deliveredPackages;
        this.orderNumber = deliveredPackages.get(0).getOrderNumber();
    }


    public int getOrderNumber() {
        return orderNumber;
    }

    public void setOrderNumber(int orderNumber) {
        this.orderNumber = orderNumber;
    }

    public List<Package> getDeliveredPackages() {
        return deliveredPackages;
    }

    public void setDeliveredPackages(List<Package> deliveredPackages) {
        this.deliveredPackages = deliveredPackages;
    }

    @Override
    public String toString() {
        StringBuffer buffer = new StringBuffer(SEPARATOR + "\n");
        buffer.append("Delivery order #" + getOrderNumber() + "\n");
        for (Package aPackage : getDeliveredPackages()) {
            buffer.append(aPackage);
            buffer.append("\n");
        }
        buffer.append(SEPARATOR + "\n");
        return buffer.toString();
    }

}
