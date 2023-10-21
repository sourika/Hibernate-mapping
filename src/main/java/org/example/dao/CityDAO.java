package org.example.dao;


import org.example.entity.City;
import org.hibernate.Session;
import org.hibernate.query.Query;

public class CityDAO extends GenericDAO {
    public CityDAO(Session session) {
        super(City.class, session);
    }

    public City getByName(String city) {
        Query<City> query = getCurrentSession().createQuery("from City c where c.city= :name", City.class);
        query.setParameter("name", city);
        query.setMaxResults(1);
        return query.getSingleResult();
    }

}


