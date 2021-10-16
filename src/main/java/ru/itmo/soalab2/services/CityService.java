package ru.itmo.soalab2.services;

import org.springframework.http.ResponseEntity;
import ru.itmo.soalab2.model.City;
import ru.itmo.soalab2.model.OperationResponse;
import ru.itmo.soalab2.repo.CityRepository;
import ru.itmo.soalab2.validators.CityValidator;
import ru.itmo.soalab2.validators.ValidateFieldsException;
import java.time.ZonedDateTime;

public class CityService {
    private final CityValidator cityValidator;
    private final CityRepository cityRepository;

    public CityService(CityRepository cityRepository) {
        this.cityValidator = new CityValidator();
        this.cityRepository = cityRepository;
    }

    public ResponseEntity<?> createCity(City newCity) throws Exception {
        try {
            newCity.setCreationDate(ZonedDateTime.now());
            cityValidator.validate(newCity);
            Long id = cityRepository.save(newCity).getId();
            return ResponseEntity.status(200).body(new OperationResponse(id, "City created successfully"));
        } catch (ValidateFieldsException ex) {
            return sendErrorList(ex);
        }
    }

    public ResponseEntity<?> updateCity(City updatedCity) throws Exception {
        try {
            cityValidator.validate(updatedCity);
            boolean isFound = cityRepository.existsById(updatedCity.getId());
            if (isFound) {
                cityRepository.save(updatedCity);
                return ResponseEntity.status(200).body(new OperationResponse(updatedCity.getId(), "City updated successfully"));
            } else {
                return ResponseEntity.status(404).body(new OperationResponse(updatedCity.getId(), "Cannot find city with id " + updatedCity.getId()));
            }
        } catch (ValidateFieldsException ex) {
            return sendErrorList(ex);
        }
    }

    public ResponseEntity<?> deleteCity(Long id) {
        boolean isFound = cityRepository.existsById(id);
        cityRepository.deleteById(id);
        if (isFound) {
            return ResponseEntity.status(200).body(new OperationResponse(id, "City deleted successfully"));
        } else {
            return ResponseEntity.status(404).body(new OperationResponse(id,"Cannot find city with id " + id));
        }
    }

//    public void getAllCities(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//        int numberOfRecordsPerPage = 5;
//        int selectedPage = 1;
//        List<City> cities = cityDao.getAllCities();
//        int citiesQuality = (int)Math. ceil( (double) (cities.size()+1) / numberOfRecordsPerPage);
//        request.setAttribute("pagesQuality", IntStream.range(1, (int)Math. ceil( citiesQuality + 1)).toArray());
//        request.setAttribute("citiesLength", cities.size());
//        int from = (selectedPage - 1) * numberOfRecordsPerPage;
//        request.setAttribute("cities", Arrays.copyOfRange(cities.toArray(), from , from + numberOfRecordsPerPage) );
//        request.getRequestDispatcher("jsp/main-page.jsp").forward(request, response);
//    }

//    public void filterCities(HttpServletRequest request, HttpServletResponse response) throws Exception {
//        Map<String, String[]> queryMap = request.getParameterMap();
//        List<City> filteredCities = cityDao.getFilteredCities(queryMap);
//        sendCities(request, response, filteredCities);
//    }
//
//    public void filterCitiesByMetersAboveSeaLevel(HttpServletRequest request, HttpServletResponse response) throws Exception {
//        int metersAboveSeaLevel = Integer.parseInt(request.getParameter("metersAboveSeaLevel"));
//        List<City> filteredCities = cityDao.findCitiesMetersAboveSeeLevelMore(metersAboveSeaLevel);
//        sendCities(request, response, filteredCities);
//    }
//
//    private void sendCities(HttpServletRequest request, HttpServletResponse response, List<City> filteredCities) throws Exception {
//        int numberOfRecordsPerPage = Integer.parseInt(request.getParameter("numberOfRecordsPerPage"));
//        int selectedPage = Integer.parseInt(request.getParameter("selectedPage"));
//        int from = (selectedPage - 1) * numberOfRecordsPerPage;
//        int to = (from + numberOfRecordsPerPage > filteredCities.size()) ? filteredCities.size() : from + numberOfRecordsPerPage ;
//        List<City> cities = new ArrayList<>(filteredCities.subList(from , to));
//        response.setContentType("text/xml");
//        response.setCharacterEncoding("UTF-8");
//        citiesList.setCities(cities);
//        response.setStatus(200);
//        Writer writer = new StringWriter();
//        Serializer serializer = new Persister();
//        serializer.write(citiesList, writer);
//        String xml = writer.toString();
//        response.getWriter().print(xml);
//    }
//
    private ResponseEntity<?> sendErrorList(ValidateFieldsException ex) {
        return ResponseEntity.status(400).body(ex.getErrorMsg());
    }
//
//    public void getUniqueMetersAboveSeeLevel(HttpServletRequest request, HttpServletResponse response) throws Exception {
//        List<Integer> uniqueMetersAboveSeeLevel = cityDao.getUniqueMetersAboveSeeLevel();
//        metersAboveSeaLevelList.setMeters(uniqueMetersAboveSeeLevel);
//        response.setStatus(200);
//        Writer writer = new StringWriter();
//        response.setStatus(200);
//        Serializer serializer = new Persister();
//        serializer.write(metersAboveSeaLevelList, writer);
//        String xml = writer.toString();
//        response.getWriter().print(xml);
//    }
//
//    public void sort(HttpServletRequest request, HttpServletResponse response) throws Exception {
//        String sortBy = request.getParameter("sortBy");
//        String order = request.getParameter("order");
//        List<City> sortedCities = cityDao.sort(sortBy, order);
//        sendCities(request, response, sortedCities);
//    }
}
