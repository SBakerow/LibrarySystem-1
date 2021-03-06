package com.pvt.jd2.project.back.dao;

import com.pvt.jd2.project.common.dao.BookUserDao;
import com.pvt.jd2.project.common.domain.Book;
import com.pvt.jd2.project.common.domain.BookUser;
import com.pvt.jd2.project.common.domain.ComparisonStatus;
import com.pvt.jd2.project.common.domain.User;
import com.pvt.jd2.project.common.domain.metamodel.BookUser_;
import com.pvt.jd2.project.common.exceptions.DatabaseException;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: Oleg
 * Date: 25.01.14
 * Time: 1:07
  */
@Repository
public class BookUserDaoImpl implements BookUserDao {

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public void create(BookUser bookUser) throws DatabaseException {
        try{
            Session session = sessionFactory.getCurrentSession();
            session.persist(bookUser);
        }catch(Exception e){
            throw new DatabaseException(e);
        }
    }

    @Override
    public void update(BookUser bookUser) throws DatabaseException {
        try{
            Session session = sessionFactory.getCurrentSession();
            session.merge(bookUser);
        }catch(Exception e){
            throw new DatabaseException(e);
        }
    }

    @Override
    public void delete(BookUser bookUser) throws DatabaseException {
        try{
            Session session = sessionFactory.getCurrentSession();
            session.delete(bookUser);
        }catch(Exception e){
            throw new DatabaseException(e);
        }
    }

    @Override
    public BookUser findByBook(Book book) throws DatabaseException {
        try{
            Session session = sessionFactory.getCurrentSession();
            return (BookUser)session.get(BookUser.class, book.getId());
        }catch(Exception e){
            throw new DatabaseException(e);
        }
    }

    private Criteria createCriteria(){
        Session session = sessionFactory.getCurrentSession();
        return session.createCriteria(BookUser.class);
    }


    @Override
    public List<BookUser> list() throws DatabaseException{
        try{
            Criteria criteria = createCriteria();
            return (List<BookUser>)criteria.list();
        }catch(Exception e){
            throw new DatabaseException(e);
        }
    }

    @Override
    public List<BookUser> listByUser(User user) throws DatabaseException {
        try{
            Criteria criteria = createCriteria();
            criteria.add(Restrictions.eq(BookUser_.USER_ID, user.getId()));
            return (List<BookUser>)criteria.list();
        }catch (Exception e){
            throw new DatabaseException(e);
        }
    }

    @Override
    public List<BookUser> listByDebt() throws DatabaseException {
        try{
            Date date = new Date();
            Criteria criteria = createCriteria();
            criteria.add(Restrictions.lt(BookUser_.END_DATE, date));
            return (List<BookUser>)criteria.list();
        }catch(Exception e){
            throw new DatabaseException(e);
        }
    }

    @Override
    public List<BookUser> listByDebtUser(User user) throws DatabaseException {
        try{
            Date date = new Date();
            Criteria criteria = createCriteria();
            criteria.add(Restrictions.eq(BookUser_.USER_ID, user.getId()));
            criteria.add(Restrictions.lt(BookUser_.END_DATE, date));
            return (List<BookUser>)criteria.list();
        }catch(Exception e){
            throw new DatabaseException(e);
        }
    }

    @Override
    public List<BookUser> listByIsContinued() throws DatabaseException {
        try{
            Criteria criteria = createCriteria();
            criteria.add(Restrictions.eq(BookUser_.IS_CONTINUED, true));
            return (List<BookUser>)criteria.list();
        }catch(Exception e){
            throw new DatabaseException(e);
        }
    }

    @Override
    public List<BookUser> listByIsContinued(User user) throws DatabaseException {
        try{
            Criteria criteria = createCriteria();
            criteria.add(Restrictions.eq(BookUser_.USER_ID, user.getId()));
            criteria.add(Restrictions.eq(BookUser_.IS_CONTINUED, true));
            return (List<BookUser>)criteria.list();
        }catch(Exception e){
            throw new DatabaseException(e);
        }
    }

    @Override
    public List<BookUser> listByStartDate(Date startDate, ComparisonStatus status) throws DatabaseException {
        try{
            Criteria criteria = createCriteria();
            updateCriteria(criteria, BookUser_.START_DATE, startDate, status);
            return (List<BookUser>)criteria.list();
        }catch(Exception e){
            throw new DatabaseException(e);
        }
    }

    @Override
    public List<BookUser> listByEndDate(Date endDate, ComparisonStatus status) throws DatabaseException {
        try{
            Criteria criteria = createCriteria();
            updateCriteria(criteria, BookUser_.END_DATE, endDate, status);
            return (List<BookUser>)criteria.list();
        }catch(Exception e){
            throw new DatabaseException(e);
        }
    }

    @Override
    public List<BookUser> listByStartDateBetween(Date startDate, Date endDate) throws DatabaseException {
        try{
            Criteria criteria = createCriteria();
            updateCriteria(criteria, BookUser_.START_DATE, startDate, endDate);
            return (List<BookUser>)criteria.list();
        }catch(Exception e){
            throw new DatabaseException(e);
        }
    }

    @Override
    public List<BookUser> listByEndDateBetween(Date startDate, Date endDate) throws DatabaseException {
        try{
            Criteria criteria = createCriteria();
            updateCriteria(criteria, BookUser_.END_DATE, startDate, endDate);
            return (List<BookUser>)criteria.list();
        }catch(Exception e){
            throw new DatabaseException(e);
        }
    }

    private void updateCriteria(Criteria criteria, String field, Date startDate, Date endDate){
        criteria.add(Restrictions.between(field, startDate, endDate));
    }

    private void updateCriteria(Criteria criteria, String field, Date date, ComparisonStatus status) {
        switch (status){
            case EQ:
                criteria.add(Restrictions.eq(field, date));
                break;
            case GE:
                criteria.add(Restrictions.ge(field, date));
                break;
            case GT:
                criteria.add(Restrictions.gt(field, date));
                break;
            case LE:
                criteria.add(Restrictions.le(field, date));
                break;
            case LT:
                criteria.add(Restrictions.lt(field, date));
                break;
        }
    }

}
