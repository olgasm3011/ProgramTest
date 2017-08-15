package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;
import ru.stqa.pft.addressbook.model.GroupData;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.testng.Assert.assertEquals;

public class ContactDeletionTest extends TestBase {
  @BeforeMethod
  public void ensurePreconditions() {
    app.goTo().gotoHomePage();
    if (app.db().contacts().size() == 0) {
      app.goTo().groupPage();
      if (app.db().groups().size() == 0){
        app.group().create(new GroupData().withName("test3"));
      }
      app.contact().create(new ContactData().withLastname("Smirnova"), true);
    }
    app.goTo().gotoHomePage();
  }

  @Test
  public void testContactDeletion(){
    Contacts before = app.db().contacts();
    ContactData deleteContact = before.iterator().next();
    app.contact().delete(deleteContact);
    app.goTo().gotoHomePage();
    assertThat(app.contact().count(), equalTo(before.size()-1));
    Contacts after = app.db().contacts();
    assertThat(after, equalTo(before.without(deleteContact)));
    verifyContactListInUI();
  }
}
