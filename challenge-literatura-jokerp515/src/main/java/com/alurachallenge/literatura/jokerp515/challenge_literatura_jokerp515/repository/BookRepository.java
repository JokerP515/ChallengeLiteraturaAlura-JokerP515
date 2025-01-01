package com.alurachallenge.literatura.jokerp515.challenge_literatura_jokerp515.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.alurachallenge.literatura.jokerp515.challenge_literatura_jokerp515.model.Book;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {

    List<Book> findByLanguagesContaining(String language);

    Optional<Book> findByTitle(String title);

}
