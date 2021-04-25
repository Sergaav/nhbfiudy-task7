package com.epam.rd.java.basic.practice7.items;

import java.util.ArrayList;
import java.util.List;

public class ShipOrder {

    private List<Order> orders;

    public List<Order> getOrders() {
        if (orders == null) {
            orders = new ArrayList<>();
        }
        return orders;
    }

    @Override
    public String toString() {
        if (orders == null || orders.isEmpty()) {
            return "Test contains no questions";
        }
        StringBuilder result = new StringBuilder();
        for (Order question : orders) {
            result.append(question).append(System.lineSeparator());
        }
        return result.toString();
    }
}
