package org.example.dao;

import org.hibernate.Query;
import org.hibernate.SessionFactory;

import javax.persistence.OrderColumn;
import java.io.Serializable;
import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

public class RetrieverDao<T> {

    protected SessionFactory sessionFactory;
    protected String errorMessage;
    protected T managedEntity;
    protected Integer maxQuantity = null;
    protected Integer firstIndex = null;
    protected Class<T> clazz;

    protected RetrieverDao(SessionFactory sessionFactory,Class<T> clazz) {
        this.sessionFactory=sessionFactory;
        this.clazz = clazz;
    }

    public List<T> retrieveAll(int firstIndex, int quantity) {
        this.firstIndex = firstIndex;
        this.maxQuantity = quantity;
        return retrieveAll();
    }

    public List<T> retrieveAll() {
        Query query = sessionFactory.getCurrentSession().createQuery(createRetrieveAllQuery());
        limitTuplesQuantity(query);
        return query.list();
    }

    private String createRetrieveAllQuery() {
        StringBuilder builder = new StringBuilder("SELECT e FROM ");
        builder.append(clazz.getName());
        builder.append(" e");
        builder.append(createOrderParameter());
        return builder.toString();
    }

    protected StringBuilder createOrderParameter() {
        StringBuilder builder = new StringBuilder();
        List<String> orderProperties = retrieveOrderProperties(clazz);
        int index = 0;
        while (index < orderProperties.size()) {
            if (index == 0)
                builder.append(" order by e.");
            else
                builder.append(",");
            builder.append(orderProperties.get(index));
            index++;
        }
        return builder;
    }

    protected List<String> retrieveOrderProperties(Class clazz) {
        List<String> orderProperties = new ArrayList<String>();
        for (Field field : clazz.getDeclaredFields()) {
            String name = field.getName();
            Annotation[] annotations = field.getDeclaredAnnotations();
            for (int i = 0; i < annotations.length; i++) {
                if (annotations[i].annotationType().equals(OrderColumn.class)) {
                    orderProperties.add(name);
                }
            }
        }
        return orderProperties;
    }

    public T retrieveById(Serializable id) {
        return (T) sessionFactory.getCurrentSession().byId(clazz).load(id);
    }

    public List<T> retrieveByFilter(Object value, int filter) {
        throw new UnsupportedOperationException("Operação não suportada");
    }

    public List<T> retrieveByFilter(Object value, int filter, int firstIndex, int quantity) {
        this.firstIndex = firstIndex;
        this.maxQuantity = quantity;
        return retrieveByFilter(value, filter);
    }

    public int getTuplesQuantity() {
        Query query = sessionFactory.getCurrentSession().createQuery(createTuplesQuantityQuery());
        return ((Number) query.uniqueResult()).intValue();
    }

    protected String createTuplesQuantityQuery() {
        StringBuilder builder = new StringBuilder("SELECT count(e) FROM ");
        builder.append(clazz.getName());
        builder.append(" e");
        return builder.toString();
    }

    protected void limitTuplesQuantity(Query query) {
        if (maxQuantity != null && firstIndex != null) {
            query.setFirstResult(firstIndex);
            if (firstIndex % maxQuantity == 0)
                query.setMaxResults(maxQuantity);
            else
                query.setMaxResults(0);
            maxQuantity = null;
            firstIndex = null;
        }
    }

    protected String createSearchParameter(String sentence) {
        StringBuilder builder = new StringBuilder();
        builder.append(sentence);
        builder.append("%");
        return builder.toString();
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public T getManagedEntity() {
        return managedEntity;
    }
}
