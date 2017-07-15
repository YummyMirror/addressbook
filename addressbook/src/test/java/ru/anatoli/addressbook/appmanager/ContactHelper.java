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
        return wd.findElement(By.name("firstname")).getAttribute("value");
    }

    public String getMiddleName() {
        return wd.findElement(By.name("middlename")).getAttribute("value");
    }

    public String getLastName() {
        return wd.findElement(By.name("lastname")).getAttribute("value");
    }

    public String getNickname() {
        return wd.findElement(By.name("nickname")).getAttribute("value");
    }

    public String getCompany() {
        return wd.findElement(By.name("company")).getAttribute("value");
    }

    public String getTitle() {
        return wd.findElement(By.name("title")).getAttribute("value");
    }

    public String getAddress() {
        return wd.findElement(By.name("address")).getText();
    }

    public String getHomePhone() {
        return wd.findElement(By.name("home")).getAttribute("value");
    }

    public String getMobilePhone() {
        return wd.findElement(By.name("mobile")).getAttribute("value");
    }

    public String getWorkPhone() {
        return wd.findElement(By.name("work")).getAttribute("value");
    }

    public String getFax() {
        return wd.findElement(By.name("fax")).getAttribute("value");
    }

    public String getEmail1() {
        return wd.findElement(By.name("email")).getAttribute("value");
    }

    public String getEmail2() {
        return wd.findElement(By.name("email2")).getAttribute("value");
    }

    public String getEmail3() {
        return wd.findElement(By.name("email3")).getAttribute("value");
    }

    public String getBirthDay() {
        return wd.findElement(By.name("bday")).getAttribute("value");
    }

    public String getBirthMonth() {
        return wd.findElement(By.name("bmonth")).getAttribute("value");
    }

    public String getBirthYear() {
        return wd.findElement(By.name("byear")).getAttribute("value");
    }

    public String getAnniversaryDay() {
        return wd.findElement(By.name("aday")).getAttribute("value");
    }

    public String getAnniversaryMonth() {
        return wd.findElement(By.name("amonth")).getAttribute("value");
    }

    public String getAnniversaryYear() {
        return wd.findElement(By.name("ayear")).getAttribute("value");
    }

    public String getSecondaryAddress() {
        return wd.findElement(By.name("address2")).getText();
    }

    public String getSecondaryHome() {
        return wd.findElement(By.name("phone2")).getAttribute("value");
    }

    public String getSecondaryNotes() {
        return wd.findElement(By.name("notes")).getText();
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
                ContactData contact = new ContactData().withContactId(id)
                                                        .withFirstName(firstName)
                                                        .withLastName(lastName);
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
