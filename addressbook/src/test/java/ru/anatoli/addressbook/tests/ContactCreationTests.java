package ru.anatoli.addressbook.tests;

import org.testng.annotations.Test;
import ru.anatoli.addressbook.models.ContactData;
import java.util.Set;
import static org.testng.Assert.assertEquals;

/**
 * Created by anatoli.anukevich on 6/27/2017.
 */
public class ContactCreationTests extends TestBase {
    @Test
    public void testContactCreation() {
        applicationManager.getNavigationHelper().goToHomePage();

        //Getting Set of ContactData object model BEFORE creation
        Set<ContactData> before = applicationManager.getContactHelper().getContactSet();

        //Creating new CONTACT
        ContactData contactData = new ContactData().withFirstName("firstName")
                                                    .withMiddleName(null)
                                                    .withLastName("lastName");
        applicationManager.getContactHelper().createContact(contactData);

        //Getting Set of ContactData object model AFTER creation
        Set<ContactData> after = applicationManager.getContactHelper().getContactSet();

        //Asserting collections by SIZE
        assertEquals(before.size() + 1, after.size());
    }
}
