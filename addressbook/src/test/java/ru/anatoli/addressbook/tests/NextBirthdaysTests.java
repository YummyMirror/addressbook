package ru.anatoli.addressbook.tests;

import org.testng.annotations.Test;
import ru.anatoli.addressbook.models.ContactData;
import java.util.Set;
import static org.testng.Assert.assertEquals;

/**
 * Created by anatoli.anukevich on 8/24/2017.
 */
public class NextBirthdaysTests extends TestBase {
    @Test(enabled = true)
    public void testNextBirthdays() {
        applicationManager.getNavigationHelper().goToNextBirthdaysPage();

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
