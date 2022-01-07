package ru.itmo.soalab2.controller;

import ru.itmo.soalab2.model.*;

import javax.jws.WebMethod;
import javax.jws.WebService;

@WebService
public interface CitiesSoapControllerInterface {
    @WebMethod
    PaginationResult getCities(CityRequestParams filterParams);

    @WebMethod
    City addCity(CityFromClient cityFromClient) throws Exception;

    @WebMethod
    City updateCity(CityFromClient cityFromClient) throws Exception;

    @WebMethod
    void deleteCity(Long id) throws WrongParamsFault;

    @WebMethod
    CityListWrap getCitiesByMetersAboveSeaLevel(String metersAboveSeaLevel);

    @WebMethod
    CityListWrap getCitiesByName(String name);

    @WebMethod
    MetersAboveSeaLevelListWrap getUniqueMetersAboveSeaLevel();
}
