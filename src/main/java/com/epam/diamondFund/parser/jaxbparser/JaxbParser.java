package com.epam.diamondFund.parser.jaxbparser;

import com.epam.diamondFund.entity.Gems;
import com.epam.diamondFund.exception.XmlParserException;
import com.epam.diamondFund.parser.Parser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import java.io.FileNotFoundException;
import java.io.FileReader;

public class JaxbParser implements Parser {
    private static Logger logger = LoggerFactory.getLogger(JaxbParser.class);
    private static final String PARSING_ERROR = "JaxbParsing error occurred: ";
    private static final String FILE_NOT_FOUND_ERROR = "File not found: ";
    @Override
    public Gems parseFromXml(String xmlPath) throws XmlParserException {
        Gems gems;
        try {
            JAXBContext context = JAXBContext.newInstance(Gems.class);
            Unmarshaller unmarshaller = context.createUnmarshaller();
            FileReader fileReader = new FileReader(xmlPath);
             gems = (Gems) unmarshaller.unmarshal(fileReader);
        } catch (JAXBException e) {
            logger.error(PARSING_ERROR, e);
            System.err.print(PARSING_ERROR + e);
            throw new XmlParserException(PARSING_ERROR, e);
        } catch (FileNotFoundException e) {
            logger.error(FILE_NOT_FOUND_ERROR, e);
            System.err.print(FILE_NOT_FOUND_ERROR + e);
            throw new XmlParserException(FILE_NOT_FOUND_ERROR, e);
        }
        return gems;
    }
}
