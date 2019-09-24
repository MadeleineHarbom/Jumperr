package model;

import java.util.ArrayList;

import storage.LocalStorage;

public class Trip {

    private int id;
    private String date;
    private String timeOfDeparture; // afgangstidspunkt
    private String timeOfArrival; // ankomsttidspunkt
    private String departureAddress; // afgangsadresse
    private String arrivalAddress; // ankomstadresse
    private User driver;
    private int availableSeats;
    private ArrayList<PickUpPoint> pickUpPoints = new ArrayList<>();

    public Trip(String date, String timeOfDeparture, String timeOfArrival, String departureAddress,
            String arrivalAddress, User driver, int availableSeats) {
        this.id = generateUniqueID();
        this.date = date;
        this.timeOfDeparture = timeOfDeparture;
        this.timeOfArrival = timeOfArrival;
        this.departureAddress = departureAddress;
        this.arrivalAddress = arrivalAddress;
        this.driver = driver;
        this.availableSeats = availableSeats;
    }

    public int generateUniqueID() {
        int id = 0;

        if (LocalStorage.getTrips().size() == 0) {
            id = 1;
        } else {
            int lastTripIndex = LocalStorage.getTrips().size() - 1;
            id = LocalStorage.getTrips().get(lastTripIndex).id + 1;
        }
        return id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public int getAvailableSeats() {
        return availableSeats;
    }

    public void setAvailableSeats(int availableSeats) {
        this.availableSeats = availableSeats;
    }

    public ArrayList<PickUpPoint> getPickUpPoints() {
        return pickUpPoints;
    }

    public void addPickUpPoint(PickUpPoint pickUpPoint) {
        this.pickUpPoints.add(pickUpPoint);
    }

    public void removePickUpPoint(PickUpPoint pickUpPoint) {
        this.pickUpPoints.remove(pickUpPoint);
    }

}
