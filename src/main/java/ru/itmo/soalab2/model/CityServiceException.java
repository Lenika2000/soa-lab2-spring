package ru.itmo.soalab2.model;

import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Response;

public class CityServiceException extends RuntimeException {
    private final String message;
    private final int responseCode;

    public CityServiceException(String message, int responseCode){
        this.message = message;
        this.responseCode = responseCode;
    }

    public WebApplicationException toWebApplicationException(){
        return new WebApplicationException(Response.status(responseCode).entity(message).build());
    }

    @Override
    public String getMessage() {
        return message;
    }

    public int getResponseCode() {
        return responseCode;
    }
}
