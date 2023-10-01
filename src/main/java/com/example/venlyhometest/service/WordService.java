package com.example.venlyhometest.service;

import com.example.venlyhometest.dto.RelationshipRequest;
import com.example.venlyhometest.dto.RelationshipResponseJson;
import com.example.venlyhometest.dto.RelationshipType;
import com.example.venlyhometest.entity.Relationship;
import com.example.venlyhometest.repo.RelationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class WordService {

    private final RelationRepository relationRepository;

    public List<RelationshipResponseJson> getRelationshipResponseJsons(Optional<Boolean> maybeInverse, RelationshipType searchRelation) {
        Predicate<RelationshipType> p = x -> searchRelation.equals(RelationshipType.NONE) || x.equals(searchRelation);
        List<RelationshipResponseJson> relationship = relationRepository.findAll().stream()
                .filter( x -> p.test(x.getRelationshipType()))
                .map( x -> new RelationshipResponseJson(x.getW1(), x.getW2(), x.getRelationshipType()))
                .collect(Collectors.toList());

        List<RelationshipResponseJson> relationshipTypesInverse = relationship.stream()
                .filter( x -> !maybeInverse.isEmpty())
                .map( x -> new RelationshipResponseJson(x.w2(), x.w1(), x.relationshipType()))
                .collect(Collectors.toList());

        relationship.addAll(relationshipTypesInverse);
        return relationship;
    }

    public void addRelation(final RelationshipRequest request) {
        Relationship relationship = new Relationship(request.getW1(), request.getW2(), request.getType());
        relationRepository.save(relationship);
    }
}
