package ru.anatoli.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.SkipException;
import ru.anatoli.addressbook.models.ContactData;
import java.security.SecureRandom;
import java.util.*;

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
        input(By.name("phone2"), contactData.getSecondaryPhone());
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

    public void openDetailsPageById(int id) {
        click(By.xpath("//a[@href='view.php?id=" + id + "']"));
    }

    public void initiateContactModificationFromDetailsPage() {
        click(By.name("modifiy"));
    }

    public void performSearch(String firstName) {
        input(By.name("searchstring"), firstName);
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

    public String getHomepage() {
        return getAttribute(By.name("homepage"), "value");
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

    public void skipTestIfFormNotAllFilled(ContactData comparedContact) {
        if (!isContactEditFormAllFilled(comparedContact)) {
            System.out.println("Test is ignored due to the not all fields are filled in Edit form");
            throw new SkipException("Test is ignored due to the not all fields are filled in Edit form");
        }
    }

    public void skipTestIfFormIsAllFilled(ContactData comparedContact) {
        if (isContactEditFormAllFilled(comparedContact)) {
            System.out.println("Test is ignored due to the ALL fields are filled in Edit form");
            throw new SkipException("Test is ignored due to the ALL fields are filled in Edit form");
        }
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

    public Set<ContactData> getBirthDaysSet() {
        Set<ContactData> birthDays = new HashSet<ContactData>();
        List<WebElement> elements = wd.findElements(By.xpath("//tr[@class = 'odd' or @class = 'even']"));
        for (WebElement rows : elements) {
            List<WebElement> cells = rows.findElements(By.tagName("td"));
            String link = cells.get(6).findElement(By.tagName("a")).getAttribute("href");
            int id = Integer.parseInt(link.substring(41));
            String firstName = cells.get(2).getText();
            String email = cells.get(4).getText();
            String phone = cells.get(5).getText();
            ContactData contact = new ContactData().withContactId(id)
                                                   .withFirstName(firstName)
                                                   .withEmail(email)
                                                   .withHomePhone(phone);
            birthDays.add(contact);
        }
        return birthDays;
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

    public void modifyContactFromDetailsPage(ContactData contactData) {
        openDetailsPageById(contactData.getContactId());
        initiateContactModificationFromDetailsPage();
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
        String homepage = getHomepage();
        String birthDay = getBirthDay();
        String birthMonth = getBirthMonth();
        String birthYear = getBirthYear();
        String anniversaryDay = getAnniversaryDay();
        String anniversaryMonth = getAnniversaryMonth();
        String anniversaryYear = getAnniversaryYear();
        String secondaryAddress = getSecondaryAddress();
        String secondaryHome = getSecondaryHome();
        String secondaryNotes = getSecondaryNotes();

        //Switch first letter from LowerCase to UpperCase for AnniversaryMonth
        String firstCharInUpperCase = anniversaryMonth.substring(0, 1).toUpperCase();
        String withRemovedFirstChar = anniversaryMonth.substring(1);
        String anniversaryMonthWithUpperCaseFirstChar = firstCharInUpperCase + withRemovedFirstChar;

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
                                                    .withHomepage(homepage)
                                                    .withBirthDay(birthDay)
                                                    .withBirthMonth(birthMonth)
                                                    .withBirthYear(birthYear)
                                                    .withAnniversaryDay(anniversaryDay)
                                                    .withAnniversaryMonth(anniversaryMonthWithUpperCaseFirstChar)
                                                    .withAnniversaryYear(anniversaryYear)
                                                    .withSecondaryAddress(secondaryAddress)
                                                    .withSecondaryPhone(secondaryHome)
                                                    .withSecondaryNotes(secondaryNotes);
        back();
        return contactData;
    }

    public ContactData getContactDataFromDetailsForm(ContactData outsideData) {
        openDetailsPageById(outsideData.getContactId());

        //Getting all content on the Details page
        String content = getText(By.id("content"));
        String parsed[] = content.split("\n");

        //Dirty data
        String names = parsed[0];
        String namesArray[] = names.split(" ");
        String homePhoneDirty = parsed[7];
        String mobilePhoneDirty = parsed[8];
        String workPhoneDirty = parsed[9];
        String faxDirty = parsed[10];
        String birthDataDirty = parsed[18];
        String anniversaryDataDirty = parsed[19];
        String birthArray[] = birthDataDirty.split(" ");
        String birthDayDirty = birthArray[1];
        String anniversaryArray[] = anniversaryDataDirty.split(" ");
        String anniversaryDayDirty = anniversaryArray[1];
        String secondaryPhoneDirty = parsed[23];

        //Pure data
        String firstName = namesArray[0];
        String middleName = namesArray[1];
        String lastName = namesArray[2];
        String nickname = parsed[1];
        String title = parsed[3];
        String company = parsed[4];
        String address = parsed[5];
        String homePhone = homePhoneDirty.replaceAll("H: ", "");
        String mobilePhone = mobilePhoneDirty.replaceAll("M: ", "");
        String workPhone = workPhoneDirty.replaceAll("W: ", "");
        String fax = faxDirty.replaceAll("F: ", "");
        String email = parsed[12];
        String email2 = parsed[13];
        String email3 = parsed[14];
        String homepage = parsed[16];
        String birthDay = birthDayDirty.replaceAll("\\.", "");
        String birthMonth = birthArray[2];
        String birthYear = birthArray[3];
        String anniversaryDay = anniversaryDayDirty.replaceAll("\\.", "");
        String anniversaryMonth = anniversaryArray[2];
        String anniversaryYear = anniversaryArray[3];
        String secondaryAddress = parsed[21];
        String secondaryPhone = secondaryPhoneDirty.replaceAll("P: ", "");
        String secondaryNotes = parsed[25];

        ContactData contactData = new ContactData().withFirstName(firstName)
                                                    .withMiddleName(middleName)
                                                    .withLastName(lastName)
                                                    .withNickname(nickname)
                                                    .withTitle(title)
                                                    .withCompany(company)
                                                    .withAddress(address)
                                                    .withHomePhone(homePhone)
                                                    .withMobilePhone(mobilePhone)
                                                    .withWorkPhone(workPhone)
                                                    .withFax(fax)
                                                    .withEmail(email)
                                                    .withEmail2(email2)
                                                    .withEmail3(email3)
                                                    .withHomepage(homepage)
                                                    .withBirthDay(birthDay)
                                                    .withBirthMonth(birthMonth)
                                                    .withBirthYear(birthYear)
                                                    .withAnniversaryDay(anniversaryDay)
                                                    .withAnniversaryMonth(anniversaryMonth)
                                                    .withAnniversaryYear(anniversaryYear)
                                                    .withSecondaryAddress(secondaryAddress)
                                                    .withSecondaryPhone(secondaryPhone)
                                                    .withSecondaryNotes(secondaryNotes);
        back();
        return contactData;
    }

    public boolean isContactEditFormAllFilled(ContactData comparedContact) {
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
        String homepage = getHomepage();
        String birthDay = getBirthDay();
        String birthMonth = getBirthMonth();
        String birthYear = getBirthYear();
        String anniversaryDay = getAnniversaryDay();
        String anniversaryMonth = getAnniversaryMonth();
        String anniversaryYear = getAnniversaryYear();
        String secondaryAddress = getSecondaryAddress();
        String secondaryHome = getSecondaryHome();
        String secondaryNotes = getSecondaryNotes();
        back();

        if (!firstName.equals("") && !middleName.equals("") && !lastName.equals("") && !nickname.equals("") && !company.equals("") && !title.equals("") && !address.equals("")
                && !homePhone.equals("") && !mobilePhone.equals("") && !workPhone.equals("") && !fax.equals("") && !email1.equals("") && !email2.equals("") && !email3.equals("")
                && !homepage.equals("") && !birthDay.equals("") && !birthMonth.equals("") && !birthYear.equals("") && !anniversaryDay.equals("") && !anniversaryMonth.equals("")
                && !anniversaryYear.equals("") && !secondaryAddress.equals("") && !secondaryHome.equals("") && !secondaryNotes.equals("")) {
            return true;
        } else {
            return false;
        }
    }

    public Set<String> getContactNamesFromDetailsForm(ContactData outsideData) {
        openDetailsPageById(outsideData.getContactId());

        //Getting all content on the Details page
        String content = getText(By.id("content"));

        //Parse got content
        String parsed[] = content.split("\n");
        String names = parsed[0];
        String namesArray[] = names.split(" ");

        //Create Collection with FirstName, MiddleName, LastName
        Set<String> nameSet = new HashSet<String>();
        for (String element : namesArray) {
            nameSet.add(element);
        }
        return nameSet;
    }

    public String getRandomSearchQuery(ContactData randomContact) {
        List<String> listWithRandomData = null;
        String allEmails = randomContact.getAllEmails();
        String allPhones = randomContact.getAllPhones();

        if (!allEmails.equals("") && !allPhones.equals("")) {
            //Getting random Email
            int numberOfEmails = allEmails.split("\n").length;
            int getEmailById = new SecureRandom().nextInt(numberOfEmails);
            String randomEmail = allEmails.split("\n")[getEmailById];

            //Getting random Phone
            int numberOfPhones = allPhones.split("\n").length;
            int getPhoneById = new SecureRandom().nextInt(numberOfPhones);
            String randomPhone = allPhones.split("\n")[getPhoneById];

            listWithRandomData = Arrays.asList(randomContact.getFirstName(), randomContact.getLastName(), randomContact.getAddress(), randomEmail, randomPhone);
        } else if (!allEmails.equals("") && allPhones.equals("")) {
            int numberOfEmails = allEmails.split("\n").length;
            int getEmailById = new SecureRandom().nextInt(numberOfEmails);
            String randomEmail = allEmails.split("\n")[getEmailById];

            listWithRandomData = Arrays.asList(randomContact.getFirstName(), randomContact.getLastName(), randomContact.getAddress(), randomEmail);
        } else if (allEmails.equals("") && !allPhones.equals("")) {
            int numberOfPhones = allPhones.split("\n").length;
            int getPhoneById = new SecureRandom().nextInt(numberOfPhones);
            String randomPhone = allPhones.split("\n")[getPhoneById];

            listWithRandomData = Arrays.asList(randomContact.getFirstName(), randomContact.getLastName(), randomContact.getAddress(), randomPhone);
        } else if (allEmails.equals("") && allPhones.equals("")){
            listWithRandomData = Arrays.asList(randomContact.getFirstName(), randomContact.getLastName(), randomContact.getAddress());
        } else {
            System.out.println("ERROR occurred in logic with Emails and Phones");
        }

        //Remove empty elements in Collection
        List<String> contactData = null;
        if (listWithRandomData != null) {
            contactData = new ArrayList<String>(listWithRandomData);
            contactData.removeAll(Collections.singleton(""));
        }

        int identifier = new SecureRandom().nextInt(contactData.size());
        return contactData.get(identifier);
    }

    public int getNumberOfMatching(Set<ContactData> before, String randomSearchQuery) {
        int numberOfMatching = 0;
        for (ContactData contact : before) {
            if (contact.getFirstName().contains(randomSearchQuery) || contact.getLastName().contains(randomSearchQuery) || contact.getAddress().contains(randomSearchQuery)
                    || contact.getAllEmails().contains(randomSearchQuery) || contact.getAllPhones().contains(randomSearchQuery)) {
                numberOfMatching += 1;
            }
        }
        return numberOfMatching;
    }
}
