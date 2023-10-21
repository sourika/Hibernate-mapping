package org.example.dao;

import org.example.entity.Staff;
import org.hibernate.Session;

public class StaffDAO extends GenericDAO{
    public StaffDAO(Session session) {
        super(Staff.class, session);
    }
}
