package com.epam.rd.java.basic.practice7;

public class Item {
    private String title;
    private String note;
    private int quantity;
    private double price;

    public Item() {
    }

    public Item(String title, String note, int quantity, double price) {
        this.title = title;
        this.note = note;
        this.quantity = quantity;
        this.price = price;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
}
