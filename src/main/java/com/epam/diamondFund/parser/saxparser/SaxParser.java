package com.epam.diamondFund.parser.saxparser;

import com.epam.diamondFund.entity.Gems;
import com.epam.diamondFund.exception.XmlParserException;
import com.epam.diamondFund.parser.Parser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;
import org.xml.sax.helpers.XMLReaderFactory;

import java.io.IOException;

public class SaxParser implements Parser  {
    private static final String PARSING_ERROR = "SaxParsing error occurred: ";
    private static final String I_O_STREAM_ERROR = "I/O stream error occurred: ";
    private static Logger logger = LoggerFactory.getLogger(SaxParser.class);
    private XMLReader xmlReader;
    public Gems parseFromXml(String xmlPath) throws XmlParserException {
        GemHandler gemHandler = new GemHandler();
        Gems gems = new Gems();
        try {
            xmlReader = XMLReaderFactory.createXMLReader();
            xmlReader.setContentHandler(gemHandler);
            xmlReader.parse(xmlPath);
        } catch (IOException e) {
            logger.error(PARSING_ERROR, e);
            System.err.print(PARSING_ERROR + e);
            throw new XmlParserException(PARSING_ERROR, e);
        } catch (SAXException e) {
            logger.error(I_O_STREAM_ERROR, e);
            System.err.print(I_O_STREAM_ERROR + e);
            throw new XmlParserException(I_O_STREAM_ERROR, e);
        }
        gems.setList(gemHandler.getGems());
        return  gems;
    }
}
