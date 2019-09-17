package model;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;

public class Trip {

    private LocalDate date;
    private LocalTime timeOfDeparture; // afgangstidspunkt
    private LocalTime timeOfArrival; // ankomsttidspunkt
    private String departureAddress; // afgangsadresse
    private String arrivalAddress; // ankomstadresse
    private User driver;
    private ArrayList<PickUpPoint> pickUpPoints = new ArrayList<>();

    public Trip(LocalDate date, LocalTime timeOfDeparture, LocalTime timeOfArrival, String departureAddress,
            String arrivalAddress, User driver, ArrayList<PickUpPoint> pickUpPoints) {
        this.date = date;
        this.timeOfDeparture = timeOfDeparture;
        this.timeOfArrival = timeOfArrival;
        this.departureAddress = departureAddress;
        this.arrivalAddress = arrivalAddress;
        this.driver = driver;
        this.pickUpPoints = pickUpPoints;
    }
    public Trip(LocalDate date, LocalTime timeOfDeparture, LocalTime timeOfArrival, String departureAddress,
            String arrivalAddress, User driver) {
        this.date = date;
        this.timeOfDeparture = timeOfDeparture;
        this.timeOfArrival = timeOfArrival;
        this.departureAddress = departureAddress;
        this.arrivalAddress = arrivalAddress;
        this.driver = driver;
        
    }


    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTimeOfDeparture() {
        return timeOfDeparture;
    }

    public void setTimeOfDeparture(String timeOfDeparture) {
        this.timeOfDeparture = timeOfDeparture;
    }

    public String getTimeOfArrival() {
        return timeOfArrival;
    }

    public void setTimeOfArrival(String timeOfArrival) {
        this.timeOfArrival = timeOfArrival;
    }

    public String getDepartureAddress() {
        return departureAddress;
    }

    public void setDepartureAddress(String departureAddress) {
        this.departureAddress = departureAddress;
    }

    public String getArrivalAddress() {
        return arrivalAddress;
    }

    public void setArrivalAddress(String arrivalAddress) {
        this.arrivalAddress = arrivalAddress;
    }

    public User getDriver() {
        return driver;
    }

    public void setDriver(User driver) {
        this.driver = driver;
    }

    public ArrayList<PickUpPoint> getPickUpPoints() {
        return pickUpPoints;
    }

    public void setPickUpPoints(ArrayList<PickUpPoint> pickUpPoints) {
        this.pickUpPoints = pickUpPoints;
    }

}
