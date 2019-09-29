package model;

import storage.LocalStorage;

public class PickUpPoint {

    private int id;
    private User jumper;
    private int tripId;
    private String departureAddress; // afgangsadresse
    private String arrivalAddress; // ankomstadresse
    private String price;
    private double km;

    public PickUpPoint(User jumper, int tripId, String departureAddress, String arrivalAddress, String price,
            double km) {
        this.id = generateUniqueID();
        this.jumper = jumper;
        this.tripId = tripId;
        this.departureAddress = departureAddress;
        this.arrivalAddress = arrivalAddress;
        this.price = price;
        this.km = km;
    }

    public int generateUniqueID() {
        int id = 0;
        int numberOfPickUpPointsInTrip = LocalStorage.getPickUpPoints().size();

        if (numberOfPickUpPointsInTrip == 0) {
            id = 1;
        } else {
            id = (numberOfPickUpPointsInTrip + 1);
        }
        return id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public User getJumper() {
        return jumper;
    }

    public void setJumper(User jumper) {
        this.jumper = jumper;
    }

    public int getTripId() {
        return tripId;
    }

    public void setTripId(int tripId) {
        this.tripId = tripId;
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

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public double getKm() {
        return km;
    }

    public void setKm(double km) {
        this.km = km;
    }

}
