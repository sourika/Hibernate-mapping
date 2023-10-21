package org.example.dao;

import org.example.entity.Category;
import org.hibernate.Session;

public class CategoryDAO extends GenericDAO{
    public CategoryDAO(Session session) {
        super(Category.class, session);
    }


}
