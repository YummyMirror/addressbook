package ru.anatoli.addressbook.models;

import com.google.gson.annotations.Expose;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by anatoli.anukevich on 6/25/2017.
 */
@Entity
@Table(name = "group_list")
public class GroupData {
    @Id
    @Column(name = "group_id")
    @Type(type = "int")
    private int id = Integer.MAX_VALUE;

    @Expose
    @Column(name = "group_name")
    @Type(type = "string")
    private String groupName;

    @Expose
    @Column(name = "group_header")
    @Type(type = "text")
    private String groupHeader;

    @Expose
    @Column(name = "group_footer")
    @Type(type = "text")
    private String groupFooter;

    @ManyToMany(fetch = FetchType.EAGER, mappedBy = "groups")
    private Set<ContactData> contacts = new HashSet<ContactData>(0);

    //Getters
    public int getGroupId() {
        return id;
    }

    public String getGroupName() {
        return groupName;
    }

    public String getGroupHeader() {
        return groupHeader;
    }

    public String getGroupFooter() {
        return groupFooter;
    }

    public Set<ContactData> getContacts() {
        return contacts;
    }

    //Setters
    public GroupData withGroupId(int id) {
        this.id = id;
        return this;
    }

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

    public void setContacts(Set<ContactData> contacts) {
        this.contacts = contacts;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        GroupData groupData = (GroupData) o;

        if (id != groupData.id) return false;
        return groupName != null ? groupName.equals(groupData.groupName) : groupData.groupName == null;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (groupName != null ? groupName.hashCode() : 0);
        return result;
    }
}
