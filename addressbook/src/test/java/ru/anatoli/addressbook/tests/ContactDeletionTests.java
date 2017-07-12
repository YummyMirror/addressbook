package ru.anatoli.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.anatoli.addressbook.models.ContactData;
import java.util.Set;
import static org.testng.Assert.assertEquals;

/**
 * Created by anatoli.anukevich on 7/10/2017.
 */
public class ContactDeletionTests extends TestBase {
    @BeforeMethod
    public void ensurePrecondition() {
        applicationManager.getNavigationHelper().goToHomePage();
        if (applicationManager.getContactHelper().getContactSet().size() == 0) {
            ContactData contactData = new ContactData().withFirstName("Temp first name")
                                                        .withMiddleName(null)
                                                        .withLastName("Temp last name");
            applicationManager.getContactHelper().createContact(contactData);
        }
    }

    @Test(enabled = false)
    public void testContactDeletion() {
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

    @Test(enabled = true)
    public void testContactDeletionFromUpdateForm() {
        //Getting Set of ContactData object model BEFORE deletion
        Set<ContactData> before = applicationManager.getContactHelper().getContactSet();

        //Choosing the random Contact that will be removed
        ContactData removedContact = before.iterator().next();

        applicationManager.getContactHelper().selectModifiedContactById(removedContact.getContactId());
        applicationManager.getContactHelper().deleteSelectedContact();
        applicationManager.getNavigationHelper().goToHomePage();

        //Getting Set of ContactData object model AFTER deletion
        Set<ContactData> after = applicationManager.getContactHelper().getContactSet();

        //Asserting collections by SIZE
        assertEquals(before.size() - 1, after.size());

        before.remove(removedContact);

        //Asserting by COLLECTIONS
        assertEquals(before, after);
    }
}
