package com.example.mega_city_cab.model;

public class Help {
    private int helpID;
    private String guideline;

    // Default constructor
    public Help() {}

    // Parameterized constructor
    public Help(int helpID, String guideline) {
        this.helpID = helpID;
        this.guideline = guideline;
    }

    // Getters and Setters
    public int getHelpID() {
        return helpID;
    }

    public void setHelpID(int helpID) {
        this.helpID = helpID;
    }

    public String getGuideline() {
        return guideline;
    }

    public void setGuideline(String guideline) {
        this.guideline = guideline;
    }

    @Override
    public String toString() {
        return "Help{" +
                "helpID=" + helpID +
                ", guideline='" + guideline + '\'' +
                '}';
    }
}