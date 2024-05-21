package com.example.demo.service;

import com.example.demo.models.Author;
import com.example.demo.models.AuthorALL;
import com.example.demo.models.AuthorInfo;

import java.util.List;

public interface AuthorService {

    int createAuthor(Author author);
    List<Author> readAllAuthor();
    AuthorALL readAuthor(int id);
    boolean updateAuthor(Author author, int id);
    boolean deleteAuthor(int id);

    int createAuthorInfo(AuthorInfo author);
    List<AuthorInfo> readAllAuthorInfo();

    boolean updateAuthorInfo(AuthorInfo author, int id);
    boolean deleteAuthorInfo(int id);

    List<AuthorALL> readAll();
}
