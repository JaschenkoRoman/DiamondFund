package com.epam.diamondFund.parser.domparser;

import com.epam.diamondFund.entity.*;
import com.epam.diamondFund.exception.XmlParserException;
import com.epam.diamondFund.parser.Parser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class DomParser implements Parser {
    private static final String FILE_IO_ERROR = "File error or  I/O error : ";
    private static final String COLOURFUL_GEM = "colourful-gem";
    private static final String COLOURLESS_GEM = "colourless-gem";
    private static final String CLARITY = "clarity";
    private static final String COLOUR = "colour";
    private static final String NAME = "name";
    private static final String ORIGIN = "origin";
    private static final String VALUE = "value";
    private static final String GEMS_NAMESPACE = "http://epam.com/gems";
    private static final String PARSING_ERROR = "DomParsing error occurred: ";
    private static final String PARSER_CONFIGURATION_ERROR = "DomParser configuration error: ";
    private static final String ELEMENT_NOT_FOUND_ERROR = "Element not found: ";
    private static Logger logger = LoggerFactory.getLogger(DomParser.class);
    private List<Gem> gemsList;
    private DocumentBuilder documentBuilder;

    public DomParser(){
        this.gemsList = new ArrayList<>();
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        factory.setNamespaceAware(true);
        try {
            documentBuilder = factory.newDocumentBuilder();
        } catch (ParserConfigurationException e) {
            logger.error(PARSER_CONFIGURATION_ERROR, e);
            System.err.print(PARSER_CONFIGURATION_ERROR + e);
        }
    }

    @Override
    public Gems parseFromXml(String xmlPath) throws XmlParserException{
        Gems gems = new Gems();
        Document doc;
        try {
            doc = documentBuilder.parse(xmlPath);
            Element root = doc.getDocumentElement();
            NodeList nodes = root.getChildNodes();
            for (int i = 0; i < nodes.getLength(); ++i) {
                Node gemNode = nodes.item(i);
                if (gemNode.getNodeType() == Node.ELEMENT_NODE) {
                    Gem gem = buildGem(nodes.item(i));
                    gemsList.add(gem);
                }
            }
        } catch (SAXException e) {
            logger.error(PARSING_ERROR, e);
            System.err.print(PARSING_ERROR + e);
            throw new XmlParserException(PARSING_ERROR, e);
        } catch (IOException e) {
            logger.error(FILE_IO_ERROR, e);
            System.err.print(FILE_IO_ERROR + e);
            throw new XmlParserException(FILE_IO_ERROR, e);
        }
        gems.setList(gemsList);
        return gems;
    }

    private Gem buildGem(Node gemNode) throws XmlParserException{
        Gem gem;
        switch (gemNode.getLocalName()) {
            case COLOURFUL_GEM:
                gem = buildColourfulGem(gemNode);
                break;
            case COLOURLESS_GEM:
                gem = buildColourlessGem(gemNode);
                break;
            default:
                throw new XmlParserException(ELEMENT_NOT_FOUND_ERROR + gemNode);
        }
        Element gemElement = (Element) gemNode;
        String name = gemElement.getAttribute(NAME);
        gem.setName(name);
        String origin = getChildNodeValue(ORIGIN, gemElement);
        gem.setOrigin(origin);
        int value = Integer.parseInt(getChildNodeValue(VALUE, gemElement));
        gem.setValue(value);

        return gem;
    }

    private Element getChildNode(String tagName, Element parent) {
        NodeList nodes = parent.getElementsByTagNameNS(GEMS_NAMESPACE,tagName);
        return (Element) nodes.item(0);
    }

    private String getChildNodeValue(String tagName, Element parent) {
        Element child = getChildNode(tagName, parent);
        Node childNode = child.getFirstChild();
        return childNode.getNodeValue();
    }

    private Gem buildColourfulGem(Node gemNode) {
        Gem gem = new ColourfulGem();
        Colour colour = Colour.valueOf(getChildNodeValue(COLOUR,(Element) gemNode));
        ((ColourfulGem)gem).setColour(colour);
        return gem;
    }
    private Gem buildColourlessGem(Node gemNode) {
        Gem gem = new ColourlessGem();
        int clarity = Integer.parseInt(getChildNodeValue(CLARITY,(Element) gemNode));
        ((ColourlessGem)gem).setClarity(clarity);
        return gem;
    }
}
