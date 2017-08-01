package ru.anatoli.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import ru.anatoli.addressbook.models.ContactData;
import java.io.*;
import java.util.*;
import static org.testng.Assert.assertEquals;

/**
 * Created by anatoli.anukevich on 6/27/2017.
 */
public class ContactCreationTests extends TestBase {
    @DataProvider
    public Iterator<Object[]> validDataForContactCreationFromCsv() throws IOException {
        List<Object[]> list = new ArrayList<Object[]>();
        File file = new File("src/test/resources/contactFile.csv");
        FileReader reader = new FileReader(file);
        BufferedReader bufferedReader = new BufferedReader(reader);
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
        bufferedReader.close();
        reader.close();
        return list.iterator();
    }

    @BeforeMethod
    public void ensurePrecondition() {
        applicationManager.getNavigationHelper().goToHomePage();
    }

    @Test(enabled = true, dataProvider = "validDataForContactCreationFromCsv")
    public void testContactCreation(ContactData contactData) {
        //Getting Set of ContactData object model BEFORE creation
        Set<ContactData> before = applicationManager.getContactHelper().getContactSet();

        applicationManager.getContactHelper().createContact(contactData);

        //Getting Set of ContactData object model AFTER creation
        Set<ContactData> after = applicationManager.getContactHelper().getContactSet();

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
    }

    @Test(enabled = true)
    public void testContactCreationOneByOne() {
        //Getting Set of ContactData object model BEFORE creation
        Set<ContactData> before = applicationManager.getContactHelper().getContactSet();

        //Creating new CONTACT
        ContactData contactData = new ContactData().withFirstName("firstName")
                                                    .withMiddleName(null)
                                                    .withLastName("lastName")
                                                    .withNickname(null)
                                                    .withPhoto(new File("src/test/resources/NBA.jpeg"))
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
                                                    .withPhoto(new File("src/test/resources/Kobe.jpg"))
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
        Set<ContactData> after = applicationManager.getContactHelper().getContactSet();

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
    }
}
