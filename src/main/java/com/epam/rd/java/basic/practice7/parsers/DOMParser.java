package com.epam.rd.java.basic.practice7.parsers;

import com.epam.rd.java.basic.practice7.items.Item;
import com.epam.rd.java.basic.practice7.items.Order;
import com.epam.rd.java.basic.practice7.items.ShipOrder;
import com.epam.rd.java.basic.practice7.items.XMLTegs;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

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

public class DOMParser {

    public static final String FEATURE_TURN_VALIDATION_ON = "http://xml.org/sax/features/validation";
    public static final String FEATURE_TURN_SCHEMA_VALIDATION_ON = "http://apache.org/xml/features/validation/schema";


    private String fileName;

    private ShipOrder shipOrder;

    public ShipOrder getShipOrder() {
        return shipOrder;
    }

    public DOMParser(String fileName) {
        this.fileName = fileName;
    }

    public void parse(boolean validate) throws ParserConfigurationException, SAXException, IOException {
        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
        dbf.setNamespaceAware(true);
        dbf.setFeature(FEATURE_TURN_SCHEMA_VALIDATION_ON, true);
        dbf.setFeature(FEATURE_TURN_SCHEMA_VALIDATION_ON, true);
        DocumentBuilder db = dbf.newDocumentBuilder();
        Document document = db.parse(fileName);
        Element root = document.getDocumentElement();
        ShipOrder shipOrder = new ShipOrder();
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
        System.out.println(nodeOrder.getTextContent());
        Node nodeName = element.getElementsByTagName(XMLTegs.NAME.value()).item(0);
        order.setName(nodeName.getTextContent());
        Node nodeAddress = element.getElementsByTagName(XMLTegs.ADDRESS.value()).item(0);
        order.setAddress(nodeAddress.getTextContent());
        Node nodeCity = element.getElementsByTagName(XMLTegs.CITY.value()).item(0);
        order.setCity(nodeCity.getTextContent());
        Node nodeCountry = element.getElementsByTagName(XMLTegs.COUNTRY.value()).item(0);
        order.setCountry(nodeCountry.getTextContent());
        NodeList listItems = element.getElementsByTagName(XMLTegs.ITEM.value());
        for (int i = 0; i < listItems.getLength(); i++) {
            order.getItems().add(getItem(listItems.item(i)));
        }
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

    public static Document getDocument(ShipOrder shipOrder) throws ParserConfigurationException {
        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
        dbf.setNamespaceAware(true);
        DocumentBuilder db = dbf.newDocumentBuilder();
        Document document = db.newDocument();
        Element tElement = document.createElement(XMLTegs.SHIPORDER.value());
        document.appendChild(tElement);
        for (Order order : shipOrder.getOrders()) {
            Element qElement = document.createElement(XMLTegs.ORDER.value());
            tElement.appendChild(qElement);
            Element qtElement =
                    document.createElement(XML.QUESTION_TEXT.value());
            qtElement.setTextContent(question.getQuestionText());
            qElement.appendChild(qtElement);

            // add answers
            for (Answer answer : question.getAnswers()) {
                Element aElement = document.createElement(XML.ANSWER.value());
                aElement.setTextContent(answer.getContent());

                // set attribute
                if (answer.isCorrect()) {
                    aElement.setAttribute(XML.CORRECT.value(), "true");
                }
                qElement.appendChild(aElement);
            }
        }

        return document;
    }

    /**
     * Saves Test object to XML file.
     *
     * @param test
     *            Test object to be saved.
     * @param xmlFileName
     *            Output XML file name.
     */
    public static void saveToXML(Test test, String xmlFileName)
            throws ParserConfigurationException, TransformerException {
        // Test -> DOM -> XML
        saveToXML(getDocument(test), xmlFileName);
    }

    /**
     * Save DOM to XML.
     *
     * @param document
     *            DOM to be saved.
     * @param xmlFileName
     *            Output XML file name.
     */
    public static void saveToXML(Document document, String xmlFileName)
            throws TransformerException {

        StreamResult result = new StreamResult(new File(xmlFileName));

        // set up transformation
        TransformerFactory tf = TransformerFactory.newInstance();
        javax.xml.transform.Transformer t = tf.newTransformer();
        t.setOutputProperty(OutputKeys.INDENT, "yes");

        // run transformation
        t.transform(new DOMSource(document), result);
    }
}
