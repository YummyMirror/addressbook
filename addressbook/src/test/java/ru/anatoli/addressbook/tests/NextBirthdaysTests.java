package ru.anatoli.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.anatoli.addressbook.models.ContactData;
import java.io.File;
import java.lang.reflect.Array;
import java.security.SecureRandom;
import java.util.Arrays;
import java.util.List;
import java.util.Set;
import static org.testng.Assert.assertEquals;

/**
 * Created by anatoli.anukevich on 8/24/2017.
 */
public class NextBirthdaysTests extends TestBase {
    @BeforeMethod
    public void ensurePrecondition() {
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
