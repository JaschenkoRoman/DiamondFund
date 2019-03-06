package com.epam.diamondFund.entity;

import javax.xml.bind.annotation.*;
import java.util.Objects;

@XmlType(name = "colourful-gem", namespace = "http://epam.com/gems")
@XmlRootElement(name = "colourful-gem", namespace = "http://epam.com/gems")
public class ColourfulGem extends Gem {
    @XmlElement(required = true, namespace = "http://epam.com/gems")
    @XmlSchemaType(name = "colour", namespace = "http://epam.com/gems")
    private Colour colour;

    public ColourfulGem() {
    }

    public ColourfulGem(String name, String origin, int value, Colour colour) {
        super(name, origin, value);
        this.colour = colour;
    }

    @XmlAttribute
    public String getName() {
        return super.getName();
    }

    public void setName(String name){
        super.setName(name);
    }

    public Colour getColour() {
        return colour;
    }

    public void setColour(Colour colour) {
        this.colour = colour;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        ColourfulGem that = (ColourfulGem) o;
        return colour == that.colour;
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), colour);
    }

    @Override
    public String toString() {
        return "ColourfulGem{" +
                "name='" + super.getName() + '\'' +
                ", origin='" + super.getOrigin() + '\'' +
                ", value='" + super.getValue() + '\'' +
                ", colour='" + colour +
                "'}";
    }
}
