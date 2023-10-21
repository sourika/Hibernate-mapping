package org.example.dao;

import org.example.entity.Film;
import org.hibernate.Session;
import org.hibernate.query.Query;

public class FilmDAO extends GenericDAO {
    public FilmDAO(Session session) {
        super(Film.class, session);
    }

    public Film findSuitableFilmForRent() {
        String hql = "select distinct f from Film f where f.rentalRate < 2 and f.id not in (select distinct r.inventory.film from Rental r where r.returnDate is null AND r.rentalDate is not null) order by f.rentalRate, f.id";
        Query<Film> query = getCurrentSession().createQuery(hql, Film.class);
        query.setMaxResults(1);
        return query.getSingleResult();
    }
}
