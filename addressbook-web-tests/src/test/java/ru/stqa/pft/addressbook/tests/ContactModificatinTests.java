package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;
import ru.stqa.pft.addressbook.model.GroupData;

import java.io.File;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ContactModificatinTests extends TestBase {
  @BeforeMethod
  public void ensurePreconditions() {
    if (app.db().contacts().size() == 0){
      app.goTo().groupPage();
      if (app.db().groups().size() == 0){
        app.group().create(new GroupData().withName("test3"));
      }
      app.goTo().gotoHomePage();
      app.contact().create(new ContactData().withLastname("Smirnova"), true);
    }
    app.goTo().gotoHomePage();
  }

  @Test
  public void testContactModification(){
    Contacts before = app.db().contacts();
    ContactData modifyContact = before.iterator().next();
    File photo = new File("src/test/resources/photo.jpg");
    ContactData contact = new ContactData().withId(modifyContact.getId()).withFirstname("firstname").
            withPhoto(photo).withMiddlename("middlename").withLastname("lastname").withNickname("nikename").
            withPhoto(photo).withTitle("Hello").withCompany("company").withAddress("address").withHomePhone("777777").
            withMobilePhone( "+79929292292").withEmail("email@gmail.com").withByear("1990");
    app.contact().modify(contact, false);
    app.goTo().gotoHomePage();
    assertThat(app.contact().count(), equalTo(before.size()));
    Contacts after = app.db().contacts();
    assertThat(after, equalTo(before.without(modifyContact).withAdded(contact)));
    verifyContactListInUI();
  }


}
