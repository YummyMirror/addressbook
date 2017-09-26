package ru.anatoli.addressbook.tests;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import ru.anatoli.addressbook.models.ContactData;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Type;
import java.security.SecureRandom;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import static org.testng.Assert.assertEquals;

/**
 * Created by anatoli.anukevich on 7/10/2017.
 */
public class ContactModificationTests extends TestBase {
    @DataProvider
    public Iterator<Object[]> validDataForContactModificationFromJson() throws IOException {
        String json = "";
        try(BufferedReader bufferedReader = new BufferedReader(new FileReader(new File("src/test/resources/contactFileModification.json")))) {
            String line = bufferedReader.readLine();
            while (line != null) {
                json += line;
                line = bufferedReader.readLine();
            }
        }
        Gson gson = new Gson();
        Type collectionType = new TypeToken<List<ContactData>>(){}.getType(); //List<ContactData>.class
        List<ContactData> list = gson.fromJson(json, collectionType);
        return list.stream().map((contact) -> new Object[] {contact}).iterator();
    }

    @BeforeMethod
    public void ensurePrecondition() {
        applicationManager.getNavigationHelper().goToHomePage();
        if (applicationManager.getDbHelper().getContactSet().size() == 0) {
            //Choosing the random BirthDay
            List<String> days = Arrays.asList("1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20",
                                                "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31");
            int randomDayInt = new SecureRandom().nextInt(days.size());
            String randomDay = days.get(randomDayInt);

            //Choosing the random BirthMonth
            List<String> months = Arrays.asList("January", "February", "March", "April", "May", "June", "July", "August", "September", "October", "November", "December");
            int randomMonthInt = new SecureRandom().nextInt(months.size());
            String randomMonth = months.get(randomMonthInt);

            //Choosing the random BirthYear
            List<String> years = Arrays.asList("1", "2", "3", "4", "5", "6", "7", "8", "9", "0");
            int randomYearInt = new SecureRandom().nextInt(years.size());
            int randomYearInt2 = new SecureRandom().nextInt(years.size());
            String randomYear = years.get(randomYearInt);
            String randomYear2 = years.get(randomYearInt2);

            ContactData contactData = new ContactData().withFirstName("Temp first name")
                                                        .withMiddleName(null)
                                                        .withLastName("")
                                                        .withNickname(null)
                                                        .withPhoto(new File(""))
                                                        .withTitle(null)
                                                        .withCompany(null)
                                                        .withAddress(null)
                                                        .withHomePhone(null)
                                                        .withMobilePhone(null)
                                                        .withWorkPhone(null)
                                                        .withFax(null)
                                                        .withEmail(null)
                                                        .withEmail2(null)
                                                        .withEmail3(null)
                                                        .withHomepage(null)
                                                        .withBirthDay(randomDay)
                                                        .withBirthMonth(randomMonth)
                                                        .withBirthYear("19" + randomYear + randomYear2)
                                                        .withAnniversaryDay(null)
                                                        .withAnniversaryMonth(null)
                                                        .withAnniversaryYear(null)
                                                        .withSecondaryAddress(null)
                                                        .withSecondaryPhone(null)
                                                        .withSecondaryNotes(null);
            applicationManager.getContactHelper().createContact(contactData);
        }
    }

    @Test(enabled = true, dataProvider = "validDataForContactModificationFromJson")
    public void testContactModification(ContactData contactData) {
        //Getting Set of ContactData object model BEFORE modification
        Set<ContactData> before = applicationManager.getDbHelper().getContactSet();

        //Choosing the random Contact that will be modified
        ContactData modifiedContact = before.stream().findAny().get();

        contactData.withContactId(modifiedContact.getContactId());

        applicationManager.getContactHelper().modifyContact(contactData);

        //Getting Set of ContactData object model AFTER modification
        Set<ContactData> after = applicationManager.getDbHelper().getContactSet();

        //Asserting collections by SIZE
        assertEquals(before.size(), after.size());

        before.remove(modifiedContact);
        before.add(contactData);

        //Asserting by COLLECTIONS
        assertEquals(before, after);

        //Asserting UI data vs DB data
        compareUiVsDbContactData();
    }

    @Test(enabled = true, dataProvider = "validDataForContactModificationFromJson")
    public void testContactModificationFromDetailsPage(ContactData contactData) {
        //Getting Set of ContactData object model BEFORE modification
        Set<ContactData> before = applicationManager.getDbHelper().getContactSet();

        //Choosing the random Contact that will be modified
        ContactData modifiedContact = before.stream().findAny().get();

        contactData.withContactId(modifiedContact.getContactId());

        applicationManager.getContactHelper().modifyContactFromDetailsPage(contactData);

        //Getting Set of ContactData object model AFTER modification
        Set<ContactData> after = applicationManager.getDbHelper().getContactSet();

        //Asserting collections by SIZE
        assertEquals(before.size(), after.size());

        before.remove(modifiedContact);
        before.add(contactData);

        //Asserting by COLLECTIONS
        assertEquals(before, after);

        //Asserting UI data vs DB data
        compareUiVsDbContactData();
    }

    @Test(enabled = true, dataProvider = "validDataForContactModificationFromJson")
    public void testContactModificationFromBirthdaysPage(ContactData contactData) {
        //Getting Set of ContactData object model BEFORE modification
        Set<ContactData> before = applicationManager.getDbHelper().getContactSet();

        //Getting Set of ContactData object model with BirthDays and BirthMonths BEFORE modification
        Set<ContactData> birthDaysSet = applicationManager.getDbHelper().getContactSetWithBirthdays();

        //Choosing the random Contact that will be modified
        ContactData modifiedContact = birthDaysSet.stream().findAny().get();

        contactData.withContactId(modifiedContact.getContactId());

        applicationManager.getNavigationHelper().goToNextBirthdaysPage();
        applicationManager.getContactHelper().modifyContact(contactData);

        //Getting Set of ContactData object model AFTER modification
        Set<ContactData> after = applicationManager.getDbHelper().getContactSet();

        //Asserting collections by SIZE
        assertEquals(before.size(), after.size());

        //Asserting by COLLECTIONS
        assertEquals(before, after);

        //Asserting UI data vs DB data
        compareUiVsDbContactData();
    }
}
