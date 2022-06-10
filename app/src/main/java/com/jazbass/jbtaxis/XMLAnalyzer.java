package com.jazbass.jbtaxis;

import android.provider.DocumentsContract;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

import java.io.IOException;
import java.util.ArrayList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

public class XMLAnalyzer {
    InputSource inputSource;

    public XMLAnalyzer(InputSource inputSource) {
        this.inputSource = inputSource;
    }

    public ArrayList<Taxi> parse(){
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        ArrayList<Taxi> taxiList = new ArrayList<>();

        Document domDocument = null;
        try {
            DocumentBuilder builder = factory.newDocumentBuilder();
            domDocument= builder.parse(inputSource);
        } catch (ParserConfigurationException | IOException | SAXException e) {
            e.printStackTrace();
        }
        assert domDocument != null;
        Element XMLElement = domDocument.getDocumentElement();
        NodeList nodeTaxi = XMLElement.getElementsByTagName("taxi");
        for (int i = 0; i < nodeTaxi.getLength(); i++) {
            Taxi taxi = new Taxi();
            Node currentTaxi = nodeTaxi.item(i);

            taxi.setcarRegistration(
                    currentTaxi.getChildNodes().item(0).getFirstChild().getNodeValue());
            taxi.setLat(Double.parseDouble(
                    currentTaxi.getChildNodes().item(1).getFirstChild().getNodeValue()));
            taxi.setLng(Double.parseDouble(
                    currentTaxi.getChildNodes().item(2).getFirstChild().getNodeValue()));
            taxi.setAvailable(true);
            taxiList.add(taxi);
        }
        return taxiList;
    }
}
