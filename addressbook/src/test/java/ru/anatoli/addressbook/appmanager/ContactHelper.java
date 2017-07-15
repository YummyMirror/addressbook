package ru.anatoli.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import ru.anatoli.addressbook.models.ContactData;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by anatoli.anukevich on 6/27/2017.
 */
public class ContactHelper extends HelperBase {
    //Constructor
    public ContactHelper(WebDriver wd) {
        super(wd);
    }

    //Additional methods
    public void goToHomePage() {
        click(By.linkText("home"));
    }

    public void initiateContactCreation() {
        if (wd.findElement(By.tagName("h1")).getText().equals("Edit / add address book entry") && isElementPresent(By.name("submit"))) {
            return;
        } else {
            click(By.linkText("add new"));
        }
    }

    public void inputContactForm(ContactData contactData) {
        input(By.name("firstname"), contactData.getFirstName());
        input(By.name("middlename"), contactData.getMiddleName());
        input(By.name("lastname"), contactData.getLastName());
        input(By.name("nickname"), contactData.getNickname());
        attach(By.name("photo"), contactData.getPhoto());
        input(By.name("title"), contactData.getTitle());
        input(By.name("company"), contactData.getCompany());
        input(By.name("address"), contactData.getAddress());
        input(By.name("home"), contactData.getHomePhone());
        input(By.name("mobile"), contactData.getMobilePhone());
        input(By.name("work"), contactData.getWorkPhone());
        input(By.name("fax"), contactData.getFax());
        input(By.name("email"), contactData.getEmail());
        input(By.name("email2"), contactData.getEmail2());
        input(By.name("email3"), contactData.getEmail3());
        input(By.name("homepage"), contactData.getHomepage());
        select(By.name("bday"), contactData.getBirthDay());
        select(By.name("bmonth"), contactData.getBirthMonth());
        input(By.name("byear"), contactData.getBirthYear());
        select(By.name("aday"), contactData.getAnniversaryDay());
        select(By.name("amonth"), contactData.getAnniversaryMonth());
        input(By.name("ayear"), contactData.getAnniversaryYear());
        input(By.name("address2"), contactData.getSecondaryAddress());
        input(By.name("phone2"), contactData.getSecondaryHome());
        input(By.name("notes"), contactData.getSecondaryNotes());
    }

    public void submitContactForm() {
        click(By.name("submit"));
    }

    public void returnToHomePage() {
        click(By.linkText("home page"));
    }

    public void clickAddNextButton() {
        click(By.linkText("add next"));
    }

    public void selectRemovedContactById(int id) {
        click(By.xpath("//input[@id='" + id + "']"));
    }

    public void deleteSelectedContact() {
        click(By.xpath("//input[@value='Delete']"));
    }

    public void confirmContactDeletion() {
        confirmAlert();
    }

    public void selectModifiedContactById(int id) {
        click(By.xpath("//a[@href='edit.php?id=" + id + "']"));
    }

    public void submitModifiedContactForm() {
        click(By.xpath("//input[@value='Update']"));
    }

    public void selectAllContacts() {
        click(By.id("MassCB"));
    }

    public void confirmSelectAllAlert() {
        confirmAlert();
    }

    public void cancelContactDeletion() {
        cancelAlert();
    }

    public String getFirstName() {
        return getAttribute(By.name("firstname"), "value");
    }

    public String getMiddleName() {
        return getAttribute(By.name("middlename"), "value");
    }

    public String getLastName() {
        return getAttribute(By.name("lastname"), "value");
    }

    public String getNickname() {
        return getAttribute(By.name("nickname"), "value");
    }

    public String getCompany() {
        return getAttribute(By.name("company"), "value");
    }

    public String getTitle() {
        return getAttribute(By.name("title"), "value");
    }

    public String getAddress() {
        return getText(By.name("address"));
    }

    public String getHomePhone() {
        return getAttribute(By.name("home"), "value");
    }

    public String getMobilePhone() {
        return getAttribute(By.name("mobile"), "value");
    }

    public String getWorkPhone() {
        return getAttribute(By.name("work"), "value");
    }

    public String getFax() {
        return getAttribute(By.name("fax"), "value");
    }

    public String getEmail1() {
        return getAttribute(By.name("email"), "value");
    }

    public String getEmail2() {
        return getAttribute(By.name("email2"), "value");
    }

    public String getEmail3() {
        return getAttribute(By.name("email3"), "value");
    }

    public String getBirthDay() {
        return getAttribute(By.name("bday"), "value");
    }

    public String getBirthMonth() {
        return getAttribute(By.name("bmonth"), "value");
    }

    public String getBirthYear() {
        return getAttribute(By.name("byear"), "value");
    }

    public String getAnniversaryDay() {
        return getAttribute(By.name("aday"), "value");
    }

    public String getAnniversaryMonth() {
        return getAttribute(By.name("amonth"), "value");
    }

    public String getAnniversaryYear() {
        return getAttribute(By.name("ayear"), "value");
    }

    public String getSecondaryAddress() {
        return getText(By.name("address2"));
    }

