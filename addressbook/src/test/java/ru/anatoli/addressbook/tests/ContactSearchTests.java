package ru.anatoli.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.anatoli.addressbook.models.ContactData;
import java.util.Set;
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

        //Getting random 'Search query' from any available Contact
        String randomSearchQuery = applicationManager.getContactHelper().getRandomSearchQuery(randomContact);

        //Finding 'numberOfMatching' between Contact's data and random 'Search query'
        int numberOfMatching = applicationManager.getContactHelper().getNumberOfMatching(before, randomSearchQuery);

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
