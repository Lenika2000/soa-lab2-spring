package ru.itmo.soalab2.model;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "humanFromClient")
@XmlAccessorType(XmlAccessType.FIELD)
public class HumanFromClient {
    @XmlElement
    private int id;
    @XmlElement
    private String height;
    @XmlElement
    private String birthday;

    public HumanFromClient(int id, String height, String birthday) {
        this.id = id;
        this.height = height;
        this.birthday = birthday;
    }

    public HumanFromClient() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getHeight() {
        return height;
    }

    public void setHeight(String height) {
        this.height = height;
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }
}
