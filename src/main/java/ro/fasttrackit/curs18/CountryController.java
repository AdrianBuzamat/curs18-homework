package ro.fasttrackit.curs18;

import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(value = {"countries", "continents"})
public class CountryController {
    private final CountryService countryService;

    public CountryController(CountryService countryService) {
        this.countryService = countryService;
    }

    @GetMapping
    public List<Country> getAll() {
        return countryService.getCountries();
    }

    @GetMapping("names")
    public List<String> getAllNames() {
        return countryService.allCountriesName();
    }

    @GetMapping("{countryID}/capital")
    public Optional<String> getNameByID(@PathVariable int countryID) {
        return countryService.getCapitalByID(countryID);
    }

    @GetMapping("{countryID}/population")
    public Optional<Long> getPopulationByID(@PathVariable int countryID) {
        return countryService.getPopulationByID(countryID);
    }

    @GetMapping("{continentName}/countries")
    public List<Country> getCountries(@PathVariable String continentName) {
        return countryService.getCountriesByContinent(continentName);
    }

    @GetMapping("{countryID}/neighbours")
    public List<String> getNeighbours(@PathVariable int countryID) {
        return countryService.allCountryNeighboursByID(countryID)
                .orElse(null);
    }

    @GetMapping(value = "{continentName}/countries", params = {"minPopulation"})
    public List<Country> getCountriesFromContinentByPopulation(@PathVariable String continentName, @RequestParam long minPopulation) {
        return countryService.getCountriesFromContinentByPopulation(continentName, minPopulation);
    }

    @GetMapping(params = {"includeNeighbour", "excludeNeighbour"})
    public List<Country> getCountriesSelectedNeighbours(@RequestParam String includeNeighbour, @RequestParam String excludeNeighbour) {
        return countryService.getNeighboursBySelection(includeNeighbour, excludeNeighbour);
    }
}
