package com.epam.diamondFund.validator;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.xml.sax.SAXException;

import javax.xml.XMLConstants;
import javax.xml.transform.Source;
import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import javax.xml.validation.Validator;
import java.io.File;
import java.io.IOException;

public class SaxXsdValidator implements XmlValidator {
    private static Logger logger = LoggerFactory.getLogger(SaxXsdValidator.class);

    public boolean validate(String xmlPath, String xsdPath) {
        boolean isValid = true;
        String language = XMLConstants.W3C_XML_SCHEMA_NS_URI;
        SchemaFactory factory = SchemaFactory.newInstance(language);
        try {
            Schema schema = factory.newSchema(new File(xsdPath));
            Validator validator = schema.newValidator();
            Source source = new StreamSource(new File(xmlPath));
            validator.validate(source);
        } catch (SAXException e) {
            logger.error("validation is not valid: ", e);
            System.err.print("validation " + xmlPath + " is not valid because " + e.getMessage());
            isValid = false;

        } catch (IOException e) {
            logger.error("validation is not valid: ", e);
            System.err.print("validation " + xmlPath + " is not valid because " + e.getMessage());
            isValid = false;
        }

        return isValid;
    }
}
