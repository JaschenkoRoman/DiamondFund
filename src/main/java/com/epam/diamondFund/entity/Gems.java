package com.epam.diamondFund.entity;

import javax.xml.bind.annotation.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@XmlRootElement(name = "gems", namespace = "http://epam.com/gems")
public class Gems {
    @XmlElementRefs(value = {
            @XmlElementRef(name = "colourless-gem", type = ColourlessGem.class, namespace = "http://epam.com/gems"),
            @XmlElementRef(name = "colourful-gem", type = ColourfulGem.class, namespace = "http://epam.com/gems"),
    })
    private List<Gem> gems = new ArrayList<>();

    public Gems(){
        super();
    }

    public List<Gem> getList() {
        return gems;
    }

    public void setList(List<Gem> gems) {
        this.gems = gems;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Gems gems1 = (Gems) o;
        return Objects.equals(gems, gems1.gems);
    }

    @Override
    public int hashCode() {
        return Objects.hash(gems);
    }

    @Override
    public String toString() {
        return "Gems{" +
                "gems=" + gems +
                '}';
    }
}
