package model;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;

public class Trip {
	private int id;
	private static int counter = 0;

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
        assignID();
    }
    public Trip(LocalDate date, LocalTime timeOfDeparture, LocalTime timeOfArrival, String departureAddress,
            String arrivalAddress, User driver) {
        this.date = date;
        this.timeOfDeparture = timeOfDeparture;
        this.timeOfArrival = timeOfArrival;
        this.departureAddress = departureAddress;
        this.arrivalAddress = arrivalAddress;
        this.driver = driver;
        assignID();
    }
    
    private void assignID() {
    	this.id = Trip.counter;
    	Trip.counter ++;
    }


    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public LocalTime getTimeOfDeparture() {
        return timeOfDeparture;
    }

    public void setTimeOfDeparture(LocalTime timeOfDeparture) {
        this.timeOfDeparture = timeOfDeparture;
    }

    public LocalTime getTimeOfArrival() {
        return timeOfArrival;
    }

    public void setTimeOfArrival(LocalTime timeOfArrival) {
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
    
    public int getID() {
    	return id;
    }

}
