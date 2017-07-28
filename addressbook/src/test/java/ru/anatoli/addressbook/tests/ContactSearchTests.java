package ru.anatoli.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.anatoli.addressbook.models.ContactData;
import java.util.*;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertNotEquals;

/**
 * Created by anatoli.anukevich on 7/27/2017.
 */
public class ContactSearchTests extends TestBase {
    @BeforeMethod
    public void ensurePrecondition() {
        applicationManager.getNavigationHelper().goToHomePage();
    }
    @Test
    public void testContactSearchByFirstName() {
        //Getting Set of ContactData object model BEFORE search
        Set<ContactData> before = applicationManager.getContactHelper().getContactSet();

        //Creating Collections with FirstNames, LastNames, Addresses, Emails, Phones
        List<String> listFirstNames = new ArrayList<String>();
        for (ContactData contact : before) {
            listFirstNames.add(contact.getFirstName());
        }

        List<String> listLastNames = new ArrayList<String>();
        for (ContactData contact : before) {
            listLastNames.add(contact.getLastName());
        }

        List<String> listAddresses = new ArrayList<String>();
        for (ContactData contact : before) {
            listAddresses.add(contact.getAddress());
        }

        List<String> listEmails = new ArrayList<String>();
        for (ContactData contact : before) {
            listEmails.add(contact.getAllEmails());
        }

        List<String> listPhones = new ArrayList<String>();
        for (ContactData contact : before) {
            listPhones.add(contact.getAllPhones());
        }

        //Getting search string by random FirstName
        String randomFirstName = listFirstNames.iterator().next();

        //Initializing number of matching between 'Search query' with 'Collections' of FirstName, LastName, Addressees, Emails, Phones
        int matchNumberFirstName = 0;
        int matchNumberLastName = 0;
        int matchNumberAddress = 0;
        int matchNumberEmail = 0;
        int matchNumberPhone = 0;

        //Matching 'Search query' with 'Collections' of FirstName, LastName, Addressees, Emails, Phones
        for (String elem : listFirstNames) {
            if (elem.contains(randomFirstName)) {
                matchNumberFirstName += 1;
            }
        }

        for (String elem : listLastNames) {
            if (elem.contains(randomFirstName)) {
                matchNumberLastName += 1;
            }
        }

        for (String elem : listAddresses) {
            if (elem.contains(randomFirstName)) {
                matchNumberAddress += 1;
            }
        }

        for (String elem : listEmails) {
            if (elem.contains(randomFirstName)) {
                matchNumberEmail += 1;
            }
        }

        for (String elem : listPhones) {
            if (elem.contains(randomFirstName)) {
                matchNumberPhone += 1;
            }
        }

        //Getting the MAX value from matched values of Collections
        List<Integer> listWithMatchNumbers = Arrays.asList(matchNumberFirstName, matchNumberLastName, matchNumberAddress, matchNumberEmail, matchNumberPhone);
        Integer maxMatchValue = listWithMatchNumbers
                                .stream()
                                .max(Comparator.comparingInt(value -> value))
                                .get();

        applicationManager.getContactHelper().performSearch(randomFirstName);

        //Getting Set of ContactData object model AFTER search
        Set<ContactData> after = applicationManager.getContactHelper().getContactSet();

        //Asserting by SIZE of 'Collection' with 'maxMatchValue'
        if (after.size() == maxMatchValue) {
            assertEquals(maxMatchValue.intValue(), after.size());
        } else if (after.size() > maxMatchValue) {
            assertNotEquals(maxMatchValue, after.size());
        } else {
            System.out.println("ERROR occurred in logic with comparing maxMatchValue and after.size()");
        }
    }
}
