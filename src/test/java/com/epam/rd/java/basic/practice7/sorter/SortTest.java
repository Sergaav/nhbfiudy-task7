package com.epam.rd.java.basic.practice7.sorter;

import com.epam.rd.java.basic.practice7.items.Shiporder;
import com.epam.rd.java.basic.practice7.parsers.DOMParser;
import com.epam.rd.java.basic.practice7.parsers.SAXParser;
import com.epam.rd.java.basic.practice7.parsers.STAXParser;
import junit.framework.TestCase;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.stream.XMLStreamException;
import java.io.IOException;

public class SortTest extends TestCase {
    public Shiporder shiporder = null;


    public void testSortOrdersByOrderid() throws ParserConfigurationException, IOException, SAXException {
        String fileName = "input.xml";
        DOMParser domParser = new DOMParser(fileName);
        domParser.parse(true);
        shiporder = domParser.getShipOrder();
        String expected = "889923\n" +
                "Ola Nordmann\n" +
                "Langgt 23\n" +
                "4000 Stavanger\n" +
                "Norway\n" +
                "Empire Burlesque\n" +
                "1\n" +
                "10.9\n" +
                "Hide your heart\n" +
                "1\n" +
                "9.9\n" +
                "+++++++++++++++++++++++++++\n" +
                "889985\n" +
                "Angelina Jolie\n" +
                "1st avenue 23\n" +
                "Los Angeles\n" +
                "USA\n" +
                "Harry Potter and philosophy stone\n" +
                "3\n" +
                "99.99\n" +
                "Star wars\n" +
                "3\n" +
                "15.0\n" +
                "+++++++++++++++++++++++++++\n";
        Sort.sortOrdersByOrderid(shiporder);
        String result = shiporder.toString();
        System.out.println(result);
        assertEquals(expected, shiporder.toString());

    }

    public void testSortOrderByName() throws ParserConfigurationException, IOException, SAXException {
        String fileName = "input.xml";
        DOMParser domParser = new DOMParser(fileName);
        domParser.parse(true);
        shiporder = domParser.getShipOrder();
        String expected = "889985\n" +
                "Angelina Jolie\n" +
                "1st avenue 23\n" +
                "Los Angeles\n" +
                "USA\n" +
                "Harry Potter and philosophy stone\n" +
                "3\n" +
                "99.99\n" +
                "Star wars\n" +
                "3\n" +
                "15.0\n" +
                "+++++++++++++++++++++++++++\n" +
                "889923\n" +
                "Ola Nordmann\n" +
                "Langgt 23\n" +
                "4000 Stavanger\n" +
                "Norway\n" +
                "Empire Burlesque\n" +
                "1\n" +
                "10.9\n" +
                "Hide your heart\n" +
                "1\n" +
                "9.9\n" +
                "+++++++++++++++++++++++++++\n";
        Sort.sortOrderByName(shiporder);
        assertEquals(expected, shiporder.toString());
    }

    public void testSortOrderByNameBySAX() throws ParserConfigurationException, IOException, SAXException {
        String fileName = "input.xml";
        SAXParser saxController = new SAXParser(fileName);
        saxController.parse(true);
        shiporder = saxController.getShiporder();
        String expected = "889985\n" +
                "Angelina Jolie\n" +
                "1st avenue 23\n" +
                "Los Angeles\n" +
                "USA\n" +
                "Harry Potter and philosophy stone\n" +
                "3\n" +
                "99.99\n" +
                "Star wars\n" +
                "3\n" +
                "15.0\n" +
                "+++++++++++++++++++++++++++\n" +
                "889923\n" +
                "Ola Nordmann\n" +
                "Langgt 23\n" +
                "4000 Stavanger\n" +
                "Norway\n" +
                "Empire Burlesque\n" +
                "1\n" +
                "10.9\n" +
                "Hide your heart\n" +
                "1\n" +
                "9.9\n" +
                "+++++++++++++++++++++++++++\n";
        Sort.sortOrderByName(shiporder);
        assertEquals(expected, shiporder.toString());
    }

    public void testSortItemByTitle() throws ParserConfigurationException, IOException, SAXException {
        String fileName = "input.xml";
        DOMParser domParser = new DOMParser(fileName);
        domParser.parse(true);
        shiporder = domParser.getShipOrder();
        String expected = "889923\n" +
                "Ola Nordmann\n" +
                "Langgt 23\n" +
                "4000 Stavanger\n" +
                "Norway\n" +
                "Empire Burlesque\n" +
                "1\n" +
                "10.9\n" +
                "Hide your heart\n" +
                "1\n" +
                "9.9\n" +
                "+++++++++++++++++++++++++++\n" +
                "889985\n" +
                "Angelina Jolie\n" +
                "1st avenue 23\n" +
                "Los Angeles\n" +
                "USA\n" +
                "Harry Potter and philosophy stone\n" +
                "3\n" +
                "99.99\n" +
                "Star wars\n" +
                "3\n" +
                "15.0\n" +
                "+++++++++++++++++++++++++++\n";
        Sort.sortItemByTitle(shiporder);
        assertEquals(expected, shiporder.toString());
    }

    public void testSortItemByTitleBySTAX() throws  XMLStreamException {
        String fileName = "input.xml";
        STAXParser staxController = new STAXParser(fileName);
        staxController.parse();
        shiporder= staxController.getShiporder();
        String expected = "889923\n" +
                "Ola Nordmann\n" +
                "Langgt 23\n" +
                "4000 Stavanger\n" +
                "Norway\n" +
                "Empire Burlesque\n" +
                "1\n" +
                "10.9\n" +
                "Harry Potter and philosophy stone\n" +
                "3\n" +
                "99.99\n" +
                "Hide your heart\n" +
                "1\n" +
                "9.9\n" +
                "Star wars\n" +
                "3\n" +
                "15.0\n" +
                "+++++++++++++++++++++++++++\n" +
                "889985\n" +
                "Angelina Jolie\n" +
                "1st avenue 23\n" +
                "Los Angeles\n" +
                "USA\n" +
                "Empire Burlesque\n" +
                "1\n" +
                "10.9\n" +
                "Harry Potter and philosophy stone\n" +
                "3\n" +
                "99.99\n" +
                "Hide your heart\n" +
                "1\n" +
                "9.9\n" +
                "Star wars\n" +
                "3\n" +
                "15.0\n" +
                "+++++++++++++++++++++++++++\n";
        Sort.sortItemByTitle(shiporder);
        assertEquals(expected, shiporder.toString());
    }

    public void testSortItemByPrice() throws ParserConfigurationException, IOException, SAXException {
        String fileName = "input.xml";
        DOMParser domParser = new DOMParser(fileName);
        domParser.parse(true);
        shiporder = domParser.getShipOrder();
        String expected = "889923\n" +
                "Ola Nordmann\n" +
                "Langgt 23\n" +
                "4000 Stavanger\n" +
                "Norway\n" +
                "Hide your heart\n" +
                "1\n" +
                "9.9\n" +
                "Empire Burlesque\n" +
                "1\n" +
                "10.9\n" +
                "+++++++++++++++++++++++++++\n" +
                "889985\n" +
                "Angelina Jolie\n" +
                "1st avenue 23\n" +
                "Los Angeles\n" +
                "USA\n" +
                "Star wars\n" +
                "3\n" +
                "15.0\n" +
                "Harry Potter and philosophy stone\n" +
                "3\n" +
                "99.99\n" +
                "+++++++++++++++++++++++++++\n";
        Sort.sortItemByPrice(shiporder);
        assertEquals(expected, shiporder.toString());
    }
}