    public String getSecondaryHome() {
        return getAttribute(By.name("phone2"), "value");
    }

    public String getSecondaryNotes() {
        return getText(By.name("notes"));
    }

    private Set<ContactData> contactCache = null;

    public Set<ContactData> getContactSet() {
        if (contactCache != null) {
            return new HashSet<ContactData>(contactCache);
        } else {
            Set<ContactData> contactCache = new HashSet<ContactData>();
            List<WebElement> rows = wd.findElements(By.xpath("//tr[@name = 'entry']"));
            for (int i = 0; i < rows.size(); i++) {
                List<WebElement> cells = rows.get(i).findElements(By.tagName("td"));
                int id = Integer.parseInt(cells.get(0).findElement(By.tagName("input")).getAttribute("id"));
                String firstName = cells.get(2).getText();
                String lastName = cells.get(1).getText();
                String address = cells.get(3).getText();
                String emails = cells.get(4).getText();
                String phones = cells.get(5).getText();
                ContactData contact = new ContactData().withContactId(id)
                                                        .withFirstName(firstName)
                                                        .withLastName(lastName)
                                                        .withAddress(address)
                                                        .withAllEmails(emails)
                                                        .withAllPhones(phones);
                contactCache.add(contact);
            }
            return new HashSet<ContactData>(contactCache);
        }
    }

    //Manipulations with CONTACTS
    public void createContact(ContactData contactData) {
        initiateContactCreation();
        inputContactForm(contactData);
        submitContactForm();
        contactCache = null;
        returnToHomePage();
    }

    public void removeContact(ContactData removedContact) {
        selectRemovedContactById(removedContact.getContactId());
        deleteSelectedContact();
        confirmContactDeletion();
        goToHomePage();
    }

    public void modifyContact(ContactData contactData) {
        selectModifiedContactById(contactData.getContactId());
        inputContactForm(contactData);
        submitModifiedContactForm();
        returnToHomePage();
    }

    public void deleteAllSelectedContacts() {
        selectAllContacts();
        deleteSelectedContact();
        confirmContactDeletion();
        goToHomePage();
    }

    public void deleteAllContactsWithoutSelection() {
        deleteSelectedContact();
        confirmSelectAllAlert();
    }

    public void cancelDeletionAllSelectedContacts() {
        selectAllContacts();
        deleteSelectedContact();
        cancelContactDeletion();
    }

    public void removeContactFromUpdateForm(ContactData removedContact) {
        selectModifiedContactById(removedContact.getContactId());
        deleteSelectedContact();
        goToHomePage();
    }

    public void createContactsOneByOne(ContactData contactData, ContactData contactData2) {
        initiateContactCreation();
        inputContactForm(contactData);
        submitContactForm();
        addNextContact(contactData2);
        returnToHomePage();
    }

    public void addNextContact(ContactData contactData) {
        clickAddNextButton();
        inputContactForm(contactData);
        submitContactForm();
    }

    public ContactData getContactDataFromEditForm(ContactData comparedContact) {
        selectModifiedContactById(comparedContact.getContactId());
        String firstName = getFirstName();
        String middleName = getMiddleName();
        String lastName = getLastName();
        String nickname = getNickname();
        String company = getCompany();
        String title = getTitle();
        String address = getAddress();
        String homePhone = getHomePhone();
        String mobilePhone = getMobilePhone();
        String workPhone = getWorkPhone();
        String fax = getFax();
        String email1 = getEmail1();
        String email2 = getEmail2();
        String email3 = getEmail3();
        String birthDay = getBirthDay();
        String birthMonth = getBirthMonth();
        String birthYear = getBirthYear();
        String anniversaryDay = getAnniversaryDay();
        String anniversaryMonth = getAnniversaryMonth();
        String anniversaryYear = getAnniversaryYear();
        String secondaryAddress = getSecondaryAddress();
        String secondaryHome = getSecondaryHome();
        String secondaryNotes = getSecondaryNotes();

        ContactData contactData = new ContactData().withFirstName(firstName)
                                                    .withMiddleName(middleName)
                                                    .withLastName(lastName)
                                                    .withNickname(nickname)
                                                    .withCompany(company)
                                                    .withTitle(title)
                                                    .withAddress(address)
                                                    .withHomePhone(homePhone)
                                                    .withMobilePhone(mobilePhone)
                                                    .withWorkPhone(workPhone)
                                                    .withFax(fax)
                                                    .withEmail(email1)
                                                    .withEmail2(email2)
                                                    .withEmail3(email3)
                                                    .withBirthDay(birthDay)
                                                    .withBirthMonth(birthMonth)
                                                    .withBirthYear(birthYear)
                                                    .withAnniversaryDay(anniversaryDay)
                                                    .withAnniversaryMonth(anniversaryMonth)
                                                    .withAnniversaryYear(anniversaryYear)
                                                    .withSecondaryAddress(secondaryAddress)
                                                    .withSecondaryHome(secondaryHome)
                                                    .withSecondaryNotes(secondaryNotes);
        back();
        return contactData;
    }
}
