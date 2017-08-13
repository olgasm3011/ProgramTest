package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ContactCreationTest extends TestBase {

  @Test
  public void testContactCreation() {
    app.goTo().gotoHomePage();
    Contacts before = app.contact().all();
    ContactData contact = new ContactData().withFirstname("Olga").withMiddlename("Alexsandrovna").withLastname("Smirnova").withNickname("olgas").withTitle("Hello").withCompany("MTS").withAddress("Контратьевский 16").withHomePhone("777777").withMobilePhone( "+79929292292").withWorkPhone("896544").withEmail("olgas301190@gmail.com").withByear("1990");
    app.contact().create(contact);
    app.goTo().gotoHomePage();
    Contacts after = app.contact().all();
    assertThat(after.size(), equalTo(before.size()+1));
    assertThat(after, equalTo(
            before.withAdded(contact.withId(after.stream().mapToInt((g) -> g.getId()).max().getAsInt()))));
  }


  @Test
  public void testBadContactCreation() {
    app.goTo().gotoHomePage();
    Contacts before = app.contact().all();
    ContactData contact = new ContactData().withFirstname("Olga").withMiddlename("Alexsandrovna").withLastname("Smirnova").withNickname("olgas").withTitle("Hello").withCompany("MTS").withAddress("Контратьевский 16").withHomePhone("777777").withMobilePhone( "+79929292292").withEmail("olgas301190@gmail.com").withByear("1990");
    app.contact().create(contact);
    app.goTo().gotoHomePage();
    assertThat(app.contact().count(), equalTo(before.size()+1));
    Contacts after = app.contact().all();
    assertThat(after, equalTo(
            before.withAdded(contact.withId(after.stream().mapToInt((g) -> g.getId()).max().getAsInt()))));
  }
}
