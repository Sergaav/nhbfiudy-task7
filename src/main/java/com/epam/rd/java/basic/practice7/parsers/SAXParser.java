package com.epam.rd.java.basic.practice7.parsers;

import com.epam.rd.java.basic.practice7.items.*;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import javax.xml.XMLConstants;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParserFactory;
import java.io.IOException;

public class SAXParser extends DefaultHandler {

    private String xmlFileName;
    private String currentElement;

    private Shiporder shiporder;

    private Order order;

    private Item item;

    public SAXParser(String xmlFileName) {
        this.xmlFileName = xmlFileName;
    }


    public void parse(boolean validate)
            throws ParserConfigurationException, SAXException, IOException {

        SAXParserFactory factory = SAXParserFactory.newInstance();
        factory.setNamespaceAware(true);

        if (validate) {
            factory.setFeature(Constants.FEATURE_TURN_VALIDATION_ON, true);
            factory.setFeature(Constants.FEATURE_TURN_SCHEMA_VALIDATION_ON, true);
        }

        javax.xml.parsers.SAXParser parser = factory.newSAXParser();

        parser.setProperty(XMLConstants.ACCESS_EXTERNAL_DTD, "");
        parser.setProperty(XMLConstants.ACCESS_EXTERNAL_SCHEMA, "");
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
                             Attributes attributes) throws SAXException {

        currentElement = localName;

        if (XMLTegs.SHIPORDER.equalsTo(currentElement)) {
            shiporder = new Shiporder();
            return;
        }
        if (XMLTegs.ORDER.equalsTo(currentElement)) {
            order = new Order();
            return;
        }

        if (XMLTegs.ITEM.equalsTo(currentElement)) {
            item = new Item();
            return;
        }
    }

    @Override
    public void characters(char[] ch, int start, int length)
            throws SAXException {

        String elementText = new String(ch, start, length).trim();

        // return if content is empty
        if (elementText.isEmpty()) {
            return;
        }

        if (XMLTegs.QUESTION_TEXT.equalsTo(currentElement)) {
            question.setQuestionText(elementText);
            return;
        }

        if (XML.ANSWER.equalsTo(currentElement)) {
            answer.setContent(elementText);
            return;
        }
    }

    @Override
    public void endElement(String uri, String localName, String qName)
            throws SAXException {

        if (XML.QUESTION.equalsTo(localName)) {
            // just add question to container
            test.getQuestions().add(question);
            return;
        }

        if (XML.ANSWER.equalsTo(localName)) {
            // just add answer to container
            question.getAnswers().add(answer);
            return;
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
