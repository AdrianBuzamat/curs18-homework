package ro.fasttrackit.curs18;

public class Main {
    public static void main(String[] args) {
        CountryReader countryReader=new CountryReader();
        CountryService countryService=new CountryService(countryReader);
      //  System.out.println(countryService.getCountries());
       // System.out.println(countryService.getCapitalByID(1));
        //System.out.println(countryService.getPopulationByID(1));
        //System.out.println(countryService.getCountriesByContinent("asia"));
        //System.out.println(countryService.allCountryNeighboursByID(1));
        //System.out.println(countryService.getCountriesFromContinentByPopulation("asia", 59700700));
        //System.out.println(countryService.allCountriesName());
    }
}
