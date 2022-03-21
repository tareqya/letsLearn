package com.dima.letslearn;

public class Scholarship {
    private String name;
    private String description;
    private boolean toggle;

    public Scholarship(String name, String description) {
        this.name = name;
        this.description = description;
    }

    public Scholarship() {
    }

    public String getName() {
        return name;
    }

    public Scholarship setName(String name) {
        this.name = name;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public Scholarship setDescription(String description) {
        this.description = description;
        return this;
    }
    public boolean isToggle() {
        return toggle;
    }

    public Scholarship setToggle(boolean toggle) {
        this.toggle = toggle;
        return this;
    }
}
