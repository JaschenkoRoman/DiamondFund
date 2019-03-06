package com.epam.diamondFund.entity;

import javax.xml.bind.annotation.*;
import java.util.Objects;

@XmlType(name = "colourless-gem", namespace = "http://epam.com/gems")
@XmlRootElement(name = "colourless-gem", namespace = "http://epam.com/gems")
public class ColourlessGem extends Gem {
    @XmlElement(required = true, namespace = "http://epam.com/gems")
    @XmlSchemaType(name = "positiveInteger", namespace = "http://epam.com/gems")
    private int clarity;

    public ColourlessGem() {
    }

    public ColourlessGem(String name, String origin, int value, int clarity) {
        super(name, origin, value);
        if(clarity < 0 || clarity > 100) {
            throw new IllegalArgumentException("clarity must be from 0 to 100");
        }
        this.clarity = clarity;
    }

    @XmlAttribute
    public String getName() {
        return super.getName();
    }

    public void setName(String name) {
        super.setName(name);
    }

    public int getClarity() {
        return clarity;
    }

    public void setClarity(int clarity) {
        this.clarity = clarity;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        ColourlessGem that = (ColourlessGem) o;
        return clarity == that.clarity;
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), clarity);
    }

    @Override
    public String toString() {
        return "ColourlessGem{" +
                "name='" + super.getName() + '\'' +
                ", origin='" + super.getOrigin() + '\'' +
                ", value='" + super.getValue() + '\'' +
                ", clarity='" + clarity +
                "'}";
    }
}
