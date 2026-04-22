package org.example;

import org.example.client.Contact;
import org.example.factory.SpainContactFactory;
import org.example.factory.USAContactFactory;
import org.example.product.*;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class ContactFactoryTest {

    @Test
    void spainFactoryShouldCreateSpanishAddressAndPhone() {
        SpainContactFactory factory =
                new SpainContactFactory("Calle Mayor 1", "Madrid", "28013", "612345678");

        assertThat(factory.createAddress())
                .isInstanceOf(SpainAddress.class)
                .extracting(Address::getAddress)
                .isEqualTo("Calle Mayor 1, 28013 Madrid");

        assertThat(factory.createPhone())
                .isInstanceOf(SpainPhone.class)
                .extracting(Phone::getNumber)
                .isEqualTo("+34 612 34 56 78");
    }

    @Test
    void usaFactoryShouldCreateAmericanAddressAndPhone() {
        USAContactFactory factory =
                new USAContactFactory("5th Avenue 350", "New York", "10018", "2125557890");

        assertThat(factory.createAddress())
                .isInstanceOf(USAAddress.class)
                .extracting(Address::getAddress)
                .isEqualTo("5th Avenue 350, New York, 10018");

        assertThat(factory.createPhone())
                .isInstanceOf(USAPhone.class)
                .extracting(Phone::getNumber)
                .isEqualTo("+1 (212) 555-7890");
    }

    @Test
    void contactShouldComposeFormattedSpanishData() {
        Contact contact = new Contact(
                new SpainContactFactory("Gran Via 10", "Barcelona", "08001", "600112233")
        );

        assertThat(contact.toString())
                .isEqualTo("Address: Gran Via 10, 08001 Barcelona | Phone: +34 600 11 22 33");
    }

    @Test
    void contactShouldComposeFormattedUsaData() {
        Contact contact = new Contact(
                new USAContactFactory("5th Avenue 350", "New York", "10018", "2125557890")
        );

        assertThat(contact.toString())
                .isEqualTo("Address: 5th Avenue 350, New York, 10018 | Phone: +1 (212) 555-7890");
    }

    @Test
    void phonesShouldFallbackToDigitsWhenLengthIsUnexpected() {
        assertThat(new SpainPhone("12-34").getNumber()).isEqualTo("+34 1234");
        assertThat(new USAPhone("555-12").getNumber()).isEqualTo("+1 55512");
    }
}
