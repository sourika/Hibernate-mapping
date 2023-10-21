package org.example.dao;

import org.example.entity.Language;
import org.hibernate.Session;

public class LanguageDAO extends GenericDAO{
    public LanguageDAO(Session session) {
        super(Language.class, session);
    }
}
