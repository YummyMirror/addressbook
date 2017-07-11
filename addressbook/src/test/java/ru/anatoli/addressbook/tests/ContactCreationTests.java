package ru.anatoli.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.anatoli.addressbook.models.ContactData;

import java.io.File;
import java.util.Set;
import static org.testng.Assert.assertEquals;

/**
 * Created by anatoli.anukevich on 6/27/2017.
 */
public class ContactCreationTests extends TestBase {
    @BeforeMethod
    public void ensurePrecondition() {
        applicationManager.getNavigationHelper().goToHomePage();
    }

    @Test
    public void testContactCreation() {
        //Getting Set of ContactData object model BEFORE creation
        Set<ContactData> before = applicationManager.getContactHelper().getContactSet();

        //Creating new CONTACT
        ContactData contactData = new ContactData().withFirstName("firstName")
                                                    .withMiddleName("middleName")
                                                    .withLastName("lastName")
                                                    .withNickname("nickname")
                                                    .withPhoto(new File("src/test/resources/NBA.jpeg"))
                                                    .withTitle("Lead department")
                                                    .withCompany("Google")
                                                    .withAddress("California, LA")
                                                    .withHomePhone("111-222-333")
                                                    .withMobilePhone("123/123/123")
                                                    .withWorkPhone("123-456-789")
                                                    .withFax("111222333")
                                                    .withEmail("1@mail.ru")
                                                    .withEmail2("2@mail.ru")
                                                    .withEmail3("3@mail.ru")
                                                    .withHomepage("www.google.com");
        applicationManager.getContactHelper().createContact(contactData);

        //Getting Set of ContactData object model AFTER creation
        Set<ContactData> after = applicationManager.getContactHelper().getContactSet();

        //Asserting collections by SIZE
        assertEquals(before.size() + 1, after.size());

        contactData.withContactId(after
                                    .stream()
                                    .max((contact1, contact2) -> Integer.compare(contact1.getContactId(), contact2.getContactId()))
                                    .get()
                                    .getContactId());
        before.add(contactData);

        //Asserting by COLLECTIONS
        assertEquals(before, after);
    }
}
