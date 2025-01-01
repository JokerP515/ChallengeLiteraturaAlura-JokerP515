package com.alurachallenge.literatura.jokerp515.challenge_literatura_jokerp515.main;

import java.util.Scanner;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

import com.alurachallenge.literatura.jokerp515.challenge_literatura_jokerp515.model.Author;
import com.alurachallenge.literatura.jokerp515.challenge_literatura_jokerp515.model.Book;
import com.alurachallenge.literatura.jokerp515.challenge_literatura_jokerp515.model.JsonProcessor;
import com.alurachallenge.literatura.jokerp515.challenge_literatura_jokerp515.repository.AuthorRepository;
import com.alurachallenge.literatura.jokerp515.challenge_literatura_jokerp515.repository.BookRepository;
import com.alurachallenge.literatura.jokerp515.challenge_literatura_jokerp515.service.BookService;
import com.alurachallenge.literatura.jokerp515.challenge_literatura_jokerp515.service.ConsumptionAPI;

@Component
public class Main {
    private Scanner sc = new Scanner(System.in);
    private ConsumptionAPI consumptionAPI = new ConsumptionAPI();
    private final String URL = "https://gutendex.com/books?";
    private JsonProcessor jsonProcessor = new JsonProcessor();

    @Autowired
    private AuthorRepository authorRepository;
    @Autowired
    private BookRepository bookRepository;
    @Autowired
    private BookService bookService = new BookService();

    public void menu() {
        System.out.println("Bienvenido al sistema de literatura creado por Jokerp515\n");

        while (true) {
            System.out.println(
                    "Menú principal: \n1. Buscar libro por titulo\n2. Listar libros registrados\n3. Listar autores registrados\n4. Listar autores vivos en un determinado año\n5. Listar libros por idioma\n0. Salir");
            System.out.print("Ingrese la opción deseada: ");
            int option = sc.nextInt();
            sc.nextLine();
            switch (option) {
                case 1:
                    buscarLibroPorTitulo();
                    break;
                case 2:
                    listarLibrosRegistrados();
                    break;
                case 3:
                    listarAutoresRegistrados();
                    break;
                case 4:
                    listarAutoresVivos();
                    break;
                case 5:
                    listarLibrosPorIdioma();
                    break;
                case 0:
                    System.out.println("Gracias por usar el programa");
                    sc.close();
                    System.exit(0);
                    break;
                default:
                    System.out.println("Opción no válida, inténtalo de nuevo");
                    break;
            }
            System.out.println("");
        }
    }

    // Temp fix
    private String correccionURL(String search) {
        if (search.contains(" ")) {
            search = search.replaceAll(" ", "%20");
        }
        return search;
    }

    private void buscarLibroPorTitulo() {
        System.out.print("Ingrese el titulo del libro a buscar: ");
        String title = sc.nextLine();
        String jsonResponse = consumptionAPI.getData(URL + "search=" + correccionURL(title));
        // System.out.println(jsonResponse);
        Book book = jsonProcessor.processFirstBook(jsonResponse);
        // Save the book and print it
        if(book != null){
            System.out.println(book);
            bookService.saveBook(book);
        }else System.out.println("Libro no encontrado");
    }

    private void listarLibrosRegistrados() {
        System.out.println("Listado de libros registrados:\n");
        List<Book> books = bookRepository.findAll();
        if(books.isEmpty()){
            System.out.println("No hay libros registrados");
        }else{
            books.forEach(System.out::println);
        }
    }

    private void listarAutoresRegistrados() {
        System.out.println("Listado de autores registrados:\n");
        List<Author> authors = authorRepository.findAll();
        if(authors.isEmpty()){
            System.out.println("No hay autores registrados");
        }else{
            authors.forEach(System.out::println);
        }
    }

    private void listarAutoresVivos() {
        System.out.print("Ingrese el año a buscar: ");
        int year = sc.nextInt();
        List<Author> authors = authorRepository.findByBirthYearLessThanAndDeathYearGreaterThan(year, year);
        if(authors.isEmpty()){
            System.out.println("No hay autores vivos en el año "+year);
        }else{
            authors.forEach(System.out::println);
        }
    }

    private void listarLibrosPorIdioma() {
        System.out.print("Ingrese el idioma a buscar: ");
        String language = sc.nextLine();
        List<Book> books = bookRepository.findByLanguagesContaining(language);
        if(books.isEmpty()){
            System.out.println("No hay libros en el idioma "+language);
        }else{
            books.forEach(System.out::println);
        }
    }

}
