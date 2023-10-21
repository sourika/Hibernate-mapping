package org.example.dao;

import org.example.entity.Address;
import org.hibernate.Session;

public class AddressDAO extends GenericDAO{

    public AddressDAO(Session session) {
        super(Address.class, session);
    }
}
