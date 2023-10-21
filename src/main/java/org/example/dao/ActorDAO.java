package org.example.dao;

import org.example.entity.Actor;
import org.hibernate.Session;

public class ActorDAO extends GenericDAO {

    public ActorDAO(Session session) {
        super(Actor.class, session);
    }


}
