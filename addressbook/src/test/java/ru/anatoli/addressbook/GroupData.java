package ru.anatoli.addressbook;

/**
 * Created by anatoli.anukevich on 6/25/2017.
 */
public class GroupData {
    private String groupName;
    private String groupHeader;
    private String groupFooter;

    //Getters
    public String getGroupName() {
        return groupName;
    }

    public String getGroupHeader() {
        return groupHeader;
    }

    public String getGroupFooter() {
        return groupFooter;
    }

    //Setters
    public GroupData withGroupName(String groupName) {
        this.groupName = groupName;
        return this;
    }

    public GroupData withGroupHeader(String groupHeader) {
        this.groupHeader = groupHeader;
        return this;
    }

    public GroupData withGroupFooter(String groupFooter) {
        this.groupFooter = groupFooter;
        return this;
    }
}
