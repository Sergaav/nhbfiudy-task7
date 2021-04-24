package com.epam.rd.java.basic.practice7;

public class Order {
    private String orderId;
    private String orderPerson;
    private Item item;
    private Delivery shipTo;

    public Order(String orderId, String orderPerson, Item item, Delivery shipTo) {
        this.orderId = orderId;
        this.orderPerson = orderPerson;
        this.item = item;
        this.shipTo = shipTo;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public String getOrderPerson() {
        return orderPerson;
    }

    public void setOrderPerson(String orderPerson) {
        this.orderPerson = orderPerson;
    }

    public Item getItem() {
        return item;
    }

    public void setItem(Item item) {
        this.item = item;
    }

    public Delivery getShipTo() {
        return shipTo;
    }

    public void setShipTo(Delivery shipTo) {
        this.shipTo = shipTo;
    }
}
