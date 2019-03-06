package com.epam.diamondFund.director;

import com.epam.diamondFund.entity.Gems;
import com.epam.diamondFund.exception.XmlParserException;
import com.epam.diamondFund.parser.Parser;
import com.epam.diamondFund.parser.ParserFactory;
import com.epam.diamondFund.validator.XmlValidator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ParseDirector {
    private static final String PARSE_DIRECTOR_ERROR = "ParseDirector error occurred: ";
    private static Logger logger = LoggerFactory.getLogger(ParseDirector.class);

    public Gems process
            (String xmlPath, String xsdPath, XmlValidator validator, ParserFactory parserFactory, String parserType) throws XmlParserException {
        Parser parser = parserFactory.getParser(parserType);
        Gems gems;
        if (!validator.validate(xmlPath, xsdPath)) {
            throw new XmlParserException(xmlPath + "doesnt comply with" + xsdPath);
        }
        try {
            gems = parser.parseFromXml(xmlPath);
        } catch (XmlParserException e) {
            logger.error(PARSE_DIRECTOR_ERROR, e);
            System.err.print(PARSE_DIRECTOR_ERROR + e);
            throw e;
        }
        return gems;
    }
}
