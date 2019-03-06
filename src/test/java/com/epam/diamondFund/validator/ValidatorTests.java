package com.epam.diamondFund.validator;

import org.junit.Assert;
import org.junit.Test;

public class ValidatorTests {
    private static final XmlValidator XML_VALIDATOR = new SaxXsdValidator();

    @Test
    public void testValidateShouldReturnTrueWhenCorrectXmlSupplied(){
        boolean isValid = XML_VALIDATOR
                .validate("src/test/resources/xml/gems.xml", "src/test/resources/xml/gems.xsd");
        Assert.assertTrue(isValid);
    }
    @Test
    public void testValidateShouldReturnFalseWhenIncorrectXmlWithDuplicateTagSupplied(){
        boolean isValid = XML_VALIDATOR
                .validate("src/test/resources/xml/gemsIncorrect1.xml", "src/test/resources/xml/gems.xsd");
        Assert.assertFalse(isValid);
    }
    @Test
    public void testValidateShouldReturnFalseWhenIncorrectXmlWithIncorrectFirstElementSupplied(){
        boolean isValid = XML_VALIDATOR
                .validate("src/test/resources/xml/gemsIncorrect2.xml", "src/test/resources/xml/gems.xsd");
        Assert.assertFalse(isValid);
    }
    @Test
    public void testValidateShouldReturnFalseWhenIncorrectXmlWithoutAnyElementsSupplied(){
        boolean isValid = XML_VALIDATOR
                .validate("src/test/resources/xml/gemsIncorrect3.xml", "src/test/resources/xml/gems.xsd");
        Assert.assertFalse(isValid);
    }
    @Test
    public void testValidateShouldReturnFalseWhenCorrectXmlDoesntExist(){
        boolean isValid = XML_VALIDATOR
                .validate("src/test/resources/xml/gems99.xml", "src/test/resources/xml/gems.xsd");
        Assert.assertFalse(isValid);
    }

}
