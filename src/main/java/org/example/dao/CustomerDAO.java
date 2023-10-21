package org.example.dao;

import org.example.entity.Customer;
import org.hibernate.Session;

public class CustomerDAO extends GenericDAO {

    public CustomerDAO(Session session) {
        super(Customer.class, session);
    }


}
