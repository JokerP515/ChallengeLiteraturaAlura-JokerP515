package com.alurachallenge.literatura.jokerp515.challenge_literatura_jokerp515.service;

import com.alurachallenge.literatura.jokerp515.challenge_literatura_jokerp515.repository.AuthorRepository;
import com.alurachallenge.literatura.jokerp515.challenge_literatura_jokerp515.repository.BookRepository;
import com.alurachallenge.literatura.jokerp515.challenge_literatura_jokerp515.model.Author;
import com.alurachallenge.literatura.jokerp515.challenge_literatura_jokerp515.model.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class BookService {

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private AuthorRepository authorRepository;

    public void saveBook(Book book) {
        Author author = book.getAuthor();
        Optional<Author> existingAuthor = authorRepository.findByNameAndBirthYearAndDeathYear(
                author.getName(), author.getBirthYear(), author.getDeathYear());

        if (existingAuthor.isPresent()) {
            book.setAuthor(existingAuthor.get());
        } else {
            authorRepository.save(author);
        }

        if (bookRepository.findByTitle(book.getTitle()).isEmpty()) {
            bookRepository.save(book);
        } else {
            System.out.println("El libro ya existe en la base de datos");
            // Get the book and print it
            //System.out.println(bookRepository.findByTitle(book.getTitle()).get());
        }
    }
}