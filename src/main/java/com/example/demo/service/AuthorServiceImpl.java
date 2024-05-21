package com.example.demo.service;

import com.example.demo.models.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

@Service
public class AuthorServiceImpl implements AuthorService{

    private final String INSERT_AUTHOR_Info_QUERY = "INSERT INTO author_info(id,books_count,country,education,language,author_id) VALUES(?,?,?,?,?,?)";
    private final String INSERT_AUTHOR_QUERY = "INSERT INTO author(id,name, ) VALUES(?,?)";

    private final String SELECT_AUTHOR_QUERY = "SELECT * FROM author";
    private final String SELECT_AUTHOR = "SELECT * FROM author_info WHERE author_id = ?";
    private final String SELECT_AUTHOR_INFO = "SELECT * FROM author WHERE id = ?";

    private final String SELECT_AUTHOR_INFOF = "SELECT * FROM author_info WHERE id = ?";


    private final String UPDATE_AUTHOR_QUERY = "UPDATE author SET id=?,name=? WHERE id=?";
    private final String DELETE_AUTHOR_QUERY = "DELETE FROM author WHERE id=?";

    private final String UPDATE_AUTHOR_INFO_QUERY = "UPDATE author_info SET id=?,books_count=?,country=?,education=?,language,author_id=? WHERE id=?";
    private final String DELETE_AUTHOR_INFO_QUERY = "DELETE FROM author_info WHERE id=?";
    @Autowired
    JdbcTemplate jdbcTemplate;

    @Override
    public int createAuthor(Author author) {
        return jdbcTemplate.update(INSERT_AUTHOR_QUERY, new Object[] { author.getId(), author.getName()});
    }

    @Override
    public List<Author> readAllAuthor() {
        List<AuthorInfo> authorsinfo = new ArrayList<>();
        List<Author> authors = jdbcTemplate.query(SELECT_AUTHOR_QUERY, new BeanPropertyRowMapper<Author>(Author.class));
        for (Author element : authors) {
             authorsinfo.addAll(jdbcTemplate.query(SELECT_AUTHOR ,  new BeanPropertyRowMapper<AuthorInfo>(AuthorInfo.class), element.getId()));
        }

        return authors;
    }

    @Override
    public AuthorALL readAuthor(int id) {
        Author author = jdbcTemplate.queryForObject(SELECT_AUTHOR_INFO,
                BeanPropertyRowMapper.newInstance(Author.class), id);
        AuthorALL authorALL = new AuthorALL();
        authorALL.setName(author.getName());
        AuthorInfo info = jdbcTemplate.queryForObject(SELECT_AUTHOR_INFOF,
                BeanPropertyRowMapper.newInstance(AuthorInfo.class), id);
        authorALL.setLanguage(info.getLanguage());
        authorALL.setEducation(info.getEducation());
        authorALL.setCountry(info.getCountry());
        authorALL.setBooks_count(info.getBooks_count());
        return authorALL;
    }

    @Override
    public boolean updateAuthor(Author author, int id) {
        jdbcTemplate.update(UPDATE_AUTHOR_QUERY, new Object[] { author.getId(), author.getName(),id });
        return true;
    }

    @Override
    public boolean deleteAuthor(int id) {
         jdbcTemplate.update(DELETE_AUTHOR_QUERY, new Object[] { id });
        return true;
    }

    @Override
    public int createAuthorInfo(AuthorInfo author) {
        return jdbcTemplate.update(INSERT_AUTHOR_Info_QUERY, new Object[] { author.getId(), author.getBooks_count(), author.getCountry(), author.getEducation(), author.getLanguage(), author.getAuthor_id()});

    }

    @Override
    public List<AuthorInfo> readAllAuthorInfo() {
        List<AuthorInfo> authorsinfo = new ArrayList<>();
        List<Author> authors = jdbcTemplate.query(SELECT_AUTHOR_QUERY, new BeanPropertyRowMapper<Author>(Author.class));
        for (Author element : authors) {
            authorsinfo.addAll(jdbcTemplate.query(SELECT_AUTHOR ,  new BeanPropertyRowMapper<AuthorInfo>(AuthorInfo.class), element.getId()));
        }
        return authorsinfo;
    }



    @Override
    public boolean updateAuthorInfo(AuthorInfo author, int id) {
        jdbcTemplate.update(UPDATE_AUTHOR_INFO_QUERY, new Object[] { author.getId(), author.getBooks_count(), author.getCountry(), author.getEducation(), author.getLanguage(), author.getAuthor_id(),id });

        return true;
    }

    @Override
    public boolean deleteAuthorInfo(int id) {
        jdbcTemplate.update(DELETE_AUTHOR_INFO_QUERY, new Object[] { id });
        return true;
    }

    @Override
    public List<AuthorALL> readAll() {
        List<AuthorInfo> authorsinfo = new ArrayList<>();
        List<Author> authors = jdbcTemplate.query(SELECT_AUTHOR_QUERY, new BeanPropertyRowMapper<Author>(Author.class));
        for (Author element : authors) {
            authorsinfo.addAll(jdbcTemplate.query(SELECT_AUTHOR ,  new BeanPropertyRowMapper<AuthorInfo>(AuthorInfo.class), element.getId()));
        }
        List<AuthorALL> all = new ArrayList<>();

        for (Author element : authors) {
            AuthorALL authorALL = new AuthorALL();
            authorALL.setName(element.getName());

            authorALL.setCountry(authorsinfo.get(element.getId()-1).getCountry());
            authorALL.setBooks_count(authorsinfo.get(element.getId()-1).getBooks_count());
            authorALL.setEducation(authorsinfo.get(element.getId()-1).getEducation());
            authorALL.setLanguage(authorsinfo.get(element.getId()-1).getLanguage());
            all.add(authorALL);
        }

        return all;
    }
}
