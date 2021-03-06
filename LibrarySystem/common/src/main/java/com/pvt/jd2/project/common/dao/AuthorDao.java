package com.pvt.jd2.project.common.dao;

import com.pvt.jd2.project.common.domain.Author;
import com.pvt.jd2.project.common.exceptions.DatabaseException;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: Oleg
 * Date: 22.01.14
 * Time: 17:31
 */
public interface AuthorDao {

    void create(Author author) throws DatabaseException;

    void delete(Author author) throws DatabaseException;

    List<Author> list() throws DatabaseException;

    Author findById(Long id) throws DatabaseException;

    List<Author> listByPartOfFirstName(String partOfFirstName) throws DatabaseException;

    List<Author> listByPartOfLastName(String partOfLastName) throws DatabaseException;

}
