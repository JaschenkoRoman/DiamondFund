package com.epam.diamondFund.parser;

import com.epam.diamondFund.parser.domparser.DomParser;
import com.epam.diamondFund.parser.jaxbparser.JaxbParser;
import com.epam.diamondFund.parser.saxparser.SaxParser;

public class ParserFactory {
    public Parser getParser(String parserType) {
        Parser parser = null;
        if (parserType.equalsIgnoreCase("sax")) {
            parser = new SaxParser();
        } else if (parserType.equalsIgnoreCase("dom")) {
            parser = new DomParser();
        } else if (parserType.equalsIgnoreCase("jaxb")) {
            parser = new JaxbParser();
        }
        return parser;
    }
}
