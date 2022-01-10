package ru.itmo.soalab2.controller;

import ru.itmo.soalab2.model.*;
import ru.itmo.soalab2.validators.ValidateFieldsException;

import javax.jws.WebMethod;
import javax.jws.WebService;
import javax.xml.soap.SOAPException;

@WebService
public interface CitiesSoapControllerInterface {
    @WebMethod
    PaginationResult getCities(CityRequestParams filterParams) throws WrongParamsFault;

    @WebMethod
    City addCity(CityFromClient cityFromClient) throws ValidateFieldsException;

    @WebMethod
    City updateCity(CityFromClient cityFromClient) throws ValidateFieldsException;

    @WebMethod
    void deleteCity(Long id) throws WrongParamsFault;

    @WebMethod
    CityListWrap getCitiesByMetersAboveSeaLevel(String metersAboveSeaLevel) throws WrongParamsFault;

    @WebMethod
    CityListWrap getCitiesByName(String name) throws WrongParamsFault;

    @WebMethod
    MetersAboveSeaLevelListWrap getUniqueMetersAboveSeaLevel() throws WrongParamsFault;
}
