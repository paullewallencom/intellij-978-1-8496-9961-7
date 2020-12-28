package org.example.entities;

import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import javax.persistence.*;
import java.util.Collection;
import java.util.Date;

/**
 * Created with IntelliJ IDEA.
 * User: brevleq
 * Date: 30/05/13
 * Time: 11:03
 * To change this template use File | Settings | File Templates.
 */
@javax.persistence.Table(name = "person", schema = "", catalog = "contacts")
@Entity
public class EntityPerson {
    private Integer id;
    @OrderColumn
    private String completeName;
    private Date birthDate;
    private String placeOfBirth;
    private Collection<EntityEmail> emailsById;
    private Collection<EntityPhone> phonesById;

    @GeneratedValue(strategy = GenerationType.AUTO)
    @javax.persistence.Column(name = "id", nullable = false, insertable = true, updatable = true, length = 10, precision = 0)
    @Id
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @javax.persistence.Column(name = "complete_name", nullable = false, insertable = true, updatable = true, length = 250, precision = 0)
    @Basic
    public String getCompleteName() {
        return completeName;
    }

    public void setCompleteName(String completeName) {
        this.completeName = completeName;
    }

    @javax.persistence.Column(name = "birth_date", nullable = false, insertable = true, updatable = true, length = 10, precision = 0)
    @Basic
    public Date getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
    }

    @javax.persistence.Column(name = "place_of_birth", nullable = false, insertable = true, updatable = true, length = 250, precision = 0)
    @Basic
    public String getPlaceOfBirth() {
        return placeOfBirth;
    }

    public void setPlaceOfBirth(String placeOfBirth) {
        this.placeOfBirth = placeOfBirth;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        EntityPerson that = (EntityPerson) o;

        if (birthDate != null ? !birthDate.equals(that.birthDate) : that.birthDate != null) return false;
        if (completeName != null ? !completeName.equals(that.completeName) : that.completeName != null) return false;
        if (id != null ? !id.equals(that.id) : that.id != null) return false;
        if (placeOfBirth != null ? !placeOfBirth.equals(that.placeOfBirth) : that.placeOfBirth != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (completeName != null ? completeName.hashCode() : 0);
        result = 31 * result + (birthDate != null ? birthDate.hashCode() : 0);
        result = 31 * result + (placeOfBirth != null ? placeOfBirth.hashCode() : 0);
        return result;
    }

    @LazyCollection(value = LazyCollectionOption.FALSE)
    @OneToMany(mappedBy = "personByPersonId", cascade = CascadeType.ALL)
    public Collection<EntityEmail> getEmailsById() {
        return emailsById;
    }

    public void setEmailsById(Collection<EntityEmail> emailsById) {
        this.emailsById = emailsById;
    }

    @LazyCollection(value = LazyCollectionOption.FALSE)
    @OneToMany(mappedBy = "personByPersonId", cascade = CascadeType.ALL)
    public Collection<EntityPhone> getPhonesById() {
        return phonesById;
    }

    public void setPhonesById(Collection<EntityPhone> phonesById) {
        this.phonesById = phonesById;
    }
}
