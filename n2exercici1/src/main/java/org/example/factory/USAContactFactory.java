package org.example.factory;

import org.example.product.Address;
import org.example.product.Phone;
import org.example.product.USAAddress;
import org.example.product.USAPhone;

public class USAContactFactory implements ContactFactory{
    private final String street;
    private final String city;
    private final String postalCode;
    private final String number;

    public USAContactFactory(String street, String city, String postalCode, String number) {
        this.street = street;
        this.city = city;
        this.postalCode = postalCode;
        this.number = number;
    }

    @Override
    public Address createAddress() {
        return new USAAddress(street,postalCode,city);
    }

    @Override
    public Phone createPhone() {
        return new USAPhone(number);
    }
}
