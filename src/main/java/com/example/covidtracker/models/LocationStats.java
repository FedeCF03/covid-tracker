package com.example.covidtracker.models;


public class LocationStats{
private String state;
private String country;
private int totalCases;
private int diffPrevDay;

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public int getTotalCases() {
        return totalCases;
    }

    public void setTotalCases(int totalCases) {
        this.totalCases = totalCases;
    }

    public int getDiffPrevDay() {
        return diffPrevDay;
    }

    public void setDiffPrevDay(int diffPrevDay) {
        this.diffPrevDay = diffPrevDay;
    }

    @Override
    public String toString() {
        return "LocationStats{" +
                "state='" + state + '\'' +
                ", country='" + country + '\'' +
                ", totalCases=" + totalCases +
                ", diffPrevDay=" + diffPrevDay +
                '}';
    }


    public LocationStats(String country, int totalCases, int diffPrevDay) {
        this.country = country;
        this.totalCases = totalCases;
        this.diffPrevDay = diffPrevDay;
    } public LocationStats() {

    }
}
