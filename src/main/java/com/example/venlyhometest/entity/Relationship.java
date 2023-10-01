package com.example.venlyhometest.entity;

import com.example.venlyhometest.dto.RelationshipType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.RequiredArgsConstructor;

@Entity
@RequiredArgsConstructor
public class Relationship {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private final String w1;
    private final String w2;
    private final RelationshipType relationshipType;
}
