package org.example.entities;

import javax.persistence.*;

/**
 * Created with IntelliJ IDEA.
 * User: brevleq
 * Date: 30/05/13
 * Time: 11:03
 * To change this template use File | Settings | File Templates.
 */
@javax.persistence.Table(name = "email", schema = "", catalog = "contacts")
@Entity
public class EntityEmail {
    private Integer id;
    private String email;
    private EntityPerson personByPersonId;

    @GeneratedValue(strategy = GenerationType.AUTO)
    @javax.persistence.Column(name = "id", nullable = false, insertable = true, updatable = true, length = 10, precision = 0)
    @Id
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @javax.persistence.Column(name = "email", nullable = false, insertable = true, updatable = true, length = 256, precision = 0)
    @Basic
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        EntityEmail that = (EntityEmail) o;

        if (email != null ? !email.equals(that.email) : that.email != null) return false;
        if (id != null ? !id.equals(that.id) : that.id != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (email != null ? email.hashCode() : 0);
        return result;
    }

    @ManyToOne(cascade = CascadeType.PERSIST)
    @javax.persistence.JoinColumn(name = "person_id", referencedColumnName = "id", nullable = false)
    public EntityPerson getPersonByPersonId() {
        return personByPersonId;
    }

    public void setPersonByPersonId(EntityPerson personByPersonId) {
        this.personByPersonId = personByPersonId;
    }
}
