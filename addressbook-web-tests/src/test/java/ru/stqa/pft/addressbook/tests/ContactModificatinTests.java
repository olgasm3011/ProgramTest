package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;

import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.testng.Assert.*;

public class ContactModificatinTests extends TestBase {
  @BeforeMethod
  public void ensurePreconditions() {
    app.goTo().contactPage();
    if (app.contact().all().size() == 0) {
      app.contact().create(new ContactData().withLastname("Smirnova"));
    }
    app.goTo().contactPage();
  }

  @Test
  public void testContactModification(){
    Contacts before = app.contact().all();
    ContactData modifyContact = before.iterator().next();
    ContactData contact = new ContactData()
            .withId(modifyContact.getId()).withFirstname("Olga").withMiddlename("Alexsandrovna").withLastname("Smirnova").withNickname("olgas").withTitle("Hello").withCompany("MTS").withAddress("Контратьевский 16").withHome("777777").withMobile( "+79929292292").withEmail("olgas301190@gmail.com").withByear("1990");
    app.contact().modify(contact);
    app.goTo().contactPage();
    Contacts after = app.contact().all();
    assertEquals(after.size(), before.size());
    assertThat(after, equalTo(before.without(modifyContact).withAdded(contact)));
  }


}
