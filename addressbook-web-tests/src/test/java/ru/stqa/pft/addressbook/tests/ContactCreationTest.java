package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

import java.util.Comparator;
import java.util.List;
import java.util.Set;

public class ContactCreationTest extends TestBase {

  @Test
  public void testContactCreation() {
    app.goTo().contactPage();
    Set<ContactData> before = app.contact().all();
    ContactData contact = new ContactData().withFirstname("Olga").withMiddlename("Alexsandrovna").withLastname("Smirnova").withNickname("olgas").withTitle("Hello").withCompany("MTS").withAddress("Контратьевский 16").withHome("777777").withMobile( "+79929292292").withEmail("olgas301190@gmail.com").withByear("1990");
    app.contact().create(contact);
    app.goTo().contactPage();
    Set<ContactData> after = app.contact().all();
    Assert.assertEquals(after.size(), before.size()+1);
    contact.withId(after.stream().mapToInt((g)-> g.getId()).max().getAsInt());
    before.add(contact);
    Assert.assertEquals(before, after);
  }

}
