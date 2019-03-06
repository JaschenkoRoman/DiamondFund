package com.epam.diamondFund.parser.saxparser;

import com.epam.diamondFund.entity.Colour;
import com.epam.diamondFund.entity.ColourfulGem;
import com.epam.diamondFund.entity.ColourlessGem;
import com.epam.diamondFund.entity.Gem;
import com.epam.diamondFund.exception.XmlParserException;
import org.xml.sax.Attributes;
import org.xml.sax.helpers.DefaultHandler;

import java.util.ArrayList;
import java.util.List;

public class GemHandler extends DefaultHandler {
    private static final String GEMS = "gems";
    private static final String COLOURLESS_GEM = "colourless-gem";
    private static final String COLOURFUL_GEM = "colourful-gem";
    private static final String ORIGIN = "origin";
    private static final String VALUE = "value";
    private static final String CLARITY = "clarity";
    private static final String COLOUR = "colour";
    private List<Gem> gems;
    private Gem currentGem;
    private String currentElement;


    public List<Gem> getGems(){
        return gems;
    }
    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) {
        currentElement = localName;
        switch(currentElement){
            case GEMS:
                gems = new ArrayList<>();
                break;
            case COLOURLESS_GEM:
                currentGem = new ColourlessGem();
                currentGem.setName(attributes.getValue(0));
                break;
            case COLOURFUL_GEM:
                currentGem = new ColourfulGem();
                currentGem.setName(attributes.getValue(0));
                break;
        }
    }
    @Override
    public void endElement(String uri, String localName, String qName) {
        if (COLOURLESS_GEM.equals(localName) || COLOURFUL_GEM.equals(localName)) {
            gems.add(currentGem);
        }
    }

    @Override
    public void characters ( char[] ch, int start, int length) {
        String s = new String(ch, start, length).trim();
        if(currentElement != null) {
            switch (currentElement) {
                case ORIGIN:
                    currentGem.setOrigin(s);
                    break;
                case VALUE:
                    currentGem.setValue(Integer.parseInt(s));
                    break;
                case CLARITY:
                    ((ColourlessGem) currentGem).setClarity(Integer.parseInt(s));
                    break;
                case COLOUR:
                    Colour colour = Colour.valueOf(s);
                    ((ColourfulGem) currentGem).setColour(colour);
            }
        }
        currentElement = null;
    }
}
