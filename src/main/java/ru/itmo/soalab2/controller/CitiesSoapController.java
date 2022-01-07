package ru.itmo.soalab2.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.itmo.soalab2.ApplicationContextProvider;
import ru.itmo.soalab2.model.*;
import ru.itmo.soalab2.repo.CityRepository;
import ru.itmo.soalab2.services.SoapCityService;
import ru.itmo.soalab2.validators.ValidateFieldsException;

import javax.jws.WebService;

@Service
@WebService(endpointInterface = "ru.itmo.soalab2.controller.CitiesSoapControllerInterface")
public class CitiesSoapController implements CitiesSoapControllerInterface {

    private final SoapCityService soapCityService;

    @Autowired
    public CitiesSoapController(CityRepository cityRepository) {
        this.soapCityService = new SoapCityService(cityRepository);
    }

    public CitiesSoapController() {
        this.soapCityService = ApplicationContextProvider.getApplicationContext().getBean(SoapCityService.class);
    }

    @Override
    public PaginationResult getCities(CityRequestParams filterParams) {
//        CityRequestParams filterParams = new CityRequestParams(params);
        return soapCityService.getAllCities(filterParams);
    }

    @Override
    public City addCity(CityFromClient cityFromClient) throws Exception {
        try {
            return soapCityService.createCity(cityFromClient);
        } catch (ValidateFieldsException e) {
            return null;
        }
    }

    @Override
    public City updateCity(CityFromClient cityFromClient) throws Exception {
        try {
            return soapCityService.updateCity(cityFromClient);
        } catch (ValidateFieldsException e) {
            return null;
        }
    }

    @Override
    public void deleteCity(Long id) throws WrongParamsFault {
        try {
            soapCityService.deleteCity(id);
        } catch (CityServiceException e) {
            throw new WrongParamsFault(e.getMessage(), e.getResponseCode());
        }
    }

    @Override
    public CityListWrap getCitiesByMetersAboveSeaLevel(String metersAboveSeaLevel) {
        return new CityListWrap(soapCityService.filterCitiesByMetersAboveSeaLevel(new Integer(metersAboveSeaLevel)));
    }

    @Override
    public CityListWrap getCitiesByName(String name) {
        return new CityListWrap(soapCityService.getCitiesByName(name));
    }

    @Override
    public MetersAboveSeaLevelListWrap getUniqueMetersAboveSeaLevel() {
        return new MetersAboveSeaLevelListWrap(soapCityService.getUniqueMetersAboveSeeLevel());
    }
}
