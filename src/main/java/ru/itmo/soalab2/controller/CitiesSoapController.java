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
    public PaginationResult getCities(CityRequestParams filterParams) throws WrongParamsFault {
        return soapCityService.getAllCities(filterParams);
    }

    @Override
    public City addCity(CityFromClient cityFromClient) throws ValidateFieldsException {
        return soapCityService.createCity(cityFromClient);
    }

    @Override
    public City updateCity(CityFromClient cityFromClient) throws ValidateFieldsException {
        return soapCityService.updateCity(cityFromClient);
    }

    @Override
    public void deleteCity(Long id) throws WrongParamsFault {
        soapCityService.deleteCity(id);
    }

    @Override
    public CityListWrap getCitiesByMetersAboveSeaLevel(String metersAboveSeaLevel) throws WrongParamsFault {
        return new CityListWrap(soapCityService.filterCitiesByMetersAboveSeaLevel(new Integer(metersAboveSeaLevel)));
    }

    @Override
    public CityListWrap getCitiesByName(String name) throws WrongParamsFault {
        return new CityListWrap(soapCityService.getCitiesByName(name));
    }

    @Override
    public MetersAboveSeaLevelListWrap getUniqueMetersAboveSeaLevel() throws WrongParamsFault {
        return new MetersAboveSeaLevelListWrap(soapCityService.getUniqueMetersAboveSeaLevel());
    }
}
