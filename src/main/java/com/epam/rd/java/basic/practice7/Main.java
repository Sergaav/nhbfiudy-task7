package com.epam.rd.java.basic.practice7;


import com.epam.rd.java.basic.practice7.items.Shiporder;
import com.epam.rd.java.basic.practice7.parsers.DOMParser;
import com.epam.rd.java.basic.practice7.parsers.SAXParser;

import javax.xml.transform.dom.DOMResult;

public final class Main {


    public static void main(final String[] args) throws Exception {

        String fileName = "input.xml";
        DOMParser domParser = new DOMParser(fileName);
        domParser.parse(true);
        Shiporder shipOrder = domParser.getShipOrder();

        String outputXmlFile = "output.dom.xml";
        DOMParser.saveToXML(shipOrder, outputXmlFile);
        System.out.println("Output ==> " + outputXmlFile);
                ////  SAX//////////

        SAXParser saxController = new SAXParser(fileName);
        saxController.parse(true);
        Shiporder shiporder= saxController.getShiporder();

        // sort  (case 2)
      //  Sorter.sortQuestionsByAnswersNumber(test);

        // save
        outputXmlFile = "output.sax.xml";

        // other way:
        DOMParser.saveToXML(shipOrder, outputXmlFile);
        System.out.println("Output ==> " + outputXmlFile);

    }


}
