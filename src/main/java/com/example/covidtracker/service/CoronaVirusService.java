package com.example.covidtracker.service;
import  com.example.covidtracker.models.LocationStats;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.io.StringReader;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.*;

@Service
public class CoronaVirusService {
    private static String VIRUS_DATA_URL = "https://raw.githubusercontent.com/CSSEGISandData/COVID-19/master/csse_covid_19_data/csse_covid_19_time_series/time_series_covid19_confirmed_global.csv";
    private List<LocationStats> allStats = new ArrayList<>();
    public List<LocationStats> getAllStats() {
        return allStats;
    }


    @PostConstruct
    @Scheduled(cron = "1 * * * * *")
    public void fetchVirusData() throws IOException , InterruptedException {
        List<LocationStats> newStats = new ArrayList<>();
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(VIRUS_DATA_URL))
                .build();
        HttpResponse<String> httpResponse = client.send(request, HttpResponse.BodyHandlers.ofString());
        StringReader csvBodyReader = new StringReader(httpResponse.body());
        Iterable<CSVRecord> records = CSVFormat.DEFAULT.withFirstRecordAsHeader().parse(csvBodyReader);
        for(CSVRecord resultRecord : records){
            LocationStats locationStat = new LocationStats();
            locationStat.setState(resultRecord.get("Province/State"));
            locationStat.setCountry(resultRecord.get("Country/Region"));
            int latestCases = Integer.parseInt(resultRecord.get(resultRecord.size() - 1));
            int prevDayCases = Integer.parseInt(resultRecord.get(resultRecord.size() - 2));
            locationStat.setTotalCases(latestCases);
            locationStat.setDiffPrevDay(latestCases - prevDayCases);
            newStats.add(locationStat);


        }
          this.allStats = newStats;



    }
}