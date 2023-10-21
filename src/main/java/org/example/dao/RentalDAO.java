package org.example.dao;

import org.example.entity.Rental;
import org.hibernate.Session;
import org.hibernate.query.Query;

public class RentalDAO extends GenericDAO {
    public RentalDAO(Session session) {
        super(Rental.class, session);
    }

    public Rental getUnreturnedInventory() {
        Query<Rental> query = getCurrentSession().createQuery("from Rental r where r.returnDate is null", Rental.class);
        query.setMaxResults(1);
        return query.getSingleResult();
    }
}
