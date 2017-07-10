package ru.anatoli.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.anatoli.addressbook.models.ContactData;
import java.util.Set;
import static org.testng.Assert.*;

/**
 * Created by anatoli.anukevich on 7/10/2017.
 */
public class ContactDeletionTests extends TestBase {
    @Test
    public void testContactDeletion() {
        applicationManager.getNavigationHelper().goToHomePage();

        //Getting Set of ContactData object model BEFORE deletion
        Set<ContactData> before = applicationManager.getContactHelper().getContactSet();

        //Choosing the random Contact that will be removed
        ContactData removedContact = before.iterator().next();

        applicationManager.getContactHelper().removeContact(removedContact);

        //Getting Set of ContactData object model AFTER deletion
        Set<ContactData> after = applicationManager.getContactHelper().getContactSet();

        //Asserting collections by SIZE
        assertEquals(before.size() - 1, after.size());

        before.remove(removedContact);

        //Asserting by COLLECTIONS
        assertEquals(before, after);
    }
}
