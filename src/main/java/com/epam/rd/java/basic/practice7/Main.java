package com.epam.rd.java.basic.practice7;


import com.epam.rd.java.basic.practice7.items.Shiporder;
import com.epam.rd.java.basic.practice7.parsers.DOMParser;

public final class Main {


    public static void main(final String[] args) throws Exception {

        String fileName = "input.xml";
        DOMParser domParser = new DOMParser(fileName);
        domParser.parse(true);
        Shiporder shipOrder = domParser.getShipOrder();

        String outputXmlFile = "output.dom.xml";
        DOMParser.saveToXML(shipOrder, outputXmlFile);
        System.out.println("Output ==> " + outputXmlFile);


    }




}
