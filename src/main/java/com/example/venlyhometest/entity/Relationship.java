package com.example.venlyhometest.entity;

import com.example.venlyhometest.dto.RelationshipType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.RequiredArgsConstructor;

@Entity
public class Relationship {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    public String getW1() {
        return w1;
    }

    public String getW2() {
        return w2;
    }

    public RelationshipType getRelationshipType() {
        return relationshipType;
    }

    private  String w1;
    private  String w2;
    private  RelationshipType relationshipType;

    public Relationship() {

    }

    public Relationship(String w1, String w2, RelationshipType type) {
        this.w1 = w1;
        this.w2 = w2;
        this.relationshipType = type;
    }
}
