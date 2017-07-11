package ru.anatoli.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.anatoli.addressbook.models.GroupData;
import java.util.Set;
import static org.testng.Assert.assertEquals;

/**
 * Created by anatoli.anukevich on 7/11/2017.
 */
public class GroupCompOutsideInsideTests extends TestBase {
    @BeforeMethod
    public void ensurePrecondition() {
        applicationManager.getNavigationHelper().goToGroupsPage();
        if (applicationManager.getGroupHelper().getGroupSet().size() == 0) {
            GroupData groupData = new GroupData().withGroupName("Forced created groupName")
                                                    .withGroupHeader("Forced created groupHeader")
                                                    .withGroupFooter("Forced created groupFooter");
            applicationManager.getGroupHelper().createGroup(groupData);
        }
    }

    @Test
    public void testGroupCompOutsideInside() {
        //Getting Set of GroupData object model BEFORE test execution
        Set<GroupData> before = applicationManager.getGroupHelper().getGroupSet();

        //Choosing the random Group that will be compared as OUTSIDE data
        GroupData outsideData = before.iterator().next();

        //Getting INSIDE data from edit form
        GroupData insideData = applicationManager.getGroupHelper().getGroupDataFromEditForm(outsideData);

        //Asserting by groupName
        assertEquals(outsideData.getGroupName(), insideData.getGroupName());
    }
}
