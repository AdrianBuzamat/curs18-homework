package ro.fasttrackit.curs18;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CountryService {
    private final List<Country> countries = new ArrayList<>();

    public CountryService(CountryReader countryReader) {
        this.countries.addAll(countryReader.readCountries());
    }

    public List<Country> getCountries() {
        return countries;
    }

    public List<String> allCountriesName() {
        return countries.stream()
                .map(Country::name)
                .collect(Collectors.toList());
    }

    public Optional<String> getCapitalByID(int id) {
        return countries.stream()
                .filter(p -> p.id() == id)
                .map(Country::capital)
                .findFirst();
    }

    public Optional<Long> getPopulationByID(int id) {
        return countries.stream()
                .filter(p -> p.id() == id)
                .map(Country::population)
                .findFirst();
    }

    public List<Country> getCountriesByContinent(String continent) {
        return countries.stream()
                .filter(p -> p.continent().equalsIgnoreCase(continent))
                .collect(Collectors.toList());
    }

    public Optional<List<String>> allCountryNeighboursByID(int id) {
        return countries.stream()
                .filter(p -> p.id() == id)
                .map(Country::neighbours)
                .findAny();
    }
//- get countries in <continent> with population larger than <population> : /continents/<continentName>/countries?minPopulation=<minimum population> -> returns list of Country objects
public List<Country> getCountriesFromContinentByPopulation(String continent, long minPopulation) {
    return countries.stream()
            .filter(p -> p.continent().equalsIgnoreCase(continent))
            .filter(p -> p.population()>minPopulation)
            .collect(Collectors.toList());
}
//- get countries that neighbor X but not neighbor Y :
// /countries?includeNeighbour=<includedNeighbourCode>&excludeNeighbour=<excludedNeighbourCode>
// -> returns list of Country objects

}

