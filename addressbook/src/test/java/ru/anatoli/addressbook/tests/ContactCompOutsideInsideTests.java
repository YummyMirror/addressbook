package ru.anatoli.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.anatoli.addressbook.models.ContactData;
import java.io.File;
import java.util.*;
import static org.testng.Assert.assertEquals;

/**
 * Created by anatoli.anukevich on 7/14/2017.
 */
public class ContactCompOutsideInsideTests extends TestBase {
    @BeforeMethod
    public void ensurePrecondition() {
        applicationManager.getNavigationHelper().goToHomePage();
        if (applicationManager.getDbHelper().getContactSet().size() == 0) {
            ContactData contactData = new ContactData().withFirstName("FirstName")
                                                        .withMiddleName(null)
                                                        .withLastName("LastName")
                                                        .withNickname(null)
                                                        .withPhoto(new File(""))
                                                        .withCompany("Google")
                                                        .withTitle("Lead")
                                                        .withAddress("Test Address")
                                                        .withHomePhone("111-111-111")
                                                        .withMobilePhone("222.222.222")
                                                        .withWorkPhone("111.222-333/444 555")
                                                        .withFax(null)
                                                        .withEmail("1@mail.ru")
                                                        .withEmail2("2_2@gmail.com")
                                                        .withEmail3("3@yandex.ru")
                                                        .withHomepage(null)
                                                        .withBirthDay("1")
                                                        .withBirthMonth(null)
                                                        .withBirthYear(null)
                                                        .withAnniversaryDay(null)
                                                        .withAnniversaryMonth(null)
                                                        .withAnniversaryYear(null)
                                                        .withSecondaryAddress(null)
                                                        .withSecondaryPhone(null)
                                                        .withSecondaryNotes(null);
            applicationManager.getContactHelper().createContact(contactData);
        }
    }

    @Test(enabled = true)
    public void testContactCompOutsideInsideUpdateAllFields() {
        //Getting Set of ContactData object model BEFORE test execution
        Set<ContactData> before = applicationManager.getContactHelper().getContactSet();

        //Choosing the random Contact that will be compared as OUTSIDE data
        ContactData outsideData = before.stream().findAny().get();

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
        String insideEmail4 = "";
        String mergeAllEmailsInside = mergeAllItemsInside(insideEmail1, insideEmail2, insideEmail3, insideEmail4);

        //Asserting by Emails
        assertEquals(outsideAllEmails, mergeAllEmailsInside);

        //Getting OUTSIDE's and INSIDE's phones
        String outsideAllPhones = outsideData.getAllPhones();
        String insideHomePhone = insideData.getHomePhone();
        String insideMobilePhone = insideData.getMobilePhone();
        String insideWorkPhone = insideData.getWorkPhone();
        String insideSecondaryPhone = insideData.getSecondaryPhone();
        String mergeAllPhonesInside = mergeAllItemsInside(removeUnnecessarySpecChars(insideHomePhone),
                                                            removeUnnecessarySpecChars(insideMobilePhone),
                                                            removeUnnecessarySpecChars(insideWorkPhone),
                                                            removeUnnecessarySpecChars(insideSecondaryPhone));

        //Asserting by Phones
        assertEquals(outsideAllPhones, mergeAllPhonesInside);
    }

    @Test(enabled = true)
    public void testContactCompEditFormDataWithDetailsPageDataByAllFields() {
        //Getting Set of ContactData object model BEFORE test execution
        Set<ContactData> before = applicationManager.getContactHelper().getContactSet();

        //Choosing the random Contact that will be compared as OUTSIDE data
        ContactData outsideData = before.stream().findAny().get();

        //If at least one field is empty in the Edit form - Ignore Test execution
        applicationManager.getContactHelper().skipTestIfFormNotAllFilled(outsideData);

        //Getting INSIDE data from details page
        ContactData dataFromDetailsPage = applicationManager.getContactHelper().getContactDataFromDetailsForm(outsideData);

        //Getting INSIDE data from edit form
        ContactData dataFromEditPage = applicationManager.getContactHelper().getContactDataFromEditForm(outsideData);

        //Asserting by FirstName, MiddleName, LastName
        assertEquals(dataFromDetailsPage.getFirstName(), dataFromEditPage.getFirstName());
        assertEquals(dataFromDetailsPage.getMiddleName(), dataFromEditPage.getMiddleName());
        assertEquals(dataFromDetailsPage.getLastName(), dataFromEditPage.getLastName());

        //Asserting by Nickname, Company, Title, Address, Homepage
        assertEquals(dataFromDetailsPage.getNickname(), dataFromEditPage.getNickname());
        assertEquals(dataFromDetailsPage.getCompany(), dataFromEditPage.getCompany());
        assertEquals(dataFromDetailsPage.getTitle(), dataFromEditPage.getTitle());
        assertEquals(dataFromDetailsPage.getAddress(), dataFromEditPage.getAddress());
        assertEquals(dataFromDetailsPage.getHomepage(), dataFromEditPage.getHomepage());

        //Asserting by Phones
        assertEquals(dataFromDetailsPage.getHomePhone(), dataFromEditPage.getHomePhone());
        assertEquals(dataFromDetailsPage.getMobilePhone(), dataFromEditPage.getMobilePhone());
        assertEquals(dataFromDetailsPage.getWorkPhone(), dataFromEditPage.getWorkPhone());

        //Asserting by Emails
        assertEquals(dataFromDetailsPage.getEmail(), dataFromEditPage.getEmail());
        assertEquals(dataFromDetailsPage.getEmail2(), dataFromEditPage.getEmail2());
        assertEquals(dataFromDetailsPage.getEmail3(), dataFromEditPage.getEmail3());

        //Asserting by Birth Info
        assertEquals(dataFromDetailsPage.getBirthDay(), dataFromEditPage.getBirthDay());
        assertEquals(dataFromDetailsPage.getBirthMonth(), dataFromEditPage.getBirthMonth());
        assertEquals(dataFromDetailsPage.getBirthYear(), dataFromEditPage.getBirthYear());

        //Asserting by Anniversary Info
        assertEquals(dataFromDetailsPage.getAnniversaryDay(), dataFromEditPage.getAnniversaryDay());
        assertEquals(dataFromDetailsPage.getAnniversaryMonth(), dataFromEditPage.getAnniversaryMonth());
        assertEquals(dataFromDetailsPage.getAnniversaryYear(), dataFromEditPage.getAnniversaryYear());

        //Asserting by Secondary Info
        assertEquals(dataFromDetailsPage.getSecondaryAddress(), dataFromEditPage.getSecondaryAddress());
        assertEquals(dataFromDetailsPage.getSecondaryPhone(), dataFromEditPage.getSecondaryPhone());
        assertEquals(dataFromDetailsPage.getSecondaryNotes(), dataFromEditPage.getSecondaryNotes());
    }

