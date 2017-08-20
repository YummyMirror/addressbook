package ru.anatoli.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.anatoli.addressbook.models.ContactData;
import java.io.File;
import java.util.Set;
import java.util.stream.Collectors;
import static org.testng.Assert.assertEquals;

/**
 * Created by anatoli.anukevich on 7/10/2017.
 */
public class ContactDeletionTests extends TestBase {
    @BeforeMethod
    public void ensurePrecondition() {
        applicationManager.getNavigationHelper().goToHomePage();
        if (applicationManager.getDbHelper().getContactSet().size() == 0) {
            ContactData contactData = new ContactData().withFirstName("Temp first name")
                                                        .withMiddleName(null)
                                                        .withLastName("Temp last name")
                                                        .withNickname(null)
                                                        .withPhoto(new File(""))
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
                                                        .withSecondaryPhone(null)
                                                        .withSecondaryNotes(null);
            applicationManager.getContactHelper().createContact(contactData);
        }
    }

    @Test(enabled = true)
    public void testContactDeletion() {
        //Getting Set of ContactData object model BEFORE deletion
        Set<ContactData> before = applicationManager.getDbHelper().getContactSet();

        //Choosing the random Contact that will be removed
        ContactData removedContact = before.iterator().next();

        applicationManager.getContactHelper().removeContact(removedContact);

        //Getting Set of ContactData object model AFTER deletion
        Set<ContactData> after = applicationManager.getDbHelper().getContactSet();

        //Asserting collections by SIZE
        assertEquals(before.size() - 1, after.size());

        before.remove(removedContact);

        //Asserting by COLLECTIONS
        assertEquals(before, after);

        //Asserting UI data vs DB data
        Set<ContactData> ui = applicationManager.getContactHelper().getContactSet().stream()
                                                                                   .map((contact) -> new ContactData().withContactId(contact.getContactId())
                                                                                                                      .withFirstName(contact.getFirstName())
                                                                                                                      .withLastName(contact.getLastName())
                                                                                                                      .withAddress(contact.getAddress()))
                                                                                   .collect(Collectors.toSet());
        Set<ContactData> db = applicationManager.getDbHelper().getContactSet().stream()
                                                                              .map((contact) -> new ContactData().withContactId(contact.getContactId())
                                                                                                                 .withFirstName(contact.getFirstName())
                                                                                                                 .withLastName(contact.getLastName())
                                                                                                                 .withAddress(contact.getAddress()))
                                                                              .collect(Collectors.toSet());
        assertEquals(ui, db);
    }

    @Test(enabled = true)
    public void testContactDeletionFromUpdateForm() {
        //Getting Set of ContactData object model BEFORE deletion
        Set<ContactData> before = applicationManager.getDbHelper().getContactSet();

        //Choosing the random Contact that will be removed
        ContactData removedContact = before.iterator().next();

        applicationManager.getContactHelper().removeContactFromUpdateForm(removedContact);

        //Getting Set of ContactData object model AFTER deletion
        Set<ContactData> after = applicationManager.getDbHelper().getContactSet();

        //Asserting collections by SIZE
        assertEquals(before.size() - 1, after.size());

        before.remove(removedContact);

        //Asserting by COLLECTIONS
        assertEquals(before, after);

        //Asserting UI data vs DB data
        Set<ContactData> ui = applicationManager.getContactHelper().getContactSet().stream()
                                                                                   .map((contact) -> new ContactData().withContactId(contact.getContactId())
                                                                                                                      .withFirstName(contact.getFirstName())
                                                                                                                      .withLastName(contact.getLastName())
                                                                                                                      .withAddress(contact.getAddress()))
                                                                                   .collect(Collectors.toSet());
        Set<ContactData> db = applicationManager.getDbHelper().getContactSet().stream()
                                                                              .map((contact) -> new ContactData().withContactId(contact.getContactId())
                                                                                                                 .withFirstName(contact.getFirstName())
                                                                                                                 .withLastName(contact.getLastName())
                                                                                                                 .withAddress(contact.getAddress()))
                                                                              .collect(Collectors.toSet());
        assertEquals(ui, db);
    }
}
