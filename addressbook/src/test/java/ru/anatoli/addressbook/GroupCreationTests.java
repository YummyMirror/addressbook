package ru.anatoli.addressbook;

import org.testng.annotations.Test;

public class GroupCreationTests extends TestBase {
    @Test
    public void testGroupCreation() {
        GroupData groupData = new GroupData().withGroupName("GroupName")
                                            .withGroupHeader("GroupHeader")
                                            .withGroupFooter("GroupFooter");
        applicationManager.goToGroupsPage();
        applicationManager.initiateGroupCreation();
        applicationManager.fillGroupForm(groupData);
        applicationManager.submitGroupForm();
        applicationManager.returnToGroupsPage();
    }
}
