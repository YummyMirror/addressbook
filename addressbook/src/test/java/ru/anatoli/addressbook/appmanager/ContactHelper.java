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

    public void selectRemovedContactById(int id) {
        wd.findElement(By.xpath("//input[@id='" + id + "']")).click();
    }

    public void deleteSelectedContact() {
        wd.findElement(By.xpath("//input[@value='Delete']")).click();
    }

    public void confirmContactDeletion() {
        wd.switchTo().alert().accept();
    }

    public void selectModifiedContactById(int id) {
        wd.findElement(By.xpath("//a[@href='edit.php?id=" + id + "']")).click();
    }

    public void submitModifiedContactForm() {
        wd.findElement(By.xpath("//input[@value='Update']")).click();
    }

    public void selectAllContacts() {
        wd.findElement(By.id("MassCB")).click();
    }

    public void confirmSelectAllAlert() {
        wd.switchTo().alert().accept();
    }

    public void cancelContactDeletion() {
        wd.switchTo().alert().dismiss();
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
}
