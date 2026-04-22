package org.example;

import org.example.client.Contact;
import org.example.factory.ContactFactory;
import org.example.factory.SpainContactFactory;
import org.example.factory.USAContactFactory;

public class Main {
    public static void main(String[] args) {

        // Spain
        ContactFactory spainFactory = new SpainContactFactory(
                "Carrer Major 34", "Barcelona", "08001", "123456789"
        );
        Contact contactSpain = new Contact(spainFactory);
        System.out.println(contactSpain.toString());
        //OUTPUT: Address: Carrer Major 34, 08001 Barcelona | Phone: +34 623 45 67 89

        // USA
        ContactFactory usaFactory = new USAContactFactory("154 5th Avenue", "New York", "NY 10001", "0987654321");
        Contact contactUSA = new Contact(usaFactory);
        System.out.println(contactUSA.toString());
        //OUTPUT: Address: 154 5th Avenue, New York, NY 10001 | Phone: +1 (098) 765-4321
    }
}