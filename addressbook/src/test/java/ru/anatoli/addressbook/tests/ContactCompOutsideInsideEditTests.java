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

        //Getting OUTSIDE's and INSIDE's emails
        String outsideAllEmails = outsideData.getAllEmails();
        String insideEmail1 = insideData.getEmail();
        String insideEmail2 = insideData.getEmail2();
        String insideEmail3 = insideData.getEmail3();
        String mergeAllEmailsInside = mergeAllItemsInside(insideEmail1, insideEmail2, insideEmail3);

        //Asserting by Emails
        if (insideEmail1.equals("") && !insideEmail2.equals("") && !insideEmail3.equals("")) {
            assertEquals(outsideAllEmails, mergeAllEmailsInside);
        } else if (insideEmail2.equals("") && !insideEmail1.equals("") && !insideEmail3.equals("")) {
            assertEquals(outsideAllEmails, mergeAllEmailsInside);
        } else if (insideEmail3.equals("") && !insideEmail1.equals("") && !insideEmail2.equals("")) {
            assertEquals(outsideAllEmails, mergeAllEmailsInside);
        } else if (insideEmail1.equals("") && insideEmail3.equals("") && !insideEmail2.equals("")) {
            assertEquals(outsideAllEmails, mergeAllEmailsInside);
        } else if (insideEmail1.equals("") && insideEmail2.equals("") && !insideEmail3.equals("")) {
            assertEquals(outsideAllEmails, mergeAllEmailsInside);
        } else if (insideEmail2.equals("") && insideEmail3.equals("") && !insideEmail1.equals("")) {
            assertEquals(outsideAllEmails, mergeAllEmailsInside);
        } else if (!insideEmail1.equals("") && !insideEmail2.equals("") && !insideEmail3.equals("")) {
            assertEquals(outsideAllEmails, mergeAllEmailsInside);
        } else if (insideEmail1.equals("") && insideEmail2.equals("") && insideEmail3.equals("")) {
            assertEquals(outsideAllEmails, mergeAllEmailsInside);
        } else {
            System.out.println("ERROR with EMAILS' conditions occurs. Investigation is needed!!!");
        }

        //Getting OUTSIDE's and INSIDE's phones
        String outsideAllPhones = outsideData.getAllPhones();
        String insideHomePhone = insideData.getHomePhone();
        String insideMobilePhone = insideData.getMobilePhone();
        String insideWorkPhone = insideData.getWorkPhone();
        String mergeAllPhonesInside = mergeAllItemsInside(removeUnnecessarySpecChars(insideHomePhone), removeUnnecessarySpecChars(insideMobilePhone), removeUnnecessarySpecChars(insideWorkPhone));

        //Asserting by Phones
        if (insideHomePhone.equals("") && !insideMobilePhone.equals("") && !insideWorkPhone.equals("")) {
            assertEquals(outsideAllPhones, mergeAllPhonesInside);
        } else if (insideMobilePhone.equals("") && !insideHomePhone.equals("") && !insideWorkPhone.equals("")) {
            assertEquals(outsideAllPhones, mergeAllPhonesInside);
        } else if (insideWorkPhone.equals("") && !insideHomePhone.equals("") && !insideMobilePhone.equals("")) {
            assertEquals(outsideAllPhones, mergeAllPhonesInside);
        } else if (insideHomePhone.equals("") && insideWorkPhone.equals("") && !insideMobilePhone.equals("")) {
            assertEquals(outsideAllPhones, mergeAllPhonesInside);
        } else if (insideHomePhone.equals("") && insideMobilePhone.equals("") && !insideWorkPhone.equals("")) {
            assertEquals(outsideAllPhones, mergeAllPhonesInside);
        } else if (insideMobilePhone.equals("") && insideWorkPhone.equals("") && !insideHomePhone.equals("")) {
            assertEquals(outsideAllPhones, mergeAllPhonesInside);
        } else if (!insideHomePhone.equals("") && !insideMobilePhone.equals("") && !insideWorkPhone.equals("")) {
            assertEquals(outsideAllPhones, mergeAllPhonesInside);
        } else if (insideHomePhone.equals("") && insideMobilePhone.equals("") && insideWorkPhone.equals("")) {
            assertEquals(outsideAllPhones, mergeAllPhonesInside);
        } else {
            System.out.println("ERROR with PHONES' conditions occurs. Investigation is needed!!!");
        }
    }

    public String mergeAllItemsInside(String item, String item2, String item3) {
        String mergedItems = "";

        if (!item.equals("") && !item2.equals("") && !item3.equals("")) {
            mergedItems = item + " " + item2 + " " + item3;
        } else if (item.equals("") && !item2.equals("") && !item3.equals("")) {
            mergedItems = item2 + " " + item3;
        } else if (item2.equals("") && !item.equals("") && !item3.equals("")) {
            mergedItems = item + " " + item3;
        } else if (item3.equals("") && !item.equals("") && !item2.equals("")) {
            mergedItems = item + " " + item2;
        } else if (item.equals("") && item2.equals("") && !item3.equals("")) {
            mergedItems = item3;
        } else if (item.equals("") && item3.equals("") && !item2.equals("")) {
            mergedItems = item2;
        } else if (item2.equals("") && item3.equals("") && !item.equals("")) {
            mergedItems = item;
        } else if (item.equals("") && item2.equals("") && item3.equals("")) {
            mergedItems = "";
        }
        String replaced = mergedItems.replaceAll(" ", "\n");
        return replaced;
    }

    public String removeUnnecessarySpecChars(String item ) {
        return item.replaceAll("[-./\\s]", "");
        //return item.replaceAll("[\\s]|[-]|[.]|[/]", "");
    }
}
