package com.example.demo.controller;

import com.example.demo.models.*;
import com.example.demo.service.AuthorService;
import com.example.demo.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class AuthorController {
    private final AuthorService authorService;
    private final BookService bookService;

    @Autowired
    JdbcTemplate jdbcTemplate;
    @Autowired
    public AuthorController(AuthorService authorService, BookService bookService) {
        this.authorService = authorService;
        this.bookService = bookService;

    }

    @PostMapping(value = "/authorInfo")
    public ResponseEntity<?> create(@RequestBody AuthorInfo authorInfo) {
        authorService.createAuthorInfo(authorInfo);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }
    @PostMapping(value = "/author")
    public ResponseEntity<?> create(@RequestBody Author author) {
        authorService.createAuthor(author);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }
    @GetMapping(value = "/authorAll")
    public ResponseEntity<List<Author>> read() {
        final List<Author> clients = authorService.readAllAuthor();

        return clients != null &&  !clients.isEmpty()
                ? new ResponseEntity<>(clients, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
    @GetMapping(value = "/authorInfoAll")
    public ResponseEntity<List<AuthorInfo>> readAuthorInfo() {
        final List<AuthorInfo> clients = authorService.readAllAuthorInfo();

        return clients != null &&  !clients.isEmpty()
                ? new ResponseEntity<>(clients, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
    @GetMapping(value = "/All")
    public ResponseEntity<List<AuthorALL>> readAuthorAll() {
        final List<AuthorALL> clients = authorService.readAll();

        return clients != null &&  !clients.isEmpty()
                ? new ResponseEntity<>(clients, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping(value = "/read/{id}")
    public ResponseEntity<AuthorALL> readAuthor(@PathVariable(name = "id") int id) {
        final AuthorALL clients = authorService.readAuthor(id);

        return clients != null
                ? new ResponseEntity<>(clients, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
    @PutMapping(value = "/authorAp/{id}")
    public ResponseEntity<?> update(@PathVariable(name = "id") int id, @RequestBody Author client) {
        final boolean updated = authorService.updateAuthor(client, id);

        return updated
                ? new ResponseEntity<>(HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_MODIFIED);
    }
    @DeleteMapping(value = "/authorDe/{id}")
    public ResponseEntity<?> delete(@PathVariable(name = "id") int id) {
        final boolean deleted = authorService.deleteAuthor(id);

        return deleted
                ? new ResponseEntity<>(HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_MODIFIED);
    }

    @PutMapping(value = "/authorInfoAp/{id}")
    public ResponseEntity<?> updateinf(@PathVariable(name = "id") int id, @RequestBody AuthorInfo client) {
        final boolean updated = authorService.updateAuthorInfo(client, id);

        return updated
                ? new ResponseEntity<>(HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_MODIFIED);
    }
    @DeleteMapping(value = "/authorInfoDe/{id}")
    public ResponseEntity<?> deleteinf(@PathVariable(name = "id") int id) {
        final boolean deleted = authorService.deleteAuthorInfo(id);

        return deleted
                ? new ResponseEntity<>(HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_MODIFIED);
    }




    @PostMapping(value = "/book")
    public ResponseEntity<?> createBook(@RequestBody Book book) {
        bookService.createBook(book);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @GetMapping(value = "/bookAll")
    public ResponseEntity<List<BookALL>> readBook() {
        final List<BookALL> clients = bookService.readAllBook();

        return clients != null &&  !clients.isEmpty()
                ? new ResponseEntity<>(clients, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }


    @PutMapping(value = "/bookAp/{id}")
    public ResponseEntity<?> updateBook(@PathVariable(name = "id") int id, @RequestBody Book book) {
        final boolean updated = bookService.updateBook(book, id);

        return updated
                ? new ResponseEntity<>(HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_MODIFIED);
    }
    @DeleteMapping(value = "/bookDe/{id}")
    public ResponseEntity<?> deleteBook(@PathVariable(name = "id") int id) {
        final boolean deleted = bookService.deleteBook(id);

        return deleted
                ? new ResponseEntity<>(HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_MODIFIED);
    }
}
