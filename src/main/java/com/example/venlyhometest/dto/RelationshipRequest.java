package com.example.venlyhometest.dto;

import javax.validation.constraints.NotNull;

public class RelationshipRequest {
    @NotNull(message = "w1 can't be null")
    private String w1;
    @NotNull(message = "w2 can't be null")
    private String w2;
    @NotNull(message = "type can't be null")
    private RelationshipType type;

    public String getW1() {
        return w1;
    }

    public String getW2() {
        return w2;
    }

    public RelationshipType getType() {
        return type;
    }

    public RelationshipRequest(String w1, String w2, RelationshipType type) {
        this.w1 = w1;
        this.w2 = w2;
        this.type = type;
    }
}
