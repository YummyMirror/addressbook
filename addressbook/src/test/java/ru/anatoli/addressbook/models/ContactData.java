package ru.anatoli.addressbook.models;

import com.google.gson.annotations.Expose;
import org.hibernate.annotations.Type;
import javax.persistence.*;
import java.io.File;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by anatoli.anukevich on 6/27/2017.
 */
@Entity
@Table(name = "addressbook")
public class ContactData {
    @Id
    @Column(name = "id")
    @Type(type = "int")
    private int id = Integer.MAX_VALUE;

    @Expose
    @Column(name = "firstname")
    @Type(type = "string")
    private String firstName;

    @Expose
    @Column(name = "middlename")
    @Type(type = "string")
    private String middleName;

    @Expose
    @Column(name = "lastname")
    @Type(type = "string")
    private String lastName;

    @Expose
    @Column(name = "nickname")
    @Type(type = "string")
    private String nickname;

    @Expose
    @Column(name = "photo")
    @Type(type = "text")
    private String photo;

    @Expose
    @Column(name = "title")
    @Type(type = "string")
    private String title;

    @Expose
    @Column(name = "company")
    @Type(type = "string")
    private String company;

    @Expose
    @Column(name = "address")
    @Type(type = "text")
    private String address;

    @Expose
    @Transient //Ignoring
    private String allPhones;

    @Expose
    @Column(name = "home")
    @Type(type = "text")
    private String homePhone;

    @Expose
    @Column(name = "mobile")
    @Type(type = "text")
    private String mobilePhone;

    @Expose
    @Column(name = "work")
    @Type(type = "text")
    private String workPhone;

    @Expose
    @Column(name = "fax")
    @Type(type = "text")
    private String fax;

    @Expose
    @Transient //Ignoring
    private String allEmails;

    @Expose
    @Column(name = "email")
    @Type(type = "text")
    private String email;

    @Expose
    @Column(name = "email2")
    @Type(type = "text")
    private String email2;

    @Expose
    @Column(name = "email3")
    @Type(type = "text")
    private String email3;

    @Expose
    @Column(name = "homepage")
    @Type(type = "text")
    private String homepage;

    @Expose
    @Column(name = "bday", columnDefinition = "TINYINT")
    private String birthDay;

    @Expose
    @Column(name = "bmonth")
    @Type(type = "string")
    private String birthMonth;

    @Expose
    @Column(name = "byear")
    @Type(type = "string")
    private String birthYear;

    @Expose
    @Column(name = "aday", columnDefinition = "TINYINT")
    private String anniversaryDay;

    @Expose
    @Column(name = "amonth")
    @Type(type = "string")
    private String anniversaryMonth;

    @Expose
    @Column(name = "ayear")
    @Type(type = "string")
    private String anniversaryYear;

    @Expose
    @Column(name = "address2")
    @Type(type = "text")
    private String secondaryAddress;

    @Expose
    @Column(name = "phone2")
    @Type(type = "text")
    private String secondaryPhone;

