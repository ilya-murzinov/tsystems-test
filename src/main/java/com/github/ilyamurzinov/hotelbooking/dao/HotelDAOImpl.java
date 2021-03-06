package com.github.ilyamurzinov.hotelbooking.dao;

import com.github.ilyamurzinov.hotelbooking.domain.Comment;
import com.github.ilyamurzinov.hotelbooking.domain.Hotel;
import org.hibernate.Hibernate;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Provides methods to create, update, read, delete Hotels from database
 *
 * @author Ilya Murzinov
 *         Date: 16.06.14
 */
@Repository
public class HotelDAOImpl implements HotelDAO {
    @Autowired
    private SessionFactory sessionFactory;

    /**
     * {@inheritDoc}
     */
    @Override
    public Hotel getHotel(int id) {
        Hotel hotel = (Hotel) sessionFactory.getCurrentSession().get(Hotel.class, id);
        if (hotel != null) {
            for (Comment c : hotel.getComments()) {
                Hibernate.initialize(c);
            }
        }
        return hotel;
    }

    @Override
    public void addHotel(Hotel hotel) {
        sessionFactory.getCurrentSession().save(hotel);
    }

    /**
     * {@inheritDoc}
     */
    @SuppressWarnings("unchecked")
    @Override
    public List<Hotel> listHotel() {
        List<Hotel> list = sessionFactory.getCurrentSession().
                createQuery("from Hotel order by size(comments) desc").list();
        for (Hotel h : list) {
            Hibernate.initialize(h.getComments());
        }
        return list;
    }

    /**
     * {@inheritDoc}
     */
    @SuppressWarnings("unchecked")
    @Override
    public List<Hotel> listHotel(String name) {
        return sessionFactory.getCurrentSession()
                .createQuery("from Hotel where name like '%" + name + "%' order by size(comments) desc").list();
    }

    @Override
    public void updateHotel(Hotel hotel) {
        sessionFactory.getCurrentSession().update(hotel);
    }

    @Override
    public void removeHotel(int id) {
        Hotel hotel = (Hotel) sessionFactory.getCurrentSession().load(Hotel.class, id);
        if (hotel != null) {
            sessionFactory.getCurrentSession().delete(hotel);
        }
    }
}
