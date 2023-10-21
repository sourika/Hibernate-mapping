package org.example.dao;

import org.example.entity.FilmText;
import org.hibernate.Session;

public class FilmTextDAO extends GenericDAO{
    public FilmTextDAO(Session session) {
        super(FilmText.class, session);
    }
}
