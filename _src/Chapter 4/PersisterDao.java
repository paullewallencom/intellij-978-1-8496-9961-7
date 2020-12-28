package org.example.dao;

import org.hibernate.SessionFactory;

/**
 * Created with IntelliJ IDEA.
 * User: brevleq
 * Date: 14/06/13
 * Time: 15:53
 * To change this template use File | Settings | File Templates.
 */
public class PersisterDao<T> extends RetrieverDao<T> implements IPersisterDao<T>{

    protected PersisterDao(SessionFactory sessionFactory, Class clazz) {
        super(sessionFactory, clazz);
    }

    @Override
    public void create(T entity) throws Exception {
        sessionFactory.getCurrentSession().persist(entity);
        managedEntity=entity;
    }

    @Override
    public void edit(T entity) throws Exception {
        sessionFactory.getCurrentSession().merge(entity);
        managedEntity=entity;
    }

    @Override
    public void delete(T entity) throws Exception {
        sessionFactory.getCurrentSession().delete(entity);
    }
}
