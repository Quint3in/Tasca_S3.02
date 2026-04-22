package org.example.product;

public class USAAddress implements Address{
    private final String street;
    private final String postalCode;
    private final String city;

    public USAAddress(String street, String postalCode, String city) {
        this.street = street;
        this.postalCode = postalCode;
        this.city = city;
    }

    @Override
    public String getAddress() {
        return street + ", " + city + ", " + postalCode;
    }
}
