package com.example.venlyhometest.repo;

import com.example.venlyhometest.entity.Word;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WordRepository extends JpaRepository<Word, Long> {
}
