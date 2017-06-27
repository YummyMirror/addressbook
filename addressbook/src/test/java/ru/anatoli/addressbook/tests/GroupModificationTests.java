package ru.anatoli.addressbook.tests;

import org.testng.annotations.Test;
import ru.anatoli.addressbook.models.GroupData;

/**
 * Created by anatoli.anukevich on 6/27/2017.
 */
public class GroupModificationTests extends TestBase {
    @Test
    public void testGroupModification() {
        GroupData groupData = new GroupData().withGroupName("GroupName")
                                            .withGroupHeader("Header")
                                            .withGroupFooter("Footer");

        applicationManager.getNavigationHelper().goToGroupsPage();
        applicationManager.getGroupHelper().selectGroup();
        applicationManager.getGroupHelper().initiateGroupModification();
        applicationManager.getGroupHelper().fillGroupForm(groupData);
        applicationManager.getGroupHelper().submitGroupModificationForm();
        applicationManager.getGroupHelper().returnToGroupsPage();
    }
}
