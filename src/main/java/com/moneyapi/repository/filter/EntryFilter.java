package com.moneyapi.repository.filter;

import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

public class EntryFilter {

    private String description;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate valueDateIn;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate valueDateUntil;

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDate getValueDateIn() {
        return valueDateIn;
    }

    public void setValueDateIn(LocalDate valueDateIn) {
        this.valueDateIn = valueDateIn;
    }

    public LocalDate getValueDateUntil() {
        return valueDateUntil;
    }

    public void setValueDateUntil(LocalDate valueDateUntil) {
        this.valueDateUntil = valueDateUntil;
    }
}
