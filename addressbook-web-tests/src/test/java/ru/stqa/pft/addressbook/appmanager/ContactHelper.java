package ru.stqa.pft.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

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

  public void create(ContactData contact){
    gotoAddContactPage();
    fillContactCreation(contact);
    submitContactCreation();
    contactCash = null;
  }

  public void modify(ContactData contact) {
    editContact(contact.getId());
    fillContactCreation(contact);
    updateContact();
    contactCash = null;
  }

  public void selectContact(int index) {
    wd.findElements(By.name("selected[]")).get(index).click();
  }
  public void selectContactById(int id) {
    wd.findElement(By.cssSelector("input[id='" + id  + "']")).click();
  }

  public void switchDelete(){
    switchTo();
  }
  public void deleteSelectedContact() {
    click(By.xpath(".//*[@value='Delete']"));
  }
  public void editContact(int id) {
   // wd.findElements(By.xpath("(.//*[@src='icons/pencil.png'])")).get(index).click();
    wd.findElement(By.cssSelector("a[href='edit.php?id=" + id + "']")).click();
  }

  public void updateContact() {
    click(By.name("update"));
  }

  public void delete(ContactData contact) {
    selectContactById(contact.getId());
    deleteSelectedContact();
    switchDelete();
    contactCash = null;
  }
  public int count(){
    return  wd.findElements(By.name("selected[]")).size();
  }

  private Contacts contactCash = null;
  public Contacts all() {
    if (contactCash != null) {
      return new Contacts(contactCash);
    }
    contactCash = new Contacts();
    List<WebElement> elements = wd.findElements(By.cssSelector("input[accept]"));
    for (WebElement element : elements) {
      String lastname = element.findElement(By.xpath("../../td[2]")).getText();
      String firstname = element.findElement(By.xpath("../../td[3]")).getText();
      int id = Integer.parseInt(element.getAttribute("value"));
      contactCash.add(new ContactData().withId(id).withLastname(lastname).withFirstname(firstname));
    }
    return new Contacts(contactCash);
  }

  public ContactData infoFromEditForm(ContactData contact) {
    initContactModificationById(contact.getId());
    String firstname = wd.findElement(By.name("firstname")).getAttribute("value");
    String lastname = wd.findElement(By.name("lastname")).getAttribute("value");
    String home = wd.findElement(By.name("home")).getAttribute("value");
    String mobile = wd.findElement(By.name("mobile")).getAttribute("value");
    String work = wd.findElement(By.name("work")).getAttribute("value");
    wd.navigate().back();
    return new ContactData().withId(contact.getId()).withFirstname(firstname).withLastname(lastname).withMobilePhone(mobile).withWorkPhone(work);
  }

  public void initContactModificationById(int id) {
    WebElement checkbox = wd.findElement(By.cssSelector(String.format("input[value='%s']", id)));
    WebElement row = checkbox.findElement(By.xpath("./../.."));
    List<WebElement> cells = row.findElements(By.tagName("td"));
    cells.get(7).findElement(By.tagName("a")).click();
  }
}
