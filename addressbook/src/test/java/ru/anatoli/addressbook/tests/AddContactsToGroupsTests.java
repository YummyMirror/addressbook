package ru.anatoli.addressbook.tests;

import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.anatoli.addressbook.models.ContactData;
import ru.anatoli.addressbook.models.GroupData;
import java.io.File;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertFalse;
import static org.testng.Assert.assertTrue;

/**
 * Created by anatoli.anukevich on 8/27/2017.
 */
public class AddContactsToGroupsTests extends TestBase {
    @BeforeMethod
    public void ensurePrecondition() {
        if (applicationManager.getDbHelper().getGroupSet().size() == 0) {
            GroupData groupData = new GroupData().withGroupName("Test GroupName")
                                                 .withGroupHeader(null)
                                                 .withGroupFooter(null);
            applicationManager.getNavigationHelper().goToGroupsPage();
            applicationManager.getGroupHelper().createGroup(groupData);
        }

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
                                                        .withBirthDay(null)
                                                        .withBirthMonth(null)
                                                        .withBirthYear(null)
                                                        .withAnniversaryDay(null)
                                                        .withAnniversaryMonth(null)
                                                        .withAnniversaryYear(null)
                                                        .withSecondaryAddress(null)
                                                        .withSecondaryPhone(null)
                                                        .withSecondaryNotes(null);
            applicationManager.getContactHelper().initiateContactCreation();
            applicationManager.getContactHelper().createContact(contactData);
        }

        applicationManager.getNavigationHelper().goToHomePage();
    }

    @Test(enabled = true)
    public void testAddContactToGroup() {
        //Choosing the random Contact that will be moved
        ContactData movedContact = applicationManager.getContactHelper().getContactSet().iterator().next();

        //Getting the random GroupData name
        String randomGroupName = applicationManager.getDbHelper().getGroupSet().iterator().next().getGroupName();

        applicationManager.getContactHelper().addContactToGroup(movedContact, randomGroupName);

        String currentGroup = applicationManager.getContactHelper().getCurrentGroup();

        //Asserting by GroupNames
        assertTrue(randomGroupName.contains(currentGroup));

        String removeFromGroupButtonName = applicationManager.getContactHelper().getRemoveFromGroupButtonName();

        //Asserting by 'Remove from group' button
        assertTrue(removeFromGroupButtonName.contains(currentGroup));
    }

    @Test(enabled = true)
    public void testAddAllContactsToGroup() {
        //Getting the random GroupData name
        String randomGroupName = applicationManager.getDbHelper().getGroupSet().iterator().next().getGroupName();

        applicationManager.getContactHelper().addAllContactsToGroup(randomGroupName);

        String currentGroup = applicationManager.getContactHelper().getCurrentGroup();
        //Asserting by GroupNames
        assertTrue(randomGroupName.contains(currentGroup));

        String removeFromGroupButtonName = applicationManager.getContactHelper().getRemoveFromGroupButtonName();

        //Asserting by 'Remove from group' button
        assertTrue(removeFromGroupButtonName.contains(currentGroup));
    }

    @Test(enabled = true)
    public void testAddContactToGroupWithoutSelection() {
        //Getting the random GroupData name
        String randomGroupName = applicationManager.getDbHelper().getGroupSet().iterator().next().getGroupName();

        applicationManager.getContactHelper().selectValueInDropDownList(By.name("to_group"), randomGroupName);
        applicationManager.getContactHelper().addContactToGroup();

        String errorMessage = applicationManager.getContactHelper().getErrorMessageWhileAddContactToGroupWithoutSelection();

        //Asserting by Error message
        assertEquals(errorMessage, "No users selected. Please use the checkbox to select a user.");

        //Asserting by not presenting the 'Remove from group' button
        assertFalse(applicationManager.getContactHelper().isElementPresent(By.name("remove")));
    }
}
