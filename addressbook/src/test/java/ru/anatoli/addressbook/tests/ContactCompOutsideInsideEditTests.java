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
            assertEquals(outsideData.getAllEmails(), mergeSecondThirdItems(insideEmail2, insideEmail3));
        } else if (insideEmail2.equals("") && !insideEmail1.equals("") && !insideEmail3.equals("")) {
            assertEquals(outsideData.getAllEmails(), mergeFirstThirdItems(insideEmail1, insideEmail3));
        } else if (insideEmail3.equals("") && !insideEmail1.equals("") && !insideEmail2.equals("")) {
            assertEquals(outsideData.getAllEmails(), mergeFirstSecondItems(insideEmail1, insideEmail2));
        } else if (insideEmail1.equals("") && insideEmail3.equals("") && !insideEmail2.equals("")) {
            assertEquals(outsideData.getAllEmails(), insideEmail2);
        } else if (insideEmail1.equals("") && insideEmail2.equals("") && !insideEmail3.equals("")) {
            assertEquals(outsideData.getAllEmails(), insideEmail3);
        } else if (insideEmail2.equals("") && insideEmail3.equals("") && !insideEmail1.equals("")) {
            assertEquals(outsideData.getAllEmails(), insideEmail1);
        } else if (!insideEmail1.equals("") && !insideEmail2.equals("") && !insideEmail3.equals("")) {
            assertEquals(outsideData.getAllEmails(), mergeAllInsideItems(insideEmail1, insideEmail2, insideEmail3));
        } else if (insideEmail1.equals("") && insideEmail2.equals("") && insideEmail3.equals("")) {
        assertEquals(outsideData.getAllEmails(), mergeAllEmptyItems(insideEmail1, insideEmail2, insideEmail3));
        } else {
            System.out.println("ERROR with EMAILS' conditions occurs. Investigation is needed!!!");
        }

        //Getting INSIDE's phones
        String insideHomePhone = insideData.getHomePhone();
        String insideMobilePhone = insideData.getMobilePhone();
        String insideWorkPhone = insideData.getWorkPhone();

        //Asserting by Phones
        if (insideHomePhone.equals("") && !insideMobilePhone.equals("") && !insideWorkPhone.equals("")) {
            assertEquals(outsideData.getAllPhones(), mergeSecondThirdItems(removeUnnecessarySpecChars(insideMobilePhone), removeUnnecessarySpecChars(insideWorkPhone)));
        } else if (insideMobilePhone.equals("") && !insideHomePhone.equals("") && !insideWorkPhone.equals("")) {
            assertEquals(outsideData.getAllPhones(), mergeFirstThirdItems(removeUnnecessarySpecChars(insideHomePhone), removeUnnecessarySpecChars(insideWorkPhone)));
        } else if (insideWorkPhone.equals("") && !insideHomePhone.equals("") && !insideMobilePhone.equals("")) {
            assertEquals(outsideData.getAllPhones(), mergeFirstSecondItems(removeUnnecessarySpecChars(insideHomePhone), removeUnnecessarySpecChars(insideMobilePhone)));
        } else if (insideHomePhone.equals("") && insideWorkPhone.equals("") && !insideMobilePhone.equals("")) {
            assertEquals(outsideData.getAllPhones(), removeUnnecessarySpecChars(insideMobilePhone));
        } else if (insideHomePhone.equals("") && insideMobilePhone.equals("") && !insideWorkPhone.equals("")) {
            assertEquals(outsideData.getAllPhones(), removeUnnecessarySpecChars(insideWorkPhone));
        } else if (insideMobilePhone.equals("") && insideWorkPhone.equals("") && !insideHomePhone.equals("")) {
            assertEquals(outsideData.getAllPhones(), removeUnnecessarySpecChars(insideHomePhone));
        } else if (!insideHomePhone.equals("") && !insideMobilePhone.equals("") && !insideWorkPhone.equals("")) {
            assertEquals(outsideData.getAllPhones(), mergeAllInsideItems(removeUnnecessarySpecChars(insideHomePhone), removeUnnecessarySpecChars(insideMobilePhone), removeUnnecessarySpecChars(insideWorkPhone)));
        } else if (insideHomePhone.equals("") && insideMobilePhone.equals("") && insideWorkPhone.equals("")) {
            assertEquals(outsideData.getAllPhones(), mergeAllEmptyItems(removeUnnecessarySpecChars(insideHomePhone), removeUnnecessarySpecChars(insideMobilePhone), removeUnnecessarySpecChars(insideWorkPhone)));
        } else {
            System.out.println("ERROR with PHONES' conditions occurs. Investigation is needed!!!");
        }
    }

    public String mergeAllInsideItems(String item, String item2, String item3) {
        String mergedItems = item + " " + item2 + " " + item3;
        return mergedItems.replaceAll(" ", "\n");
    }

    public String mergeFirstSecondItems(String item, String item2) {
        String mergedItems = item + " " + item2;
        return mergedItems.replaceAll(" ", "\n");
    }

    public String mergeFirstThirdItems(String item, String item3) {
        String mergedItems = item + " " + item3;
        return mergedItems.replaceAll(" ", "\n");
    }

    public String mergeSecondThirdItems(String item2, String item3) {
        String mergedItems = item2 + " " + item3;
        return mergedItems.replaceAll(" ", "\n");
    }

    public String mergeAllEmptyItems(String item, String item2, String item3) {
        String mergedItems = item + " " + item2 + " " + item3;
        return mergedItems.replaceAll(" ", "");
    }

    public String removeUnnecessarySpecChars(String item ) {
        return item.replaceAll("[-./\\s]", "");
        //return item.replaceAll("[\\s]|[-]|[.]|[/]", "");
    }
}
