package org.models;

public enum Category {
    Wearable("w"),Eatable("e"),Drinks("d");

    private String name;

    Category(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
