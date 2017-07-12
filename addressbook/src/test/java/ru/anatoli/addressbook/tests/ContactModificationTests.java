package ru.anatoli.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.anatoli.addressbook.models.ContactData;

import java.io.File;
import java.util.Set;
import static org.testng.Assert.assertEquals;

/**
 * Created by anatoli.anukevich on 7/10/2017.
 */
public class ContactModificationTests extends TestBase {
    @BeforeMethod
    public void ensurePrecondition() {
        applicationManager.getNavigationHelper().goToHomePage();
        if (applicationManager.getContactHelper().getContactSet().size() == 0) {
            ContactData contactData = new ContactData().withFirstName("Forced created FirstName")
                                                        .withMiddleName("Forced created MiddleName")
                                                        .withLastName("Forced created LastName")
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
            applicationManager.getContactHelper().createContact(contactData);
        }
    }

    @Test
    public void testContactModification() {
        //Getting Set of ContactData object model BEFORE modification
        Set<ContactData> before = applicationManager.getContactHelper().getContactSet();

        //Choosing the random Contact that will be modified
        ContactData modifiedContact = before.iterator().next();

        ContactData contactData = new ContactData().withContactId(modifiedContact.getContactId())
                                                    .withFirstName("Modified FirstName")
                                                    .withMiddleName(null)
                                                    .withLastName("Modified LastName")
                                                    .withNickname(null)
                                                    .withPhoto(new File("src/test/resources/Kobe.jpg"))
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

        applicationManager.getContactHelper().modifyContact(contactData);

        //Getting Set of ContactData object model AFTER modification
        Set<ContactData> after = applicationManager.getContactHelper().getContactSet();

        //Asserting collections by SIZE
        assertEquals(before.size(), after.size());

        before.remove(modifiedContact);
        before.add(contactData);

        //Asserting by COLLECTIONS
        assertEquals(before, after);
    }
}
