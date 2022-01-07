package ru.itmo.soalab2.model;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.ArrayList;

@XmlRootElement(name = "cityList")
@XmlAccessorType(XmlAccessType.FIELD)
public class CityListWrap {
    public CityListWrap(){}

    public CityListWrap(ArrayList<City> cities){
        this.cities = cities;
    }

    @XmlElement
    private ArrayList<City> cities;
}
