package com.pvt.jd2.project.common.dao;

import com.pvt.jd2.project.common.domain.ActivationStatus;
import com.pvt.jd2.project.common.domain.Book;
import com.pvt.jd2.project.common.exceptions.DatabaseException;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: Oleg
 * Date: 24.01.14
 * Time: 19:31
 */
public interface BookDao {

    void create(Book book) throws DatabaseException;

    void delete(Book book) throws DatabaseException;

    void activate(Book book) throws DatabaseException;

    void deactivate(Book book) throws DatabaseException;

    Book findById(Long id) throws DatabaseException;

    Book findByIsbn(String isbn) throws DatabaseException;

    List<Book> list(ActivationStatus status) throws DatabaseException;

    List<Book> listByPartOfName(String partOfName, ActivationStatus status) throws DatabaseException;

    List<Book> listByPartOfDescription(String partOfDescription, ActivationStatus status) throws DatabaseException;

}
