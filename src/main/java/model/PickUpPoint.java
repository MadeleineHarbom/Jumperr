package model;

public class PickUpPoint {

    private String departureAddress; // afgangsadresse
    private String arrivalAddress; // ankomstadresse
    private String price;
    private double km;

    public PickUpPoint(String departureAddress, String arrivalAddress, double km) {
        this.departureAddress = departureAddress;
        this.arrivalAddress = arrivalAddress;
        this.km = km;
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
