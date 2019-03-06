package com.epam.diamondFund.parser;

import com.epam.diamondFund.entity.*;
import com.epam.diamondFund.exception.XmlParserException;
import com.epam.diamondFund.parser.domparser.DomParser;
import com.epam.diamondFund.parser.jaxbparser.JaxbParser;
import com.epam.diamondFund.parser.saxparser.SaxParser;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.containsInAnyOrder;

public class ParserTests {
    private final static Gems EXPECTED = new Gems();
    @BeforeClass
    public static void setUp(){
        List<Gem> gems = Arrays.asList(
                new ColourlessGem("beryl", "RSA", 5, 90),
                new ColourlessGem("topaz", "Brazil", 8, 80),
                new ColourfulGem("rubin", "India", 7, Colour.Red),
                new ColourfulGem("jasper", "Russia", 11, Colour.Brown)
        );
        EXPECTED.setList(gems);
    }

    @Test
    public void testSaxParserShouldParseFromXml() throws XmlParserException {
        Parser saxParser = new SaxParser();
        Gems actual = saxParser.parseFromXml("src/test/resources/xml/gems.xml");
        Assert.assertNotNull(actual);
        assertThat(actual.getList(), containsInAnyOrder(EXPECTED.getList().toArray()));
    }
    @Test
    public void testDomParserShouldParseFromXml() throws XmlParserException {
        Parser domParser = new DomParser();
        Gems actual = domParser.parseFromXml("src/test/resources/xml/gems.xml");
        Assert.assertNotNull(actual);
        assertThat(actual.getList(), containsInAnyOrder(EXPECTED.getList().toArray()));
    }
    @Test
    public void testJaxbParserShouldParseFromXml() throws XmlParserException {
        Parser jaxbParser = new JaxbParser();
        Gems actual = jaxbParser.parseFromXml("src/test/resources/xml/gems.xml");
        Assert.assertNotNull(actual);
        assertThat(actual.getList(), containsInAnyOrder(EXPECTED.getList().toArray()));
    }
}
