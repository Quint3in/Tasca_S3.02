package org.example.client;

import org.example.factory.ContactFactory;
import org.example.product.Address;
import org.example.product.Phone;

public class Contact {
    private final Address address;
    private final Phone phone;

    public Contact(ContactFactory contactFactory) {
        address = contactFactory.createAddress();
        phone = contactFactory.createPhone();
    }

    @Override
    public String toString() {
        return "Address: " + address.getAddress() +" | Phone: "+ phone.getNumber();
    }
}
