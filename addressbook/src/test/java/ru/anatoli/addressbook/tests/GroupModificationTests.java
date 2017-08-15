package ru.anatoli.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.anatoli.addressbook.models.GroupData;
import java.util.Set;
import java.util.stream.Collectors;

import static org.testng.Assert.assertEquals;

/**
 * Created by anatoli.anukevich on 6/27/2017.
 */
public class GroupModificationTests extends TestBase {
    @BeforeMethod
    public void ensurePrecondition() {
        applicationManager.getNavigationHelper().goToGroupsPage();
        if (applicationManager.getDbHelper().getGroupSet().size() == 0) {
            GroupData groupData = new GroupData().withGroupName("Temp name")
                                                .withGroupHeader("Temp header")
                                                .withGroupFooter(null);
            applicationManager.getGroupHelper().createGroup(groupData);
        }
    }

    @Test(enabled = true)
    public void testGroupModification() {
        //Getting Set of GroupData object model BEFORE modification
        Set<GroupData> before = applicationManager.getDbHelper().getGroupSet();

        //Choosing the random Group that will be modified
        GroupData modifiedGroup = before.iterator().next();

        GroupData groupData = new GroupData().withGroupId(modifiedGroup.getGroupId())
                                            .withGroupName("GroupName")
                                            .withGroupHeader("Header")
                                            .withGroupFooter("Footer");
        applicationManager.getGroupHelper().modifyGroup(groupData);

        //Getting Set of GroupData object model AFTER modification
        Set<GroupData> after = applicationManager.getDbHelper().getGroupSet();

        //Asserting collections by SIZE
        assertEquals(before.size(), after.size());

        before.remove(modifiedGroup);
        before.add(groupData);

        //Asserting by COLLECTIONS
        assertEquals(before, after);

        //Asserting UI data vs DB data
        Set<GroupData> uiData = applicationManager.getGroupHelper().getGroupSet();
        Set<GroupData> dbData = applicationManager.getDbHelper().getGroupSet();

        assertEquals(uiData, dbData.stream()
                                   .map((group) -> new GroupData().withGroupId(group.getGroupId())
                                                                  .withGroupName(group.getGroupName()))
                                   .collect(Collectors.toSet()));
    }

    @Test(enabled = true)
    public void testGroupModificationWithoutSelection() {
        //Getting Set of GroupData object model BEFORE modification
        Set<GroupData> before = applicationManager.getDbHelper().getGroupSet();

        applicationManager.getGroupHelper().initiateGroupModification();
        String noticeTitle = applicationManager.getGroupHelper().getErrorMessageDuringDeletionAndModification();

        //Asserting by NOTICE title
        assertEquals(noticeTitle, "Notice");

        GroupData groupData = new GroupData().withGroupName("Test Name")
                                                .withGroupHeader("Test Header")
                                                .withGroupFooter("Test Footer");
        applicationManager.getGroupHelper().fillGroupForm(groupData);
        applicationManager.getGroupHelper().submitGroupModificationForm();
        String invalidIdMessage = applicationManager.getGroupHelper().getErrorTextAfterClickingUpdate();

        //Asserting by INVALID ID error message
        assertEquals(invalidIdMessage, "Invalid ID.");

        applicationManager.getGroupHelper().returnToGroupsPage();

        //Getting Set of GroupData object model AFTER modification
        Set<GroupData> after = applicationManager.getDbHelper().getGroupSet();

        //Asserting collections by SIZE
        assertEquals(before.size(), after.size());

        //Asserting by COLLECTIONS
        assertEquals(before, after);

        //Asserting UI data vs DB data
        Set<GroupData> uiData = applicationManager.getGroupHelper().getGroupSet();
        Set<GroupData> dbData = applicationManager.getDbHelper().getGroupSet();

        assertEquals(uiData, dbData.stream()
                                   .map((group) -> new GroupData().withGroupId(group.getGroupId())
                                                                  .withGroupName(group.getGroupName()))
                                   .collect(Collectors.toSet()));
    }
}
