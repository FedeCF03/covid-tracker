package com.example.covidtracker.controller;

import com.example.covidtracker.models.LocationStats;
import com.example.covidtracker.service.CoronaVirusService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.*;

@RestController
public class HomeController     {
@Autowired
    CoronaVirusService coronaVirusService;


@GetMapping("/{country}")
public  List<LocationStats> getCase(@PathVariable(value = "country") String country )  {


    List<LocationStats> allStats  = coronaVirusService.getAllStats();

LocationStats getter = new LocationStats();
getter.setCountry(country);
for (LocationStats stats : allStats) {

    if (stats.getCountry().equals(getter.getCountry())) {
       getter.setTotalCases(stats.getTotalCases());
        getter.setDiffPrevDay(stats.getDiffPrevDay());
        getter.setState(stats.getState());
    }
}

return Collections.singletonList(getter);

}

    @GetMapping("/")
    public  List<LocationStats> getAllCases( ) {


        return coronaVirusService.getAllStats();



    }
}

