package com.ktr.offer.domain.user.model;

import com.fasterxml.jackson.annotation.JsonValue;

/**
 * @author Tahar Kerdoud
 */
public enum Gender {

    M("Male", "M"), F("Female", "F");

    String label;
    String shortLabel;

    Gender(String label, String shortLabel) {
        this.label = label;
        this.shortLabel = shortLabel;
    }

    /**
     * @return String
     * @apiNote  only for JSON use, return label for static value
     */
    @JsonValue
    public String getLable() {
        return label;
    }


}
