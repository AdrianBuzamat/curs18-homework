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
                .findFirst()
                .map(Country::capital);
    }

    public Optional<Long> getPopulationByID(int id) {
        return countries.stream()
                .filter(p -> p.id() == id)
                .findFirst()
                .map(Country::population);
    }

    public List<Country> getCountriesByContinent(String continent) {
        return countries.stream()
                .filter(p -> p.continent().equalsIgnoreCase(continent))
                .collect(Collectors.toList());
    }

    public Optional<List<String>> allCountryNeighboursByID(int id) {
        return countries.stream()
                .filter(p -> p.id() == id)
                .findAny()
                .map(Country::neighbours);
    }

    public List<Country> getCountriesFromContinentByPopulation(String continent, long minPopulation) {
        return countries.stream()
                .filter(p -> p.continent().equalsIgnoreCase(continent))
                .filter(p -> p.population() > minPopulation)
                .collect(Collectors.toList());
    }

    public List<Country> getNeighboursBySelection(String includedNeighbour, String excludedNeighbour) {
        return countries.stream()
                .filter(p -> p.neighbours().contains(includedNeighbour))
                .filter(p -> !p.neighbours().contains(excludedNeighbour))
                .collect(Collectors.toList());
    }
}

