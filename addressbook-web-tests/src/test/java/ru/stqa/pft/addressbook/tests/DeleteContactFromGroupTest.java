package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;
import ru.stqa.pft.addressbook.model.GroupData;
import ru.stqa.pft.addressbook.model.Groups;

import java.io.File;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

/**
 * Created by oasmir12 on 16.08.2017.
 */
public class DeleteContactFromGroupTest extends TestBase{
  @BeforeMethod
  public void ensurePreconditions() {
    if (app.db().groups().size() == 0){
      app.goTo().groupPage();
      app.group().create(new GroupData().withName("test1"));
    }
    if (app.db().contacts().size() == 0){
      app.goTo().gotoHomePage();
      File photo = new File("src/test/resources/photo.jpg");
      app.contact().create(new ContactData().withFirstname("firstname").
              withPhoto(photo).withMiddlename("middlename").withLastname("lastname").withNickname("nikename").
              withPhoto(photo).withTitle("Hello").withCompany("company").withAddress("address").withHomePhone("777777").
              withMobilePhone( "+79929292292").withEmail("email@gmail.com").withByear("1990"),true);
      app.goTo().gotoHomePage();
    }
  }

  @Test
  public void testDeleteContactFromGroup(){
    app.goTo().gotoHomePage();
    Contacts before = app.db().contacts();
    Groups groups = app.db().groups();
    ContactData contact = before.iterator().next();
    int contactId = contact.getId();
    Groups beforeContactGroup = contact.getGroups();

    if (beforeContactGroup.size() == 0){
      GroupData addGroup = groups.iterator().next();
      app.contact().addContacts(contact,addGroup);
      app.goTo().gotoHomePage();
      contact = app.db().getContactsById(contactId);
      beforeContactGroup = contact.getGroups();
    }
    GroupData deleteGroup = beforeContactGroup.iterator().next();
    app.contact().deleteContactFromGroup(contact, deleteGroup);
    app.goTo().gotoHomePage();
    Groups afterContactGroup = app.db().getContactsById(contactId).getGroups();
    Groups beforeGroup = beforeContactGroup.without(deleteGroup);
    assertThat(afterContactGroup, equalTo(beforeGroup));
  }
}
