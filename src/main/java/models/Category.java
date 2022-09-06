package models;

import java.util.UUID;

public class Category {
    private final long id;
    private final String name;

    public Category(long id, String name) {
        this.id = id;
        this.name = name;
    }

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }
}