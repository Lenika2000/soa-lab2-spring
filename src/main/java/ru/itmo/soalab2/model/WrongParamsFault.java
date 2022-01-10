package ru.itmo.soalab2.model;

import javax.xml.ws.WebFault;

@WebFault(name = "wrongParamsFault", targetNamespace = "http://controller.soalab2.itmo.ru/")
public class WrongParamsFault extends Exception {
    private WebServiceExceptionFaultDto faultInfo;

    public WrongParamsFault(String message, WebServiceExceptionFaultDto faultIFault){
        super(message);
        this.faultInfo = faultIFault;
    }

    public WebServiceExceptionFaultDto getFaultInfo() {
        return faultInfo;
    }

    public void setFaultInfo(WebServiceExceptionFaultDto faultInfo) {
        this.faultInfo = faultInfo;
    }
}
