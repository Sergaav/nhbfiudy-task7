package com.epam.rd.java.basic.practice7.parsers;

import com.epam.rd.java.basic.practice7.items.*;
import org.xml.sax.helpers.DefaultHandler;

import javax.xml.stream.XMLEventReader;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.events.*;
import javax.xml.transform.stream.StreamSource;
import java.util.ArrayList;
import java.util.List;

public class STAXParser extends DefaultHandler {

    private final String xmlFileName;

    private Shiporder shiporder;

    public Shiporder getShiporder() {
        return shiporder;
    }

    public STAXParser(String xmlFileName) {
        this.xmlFileName = xmlFileName;
    }

    public void parse() throws XMLStreamException {

        Order order = null;
        Item item = null;
        List<Order> orders = new ArrayList<>();
        List<Item> items = new ArrayList<>();

        String currentElement = null;

        XMLInputFactory factory = XMLInputFactory.newInstance();

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

                if (XMLTegs.SHIPORDER.equalsTo(currentElement)) {
                    shiporder = new Shiporder();
                    continue;
                }

                if (XMLTegs.ORDER.equalsTo(currentElement)) {
                    order = new Order();
                    continue;
                }

                if (XMLTegs.ITEM.equalsTo(currentElement)) {
                    item = new Item();
                }
            }

            if (event.isCharacters()) {
                Characters characters = event.asCharacters();

                if (XMLTegs.ORDERID.equalsTo(currentElement)) {
                    if (order != null) {
                        order.setOrderid(characters.getData());
                    }
                    continue;
                }
                if (XMLTegs.NAME.equalsTo(currentElement)) {
                    if (order != null) {
                        order.setName(characters.getData());
                    }
                    continue;
                }
                if (XMLTegs.ADDRESS.equalsTo(currentElement)) {
                    if (order != null) {
                        order.setAddress(characters.getData());
                    }
                    continue;
                }
                if (XMLTegs.CITY.equalsTo(currentElement)) {
                    if (order != null) {
                        order.setCity(characters.getData());
                    }
                    continue;
                }
                if (XMLTegs.COUNTRY.equalsTo(currentElement)) {
                    if (order != null) {
                        order.setCountry(characters.getData());
                    }
                    continue;
                }
                if (XMLTegs.TITLE.equalsTo(currentElement)) {
                    if (item != null) {
                        item.setTitle(characters.getData());
                    }
                    continue;
                }
                if (XMLTegs.QUANTITY.equalsTo(currentElement)) {
                    if (item != null) {
                        item.setQuantity(Integer.parseInt(characters.getData()));
                    }
                    continue;
                }
                if (XMLTegs.PRICE.equalsTo(currentElement)) {
                    if (item != null) {
                        item.setPrice(Double.parseDouble(characters.getData()));
                    }
                    continue;
                }

            }

            if (event.isEndElement()) {
                EndElement endElement = event.asEndElement();
                String localName = endElement.getName().getLocalPart();

                if (XMLTegs.SHIPORDER.equalsTo(localName)) {
                    shiporder.getOrders().addAll(orders);
                    continue;
                }

                if (XMLTegs.ORDER.equalsTo(localName)) {
                    if (order != null) {
                        order.setItems(items);
                        orders.add(order);
                    }
                }
                if (XMLTegs.ITEM.equalsTo(localName)) {
                    if (item != null) {
                        items.add(item);
                    }
                }
            }
        }
        reader.close();
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
