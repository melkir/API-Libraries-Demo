package com.melkir.libraries.modules;

public enum ModulesType {
    COMPONENT("component"),
    GAME("game"),
    DESIGN("design"),
    ALL_CATEGORIES("all_categories");

    private final String value;

    ModulesType(String value) {
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