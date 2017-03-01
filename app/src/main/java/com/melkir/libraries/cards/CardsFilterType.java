package com.melkir.libraries.cards;

public enum CardsFilterType {
    COMPONENT("component"),
    GAME("game"),
    DESIGN("design"),
    ALL_CATEGORIES("all_categories");

    private final String value;

    CardsFilterType(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    @Override
    public String toString() {
        return this.getValue();
    }
}