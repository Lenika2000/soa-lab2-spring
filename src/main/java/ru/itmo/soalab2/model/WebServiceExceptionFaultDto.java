package ru.itmo.soalab2.model;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "wrongParamsFault")
@XmlAccessorType(XmlAccessType.FIELD)
public class WebServiceExceptionFaultDto {
    @XmlElement
    private String message;
    @XmlElement
    private int code;

    public WebServiceExceptionFaultDto(String message, int code) {
        this.message = message;
        this.code = code;
    }

    public WebServiceExceptionFaultDto() {
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }
}
