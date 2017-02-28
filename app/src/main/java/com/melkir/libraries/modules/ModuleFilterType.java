package com.melkir.libraries.modules;

public enum ModuleFilterType {
    COMPONENT("component"),
    GAME("game"),
    DESIGN("design"),
    ALL_CATEGORIES("all_categories");

    private String value;

    ModuleFilterType(String value) {
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