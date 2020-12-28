package org.example.dao;

/**
 * Created with IntelliJ IDEA.
 * User: brevleq
 * Date: 17/12/12
 * Time: 14:28
 * To change this template use File | Settings | File Templates.
 */
public interface IPersisterDao<T> extends IRetrieverDao<T> {

    void create(T entity) throws Exception;

    void edit(T entity) throws Exception;

    void delete(T entity) throws Exception;
}
