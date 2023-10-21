package org.example.dao;


import org.hibernate.Session;
import org.hibernate.query.Query;

import java.util.List;

public abstract class GenericDAO<T> {

    private final Class<T> clazz;
    private final Session session;

    public GenericDAO(Class<T> clazz, Session session) {
        this.clazz = clazz;
        this.session = session;
    }

    public T getById(final int id) {
        return (T) getCurrentSession().get(clazz, id);
    }

    public List<T> getItems(int offset, int limit) {
        Query query = getCurrentSession().createQuery("from " + clazz.getName(), clazz);
        query.setFirstResult(offset);
        query.setMaxResults(limit);
        return query.getResultList();
    }

    public List<T> findAll() {
        Query query = getCurrentSession().createQuery("from " + clazz.getName(), clazz);
        return query.getResultList();
    }

    public T save(final T entity) {
        getCurrentSession().saveOrUpdate(entity);
        return entity;
    }

    public T update(final T entity) {
        return (T) getCurrentSession().merge(entity);
    }

    public void delete(final T entity) {
        getCurrentSession().delete(entity);
    }

    public void deleteById(final int id) {
        final T entity = getById(id);
        delete(entity);
    }

    protected Session getCurrentSession() {
        return session;
    }
}
