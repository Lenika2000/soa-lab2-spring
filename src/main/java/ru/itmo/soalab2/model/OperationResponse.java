package ru.itmo.soalab2.model;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "operationResponse")
@XmlAccessorType(XmlAccessType.FIELD)
public class OperationResponse {
    @XmlElement
    private Long cityId;
    @XmlElement
    private String message;

    public OperationResponse(Long cityId, String message) {
        this.cityId = cityId;
        this.message = message;
    }

    public OperationResponse() {
    }

    public Long getCityId() {
        return cityId;
    }

    public void setCityId(Long cityId) {
        this.cityId = cityId;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
