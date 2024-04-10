package com.louisptc.AbsenceManager.Model;

public class Promotion {

    private int id;
    private String name;
    private int year;

    public Promotion(int id, String name, int year) {
        this.id = id;
        this.name = name;
        this.year = year;
    }

    public Promotion(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }
    
}
