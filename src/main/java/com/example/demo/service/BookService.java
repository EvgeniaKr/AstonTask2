package com.example.demo.service;

import com.example.demo.models.*;

import java.util.List;

public interface BookService {

    int createBook(Book book);
    List<BookALL> readAllBook();
    boolean updateBook(Book book, int id);
    boolean deleteBook(int id);

}
