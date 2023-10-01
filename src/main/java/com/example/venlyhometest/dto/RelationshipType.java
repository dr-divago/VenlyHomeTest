package com.example.venlyhometest.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public enum RelationshipType {

    @JsonProperty("synonym")
    SYNONYM,
    ANTONYM,
    RELATED
}