    @Test(enabled = true)
    public void testContactCompEditFormWithDetailsPageDataByNames() {
        //Getting Set of ContactData object model BEFORE test execution
        Set<ContactData> before = applicationManager.getContactHelper().getContactSet();

        //Choosing the random Contact
        ContactData randomContact = before.stream().findAny().get();

        //If all Edit form is filled in - Ignore Test execution
        applicationManager.getContactHelper().skipTestIfFormIsAllFilled(randomContact);

        //Getting INSIDE data from Edit form
        ContactData contactDataFromEditForm = applicationManager.getContactHelper().getContactDataFromEditForm(randomContact);

        //Create Collection with FirstName, MiddleName, LastName
        Set<String> insideDataEditForm = new HashSet<String>(Arrays.asList(contactDataFromEditForm.getFirstName(),
                                                                            contactDataFromEditForm.getMiddleName(),
                                                                            contactDataFromEditForm.getLastName()));
        //Remove empty elements from Collection
        insideDataEditForm.removeAll(Collections.singleton(""));
        //insideDataEditForm.removeAll(Arrays.asList("", null));

        //Getting Collection with FirstName, MiddleName, LastName from Details page
        Set<String> insideDataDetailsForm = applicationManager.getContactHelper().getContactNamesFromDetailsForm(randomContact);

        //Asserting collections by SIZE
        assertEquals(insideDataEditForm.size(), insideDataDetailsForm.size());
    }

    public String mergeAllItemsInside(String item, String item2, String item3, String item4) {
        String mergedItems = "";

        if (!item.isEmpty() && !item2.isEmpty() && !item3.isEmpty() && !item4.isEmpty()) {
            mergedItems = item + " " + item2 + " " + item3 + " " + item4;
        } else if (item.isEmpty() && !item2.isEmpty() && !item3.isEmpty() && !item4.isEmpty()) {
            mergedItems = item2 + " " + item3 + " " + item4;
        } else if (item2.isEmpty() && !item.isEmpty() && !item3.isEmpty() && !item4.isEmpty()) {
            mergedItems = item + " " + item3 + " " + item4;
        } else if (item3.isEmpty() && !item.isEmpty() && !item2.isEmpty() && !item4.isEmpty()) {
            mergedItems = item + " " + item2 + " " + item4;
        } else if (item4.isEmpty() && !item.isEmpty() && !item2.isEmpty() && !item3.isEmpty()) {
            mergedItems = item + " " + item2 + " " + item3;
        } else if (item.isEmpty() && item2.isEmpty() && !item3.isEmpty() && !item4.isEmpty()) {
            mergedItems = item3 + " " + item4;
        } else if (item.isEmpty() && item3.isEmpty() && !item2.isEmpty() && !item4.isEmpty()) {
            mergedItems = item2 + " " + item4;
        } else if (item3.isEmpty() && item4.isEmpty() && !item.isEmpty() && !item2.isEmpty()) {
            mergedItems = item + " " + item2;
        } else if (item2.isEmpty() && item4.isEmpty() && !item.isEmpty() && !item3.isEmpty()) {
            mergedItems = item + " " + item3;
        } else if (item2.isEmpty() && item3.isEmpty() && !item.isEmpty() && !item4.isEmpty()) {
            mergedItems = item + " " + item4;
        } else if (item.isEmpty() && item4.isEmpty() && !item2.isEmpty() && !item3.isEmpty()) {
            mergedItems = item2 + " " + item3;
        } else if (item2.isEmpty() && item3.isEmpty() && item4.isEmpty() && !item.isEmpty()) {
            mergedItems = item;
        } else if (item.isEmpty() && item3.isEmpty() && item4.isEmpty() && !item2.isEmpty()) {
            mergedItems = item2;
        } else if (item.isEmpty() && item2.isEmpty() && item4.isEmpty() && !item3.isEmpty()) {
            mergedItems = item3;
        } else if (item.isEmpty() && item2.isEmpty() && item3.isEmpty() && !item4.isEmpty()) {
            mergedItems = item4;
        } else if (item.isEmpty() && item2.isEmpty() && item3.isEmpty() && item4.isEmpty()) {
            mergedItems = "";
        } else {
            System.out.println("ERROR occurred. Investigation is needed!");
        }
        String replaced = mergedItems.replaceAll(" ", "\n");
        return replaced;
    }

    public String removeUnnecessarySpecChars(String item ) {
        return item.replaceAll("[-./\\s()]", "");
        //return item.replaceAll("[\\s]|[-]|[.]|[/]", "");
    }
}
