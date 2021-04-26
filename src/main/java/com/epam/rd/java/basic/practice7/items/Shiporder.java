package com.epam.rd.java.basic.practice7.items;

import java.util.ArrayList;
import java.util.List;

public class Shiporder {

    private List<Order> orders;

    public List<Order> getOrders() {
        if (orders == null) {
            orders = new ArrayList<>();
        }
        return orders;
    }

}
