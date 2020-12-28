package org.example.controllers;

import org.example.dao.RetrieverDao;
import org.example.entities.EntityPerson;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: brevleq
 * Date: 10/06/13
 * Time: 10:24
 * To change this template use File | Settings | File Templates.
 */
public class PersonTableController {

    private RetrieverDao<EntityPerson> retriever;
    private EntityPerson selected;
    private List<EntityPerson> persons;

    public void updateTable(){
        setPersons(retriever.retrieveAll());
    }

    public EntityPerson getSelected() {
        return selected;
    }

    public void setSelected(EntityPerson selected) {
        this.selected = selected;
    }

    public List<EntityPerson> getPersons() {
        return persons;
    }

    public void setPersons(List<EntityPerson> persons) {
        this.persons = persons;
    }

    public void setRetriever(RetrieverDao<EntityPerson> retriever) {
        this.retriever = retriever;
    }
}
