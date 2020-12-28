package org.example.ws;

import org.example.entities.EntityEmail;
import org.example.entities.EntityPerson;
import org.example.entities.EntityPhone;
import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Date;

/**
 * Created with IntelliJ IDEA.
 * User: brevleq
 * Date: 14/06/13
 * Time: 10:19
 * To change this template use File | Settings | File Templates.
 */
public class ContactsPersisterTest {
    /**
     * @verifies return false if person is null
     * @see ContactsPersister#checkPersonIsValid(org.example.entities.EntityPerson)
     */
    @Test
    public void checkPersonIsValid_shouldReturnFalseIfPersonIsNull() throws Exception {
        ContactsPersister service=new ContactsPersister();
        Assert.assertFalse(service.checkPersonIsValid(null));
    }

    /**
     * @verifies return false if person's email or phone list are null
     * @see ContactsPersister#checkPersonIsValid(org.example.entities.EntityPerson)
     */
    @Test
    public void checkPersonIsValid_shouldReturnFalseIfPersonsEmailOrPhoneListAreNull() throws Exception {
        EntityPerson person=new EntityPerson();
        person.setBirthDate(new Date());
        person.setCompleteName("Tomas Watson");
        person.setPlaceOfBirth("New York, NY");
        person.setEmailsById(null);
        ArrayList<EntityPhone>phones=new ArrayList<EntityPhone>();
        phones.add(new EntityPhone());
        person.setPhonesById(phones);
        ContactsPersister service=new ContactsPersister();
        Assert.assertFalse(service.checkPersonIsValid(person));
        person.setPhonesById(null);
        ArrayList<EntityEmail> emails=new ArrayList<EntityEmail>();
        emails.add(new EntityEmail());
        person.setEmailsById(emails);
        Assert.assertFalse(service.checkPersonIsValid(person));
    }

    /**
     * @verifies return false if any person's properties are null or empty
     * @see ContactsPersister#checkPersonIsValid(org.example.entities.EntityPerson)
     */
    @Test
    public void checkPersonIsValid_shouldReturnFalseIfAnyPersonsPropertiesAreNullOrEmpty() throws Exception {
        EntityPerson person=new EntityPerson();
        person.setBirthDate(null);
        person.setCompleteName("Tomas Watson");
        person.setPlaceOfBirth("New York, NY");
        ArrayList<EntityPhone>phones=new ArrayList<EntityPhone>();
        phones.add(new EntityPhone());
        person.setPhonesById(phones);
        ArrayList<EntityEmail> emails=new ArrayList<EntityEmail>();
        emails.add(new EntityEmail());
        person.setEmailsById(emails);
        ContactsPersister service=new ContactsPersister();
        Assert.assertFalse(service.checkPersonIsValid(person));
        person.setBirthDate(new Date());
        person.setCompleteName("");
        Assert.assertFalse(service.checkPersonIsValid(person));
        person.setCompleteName(null);
        Assert.assertFalse(service.checkPersonIsValid(person));
        person.setCompleteName("Tomas Watson");
        person.setPlaceOfBirth("");
        Assert.assertFalse(service.checkPersonIsValid(person));
        person.setPlaceOfBirth(null);
        Assert.assertFalse(service.checkPersonIsValid(person));
    }

    /**
     * @verifies return true if all person's properties have some value and email or phone list has at least one value
     * @see ContactsPersister#checkPersonIsValid(org.example.entities.EntityPerson)
     */
    @Test
    public void checkPersonIsValid_shouldReturnTrueIfAllPersonsPropertiesHaveSomeValueAndEmailOrPhoneListHasAtLeastOneValue() throws Exception {
        EntityPerson person=new EntityPerson();
        person.setBirthDate(new Date());
        person.setCompleteName("Tomas Watson");
        person.setPlaceOfBirth("New York, NY");
        ArrayList<EntityPhone>phones=new ArrayList<EntityPhone>();
        phones.add(new EntityPhone());
        person.setPhonesById(phones);
        ArrayList<EntityEmail> emails=new ArrayList<EntityEmail>();
        emails.add(new EntityEmail());
        person.setEmailsById(emails);
        ContactsPersister service=new ContactsPersister();
        Assert.assertTrue(service.checkPersonIsValid(person));
    }

    /**
     * @verifies return false if person's email and phone list are empty
     * @see ContactsPersister#checkPersonIsValid(org.example.entities.EntityPerson)
     */
    @Test
    public void checkPersonIsValid_shouldReturnFalseIfPersonsEmailAndPhoneListAreEmpty() throws Exception {
        EntityPerson person=new EntityPerson();
        person.setBirthDate(new Date());
        person.setCompleteName("Tomas Watson");
        person.setPlaceOfBirth("New York, NY");
        person.setEmailsById(new ArrayList<EntityEmail>());
        person.setPhonesById(new ArrayList<EntityPhone>());
        ContactsPersister service=new ContactsPersister();
        Assert.assertFalse(service.checkPersonIsValid(person));
    }
}
