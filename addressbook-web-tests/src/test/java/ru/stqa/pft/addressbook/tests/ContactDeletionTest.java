package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

import java.util.List;

public class ContactDeletionTest extends TestBase {

  @Test
  public void testContactDeletion(){
    app.getNavigationHelper().goToContactPage();

    if (!app.getContactHelper().isThereContact()){
      app.getContactHelper().createContact(new ContactData("Olga", "Alexsandrovna", "Smirnova", "olgas", "Hello", "MTS", "Контратьевский 16", "777777", "+79929292292", "olgas301190@gmail.com", "1990"));
    }
    app.getNavigationHelper().goToContactPage();
    List<ContactData> before = app.getContactHelper().getContactList();
    app.getContactHelper().selectContact(before.size() - 1);
    app.getContactHelper().deleteSelectedContact();
    app.getContactHelper().switchDelete();
    app.getNavigationHelper().goToContactPage();
    List<ContactData> after = app.getContactHelper().getContactList();
    Assert.assertEquals(after.size(), before.size() - 1);
  }
}
