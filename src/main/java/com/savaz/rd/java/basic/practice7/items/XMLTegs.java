package com.savaz.rd.java.basic.practice7.items;

public enum XMLTegs {

    SHIPORDER("shiporder"),
    ORDER("order"),
    ITEM("item"),
    ORDERID("orderid"),
    NAME("name"),
    ADDRESS("address"),
    CITY("city"),
    COUNTRY("country"),
    TITLE("title"),
    QUANTITY("quantity"),
    PRICE("price");

    private String value;

    XMLTegs(String value) {
        this.value = value;
    }

    public boolean equalsTo(String name) {
        return value.equals(name);
    }

    public String value() {
        return value;
    }
}
