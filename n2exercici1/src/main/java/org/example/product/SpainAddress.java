package org.example.product;

public class SpainAddress implements Address{

    private final String street;
    private final String postalCode;
    private final String city;

    public SpainAddress(String street, String postalCode, String city) {
        this.street = street;
        this.postalCode = postalCode;
        this.city = city;
    }

    @Override
    public String getAddress() {
        return street + ", " + postalCode + " " + city;
    }
}
