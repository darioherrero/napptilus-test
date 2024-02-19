package com.napptilus.price.domain.type;

public enum Currency {
    EUR("€"), UNKNOWN("");

    private final String description;

    Currency(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

    public static String getDescription(Currency currencyType) {
        return currencyType.getDescription();
    }
}