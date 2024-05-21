package com.example.demo.models;

import javax.xml.crypto.Data;

public class AuthorInfo {
    private Integer  id;
    private Integer books_count;
    private String country;
    private String education;
    private String language;
    private Integer author_id;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }



    public Integer getBooks_count() {
        return books_count;
    }

    public void setBooks_count(Integer books_count) {
        this.books_count = books_count;
    }


    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getEducation() {
        return education;
    }

    public void setEducation(String education) {
        this.education = education;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public Integer getAuthor_id() {
        return author_id;
    }

    public void setAuthor_id(Integer author_id) {
        this.author_id = author_id;
    }
}
