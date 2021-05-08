package com.savaz.rd.java.basic.practice7.parsers;

import com.savaz.rd.java.basic.practice7.items.*;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.XMLConstants;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class DOMParser {

    private final String fileName;

    private Shiporder shipOrder;

    public Shiporder getShipOrder() {
        return shipOrder;
    }

    public DOMParser(String fileName) {
        this.fileName = fileName;
    }

    public void parse(boolean validate) throws ParserConfigurationException, SAXException, IOException {
        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
        dbf.setNamespaceAware(validate);
        dbf.setFeature(Constants.FEATURE_TURN_VALIDATION_ON, true);
        dbf.setFeature(Constants.FEATURE_TURN_SCHEMA_VALIDATION_ON, true);
        dbf.setFeature("http://apache.org/xml/features/disallow-doctype-decl", true);
        dbf.setFeature("http://xml.org/sax/features/external-general-entities", false);
        DocumentBuilder db = dbf.newDocumentBuilder();
        Document document = db.parse(fileName);
        Element root = document.getDocumentElement();
        shipOrder = new Shiporder();
        NodeList orderNodes = root
                .getElementsByTagName(XMLTegs.ORDER.value());
        for (int i = 0; i < orderNodes.getLength(); i++) {
            shipOrder.getOrders().add(getOrder(orderNodes.item(i)));
        }


    }

    public static Order getOrder(Node node) {
        Order order = new Order();
        Element element = (Element) node;
        Node nodeOrder = element.getElementsByTagName(XMLTegs.ORDERID.value()).item(0);
        order.setOrderid(nodeOrder.getTextContent());
        Node nodeName = element.getElementsByTagName(XMLTegs.NAME.value()).item(0);
        order.setName(nodeName.getTextContent());
        Node nodeAddress = element.getElementsByTagName(XMLTegs.ADDRESS.value()).item(0);
        order.setAddress(nodeAddress.getTextContent());
        Node nodeCity = element.getElementsByTagName(XMLTegs.CITY.value()).item(0);
        order.setCity(nodeCity.getTextContent());
        Node nodeCountry = element.getElementsByTagName(XMLTegs.COUNTRY.value()).item(0);
        order.setCountry(nodeCountry.getTextContent());
        NodeList listItems = element.getElementsByTagName(XMLTegs.ITEM.value());
        List<Item> list = new ArrayList<>();
        for (int i = 0; i < listItems.getLength(); i++) {
            list.add(getItem(listItems.item(i)));
        }
        order.setItems(list);
        return order;
    }

    public static Item getItem(Node node) {
        Item item = new Item();
        Element element = (Element) node;
        Node nodeItem = element.getElementsByTagName(XMLTegs.TITLE.value()).item(0);
        item.setTitle(nodeItem.getTextContent());
        Node nodeQuantity = element.getElementsByTagName(XMLTegs.QUANTITY.value()).item(0);
        item.setQuantity(Integer.parseInt(nodeQuantity.getTextContent()));
        Node nodePrice = element.getElementsByTagName(XMLTegs.PRICE.value()).item(0);
        item.setPrice(Double.parseDouble(nodePrice.getTextContent()));
        return item;
    }

    public static Document getDocument(Shiporder shipOrder) throws ParserConfigurationException {
        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
        dbf.setFeature("http://apache.org/xml/features/disallow-doctype-decl", true);
        dbf.setFeature("http://xml.org/sax/features/external-general-entities", false);
        dbf.setNamespaceAware(true);
        DocumentBuilder db = dbf.newDocumentBuilder();
        Document document = db.newDocument();
        Element tElement = document.createElement(XMLTegs.SHIPORDER.value());
        document.appendChild(tElement);
        for (Order order : shipOrder.getOrders()) {
            Element orderElement = document.createElement(XMLTegs.ORDER.value());
            tElement.appendChild(orderElement);
            Element orderIdElement =
                    document.createElement(XMLTegs.ORDERID.value());
            orderIdElement.setTextContent(order.getOrderid());
            orderElement.appendChild(orderIdElement);

            Element nameElement =
                    document.createElement(XMLTegs.NAME.value());
            nameElement.setTextContent(order.getName());
            orderElement.appendChild(nameElement);

            Element addressElement =
                    document.createElement(XMLTegs.ADDRESS.value());
            addressElement.setTextContent(order.getAddress());
            orderElement.appendChild(addressElement);

            Element cityElement =
                    document.createElement(XMLTegs.CITY.value());
            cityElement.setTextContent(order.getCity());
            orderElement.appendChild(cityElement);

            Element countryElement =
                    document.createElement(XMLTegs.COUNTRY.value());
            countryElement.setTextContent(order.getCountry());
            orderElement.appendChild(countryElement);

            for (Item item : order.getItems()) {
                Element itemElement =
                        document.createElement(XMLTegs.ITEM.value());
                orderElement.appendChild(itemElement);
                Element titleElement = document.createElement(XMLTegs.TITLE.value());
                titleElement.setTextContent(item.getTitle());
                itemElement.appendChild(titleElement);

                Element quantityElement = document.createElement(XMLTegs.QUANTITY.value());
                quantityElement.setTextContent(String.valueOf(item.getQuantity()));
                itemElement.appendChild(quantityElement);

                Element priceElement = document.createElement(XMLTegs.PRICE.value());
                priceElement.setTextContent(String.valueOf(item.getPrice()));
                itemElement.appendChild(priceElement);
            }
        }
        return document;
    }

    public static void saveToXML(Shiporder shipOrder, String xmlFileName)
            throws ParserConfigurationException, TransformerException {
        saveToXML(getDocument(shipOrder), xmlFileName);
    }

    public static void saveToXML(Document document, String xmlFileName)
            throws TransformerException {

        StreamResult result = new StreamResult(new File(xmlFileName));

        TransformerFactory tf = TransformerFactory.newInstance();
        tf.setAttribute(XMLConstants.ACCESS_EXTERNAL_DTD, "");
        tf.setAttribute(XMLConstants.ACCESS_EXTERNAL_STYLESHEET, "");
        javax.xml.transform.Transformer t = tf.newTransformer();
        t.setOutputProperty(OutputKeys.INDENT, "yes");
        t.transform(new DOMSource(document), result);
    }
}
