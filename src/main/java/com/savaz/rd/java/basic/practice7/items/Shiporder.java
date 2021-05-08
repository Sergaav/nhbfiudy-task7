package com.savaz.rd.java.basic.practice7.items;

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

    @Override
    public String toString() {
        if (orders == null || orders.isEmpty()) {
            return "You don`t have orders to ship";
        }
        StringBuilder result = new StringBuilder();
        for (Order order : orders) {
            result.append(order.getOrderid()).append('\n');
            result.append(order.getName()).append('\n');
            result.append(order.getAddress()).append('\n');
            result.append(order.getCity()).append('\n');
            result.append(order.getCountry()).append('\n');
            if (order.getItems() == null || order.getItems().isEmpty()) {
                return "You haven`t items in order";
            }
            for (Item item : order.getItems()){
                result.append(item.getTitle()).append("\n");
                result.append(item.getQuantity()).append("\n");
                result.append(item.getPrice()).append("\n");
            }
            result.append("+++++++++++++++++++++++++++\n");
        }
        return result.toString();
    }

}
