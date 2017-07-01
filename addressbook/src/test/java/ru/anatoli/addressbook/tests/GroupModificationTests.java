package ru.anatoli.addressbook.tests;

import org.testng.annotations.Test;
import ru.anatoli.addressbook.models.GroupData;
import java.util.Set;
import static org.testng.Assert.assertEquals;

/**
 * Created by anatoli.anukevich on 6/27/2017.
 */
public class GroupModificationTests extends TestBase {
    @Test
    public void testGroupModification() {
        applicationManager.getNavigationHelper().goToGroupsPage();
        //Getting Set of GroupData object model BEFORE modification
        Set<GroupData> before = applicationManager.getGroupHelper().getGroupSet();

        //Choosing the random Group that will be modified
        GroupData modifiedGroup = before.iterator().next();

        GroupData groupData = new GroupData().withGroupId(modifiedGroup.getGroupId())
                                            .withGroupName("GroupName")
                                            .withGroupHeader("Header")
                                            .withGroupFooter("Footer");
        applicationManager.getGroupHelper().modifyGroup(groupData);

        //Getting Set of GroupData object model AFTER modification
        Set<GroupData> after = applicationManager.getGroupHelper().getGroupSet();

        //Asserting collections by SIZE
        assertEquals(before.size(), after.size());
    }
}
