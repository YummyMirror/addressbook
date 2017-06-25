package ru.anatoli.addressbook.tests;

import org.testng.annotations.Test;
import ru.anatoli.addressbook.models.GroupData;

public class GroupCreationTests extends TestBase {
    @Test
    public void testGroupCreation() {
        GroupData groupData = new GroupData().withGroupName("GroupName")
                                            .withGroupHeader("GroupHeader")
                                            .withGroupFooter("GroupFooter");
        applicationManager.getNavigationHelper().goToGroupsPage();
        applicationManager.initiateGroupCreation();
        applicationManager.fillGroupForm(groupData);
        applicationManager.submitGroupForm();
        applicationManager.returnToGroupsPage();
    }
}
