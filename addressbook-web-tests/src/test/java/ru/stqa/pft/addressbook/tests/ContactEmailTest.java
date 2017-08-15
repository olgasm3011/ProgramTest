package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

import java.util.Arrays;
import java.util.stream.Collectors;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static ru.stqa.pft.addressbook.tests.TestBase.app;

public class ContactEmailTest extends TestBase {
  @Test
  public void testContactEmails() {
    app.goTo().gotoHomePage();
    ContactData contact = app.db().contacts().iterator().next();
    ContactData contactInfoFromEditForm = app.contact().infoFromEditForm(contact);
    System.out.printf("contact: %s, \n mergeEmails(contactInfoFromEditForm): %s", contact, mergeEmails(contactInfoFromEditForm));
    assertThat(contact.getEmail(), equalTo(mergeEmails(contactInfoFromEditForm)));
  }

  private String mergeEmails(ContactData contact) {
    return Arrays.asList(contact.getEmail(), contact.getEmail2(), contact.getEmail3())
            .stream().filter((s) -> ! s.equals(""))
            .collect(Collectors.joining("\n"));
  }
}
