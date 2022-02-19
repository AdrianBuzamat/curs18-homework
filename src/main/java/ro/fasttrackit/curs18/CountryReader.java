package ro.fasttrackit.curs18;


import org.springframework.stereotype.Component;


import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Component
public class CountryReader {
    private FileReader file;

    public CountryReader() {
        try {
            String filePath = "src/main/resources/static/countries.txt";
            this.file = new FileReader(filePath);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public List<Country> readCountries() {
        BufferedReader myReader;
        List<Country> result = new ArrayList<>();
        try {
            myReader = new BufferedReader(file);
            String line;
            int count = 1;
            while ((line = myReader.readLine()) != null) {
                result.add(readCountryLine(count++, line));
            }
        } catch (IOException e) {
            System.out.println("File not found");
        }
        return result;
    }

    private Country readCountryLine(int id, String line) {
        String[] countryData = line.split("[\\|~]");
        String name = countryData[0];
        String capital = countryData[1];
        long population = Long.parseLong(countryData[2]);
        long area = Long.parseLong(countryData[3]);
        String continent = countryData[4];
        List<String> neighbours = IntStream
                .range(5, countryData.length)
                .mapToObj(i -> countryData[i])
                .collect(Collectors.toList());

        return new Country(id, name, capital, population, area, continent, neighbours);
    }
}
