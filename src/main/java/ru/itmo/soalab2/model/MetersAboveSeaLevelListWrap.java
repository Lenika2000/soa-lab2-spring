package ru.itmo.soalab2.model;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.ArrayList;

@XmlRootElement(name = "uniqueMetersAboveSeeLevelList")
@XmlAccessorType(XmlAccessType.FIELD)
public class MetersAboveSeaLevelListWrap {
    public MetersAboveSeaLevelListWrap(){}

    public MetersAboveSeaLevelListWrap(ArrayList<String> uniqueMetersAboveSeeLevel){
        this.uniqueMetersAboveSeeLevel = uniqueMetersAboveSeeLevel;
    }

    @XmlElement
    private ArrayList<String> uniqueMetersAboveSeeLevel;
}
