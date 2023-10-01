package com.example.venlyhometest.repo;

import com.example.venlyhometest.entity.Relationship;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RelationRepository extends JpaRepository<Relationship, Long> {
}
