package org.example.factory;

import org.example.product.Address;
import org.example.product.Phone;

public interface ContactFactory {
    Address createAddress();
    Phone createPhone();
}
