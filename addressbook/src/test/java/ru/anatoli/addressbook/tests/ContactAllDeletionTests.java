package ru.anatoli.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.anatoli.addressbook.models.ContactData;
import java.io.File;
import java.util.Set;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

/**
 * Created by anatoli.anukevich on 7/10/2017.
 */
public class ContactAllDeletionTests extends TestBase {
    @BeforeMethod
    public void ensurePrecondition() {
        applicationManager.getNavigationHelper().goToHomePage();

        ContactData contactData = new ContactData().withFirstName("FirstName")
                                                    .withMiddleName("MiddleName")
                                                    .withLastName("LastName")
                                                    .withNickname(null)
                                                    .withPhoto(new File("src/test/resources/NBA.jpeg"))
                                                    .withTitle(null)
                                                    .withCompany(null)
                                                    .withAddress(null)
                                                    .withHomePhone(null)
                                                    .withMobilePhone(null)
                                                    .withWorkPhone(null)
                                                    .withFax(null)
                                                    .withEmail(null)
                                                    .withEmail2(null)
                                                    .withEmail3(null)
                                                    .withHomepage(null)
                                                    .withBirthDay(null)
                                                    .withBirthMonth(null)
                                                    .withBirthYear(null)
                                                    .withAnniversaryDay(null)
                                                    .withAnniversaryMonth(null)
                                                    .withAnniversaryYear(null)
                                                    .withSecondaryAddress(null)
                                                    .withSecondaryHome(null)
                                                    .withSecondaryNotes(null);

        int contactNumber = applicationManager.getContactHelper().getContactSet().size();
        if (contactNumber == 0) {
            for (int i = 0; i < 3; i++) {
                applicationManager.getContactHelper().createContact(contactData);
            }
        } else if (contactNumber < 3) {
            for (int i = 0; i < 2; i++) {
                applicationManager.getContactHelper().createContact(contactData);
            }
        }
    }

    @Test(enabled = true)
    public void testContactAllDeletion() {
        applicationManager.getContactHelper().deleteAllSelectedContacts();

        int after = applicationManager.getContactHelper().getContactSet().size();

        //Asserting by SIZE
        assertTrue(after == 0);
    }

    @Test(enabled = true)
    public void testContactDeletionAllWithoutSelection() {
        //Getting Set of ContactData object model BEFORE clicking 'Select ALL'
        Set<ContactData> before = applicationManager.getContactHelper().getContactSet();

        applicationManager.getContactHelper().deleteAllContactsWithoutSelection();

        //Getting Set of ContactData object model AFTER clicking 'Select ALL'
        Set<ContactData> after = applicationManager.getContactHelper().getContactSet();

        //Asserting collections by SIZE
        assertEquals(before.size(), after.size());

        //Asserting by COLLECTIONS
        assertEquals(before, after);
    }

    @Test(enabled = true)
    public void testContactDeletionAllCancel() {
        //Getting Set of ContactData object model BEFORE clicking 'Select ALL'
        Set<ContactData> before = applicationManager.getContactHelper().getContactSet();

        applicationManager.getContactHelper().cancelDeletionAllSelectedContacts();

        //Getting Set of ContactData object model AFTER clicking 'Select ALL'
        Set<ContactData> after = applicationManager.getContactHelper().getContactSet();

        //Asserting collections by SIZE
        assertEquals(before.size(), after.size());

        //Asserting by COLLECTIONS
        assertEquals(before, after);
    }
}
