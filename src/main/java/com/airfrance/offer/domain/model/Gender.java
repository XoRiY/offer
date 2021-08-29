package com.airfrance.offer.domain.model;

import com.fasterxml.jackson.annotation.JsonValue;

public enum Gender {

    M("Male", "M"), F("Female", "F");

    String label;
    String shortLabel;

    Gender(String label, String shortLabel) {
        this.label = label;
        this.shortLabel = shortLabel;
    }

    @JsonValue
    public String getLable() {
        return label;
    }


}
