package com.epam.diamondFund.parser;

import com.epam.diamondFund.entity.Gem;
import com.epam.diamondFund.entity.Gems;
import com.epam.diamondFund.exception.XmlParserException;

import java.util.List;

public interface Parser {
    Gems parseFromXml(String xmlPath) throws XmlParserException;
}
