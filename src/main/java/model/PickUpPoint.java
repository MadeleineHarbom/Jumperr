package model;

public class PickUpPoint {

    private User jumper;
    private String departureAddress; // afgangsadresse
    private String arrivalAddress; // ankomstadresse
    private String price;
    private double km;

    public PickUpPoint(User jumper, String departureAddress, String arrivalAddress, String price, double km) {
        this.jumper = jumper;
        this.departureAddress = departureAddress;
        this.arrivalAddress = arrivalAddress;
        this.price = price;
        this.km = km;
    }

    public User getJumper() {
        return jumper;
    }

    public void setJumper(User jumper) {
        this.jumper = jumper;
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
