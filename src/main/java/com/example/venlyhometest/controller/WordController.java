package com.example.venlyhometest.controller;


import com.example.venlyhometest.dto.RelationshipRequest;
import com.example.venlyhometest.dto.RelationshipResponseJson;
import com.example.venlyhometest.dto.RelationshipType;
import com.example.venlyhometest.entity.Relationship;
import com.example.venlyhometest.repo.RelationRepository;
import com.example.venlyhometest.service.WordService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;


@RestController
@RequestMapping("/api/relationship")
@RequiredArgsConstructor
public class WordController {

    private final WordService wordService;
    @PostMapping("/add")
    public ResponseEntity<Void> addRelation(@Valid @RequestBody  RelationshipRequest request) {
        wordService.addRelation(request);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @GetMapping("/get-words-relation")
    public ResponseEntity<List<RelationshipResponseJson>> getRelation(
            @RequestParam(name = "filterByRelation", required = false) String r,
            @RequestParam(name = "inverse", required = false) Boolean inverse) {
        Optional<Boolean> maybeInverse = Optional.ofNullable(inverse);
        RelationshipType searchRelation = RelationshipType.valueOf(Optional.ofNullable(r).orElse("none").toUpperCase());

        List<RelationshipResponseJson> relationship = wordService.getRelationshipResponseJsons(maybeInverse, searchRelation);

        return ResponseEntity.ok(relationship);
    }


}
