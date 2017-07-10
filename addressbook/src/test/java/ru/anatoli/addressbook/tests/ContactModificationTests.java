package ru.anatoli.addressbook.tests;

import org.testng.annotations.Test;
import ru.anatoli.addressbook.models.ContactData;
import java.util.Set;

/**
 * Created by anatoli.anukevich on 7/10/2017.
 */
public class ContactModificationTests extends TestBase {
    @Test
    public void testContactModification() {
        applicationManager.getNavigationHelper().goToHomePage();

        //Getting Set of ContactData object model BEFORE modification
        Set<ContactData> before = applicationManager.getContactHelper().getContactSet();

        //Choosing the random Contact that will be modified
        ContactData modifiedContact = before.iterator().next();

        ContactData contactData = new ContactData().withContactId(modifiedContact.getContactId())
                                                    .withFirstName("Modified FirstName")
                                                    .withMiddleName(null)
                                                    .withLastName("Modified LastName");

        applicationManager.getContactHelper().modifyContact(contactData);

        //Getting Set of ContactData object model AFTER modification
        Set<ContactData> after = applicationManager.getContactHelper().getContactSet();
    }
}
