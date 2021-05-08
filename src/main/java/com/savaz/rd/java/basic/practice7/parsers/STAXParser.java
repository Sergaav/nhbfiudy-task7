package com.savaz.rd.java.basic.practice7.parsers;

import com.epam.rd.java.basic.practice7.items.*;
import com.savaz.rd.java.basic.practice7.items.*;
import org.xml.sax.helpers.DefaultHandler;

import javax.xml.XMLConstants;
import javax.xml.stream.XMLEventReader;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.events.*;
import javax.xml.transform.stream.StreamSource;
import java.util.ArrayList;
import java.util.List;

public class STAXParser extends DefaultHandler {

    private final String xmlFileName;

    private static Shiporder shiporder;
    private static Order order = null;
    private static Item item = null;
    private static final List<Order> orders = new ArrayList<>();
    private static final List<Item> items = new ArrayList<>();

    public Shiporder getShiporder() {
        return shiporder;
    }


    public STAXParser(String xmlFileName) {
        this.xmlFileName = xmlFileName;
    }

    public void parse() throws XMLStreamException {
        String currentElement = null;

        XMLInputFactory factory = XMLInputFactory.newInstance();
        factory.setProperty(XMLInputFactory.SUPPORT_DTD, false);
        factory.setProperty("javax.xml.stream.isSupportingExternalEntities", false);
        factory.setProperty(XMLConstants.ACCESS_EXTERNAL_DTD, "");
        factory.setProperty(XMLConstants.ACCESS_EXTERNAL_SCHEMA, "");

        factory.setProperty(XMLInputFactory.IS_NAMESPACE_AWARE, true);

        XMLEventReader reader = factory.createXMLEventReader(
                new StreamSource(xmlFileName));

        while (reader.hasNext()) {
            XMLEvent event = reader.nextEvent();

            if (event.isCharacters() && event.asCharacters().isWhiteSpace()) {
                continue;
            }

            if (event.isStartElement()) {
                StartElement startElement = event.asStartElement();
                currentElement = startElement.getName().getLocalPart();
                startElementProcessing(currentElement);
            }

            if (event.isCharacters()) {
                Characters characters = event.asCharacters();
                charactersElementProcessing(currentElement, characters);
            }

            if (event.isEndElement()) {
                EndElement endElement = event.asEndElement();
                String localName = endElement.getName().getLocalPart();
                endElementProcessing(localName);

            }
        }
        reader.close();
    }

    public static void startElementProcessing(String currentElement) {
        if (XMLTegs.SHIPORDER.equalsTo(currentElement)) {
            shiporder = new Shiporder();
        }

        if (XMLTegs.ORDER.equalsTo(currentElement)) {
            order = new Order();
        }

        if (XMLTegs.ITEM.equalsTo(currentElement)) {
            item = new Item();
        }
    }

    public static void charactersElementProcessing(String currentElement, Characters characters) {
        if (XMLTegs.ORDERID.equalsTo(currentElement) && order != null) {
            order.setOrderid(characters.getData());
        }
        if (XMLTegs.NAME.equalsTo(currentElement) && order != null) {
            order.setName(characters.getData());
        }
        if (XMLTegs.ADDRESS.equalsTo(currentElement) && order != null) {
            order.setAddress(characters.getData());
        }
        if (XMLTegs.CITY.equalsTo(currentElement) && order != null) {
            order.setCity(characters.getData());
        }
        if (XMLTegs.COUNTRY.equalsTo(currentElement) && order != null) {
            order.setCountry(characters.getData());
        }
        itemsElementProcessing(currentElement, characters);
    }

    public static void itemsElementProcessing(String currentElement, Characters characters) {
        if (XMLTegs.TITLE.equalsTo(currentElement) && item != null) {
            item.setTitle(characters.getData());
        }
        if (XMLTegs.QUANTITY.equalsTo(currentElement) && item != null) {
            item.setQuantity(Integer.parseInt(characters.getData()));
        }
        if (XMLTegs.PRICE.equalsTo(currentElement) && (item != null)) {
            item.setPrice(Double.parseDouble(characters.getData()));
        }
    }


    public static void endElementProcessing(String localName) {
        if (XMLTegs.SHIPORDER.equalsTo(localName)) {
            shiporder.getOrders().addAll(orders);
        }

        if (XMLTegs.ORDER.equalsTo(localName) && order != null) {
            order.setItems(items);
            orders.add(order);
        }
        if (XMLTegs.ITEM.equalsTo(localName) && item != null) {
            items.add(item);
        }
    }


    public static void main(String[] args) throws Exception {

        STAXParser staxContr = new STAXParser(Constants.VALID_XML_FILE);
        staxContr.parse();

        Shiporder shiporder = staxContr.getShiporder();

        System.out.println("====================================");
        System.out.print("Here is the test: \n" + shiporder);
        System.out.println("====================================");
    }

}
