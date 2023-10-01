package com.example.venlyhometest.controller;


import com.example.venlyhometest.dto.RelationshipRequest;
import com.example.venlyhometest.entity.Relationship;
import com.example.venlyhometest.repo.RelationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;


@RestController
@RequestMapping("/api/relationship")
@RequiredArgsConstructor
public class WordController {

    private final RelationRepository relationRepository;

    @PostMapping("/add")
    public ResponseEntity<Void> addRelation(@Valid @RequestBody  RelationshipRequest request) {
        Relationship relationship = new Relationship(request.getW1(), request.getW2(), request.getType());
        relationRepository.save(relationship);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }
}
