package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

import java.util.List;

public class ContactCreationTest extends TestBase {

  @Test
  public void testContactCreation() {
    app.getNavigationHelper().goToContactPage();
    int before = app.getContactHelper().getContactCount();
    app.getContactHelper().createContact(new ContactData("Olga", "Alexsandrovna", "Smirnova", "olgas", "Hello", "MTS", "Контратьевский 16", "777777", "+79929292292", "olgas301190@gmail.com", "1990"));
    int after = app.getContactHelper().getContactCount();
    Assert.assertEquals(after, before + 1);
  }

}
