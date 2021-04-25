package com.epam.rd.java.basic.practice7;


import com.epam.rd.java.basic.practice7.items.ShipOrder;
import com.epam.rd.java.basic.practice7.parsers.DOMParser;

public final class Main {


    public static void main(final String[] args) throws Exception {

        String fileName = "input.xml";
        DOMParser domParser = new DOMParser(fileName);
        domParser.parse(true);
        ShipOrder shipOrder = domParser.getShipOrder();



    }




}
