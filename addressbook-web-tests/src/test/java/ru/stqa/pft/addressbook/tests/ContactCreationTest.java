package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ContactCreationTest extends TestBase {

  @Test
  public void testContactCreation() {
    app.goTo().contactPage();
    Contacts before = app.contact().all();
    ContactData contact = new ContactData().withFirstname("Olga").withMiddlename("Alexsandrovna").withLastname("Smirnova").withNickname("olgas").withTitle("Hello").withCompany("MTS").withAddress("Контратьевский 16").withHome("777777").withMobile( "+79929292292").withEmail("olgas301190@gmail.com").withByear("1990");
    app.contact().create(contact);
    app.goTo().contactPage();
    Contacts after = app.contact().all();
    Assert.assertEquals(after.size(), before.size()+1);
    assertThat(after.size(), equalTo(before.size()+1));
    assertThat(after, equalTo(
            before.withAdded(contact.withId(after.stream().mapToInt((g) -> g.getId()).max().getAsInt()))));
  }

}
