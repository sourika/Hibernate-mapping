package org.example.dao;

import org.example.entity.Country;
import org.hibernate.Session;

public class CountryDAO extends GenericDAO{
    public CountryDAO(Session session) {
        super(Country.class, session);
    }
}
