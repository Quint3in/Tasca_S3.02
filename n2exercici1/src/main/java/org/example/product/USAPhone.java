package org.example.product;

public class USAPhone implements Phone{

    private final String number;

    public USAPhone(String number) {
        this.number = number;
    }

    @Override
    public String getNumber() {
        String digits = number.replaceAll("\\D", "");

        if (digits.length() != 10) {
            return "+1 "+digits;
        }

        return String.format(
                "+1 (%s) %s-%s",
                digits.substring(0, 3),
                digits.substring(3, 6),
                digits.substring(6, 10)
        );
    }
}
