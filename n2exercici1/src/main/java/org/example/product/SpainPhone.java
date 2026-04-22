package org.example.product;

public class SpainPhone implements Phone{
    private final String number;

    public SpainPhone(String number) {
        this.number = number;
    }

    @Override
    public String getNumber() {
        String digits = number.replaceAll("\\D", "");

        if (digits.length() != 9) {
            return "+34 " + digits;
        }

        return String.format(
                "+34 %s %s %s %s",
                digits.substring(0, 3),
                digits.substring(3, 5),
                digits.substring(5, 7),
                digits.substring(7, 9)
        );
    }
}
