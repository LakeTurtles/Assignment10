package com.ultima.Senorial.dto;
import com.fasterxml.jackson.annotation.JsonProperty;

public class MainResponse {
    @JsonProperty("week")
    private Week week;


    public Week getWeek() {
        return week;
    }

    public void setWeek(Week week) {
        this.week = week;
    }
}
