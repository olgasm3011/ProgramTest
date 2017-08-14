package ru.stqa.pft.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;

import java.util.List;

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
    attach(By.name("photo"),contactData.getPhoto());
    type(By.name("company"),contactData.getCompany());
    type(By.name("address"),contactData.getAddress());
    click(By.name("home"));
    click(By.name("mobile"));
    type(By.name("home"),contactData.getHomePhone());
    type(By.name("mobile"),contactData.getMobilePhone());
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
    contactCache = null;
  }

  public void modify(ContactData contact) {
    editContact(contact.getId());
    fillContactCreation(contact);
    updateContact();
    contactCache = null;
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
    contactCache = null;
  }
  public int count(){
    return  wd.findElements(By.name("selected[]")).size();
  }

  private Contacts contactCache = null;
 /* public Contacts all() {
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
*/
 public Contacts all() {
   contactCache = new Contacts();
   List<WebElement> rows = wd.findElements(By.name("entry"));
   for (WebElement row : rows) {
     List<WebElement> cells = row.findElements(By.tagName("td"));
     int id = Integer.parseInt(cells.get(0).findElement(By.tagName("input")).getAttribute("value"));
     String lastname = cells.get(1).getText();
     String firstname = cells.get(2).getText();
     String address = cells.get(3).getText();
     String email = cells.get(4).getText();
     String allPhones = cells.get(5).getText();
     contactCache.add(new ContactData().withId(id).withFirstname(firstname).withLastname(lastname).withEmail(email).withAddress(address).withAllPhones(allPhones));
   }
   return new Contacts(contactCache);
 }

  public ContactData infoFromEditForm(ContactData contact) {
    initContactModificationById(contact.getId());
    String firstname = wd.findElement(By.name("firstname")).getAttribute("value");
    String lastname = wd.findElement(By.name("lastname")).getAttribute("value");
    String home = wd.findElement(By.name("home")).getAttribute("value");
    String mobile = wd.findElement(By.name("mobile")).getAttribute("value");
    String work = wd.findElement(By.name("work")).getAttribute("value");
    String email = wd.findElement(By.name("email")).getAttribute("value");
    String email2 = wd.findElement(By.name("email2")).getAttribute("value");
    String email3 = wd.findElement(By.name("email3")).getAttribute("value");
    String address = wd.findElement(By.name("address")).getAttribute("value");
    wd.navigate().back();
    return new ContactData().withId(contact.getId()).withFirstname(firstname).withLastname(lastname).withEmail(email).withEmail2(email2).withEmail3(email3).
            withAddress(address).withHomePhone(home).withMobilePhone(mobile).withWorkPhone(work).withAddress(address);
  }

  public void initContactModificationById(int id) {
    WebElement checkbox = wd.findElement(By.cssSelector(String.format("input[value='%s']", id)));
    WebElement row = checkbox.findElement(By.xpath("./../.."));
    List<WebElement> cells = row.findElements(By.tagName("td"));
    cells.get(7).findElement(By.tagName("a")).click();
  }

}
