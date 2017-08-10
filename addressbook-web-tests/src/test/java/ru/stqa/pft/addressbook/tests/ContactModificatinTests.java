package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

public class ContactModificatinTests extends TestBase {

  @Test
  public void testGroupModification(){
    app.getNavigationHelper().goToContactPage();
    if (!app.getContactHelper().isThereContact()){
      app.getContactHelper().createContact(new ContactData("Olga", "Alexsandrovna", "Smirnova", "olgas", "Hello", "MTS", "Контратьевский 16", "777777", "+79929292292", "olgas301190@gmail.com", "1990"));
    }
    app.getNavigationHelper().goToContactPage();
    app.getContactHelper().selectContact();
    app.getContactHelper().editContact();
    app.getContactHelper().fillContactCreation(new ContactData("Olga", "Alexsandrovna", "Smirnova", "olgas", "Hello", "MTS", "Контратьевский 16.", "777777", "+79929292292", "olgas301190@gmail.com", "1990"));
    app.getContactHelper().updateContact();
    app.getNavigationHelper().goToContactPage();
  }
}
