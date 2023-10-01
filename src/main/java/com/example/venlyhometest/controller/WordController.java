package com.example.venlyhometest.controller;


import com.example.venlyhometest.dto.RelationshipRequest;
import com.example.venlyhometest.dto.RelationshipResponseJson;
import com.example.venlyhometest.dto.RelationshipType;
import com.example.venlyhometest.entity.Relationship;
import com.example.venlyhometest.repo.RelationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


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

    @GetMapping("/get-words-relation")
    public ResponseEntity<List<RelationshipResponseJson>> getRelation(@RequestParam(name = "filterByRelation", required = false) String r) {
        RelationshipType searchRelation = RelationshipType.valueOf(Optional.ofNullable(r).orElse("none").toUpperCase());

        List<RelationshipResponseJson> relationship = relationRepository.findAll().stream()
                .filter( x -> searchRelation.equals(RelationshipType.NONE) || x.getRelationshipType().equals(searchRelation))
                .map( x -> new RelationshipResponseJson(x.getW1(), x.getW2(), x.getRelationshipType()))
                .collect(Collectors.toList());
        return ResponseEntity.ok(relationship);
    }
}
