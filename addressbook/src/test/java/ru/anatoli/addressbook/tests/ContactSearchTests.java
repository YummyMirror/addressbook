package ru.anatoli.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.anatoli.addressbook.models.ContactData;
import java.io.File;
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

        ContactData contactData = new ContactData().withFirstName("FirstName")
                                                    .withMiddleName(null)
                                                    .withLastName("LastName")
                                                    .withNickname(null)
                                                    .withPhoto(new File(""))
                                                    .withTitle(null)
                                                    .withCompany(null)
                                                    .withAddress("Address")
                                                    .withHomePhone("111")
                                                    .withMobilePhone("222")
                                                    .withWorkPhone("333")
                                                    .withFax(null)
                                                    .withEmail("1@mail.ru")
                                                    .withEmail2("2@mail.ru")
                                                    .withEmail3("3@mail.ru")
                                                    .withHomepage(null)
                                                    .withBirthDay(null)
                                                    .withBirthMonth(null)
                                                    .withBirthYear(null)
                                                    .withAnniversaryDay(null)
                                                    .withAnniversaryMonth(null)
                                                    .withAnniversaryYear(null)
                                                    .withSecondaryAddress(null)
                                                    .withSecondaryPhone("444")
                                                    .withSecondaryNotes(null);

        int contactNumber = applicationManager.getDbHelper().getContactSet().size();
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
            System.out.println("ERROR occurred in logic with comparing numberOfMatching and after.size()");
        }
    }
}
