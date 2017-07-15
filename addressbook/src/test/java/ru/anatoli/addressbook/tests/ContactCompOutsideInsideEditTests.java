package ru.anatoli.addressbook.tests;

import org.testng.annotations.Test;
import ru.anatoli.addressbook.models.ContactData;
import java.util.Set;
import static org.testng.Assert.assertEquals;

/**
 * Created by anatoli.anukevich on 7/14/2017.
 */
public class ContactCompOutsideInsideEditTests extends TestBase {
    @Test(enabled = true)
    public void testContactCompOutsideInsideUpdate() {
        applicationManager.getNavigationHelper().goToHomePage();

        //Getting Set of ContactData object model BEFORE test execution
        Set<ContactData> before = applicationManager.getContactHelper().getContactSet();

        //Choosing the random Contact that will be compared as OUTSIDE data
        ContactData outsideData = before.iterator().next();

        //Getting INSIDE data from edit form
        ContactData insideData = applicationManager.getContactHelper().getContactDataFromEditForm(outsideData);

        //Asserting by contactFirstName
        assertEquals(outsideData.getFirstName(), insideData.getFirstName());

        //Asserting by contactLastName
        assertEquals(outsideData.getLastName(), insideData.getLastName());

        //Asserting by Address
        assertEquals(outsideData.getAddress(), insideData.getAddress());

        //Asserting by Emails
        assertEquals(outsideData.getAllEmails(), mergeInsideEmails(insideData.getEmail(), insideData.getEmail2(), insideData.getEmail3()));
    }

    public String mergeInsideEmails(String email, String email2, String email3) {
        String mergedEmails = email + " " + email2 + " " + email3;
        String replaced = mergedEmails.replaceAll(" ", "\n");
        return replaced;
    }
}
