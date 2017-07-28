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

        //Getting random FirstName as Search query
        String randomFirstName = before.iterator().next().getFirstName();

        //Finding 'numberOfMatching' between data and Search query
        int numberOfMatching = 0;
        for (ContactData contact : before) {
            if (contact.getFirstName().contains(randomFirstName) || contact.getLastName().contains(randomFirstName) || contact.getAddress().contains(randomFirstName)
                    || contact.getAllEmails().contains(randomFirstName) || contact.getAllPhones().contains(randomFirstName)) {
                numberOfMatching += 1;
            }
        }

        applicationManager.getContactHelper().performSearch(randomFirstName);

        //Getting Set of ContactData object model AFTER search
        Set<ContactData> after = applicationManager.getContactHelper().getContactSet();

        //Asserting by SIZE of 'Collection' with 'numberOfMatching'
        if (after.size() == numberOfMatching) {
            assertEquals(numberOfMatching, after.size());
        } else if (after.size() > numberOfMatching) {
            assertNotEquals(numberOfMatching, after.size());
        } else {
            System.out.println("ERROR occurred in logic with comparing maxMatchValue and after.size()");
        }
    }
}
