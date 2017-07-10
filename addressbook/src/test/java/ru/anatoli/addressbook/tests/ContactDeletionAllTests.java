package ru.anatoli.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.anatoli.addressbook.models.ContactData;

import java.util.Set;

import static org.testng.Assert.assertTrue;

/**
 * Created by anatoli.anukevich on 7/10/2017.
 */
public class ContactDeletionAllTests extends TestBase {
    @BeforeMethod
    public void ensurePrecondition() {
        applicationManager.getNavigationHelper().goToHomePage();
        if (applicationManager.getContactHelper().getContactSet().size() == 0) {
            ContactData contactData = new ContactData().withFirstName("FirstName")
                                                        .withMiddleName("MiddleName")
                                                        .withLastName("LastName");
            applicationManager.getContactHelper().createContact(contactData);
            applicationManager.getContactHelper().createContact(contactData);
            applicationManager.getContactHelper().createContact(contactData);
        }
    }

    @Test(enabled = false)
    public void testContactDeletionAll() {
        applicationManager.getContactHelper().deleteAllSelectedContacts();

        int after = applicationManager.getContactHelper().getContactSet().size();

        //Asserting by SIZE
        assertTrue(after == 0);
    }

    @Test(enabled = true)
    public void testContactDeletionAllWithoutSelection() {
        //Getting Set of ContactData object model BEFORE clicking 'Select ALL'
        Set<ContactData> before = applicationManager.getContactHelper().getContactSet();

        applicationManager.getContactHelper().deleteSelectedContact();
        applicationManager.getContactHelper().confirmSelectAllAlert();

        //Getting Set of ContactData object model AFTER clicking 'Select ALL'
        Set<ContactData> after = applicationManager.getContactHelper().getContactSet();
    }
}