    @Expose
    @Column(name = "notes")
    @Type(type = "text")
    private String secondaryNotes;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "address_in_groups",
               joinColumns = @JoinColumn(name = "id"),
               inverseJoinColumns = @JoinColumn(name = "group_id"))
    private Set<GroupData> groups = new HashSet<GroupData>(0);

    //Getters
    public int getContactId() {
        return id;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getMiddleName() {
        return middleName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getNickname() {
        return nickname;
    }

    public File getPhoto() {
        return new File(photo);
    }

    public String getTitle() {
        return title;
    }

    public String getCompany() {
        return company;
    }

    public String getAddress() {
        return address;
    }

    public String getAllPhones() {
        return allPhones;
    }

    public String getHomePhone() {
        return homePhone;
    }

    public String getMobilePhone() {
        return mobilePhone;
    }

    public String getWorkPhone() {
        return workPhone;
    }

    public String getFax() {
        return fax;
    }

    public String getAllEmails() {
        return allEmails;
    }

    public String getEmail() {
        return email;
    }

    public String getEmail2() {
        return email2;
    }

    public String getEmail3() {
        return email3;
    }

    public String getHomepage() {
        return homepage;
    }

    public String getBirthDay() {
        return birthDay;
    }

    public String getBirthMonth() {
        return birthMonth;
    }

    public String getBirthYear() {
        return birthYear;
    }

    public String getAnniversaryDay() {
        return anniversaryDay;
    }

    public String getAnniversaryMonth() {
        return anniversaryMonth;
    }

    public String getAnniversaryYear() {
        return anniversaryYear;
    }

    public String getSecondaryAddress() {
        return secondaryAddress;
    }

    public String getSecondaryPhone() {
        return secondaryPhone;
    }

    public String getSecondaryNotes() {
        return secondaryNotes;
    }

    public Set<GroupData> getGroups() {
        return groups;
    }

    //Setters
    public ContactData withContactId(int id) {
        this.id = id;
        return this;
    }

    public ContactData withFirstName(String firstName) {
        this.firstName = firstName;
        return this;
    }

    public ContactData withMiddleName(String middleName) {
        this.middleName = middleName;
        return this;
    }

    public ContactData withLastName(String lastName) {
        this.lastName = lastName;
        return this;
    }

    public ContactData withNickname(String nickname) {
        this.nickname = nickname;
        return this;
    }

    public ContactData withPhoto(File photo) {
        this.photo = photo.getPath();
        return this;
    }

    public ContactData withTitle(String title) {
        this.title = title;
        return this;
    }

    public ContactData withCompany(String company) {
        this.company = company;
        return this;
    }

    public ContactData withAddress(String address) {
        this.address = address;
        return this;
    }

    public ContactData withAllPhones(String allPhones) {
        this.allPhones = allPhones;
        return this;
    }

    public ContactData withHomePhone(String homePhone) {
        this.homePhone = homePhone;
        return this;
    }

    public ContactData withMobilePhone(String mobilePhone) {
        this.mobilePhone = mobilePhone;
        return this;
    }

    public ContactData withWorkPhone(String workPhone) {
        this.workPhone = workPhone;
        return this;
    }

    public ContactData withFax(String fax) {
        this.fax = fax;
        return  this;
    }

    public ContactData withAllEmails(String allEmails) {
        this.allEmails = allEmails;
        return this;
    }

    public ContactData withEmail(String email) {
        this.email = email;
        return this;
    }

    public ContactData withEmail2(String email2) {
        this.email2 = email2;
        return this;
    }

    public ContactData withEmail3(String email3) {
        this.email3 = email3;
        return this;
    }

    public ContactData withHomepage(String homepage) {
        this.homepage = homepage;
        return this;
    }

    public ContactData withBirthDay(String birthDay) {
        this.birthDay = birthDay;
        return this;
    }

    public ContactData withBirthMonth(String birthMonth) {
        this.birthMonth = birthMonth;
        return this;
    }

    public ContactData withBirthYear(String birthYear) {
        this.birthYear = birthYear;
        return this;
    }

    public ContactData withAnniversaryDay(String anniversaryDay) {
        this.anniversaryDay = anniversaryDay;
        return this;
    }

    public ContactData withAnniversaryMonth(String anniversaryMonth) {
        this.anniversaryMonth = anniversaryMonth;
        return this;
    }

    public ContactData withAnniversaryYear(String anniversaryYear) {
        this.anniversaryYear = anniversaryYear;
        return this;
    }

    public ContactData withSecondaryAddress(String secondaryAddress) {
        this.secondaryAddress = secondaryAddress;
        return this;
    }

    public ContactData withSecondaryPhone(String secondaryPhone) {
        this.secondaryPhone = secondaryPhone;
        return this;
    }

    public ContactData withSecondaryNotes(String secondaryNotes) {
        this.secondaryNotes = secondaryNotes;
        return this;
    }

    public void setGroups(Set<GroupData> groups) {
        this.groups = groups;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ContactData that = (ContactData) o;

        if (id != that.id) return false;
        return firstName != null ? firstName.equals(that.firstName) : that.firstName == null;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (firstName != null ? firstName.hashCode() : 0);
        return result;
    }
}
