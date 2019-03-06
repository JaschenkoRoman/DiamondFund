package com.epam.diamondFund.director;

import com.epam.diamondFund.entity.*;
import com.epam.diamondFund.exception.XmlParserException;
import com.epam.diamondFund.parser.Parser;
import com.epam.diamondFund.parser.ParserFactory;
import com.epam.diamondFund.validator.XmlValidator;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

public class DirectorTests {
    private static final String XML_PATH = "src/test/resources/xml/gems.xml";
    private static final String XSD_PATH = "src/test/resources/xml/gems.xsd";
    private static final String INCORRECT_XML_PATH = "src/test/resources/xml/gemsIncorrect1.xml";
    private static final ParserFactory FACTORY = mock(ParserFactory.class);
    private static final Parser PARSER = mock(Parser.class);
    private static final XmlValidator VALIDATOR = mock(XmlValidator.class);
    private static final ParseDirector DIRECTOR = new ParseDirector();
    private List<Gem> gemList = Arrays.asList(
            new ColourlessGem("beryl", "RSA", 5, 90),
            new ColourlessGem("topaz", "Brazil", 8, 80),
            new ColourfulGem("rubin", "India", 7, Colour.Red),
            new ColourfulGem("jasper", "Russia", 11, Colour.Brown)
    );

@Before
public void setUp() {

    when(VALIDATOR.validate(XML_PATH, XSD_PATH)).thenReturn(true);
    when(VALIDATOR.validate(INCORRECT_XML_PATH, XSD_PATH)).thenReturn(false);
    when(FACTORY.getParser(anyString())).thenReturn(PARSER);

}

@Test
public void testDirectorShouldReturnGemsWhenCorrectParameters() throws XmlParserException {
    /*Given*/
    Gems expected = new Gems();
    expected.setList(gemList);
    when(PARSER.parseFromXml(XML_PATH)).thenReturn(expected);
    /*When*/
    Gems actual = DIRECTOR.process(XML_PATH, XSD_PATH, VALIDATOR, FACTORY, "sax");
    /*Then*/
    Assert.assertEquals(expected, actual);
    verify(VALIDATOR).validate(XML_PATH, XSD_PATH);
    verify(FACTORY).getParser(anyString());
    verify(PARSER).parseFromXml(XML_PATH);
    verifyNoMoreInteractions(VALIDATOR, FACTORY, PARSER, FACTORY);
}

    @Test(expected = XmlParserException.class)
    public void testDirectorShouldFailWhenIncorrectXmlPath() throws XmlParserException {
        /*Given*/
        Gems expected = new Gems();
        expected.setList(gemList);
        when(PARSER.parseFromXml(INCORRECT_XML_PATH)).thenThrow(XmlParserException.class);
        /*When*/
        DIRECTOR.process(INCORRECT_XML_PATH, XSD_PATH, VALIDATOR, FACTORY, "dom");
        /*Then*/
        verify(VALIDATOR).validate(INCORRECT_XML_PATH, XSD_PATH);
        verifyNoMoreInteractions(VALIDATOR, FACTORY, PARSER, FACTORY);
    }

}
