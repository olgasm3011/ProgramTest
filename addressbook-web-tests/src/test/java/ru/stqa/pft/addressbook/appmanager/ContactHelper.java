package ru.stqa.pft.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.firefox.FirefoxDriver;
import ru.stqa.pft.addressbook.model.ContactData;

/**
 * Created by oasmir12 on 04.08.2017.
 */
public class ContactHelper extends HelperBase{
  FirefoxDriver wd;

  public ContactHelper(FirefoxDriver wd) {
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
    click(By.name("theform"));
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
    click(By.name("theform"));
  }

  public void gotoAddContactPage() {
   click(By.linkText("add new"));
  }

  public void selectContact(String id) {
    click(By.id(id));
  }

  public void switchDelete(){
    switchTo();
  }
  public void deleteSelectedContact() {
    click(By.xpath("//div[@id='content']/form[2]/div[2]/input"));
  }
  public void editContact() {
    click(By.xpath("//table[@id='maintable']/tbody/tr[2]/td[8]/a/img"));
  }

  public void updateContact() {
    click(By.xpath("//div[@id='content']/form[1]/input[22]"));
  }
}
