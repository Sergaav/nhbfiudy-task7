package com.epam.rd.java.basic.practice7.sorter;

import com.epam.rd.java.basic.practice7.items.Constants;
import com.epam.rd.java.basic.practice7.items.Item;
import com.epam.rd.java.basic.practice7.items.Order;
import com.epam.rd.java.basic.practice7.items.Shiporder;
import com.epam.rd.java.basic.practice7.parsers.DOMParser;

import java.util.Comparator;

public class Sort {

    public static final Comparator<Order> SORT_ORDERS_BY_ORDERID = (o1, o2) -> o1.getOrderid().compareTo(o2.getOrderid());

    public static final Comparator<Order> SORT_ORDERS_BY_NAME = (o1, o2) -> o1.getName().compareTo(o2.getName());


    public static final Comparator<Item> SORT_ITEMS_BY_TITLE = (o1, o2) -> o1.getTitle().compareTo(o2.getTitle());

    public static final Comparator<Item> SORT_ITEMS_BY_PRICE = (o1, o2) -> (int) (o1.getPrice()-o2.getPrice());

    public static void sortOrdersByOrderid(Shiporder shiporder) {
        shiporder.getOrders().sort(SORT_ORDERS_BY_ORDERID);
    }

    public static void sortOrderByName(Shiporder shiporder) {
        shiporder.getOrders().sort(SORT_ORDERS_BY_NAME);
    }

    public static void sortItemByTitle(Shiporder shiporder) {
        for (Order order : shiporder.getOrders()) {
            order.getItems().sort(SORT_ITEMS_BY_TITLE);
        }
    }

    public static void sortItemByPrice(Shiporder shiporder) {
        for (Order order : shiporder.getOrders()) {
            order.getItems().sort(SORT_ITEMS_BY_PRICE);
        }
    }

    public static void main(String[] args) throws Exception {

        DOMParser domParser = new DOMParser(
                Constants.VALID_XML_FILE);
        domParser.parse(false);
        Shiporder shiporder = domParser.getShipOrder();


        System.out.println(shiporder);
        Sort.sortOrderByName(shiporder);
        System.out.println(shiporder);

        Sort.sortItemByPrice(shiporder);
        System.out.println(shiporder);
    }
}
