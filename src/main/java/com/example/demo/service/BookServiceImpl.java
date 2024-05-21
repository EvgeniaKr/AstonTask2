package com.example.demo.service;

import com.example.demo.models.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class BookServiceImpl implements BookService{

    private final String INSERT_BOOK_QUERY = "INSERT INTO book(id,name,author_id ) VALUES(?,?,?)";
    private final String UPDATE_BOOK_QUERY = "UPDATE book SET id=?,name=?,author_id=? WHERE id=?";
    private final String DELETE_BOOK_QUERY = "DELETE FROM book WHERE id=?";
    private final String SELECT_BOOK_QUERY = "SELECT * FROM book";

    private final String SELECT_AV = "SELECT * FROM attrubute_value";
    private final String SELECT_ATTRIBUTE = "SELECT * FROM attribute";
    private final String SELECT_VALUE = "SELECT * FROM value";


    @Autowired
    JdbcTemplate jdbcTemplate;

    @Override
    public int createBook(Book book) {
        return jdbcTemplate.update(INSERT_BOOK_QUERY, new Object[] { book.getId(), book.getName(), book.getAuthor_id()});

    }

    @Override
    public List<BookALL> readAllBook() {
        List<BookALL> all = new ArrayList<>();
        List<Book> books = jdbcTemplate.query(SELECT_BOOK_QUERY, new BeanPropertyRowMapper<Book>(Book.class));
        List<AV> av = jdbcTemplate.query(SELECT_AV, new BeanPropertyRowMapper<AV>(AV.class));
        List<Attribute> attributes = jdbcTemplate.query(SELECT_ATTRIBUTE, new BeanPropertyRowMapper<Attribute>(Attribute.class));
        List<Value> values = jdbcTemplate.query(SELECT_VALUE, new BeanPropertyRowMapper<Value>(Value.class));

        for (Book element : books){
            BookALL bookALL = new BookALL();
            bookALL.setName(element.getName());
            List<MapBook> mapBooks = new ArrayList<>();

            for (AV elementav : av){
                MapBook mapBook = new MapBook();
                mapBook.setAttribute(attributes.get(elementav.getAttribute_id()-1).getName());
                mapBook.setValue(values.get(elementav.getValue_id()-1).getName());
                mapBooks.add(mapBook);
            }
            bookALL.setList(mapBooks);
            all.add(bookALL);
        }

        return all;
    }

    @Override
    public boolean updateBook(Book book, int id) {
        jdbcTemplate.update(UPDATE_BOOK_QUERY, new Object[] { book.getId(), book.getName(), book.getAuthor_id(),id });
        return true;
    }

    @Override
    public boolean deleteBook(int id) {
        jdbcTemplate.update(DELETE_BOOK_QUERY, new Object[] { id });
        return true;
    }
}
