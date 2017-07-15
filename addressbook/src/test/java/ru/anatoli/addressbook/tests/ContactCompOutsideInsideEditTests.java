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

        //Getting INSIDE's emails
        String insideEmail1 = insideData.getEmail();
        String insideEmail2 = insideData.getEmail2();
        String insideEmail3 = insideData.getEmail3();

        //Asserting by Emails
        if (insideEmail1.equals("") && !insideEmail2.equals("") && !insideEmail3.equals("")) {
            assertEquals(outsideData.getAllEmails(), mergeSecondThirdEmails(insideEmail2, insideEmail3));
        } else if (insideEmail2.equals("") && !insideEmail1.equals("") && !insideEmail3.equals("")) {
            assertEquals(outsideData.getAllEmails(), mergeFirstThirdEmails(insideEmail1, insideEmail3));
        } else if (insideEmail3.equals("") && !insideEmail1.equals("") && !insideEmail2.equals("")) {
            assertEquals(outsideData.getAllEmails(), mergeFirstSecondEmails(insideEmail1, insideEmail2));
        } else if (insideEmail1.equals("") && insideEmail3.equals("") && !insideEmail2.equals("")) {
            assertEquals(outsideData.getAllEmails(), insideEmail2);
        } else if (insideEmail1.equals("") && insideEmail2.equals("") && !insideEmail3.equals("")) {
            assertEquals(outsideData.getAllEmails(), insideEmail3);
        } else if (insideEmail2.equals("") && insideEmail3.equals("") && !insideEmail1.equals("")) {
            assertEquals(outsideData.getAllEmails(), insideEmail1);
        } else if (!insideEmail1.equals("") && !insideEmail2.equals("") && !insideEmail3.equals("")) {
            assertEquals(outsideData.getAllEmails(), mergeAllInsideEmails(insideEmail1, insideEmail2, insideEmail3));
        } else if (insideEmail1.equals("") && insideEmail2.equals("") && insideEmail3.equals("")) {
        assertEquals(outsideData.getAllEmails(), mergeAllEmptyEmails(insideEmail1, insideEmail2, insideEmail3));
        } else {
            System.out.println("ERROR with conditions occurs. Investigation is needed!!!");
        }

        //Getting INSIDE's phones
        String outsidePhones = outsideData.getAllPhones();
        String insideHomePhone = insideData.getHomePhone();
        String insideMobilePhone = insideData.getMobilePhone();
        String insideWorkPhone = insideData.getWorkPhone();

        //Asserting by Phones
        assertEquals(outsideData.getAllPhones(), mergeAllInsideEmails(insideHomePhone, insideMobilePhone, insideWorkPhone));
    }

    public String mergeAllInsideEmails(String email, String email2, String email3) {
        String mergedEmails = email + " " + email2 + " " + email3;
        String replaced = mergedEmails.replaceAll(" ", "\n");
        return replaced;
    }

    public String mergeFirstSecondEmails(String email, String email2) {
        String mergedEmails = email + " " + email2;
        String replaced = mergedEmails.replaceAll(" ", "\n");
        return replaced;
    }

    public String mergeFirstThirdEmails(String email, String email3) {
        String mergedEmails = email + " " + email3;
        String replaced = mergedEmails.replaceAll(" ", "\n");
        return replaced;
    }

    public String mergeSecondThirdEmails(String email2, String email3) {
        String mergedEmails = email2 + " " + email3;
        String replaced = mergedEmails.replaceAll(" ", "\n");
        return replaced;
    }

    public String mergeAllEmptyEmails(String email, String email2, String email3) {
        String mergedEmails = email + " " + email2 + " " + email3;
        String replaced = mergedEmails.replaceAll(" ", "");
        return replaced;
    }
}
