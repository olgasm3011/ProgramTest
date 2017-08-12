package ru.stqa.pft.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.GroupData;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by oasmir12 on 04.08.2017.
 */
public class ContactHelper extends HelperBase{

  public ContactHelper(WebDriver wd) {
    super(wd);
  }

  public void submitContactCreation() {
    click(By.xpath("//div[@id='content']/form/input[21]"));
  }

  public void fillContactCreation(ContactData contactData) {
    type(By.name("firstname"),contactData.getFirstname());
    type(By.name("middlename"),contactData.getMiddlename());
    type(By.name("lastname"),contactData.getLastname());
    type(By.name("nickname"),contactData.getNickname());
    type(By.name("title"),contactData.getTitle());
    type(By.name("company"),contactData.getCompany());
    type(By.name("address"),contactData.getAddress());
    click(By.name("home"));
    click(By.name("mobile"));
    type(By.name("home"),contactData.getHome());
    type(By.name("mobile"),contactData.getMobile());
    click(By.name("work"));
    type(By.name("email"),contactData.getEmail());
    if (!selected(By.xpath("//div[@id='content']/form/select[1]//option[32]"))) {
      click(By.xpath("//div[@id='content']/form/select[1]//option[32]"));
    }
    if (!selected(By.xpath("//div[@id='content']/form/select[2]//option[12]"))) {
      click(By.xpath("//div[@id='content']/form/select[2]//option[12]"));
    }
    type(By.name("byear"),contactData.getByear());
  }

  public void gotoAddContactPage() {
   click(By.linkText("add new"));
  }

  public void createContact(ContactData contact){
    gotoAddContactPage();
    fillContactCreation(contact);
    submitContactCreation();
  }

  public void selectContact() {
    click(By.name("selected[]"));
  }
  public void selectContact(int index) {
    wd.findElements(By.name("selected[]")).get(index).click();
  }
  public void switchDelete(){
    switchTo();
  }
  public void deleteSelectedContact() {
    click(By.xpath(".//*[@value='Delete']"));
  }
  public void editContact() {
    click(By.xpath("(.//*[@src='icons/pencil.png'])[1]"));
  }
  public void editContact(int index) {
    wd.findElements(By.xpath("(.//*[@src='icons/pencil.png'])")).get(index).click();
  }

  public void updateContact() {
    click(By.name("update"));
  }

  public boolean isThereContact() {
    return isElementPresent(By.name("selected[]"));
  }

  public int getContactCount(){
    return wd.findElements(By.name("selected[]")).size();
  }
  public List<ContactData> getContactList() {
    List<ContactData> contacts = new ArrayList<ContactData>();
    List<WebElement> elements = wd.findElements(By.cssSelector("input[accept]"));
    for (WebElement element : elements) {
      String name = element.getAttribute("accept");
      ContactData contact = new ContactData(name, null, null, null, null, null, null, null, null, null, null);
      contacts.add(contact);
    }
    return contacts;
  }
}
