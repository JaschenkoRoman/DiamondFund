package com.epam.diamondFund.entity;

import javax.xml.bind.annotation.*;
import java.util.Objects;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(namespace = "http://epam.com/gems")
@XmlRootElement(namespace = "http://epam.com/gems")
public abstract class Gem {
    @XmlAttribute(name = "name", namespace = "http://epam.com/gems", required = true)
    private String name;
    @XmlElement(name = "origin", namespace = "http://epam.com/gems", required = true)
    private String origin;
    @XmlElement(name = "value", namespace = "http://epam.com/gems", required = true)
    @XmlSchemaType(name = "positiveInteger", namespace = "http://epam.com/gems")
    private int value;

    public Gem() {
    }

    public Gem(String name, String origin, int value) {
        this.name = name;
        this.origin = origin;
        this.value = value;
    }
    public String getName() {
        return name;
    }

    public String getOrigin() {
        return origin;
    }

    public int getValue() {
        return value;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setOrigin(String origin) {
        this.origin = origin;
    }

    public void setValue(int value) {
        this.value = value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Gem gem = (Gem) o;
        return value == gem.value &&
                Objects.equals(name, gem.name) &&
                Objects.equals(origin, gem.origin);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, origin, value);
    }

    @Override
    public String toString() {
        return "Gem{" +
                "name='" + name + '\'' +
                ", origin='" + origin + '\'' +
                ", value=" + value +
                '}';
    }
}
