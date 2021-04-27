package com.epam.rd.java.basic.practice7;


import com.epam.rd.java.basic.practice7.items.Shiporder;
import com.epam.rd.java.basic.practice7.parsers.DOMParser;
import com.epam.rd.java.basic.practice7.parsers.SAXParser;
import com.epam.rd.java.basic.practice7.parsers.STAXParser;
import com.epam.rd.java.basic.practice7.sorter.Sort;

public final class Main {


    public static void main(final String[] args) throws Exception {

        String fileName = args[0];
        DOMParser domParser = new DOMParser(fileName);
        domParser.parse(true);
        Shiporder shipOrder = domParser.getShipOrder();

        String outputXmlFile = "output.dom.xml";
        DOMParser.saveToXML(shipOrder, outputXmlFile);
        System.out.println(outputXmlFile);
        Sort.sortItemByPrice(shipOrder);
        System.out.println(shipOrder);
        System.out.println("////  SAX//////////");

        SAXParser saxController = new SAXParser(fileName);
        saxController.parse(true);
        Shiporder shiporder= saxController.getShiporder();

        Sort.sortOrderByName(shiporder);


        outputXmlFile = "output.sax.xml";


        DOMParser.saveToXML(shipOrder, outputXmlFile);
        System.out.println(outputXmlFile);

        STAXParser staxController = new STAXParser(fileName);
        staxController.parse();
        shipOrder= staxController.getShiporder();

        Sort.sortItemByPrice(shiporder);

        outputXmlFile = "output.stax.xml";
        DOMParser.saveToXML(shipOrder, outputXmlFile);
        System.out.println(outputXmlFile);

    }


}
