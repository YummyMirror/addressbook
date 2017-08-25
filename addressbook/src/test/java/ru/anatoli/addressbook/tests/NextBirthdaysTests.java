package ru.anatoli.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.anatoli.addressbook.models.ContactData;
import java.io.File;
import java.util.Set;
import static org.testng.Assert.assertEquals;

/**
 * Created by anatoli.anukevich on 8/24/2017.
 */
public class NextBirthdaysTests extends TestBase {
    @BeforeMethod
    public void ensurePrecondition() {
        if (applicationManager.getDbHelper().getContactSet().size() == 0) {
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
                                                        .withBirthDay("1")
                                                        .withBirthMonth("September")
                                                        .withBirthYear(null)
                                                        .withAnniversaryDay(null)
                                                        .withAnniversaryMonth(null)
                                                        .withAnniversaryYear(null)
                                                        .withSecondaryAddress(null)
                                                        .withSecondaryPhone(null)
                                                        .withSecondaryNotes(null);
            applicationManager.getContactHelper().createContact(contactData);
        }
        applicationManager.getNavigationHelper().goToNextBirthdaysPage();
    }

    @Test(enabled = true)
    public void testNextBirthdays() {
        //Getting Set of ContactData object model from DataBase with specified BirthDays and BirthMonths values
        Set<ContactData> contactSetWithBirthdays = applicationManager.getDbHelper().getContactSetWithBirthdays();

        //Getting Set of ContactData object model from 'Next Birthdays' page
        Set<ContactData> birthDays =  applicationManager.getContactHelper().getBirthDaysSet();

        //Asserting collections by SIZE
        assertEquals(contactSetWithBirthdays.size(), birthDays.size());

        //Asserting by COLLECTIONS
        assertEquals(contactSetWithBirthdays, birthDays);
    }
}
