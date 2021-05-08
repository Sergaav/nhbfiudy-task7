package com.savaz.rd.java.basic.practice7.parsers;

import com.epam.rd.java.basic.practice7.items.*;
import com.savaz.rd.java.basic.practice7.items.*;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParserFactory;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class SAXParser extends DefaultHandler {

    private final String xmlFileName;
    private String currentElement;

    private Shiporder shiporder;

    private Order order;

    private Item item;
    private List<Order> orderList;
    private List<Item> itemList;

    public SAXParser(String xmlFileName) {
        this.xmlFileName = xmlFileName;
    }


    public void parse(boolean validate)
            throws ParserConfigurationException, SAXException, IOException {

        SAXParserFactory factory = SAXParserFactory.newInstance();
        factory.setFeature("http://apache.org/xml/features/disallow-doctype-decl", true);
        factory.setFeature("http://xml.org/sax/features/external-general-entities", false);
        factory.setFeature("http://xml.org/sax/features/external-parameter-entities", false);
        factory.setFeature("http://apache.org/xml/features/nonvalidating/load-external-dtd", false);
        factory.setXIncludeAware(false);
        factory.setNamespaceAware(true);

        if (validate) {
            factory.setFeature(Constants.FEATURE_TURN_VALIDATION_ON, true);
            factory.setFeature(Constants.FEATURE_TURN_SCHEMA_VALIDATION_ON, true);
        }

        javax.xml.parsers.SAXParser parser = factory.newSAXParser();

        parser.parse(xmlFileName, this);
    }

    @Override
    public void error(org.xml.sax.SAXParseException e) throws SAXException {
        throw e;
    }

    public Shiporder getShiporder() {
        return shiporder;
    }

    @Override
    public void startElement(String uri, String localName, String qName,
                             Attributes attributes) {

        currentElement = localName;

        if (XMLTegs.SHIPORDER.equalsTo(currentElement)) {
            shiporder = new Shiporder();
            return;
        }
        if (XMLTegs.ORDER.equalsTo(currentElement)) {
            order = new Order();
            if (orderList == null) {
                orderList = new ArrayList<>();
            }
            return;
        }
        if (XMLTegs.ORDERID.equalsTo(currentElement)) {
            return;
        }
        if (XMLTegs.NAME.equalsTo(currentElement)) {
            return;
        }
        if (XMLTegs.ADDRESS.equalsTo(currentElement)) {
            return;
        }
        if (XMLTegs.CITY.equalsTo(currentElement)) {
            return;
        }
        if (XMLTegs.COUNTRY.equalsTo(currentElement)) {
            return;
        }

        if (XMLTegs.ITEM.equalsTo(currentElement)) {
            item = new Item();
            if (itemList == null) {
                itemList = new ArrayList<>();
            }
        }
    }

    @Override
    public void characters(char[] ch, int start, int length) {

        String elementText = new String(ch, start, length).trim();

        // return if content is empty
        if (elementText.isEmpty()) {
            return;
        }
        if (XMLTegs.ORDERID.equalsTo(currentElement)) {
            order.setOrderid(elementText);
            return;
        }
        if (XMLTegs.NAME.equalsTo(currentElement)) {
            order.setName(elementText);
            return;
        }
        if (XMLTegs.ADDRESS.equalsTo(currentElement)) {
            order.setAddress(elementText);
            return;
        }
        if (XMLTegs.CITY.equalsTo(currentElement)) {
            order.setCity(elementText);
            return;
        }
        if (XMLTegs.COUNTRY.equalsTo(currentElement)) {
            order.setCountry(elementText);
            return;
        }
        if (XMLTegs.TITLE.equalsTo(currentElement)) {
            item.setTitle(elementText);
            return;
        }
        if (XMLTegs.QUANTITY.equalsTo(currentElement)) {
            item.setQuantity(Integer.parseInt(elementText));
            return;
        }
        if (XMLTegs.PRICE.equalsTo(currentElement)) {
            item.setPrice(Double.parseDouble(elementText));
        }
    }

    @Override
    public void endElement(String uri, String localName, String qName) {

        if (XMLTegs.ORDER.equalsTo(localName)) {
            shiporder.getOrders().add(order);
        }
        if (XMLTegs.ITEM.equalsTo(localName)) {
            order.getItems().add(item);
        }
    }

    public static void main(String[] args) throws Exception {

        SAXParser saxContr = new SAXParser(Constants.VALID_XML_FILE);

        saxContr.parse(true);

        Shiporder shiporder = saxContr.getShiporder();

        System.out.println("====================================");
        System.out.print("Here is the test: \n" + shiporder);
        System.out.println("====================================");


    }
}
