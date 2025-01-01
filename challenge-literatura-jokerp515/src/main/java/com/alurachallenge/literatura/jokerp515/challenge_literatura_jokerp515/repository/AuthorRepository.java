package com.alurachallenge.literatura.jokerp515.challenge_literatura_jokerp515.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.alurachallenge.literatura.jokerp515.challenge_literatura_jokerp515.model.Author;

@Repository
public interface AuthorRepository extends JpaRepository<Author, Long> {

    List<Author> findByBirthYearLessThanAndDeathYearGreaterThan(int year, int year2);

    Optional<Author> findByNameAndBirthYearAndDeathYear(String name, Integer birthYear, Integer deathYear);
}
