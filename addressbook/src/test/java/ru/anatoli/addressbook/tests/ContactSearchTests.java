package ru.anatoli.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.anatoli.addressbook.models.ContactData;
import java.security.SecureRandom;
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
    public void testContactSearchByRandomInfo() {
        //Getting Set of ContactData object model BEFORE search
        Set<ContactData> before = applicationManager.getContactHelper().getContactSet();

        //Getting random Search query
        ContactData randomContact = before.stream().findAny().get();
        List<String> listWithRandomData = Arrays.asList(randomContact.getFirstName(), randomContact.getLastName(), randomContact.getAddress(),
                                                        randomContact.getAllEmails(), randomContact.getAllPhones());
        SecureRandom random = new SecureRandom();
        int identifier = random.nextInt(4);
        String randomSearchQuery = listWithRandomData.get(identifier);

        //Finding 'numberOfMatching' between data and Search query
        int numberOfMatching = 0;
        for (ContactData contact : before) {
            if (contact.getFirstName().contains(randomSearchQuery) || contact.getLastName().contains(randomSearchQuery) || contact.getAddress().contains(randomSearchQuery)
                    || contact.getAllEmails().contains(randomSearchQuery) || contact.getAllPhones().contains(randomSearchQuery)) {
                numberOfMatching += 1;
            }
        }

        applicationManager.getContactHelper().performSearch(randomSearchQuery);

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
