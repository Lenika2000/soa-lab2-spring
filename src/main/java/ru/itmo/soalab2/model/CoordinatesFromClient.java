package ru.itmo.soalab2.model;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "сoordinatesFromClient")
@XmlAccessorType(XmlAccessType.FIELD)
public class CoordinatesFromClient {
    @XmlElement
    private int id;
    @XmlElement
    private String x;
    @XmlElement
    private String y;


    public CoordinatesFromClient(int id, String x, String y) {
        this.id = id;
        this.x = x;
        this.y = y;
    }

    public CoordinatesFromClient() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getX() {
        return x;
    }

    public void setX(String x) {
        this.x = x;
    }

    public String getY() {
        return y;
    }

    public void setY(String y) {
        this.y = y;
    }
}
