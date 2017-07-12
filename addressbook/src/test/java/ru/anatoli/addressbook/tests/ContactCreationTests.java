package ru.anatoli.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.anatoli.addressbook.models.ContactData;
import java.io.File;
import java.util.Arrays;
import java.util.List;
import java.util.Set;
import static org.testng.Assert.assertEquals;

/**
 * Created by anatoli.anukevich on 6/27/2017.
 */
public class ContactCreationTests extends TestBase {
    @BeforeMethod
    public void ensurePrecondition() {
        applicationManager.getNavigationHelper().goToHomePage();
    }

    @Test(enabled = false)
    public void testContactCreation() {
        //Getting Set of ContactData object model BEFORE creation
        Set<ContactData> before = applicationManager.getContactHelper().getContactSet();

        //Lists with DAYS and MONTHS
        List<String> daysInMonth = Arrays.asList("1", "2", "3", "4", "5", "6", "7", "8", "9", "10",
                                                "11", "12", "13", "14", "15", "16", "17", "18", "19", "20",
                                                "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31");
        List<String> monthsInYear = Arrays.asList("January", "February", "March", "April", "May", "June",
                                                    "July", "August", "September", "October", "November", "December");

        //Getting random item for DAYS and MONTHS
        String randomDay = daysInMonth.iterator().next();
        String randomMonth = monthsInYear.iterator().next();

        //Creating new CONTACT
        ContactData contactData = new ContactData().withFirstName("firstName")
                                                    .withMiddleName("middleName")
                                                    .withLastName("lastName")
                                                    .withNickname("nickname")
                                                    .withPhoto(new File("src/test/resources/NBA.jpeg"))
                                                    .withTitle("Lead department")
                                                    .withCompany("Google")
                                                    .withAddress("California, LA")
                                                    .withHomePhone("111-222-333")
                                                    .withMobilePhone("123/123/123")
                                                    .withWorkPhone("123-456-789")
                                                    .withFax("111222333")
                                                    .withEmail("1@mail.ru")
                                                    .withEmail2("2@mail.ru")
                                                    .withEmail3("3@mail.ru")
                                                    .withHomepage("www.google.com")
                                                    .withBirthDay(randomDay)
                                                    .withBirthMonth(randomMonth)
                                                    .withBirthYear("1900")
                                                    .withAnniversaryDay(randomDay)
                                                    .withAnniversaryMonth(randomMonth)
                                                    .withAnniversaryYear("1950")
                                                    .withSecondaryAddress("secondary Address")
                                                    .withSecondaryHome("secondary Home")
                                                    .withSecondaryNotes("secondary Notes");
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
                                                    .withSecondaryHome(null)
                                                    .withSecondaryNotes(null);
        applicationManager.getContactHelper().createContactsOneByOne(contactData);

        //Getting Set of ContactData object model AFTER creation
        Set<ContactData> after = applicationManager.getContactHelper().getContactSet();
    }
}
