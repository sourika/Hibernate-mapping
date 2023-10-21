package org.example.dao;

import org.example.entity.Store;
import org.hibernate.Session;

public class StoreDAO extends GenericDAO{
    public StoreDAO(Session session) {
        super(Store.class, session);
    }
}
