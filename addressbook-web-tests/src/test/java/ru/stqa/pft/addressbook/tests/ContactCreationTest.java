package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

import java.util.Comparator;
import java.util.List;

public class ContactCreationTest extends TestBase {

  @Test
  public void testContactCreation() {
    app.goTo().contactPage();
    List<ContactData> before = app.contact().list();
    ContactData contact = new ContactData().withFirstname("Olga").withMiddlename("Alexsandrovna").withLastname("Smirnova").withNickname("olgas").withTitle("Hello").withCompany("MTS").withAddress("Контратьевский 16").withHome("777777").withMobile( "+79929292292").withEmail("olgas301190@gmail.com").withByear("1990");
    app.contact().create(contact);
    app.goTo().contactPage();
    List<ContactData> after = app.contact().list();
    Assert.assertEquals(after.size(), before.size()+1);
    contact.withId(after.stream().max((c1, c2) -> Integer.compare(c1.getId(), c2.getId())).get().getId());
    before.add(contact);
    Comparator<? super ContactData> byId = (f1, f2) ->Integer.compare(f1.getId(), f2.getId());
    before.sort(byId);
    after.sort(byId);
    Assert.assertEquals(before, after);
  }

}
