package com.cidenet.domain.enums;

public enum Country {
    COLOMBIA("cidenet.com.co"),
    ESTADOS_UNIDOS("cidenet.com.us");

    private final String domain;

    Country(String domain) {
        this.domain = domain;
    }

    public String getDomain() {
        return domain;
    }
}


