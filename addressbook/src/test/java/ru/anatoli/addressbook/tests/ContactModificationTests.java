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
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import static org.testng.Assert.assertEquals;

/**
 * Created by anatoli.anukevich on 7/10/2017.
 */
public class ContactModificationTests extends TestBase {
    @DataProvider
    public Iterator<Object[]> validDataForContactModificationFromJson() throws IOException {
        File file = new File("src/test/resources/contactFileModification.json");
        FileReader reader = new FileReader(file);
        BufferedReader bufferedReader = new BufferedReader(reader);
        String line = bufferedReader.readLine();
        String json = "";
        while (line != null) {
            json += line;
            line = bufferedReader.readLine();
        }
        Gson gson = new Gson();
        Type collectionType = new TypeToken<List<ContactData>>(){}.getType(); //List<ContactData>.class
        List<ContactData> list = gson.fromJson(json, collectionType);
        bufferedReader.close();
        reader.close();
        return list.stream().map((contact) -> new Object[] {contact}).iterator();
    }

    @BeforeMethod
    public void ensurePrecondition() {
        applicationManager.getNavigationHelper().goToHomePage();
        if (applicationManager.getDbHelper().getContactSet().size() == 0) {
            ContactData contactData = new ContactData().withFirstName("Forced created FirstName")
                                                        .withMiddleName("Forced created MiddleName")
                                                        .withLastName("Forced created LastName")
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
            applicationManager.getContactHelper().createContact(contactData);
        }
    }

    @Test(enabled = true, dataProvider = "validDataForContactModificationFromJson")
    public void testContactModification(ContactData contactData) {
        //Getting Set of ContactData object model BEFORE modification
        Set<ContactData> before = applicationManager.getDbHelper().getContactSet();

        //Choosing the random Contact that will be modified
        ContactData modifiedContact = before.iterator().next();

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
        Set<ContactData> ui = applicationManager.getContactHelper().getContactSet().stream()
                                                                                   .map((contact) -> new ContactData().withContactId(contact.getContactId())
                                                                                                                      .withFirstName(contact.getFirstName())
                                                                                                                      .withLastName(contact.getLastName())
                                                                                                                      .withAddress(contact.getAddress()))
                                                                                   .collect(Collectors.toSet());
        Set<ContactData> db = applicationManager.getDbHelper().getContactSet().stream()
                                                                              .map((contact) -> new ContactData().withContactId(contact.getContactId())
                                                                                                                 .withFirstName(contact.getFirstName())
                                                                                                                 .withLastName(contact.getLastName())
                                                                                                                 .withAddress(contact.getAddress()))
                                                                              .collect(Collectors.toSet());
        assertEquals(ui, db);
    }

    @Test(enabled = true, dataProvider = "validDataForContactModificationFromJson")
    public void testContactModificationFromDetailsPage(ContactData contactData) {
        //Getting Set of ContactData object model BEFORE modification
        Set<ContactData> before = applicationManager.getDbHelper().getContactSet();

        //Choosing the random Contact that will be modified
        ContactData modifiedContact = before.iterator().next();

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
        Set<ContactData> ui = applicationManager.getContactHelper().getContactSet().stream()
                                                                                   .map((contact) -> new ContactData().withContactId(contact.getContactId())
                                                                                                                      .withFirstName(contact.getFirstName())
                                                                                                                      .withLastName(contact.getLastName())
                                                                                                                      .withAddress(contact.getAddress()))
                                                                                   .collect(Collectors.toSet());
        Set<ContactData> db = applicationManager.getDbHelper().getContactSet().stream()
                                                                              .map((contact) -> new ContactData().withContactId(contact.getContactId())
                                                                                                                 .withFirstName(contact.getFirstName())
                                                                                                                 .withLastName(contact.getLastName())
                                                                                                                 .withAddress(contact.getAddress()))
                                                                              .collect(Collectors.toSet());
        assertEquals(ui, db);
    }
}
