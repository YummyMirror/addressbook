package ru.anatoli.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import ru.anatoli.addressbook.models.GroupData;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by anatoli.anukevich on 6/25/2017.
 */
public class GroupHelper extends HelperBase {
    //Constructor
    public GroupHelper(WebDriver wd) {
        super(wd);
    }

    //Additional methods
    public void initiateGroupCreation() {
        click(By.name("new"));
    }

    public void fillGroupForm(GroupData groupData) {
        input(By.name("group_name"), groupData.getGroupName());
        input(By.name("group_header"), groupData.getGroupHeader());
        input(By.name("group_footer"), groupData.getGroupFooter());
    }

    public void submitGroupCreationForm() {
        click(By.name("submit"));
    }

    public void returnToGroupsPage() {
        click(By.linkText("group page"));
    }

    public void initiateGroupModification() {
        click(By.name("edit"));
    }

    public void submitGroupModificationForm() {
        click(By.name("update"));
    }

    public void selectGroupById(int id) {
        click(By.xpath("//input[@value = '" + id + "']"));
    }

    public void deleteSelectedGroup() {
        click(By.name("delete"));
    }

    public String getErrorMessageDuringDeletionAndModification() {
        return getText(By.xpath("//*[@id='content']/b[1]"));
    }

    public String getErrorTextAfterClickingUpdate() {
        String errorMessage = getText(By.cssSelector("div.msgbox"));
        String splitErrorMessage[] = errorMessage.split("\n");
        return splitErrorMessage[0];
    }

    public String getGroupNameFromEditForm() {
        return getAttribute(By.name("group_name"), "value");
    }

    public String getGroupHeaderFromEditForm() {
        return getText(By.name("group_header"));
    }

    public String getGroupFooterFromEditForm() {
        return getText(By.name("group_footer"));
    }

    private Set<GroupData> groupCache = null;

    public Set<GroupData> getGroupSet() {
        if (groupCache != null) {
            return new HashSet<GroupData>(groupCache);
        } else {
            Set<GroupData> groupCache = new HashSet<GroupData>();
            List<WebElement> webElements = wd.findElements(By.cssSelector("span.group"));
            for (int i = 0; i < webElements.size(); i++) {
                int id = Integer.parseInt(webElements.get(i).findElement(By.tagName("input")).getAttribute("value"));
                String groupName = webElements.get(i).getText();
                GroupData group = new GroupData().withGroupId(id)
                                                .withGroupName(groupName)
                                                .withGroupHeader(null)
                                                .withGroupFooter(null);
                groupCache.add(group);
            }
            return new HashSet<GroupData>(groupCache);
        }
    }

    //Manipulations with GROUPS
    public void createGroup(GroupData groupData) {
        initiateGroupCreation();
        fillGroupForm(groupData);
        submitGroupCreationForm();
        groupCache = null;
        returnToGroupsPage();
    }

    public void deleteGroup(GroupData removedGroup) {
        selectGroupById(removedGroup.getGroupId());
        deleteSelectedGroup();
        groupCache = null;
        returnToGroupsPage();
    }

    public void deleteAllSelectedGroups(Set<GroupData> before) {
        for (GroupData group : before) {
            selectGroupById(group.getGroupId());
        }
        deleteSelectedGroup();
        returnToGroupsPage();
    }

    public void modifyGroup(GroupData groupData) {
        selectGroupById(groupData.getGroupId());
        initiateGroupModification();
        fillGroupForm(groupData);
        submitGroupModificationForm();
        groupCache = null;
        returnToGroupsPage();
    }

    public GroupData getGroupDataFromEditForm(GroupData outsideData) {
        selectGroupById(outsideData.getGroupId());
        initiateGroupModification();
        String groupNameInside = getGroupNameFromEditForm();
        String groupHeaderInside = getGroupHeaderFromEditForm();
        String groupFooterInside = getGroupFooterFromEditForm();
        GroupData groupData = new GroupData().withGroupName(groupNameInside)
                                                .withGroupHeader(groupHeaderInside)
                                                .withGroupFooter(groupFooterInside);
        back();
        return groupData;
    }
}
