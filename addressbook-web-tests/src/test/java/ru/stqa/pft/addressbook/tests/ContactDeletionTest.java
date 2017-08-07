package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;
public class ContactDeletionTest extends TestBase {

  @Test
  public void testContactDeletion(){
    app.getNavigationHelper().goToContactPage();
    app.getContactHelper().selectContact("6");
    app.getContactHelper().deleteSelectedContact();
    app.getContactHelper().switchDelete();
  }
}
