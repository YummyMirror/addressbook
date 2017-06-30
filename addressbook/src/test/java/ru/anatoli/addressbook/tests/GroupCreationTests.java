package ru.anatoli.addressbook.tests;

import org.testng.annotations.Test;
import ru.anatoli.addressbook.models.GroupData;
import java.util.Set;
import static org.testng.Assert.assertEquals;

public class GroupCreationTests extends TestBase {
    @Test
    public void testGroupCreation() {
        GroupData groupData = new GroupData().withGroupName("GroupName")
                                            .withGroupHeader("GroupHeader")
                                            .withGroupFooter(null);

        applicationManager.getNavigationHelper().goToGroupsPage();

        //Getting Set of GroupData object model BEFORE creation
        Set<GroupData> before = applicationManager.getGroupHelper().getGroupsHash();

        //Creating new GROUP
        applicationManager.getGroupHelper().createGroup(groupData);

        //Getting Set of GroupData object model AFTER creation
        Set<GroupData> after = applicationManager.getGroupHelper().getGroupsHash();

        //Asserting collections by SIZE
        assertEquals(after.size(), before.size() + 1);

        //Setting MAX id if  for object model GroupData
        groupData.withGroupId(after
                            .stream()
                            .max((group1, group2) -> Integer.compare(group1.getGroupId(), group2.getGroupId()))
                            .get()
                            .getGroupId());
        before.add(groupData);

        //Asserting by COLLECTIONS
        assertEquals(before, after);
    }
}
