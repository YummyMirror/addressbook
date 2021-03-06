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
import java.util.*;

import static org.testng.Assert.assertEquals;

/**
 * Created by anatoli.anukevich on 6/27/2017.
 */
public class ContactCreationTests extends TestBase {
    @DataProvider
    public Iterator<Object[]> validDataForContactCreationFromCsv() throws IOException {
        List<Object[]> list = new ArrayList<Object[]>();
        try(BufferedReader bufferedReader = new BufferedReader(new FileReader(new File("src/test/resources/contactFileCreation.csv")))) {
            String line = bufferedReader.readLine();
            while (line != null) {
                String[] splitData = line.split(";");
                list.add(new Object[] {new ContactData().withFirstName(splitData[0])
                                                        .withMiddleName(splitData[1])
                                                        .withLastName(splitData[2])
                                                        .withNickname(splitData[3])
                                                        .withPhoto(new File(splitData[4]))
                                                        .withTitle(splitData[5])
                                                        .withCompany(splitData[6])
                                                        .withAddress(splitData[7])
                                                        .withHomePhone(splitData[8])
                                                        .withMobilePhone(splitData[9])
                                                        .withWorkPhone(splitData[10])
                                                        .withFax(splitData[11])
                                                        .withEmail(splitData[12])
                                                        .withEmail2(splitData[13])
                                                        .withEmail3(splitData[14])
                                                        .withHomepage(splitData[15])
                                                        .withBirthDay(splitData[16])
                                                        .withBirthMonth(splitData[17])
                                                        .withBirthYear(splitData[18])
                                                        .withAnniversaryDay(splitData[19])
                                                        .withAnniversaryMonth(splitData[20])
                                                        .withAnniversaryYear(splitData[21])
                                                        .withSecondaryAddress(splitData[22])
                                                        .withSecondaryPhone(splitData[23])
                                                        .withSecondaryNotes(splitData[24])});
                line = bufferedReader.readLine();
            }
        }
        return list.iterator();
    }

    @DataProvider
    public Iterator<Object[]> validDataForContactCreationFromJson() throws IOException {
        String json = "";
        try(BufferedReader bufferedReader = new BufferedReader(new FileReader(new File("src/test/resources/contactFileCreation.json")))) {
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
    }

    @Test(enabled = true, dataProvider = "validDataForContactCreationFromJson")
    public void testContactCreation(ContactData contactData) {
        //Getting Set of ContactData object model BEFORE creation
        Set<ContactData> before = applicationManager.getDbHelper().getContactSet();

        applicationManager.getContactHelper().createContact(contactData);

        //Getting Set of ContactData object model AFTER creation
        Set<ContactData> after = applicationManager.getDbHelper().getContactSet();

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

        //Asserting UI data vs DB data
        compareUiVsDbContactData();
    }

    @Test(enabled = true)
    public void testContactCreationOneByOne() {
        //Getting Set of ContactData object model BEFORE creation
        Set<ContactData> before = applicationManager.getDbHelper().getContactSet();

        //Creating new CONTACT
        ContactData contactData = new ContactData().withFirstName("firstName")
                                                    .withMiddleName(null)
                                                    .withLastName("lastName")
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
                                                    .withBirthDay(null)
                                                    .withBirthMonth(null)
                                                    .withBirthYear(null)
                                                    .withAnniversaryDay(null)
                                                    .withAnniversaryMonth(null)
                                                    .withAnniversaryYear(null)
                                                    .withSecondaryAddress(null)
                                                    .withSecondaryPhone(null)
                                                    .withSecondaryNotes(null);

        ContactData contactData2 = new ContactData().withFirstName("111")
                                                    .withMiddleName(null)
                                                    .withLastName("222")
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
                                                    .withBirthDay(null)
                                                    .withBirthMonth(null)
                                                    .withBirthYear(null)
                                                    .withAnniversaryDay(null)
                                                    .withAnniversaryMonth(null)
                                                    .withAnniversaryYear(null)
                                                    .withSecondaryAddress(null)
                                                    .withSecondaryPhone(null)
                                                    .withSecondaryNotes(null);
        applicationManager.getContactHelper().createContactsOneByOne(contactData, contactData2);

        //Getting Set of ContactData object model AFTER creation
        Set<ContactData> after = applicationManager.getDbHelper().getContactSet();

        //Asserting collections by SIZE
        assertEquals(before.size() + 2, after.size());

        //Getting the MAX ID from AFTER collection
        int maxId = after
                    .stream()
                    .max((contact1, contact2) -> Integer.compare(contact1.getContactId(), contact2.getContactId()))
                    .get()
                    .getContactId();

        //Adding the PENULTIMATE ID to the Object model + Adding Object model to the BEFORE collection
        contactData.withContactId(maxId - 1);
        before.add(contactData);

        //Adding the MAX ID to the Object model + Adding Object model to the BEFORE collection
        contactData2.withContactId(maxId);
        before.add(contactData2);

        //Asserting by COLLECTIONS
        assertEquals(before, after);

        //Asserting UI data vs DB data
        compareUiVsDbContactData();
    }
}
