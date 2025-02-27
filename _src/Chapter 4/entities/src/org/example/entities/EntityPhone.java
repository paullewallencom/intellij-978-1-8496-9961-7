package org.example.entities;

import javax.persistence.*;

/**
 * Created with IntelliJ IDEA.
 * User: brevleq
 * Date: 30/05/13
 * Time: 11:03
 * To change this template use File | Settings | File Templates.
 */
@javax.persistence.Table(name = "phone", schema = "", catalog = "contacts")
@Entity
public class EntityPhone {
    private String number;

    @javax.persistence.Column(name = "number", nullable = false, insertable = true, updatable = true, length = 13, precision = 0)
    @Basic
    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    private Integer id;

    @GeneratedValue(strategy = GenerationType.AUTO)
    @javax.persistence.Column(name = "id", nullable = false, insertable = true, updatable = true, length = 10, precision = 0)
    @Id
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        EntityPhone that = (EntityPhone) o;

        if (id != null ? !id.equals(that.id) : that.id != null) return false;
        if (number != null ? !number.equals(that.number) : that.number != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = number != null ? number.hashCode() : 0;
        result = 31 * result + (id != null ? id.hashCode() : 0);
        return result;
    }

    private EntityPerson personByPersonId;

    @ManyToOne(cascade = CascadeType.PERSIST)
    @javax.persistence.JoinColumn(name = "person_id", referencedColumnName = "id", nullable = false)
    public EntityPerson getPersonByPersonId() {
        return personByPersonId;
    }

    public void setPersonByPersonId(EntityPerson personByPersonId) {
        this.personByPersonId = personByPersonId;
    }
}
