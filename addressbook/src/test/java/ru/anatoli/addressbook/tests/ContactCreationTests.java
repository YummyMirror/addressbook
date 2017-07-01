package ru.anatoli.addressbook.tests;

import org.testng.annotations.Test;
import ru.anatoli.addressbook.models.ContactData;

/**
 * Created by anatoli.anukevich on 6/27/2017.
 */
public class ContactCreationTests extends TestBase {
    @Test
    public void testContactCreation() {
        ContactData contactData = new ContactData().withFirstName("firstName")
                                                    .withMiddleName("middleName")
                                                    .withLastName("lastName");

        applicationManager.getContactHelper().createContact(contactData);
    }
}
