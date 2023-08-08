package com.astrotalk.hospital.enums;

public enum StaffDesignation {
    DOCTOR("DOCTOR","Doctor"),
    NURSING("NURSING","Nursing"),
    CLEANING("CLEANING","Cleaning"),
    OTHER("OTHER","Other");


    private final String code;
    private final String name;

    StaffDesignation(String code, String name) {
        this.code = code;
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public String getName() {
        return name;
    }
}
