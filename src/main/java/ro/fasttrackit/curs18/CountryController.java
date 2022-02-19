package ro.fasttrackit.curs18;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.websocket.server.PathParam;
import java.awt.geom.QuadCurve2D;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("countries")
public class CountryController {
    private final CountryService countryService;

    public CountryController(CountryService countryService) {
        this.countryService = countryService;
    }

    @GetMapping
    public List<Country> getAll(){
        return countryService.getCountries();
    }

    @GetMapping("names")
    public List<String> getAllNames(){
        return countryService.allCountriesName();
    }

    @GetMapping("{countryID}/capital")
    public Optional<String> getNameByID(@PathVariable int countryID){
        return countryService.getCapitalByID(countryID);
    }

    @GetMapping("{countryID}/population")
    public Optional<Long> getPopulationbyID(@PathVariable int countryID){
        return countryService.getPopulationByID(countryID);
    }
    //get countries in continent : /continents/<continentName>/countries
    @GetMapping("/continents/<continentName>/countries")
    public List<Country> getCountries(@PathVariable int countryID){
        return countryService.getPopulationByID(countryID);
    }

}
