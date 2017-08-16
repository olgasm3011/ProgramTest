package ru.stqa.pft.mantis.tests;

import org.testng.AssertJUnit;
import org.testng.annotations.Test;
import ru.lanwen.verbalregex.VerbalExpression;
import ru.stqa.pft.mantis.appmanager.HttpSession;
import ru.stqa.pft.mantis.model.MailMessage;
import ru.stqa.pft.mantis.model.UserData;
import javax.mail.MessagingException;
import java.io.IOException;
import java.util.Date;
import java.util.List;
import static java.lang.Thread.sleep;
/**
 * Created by oasmir12 on 16.08.2017.
 */
public class ResetPasswordTest extends TestBase{

  public void startMailServer(){
    app.mail().start();
  }

  @Test
  public void testResetPassword() throws IOException, MessagingException, InterruptedException {
    Date dataCur = new Date();
    UserData infoUser = app.db().users().iterator().next();
    String user = infoUser.getUsername();
    String password = "password";

    if (app.james().doesUserExist(user)){
      app.james().setUserPassword(user,password);
    } else {
      app.james().createUser(user,password);
    }
    app.user().login("administrator", "root");
    app.user().resetPassword(user);
    sleep(50000);
    List<MailMessage> mailMessages = app.james().waitForMail(user, password, 80000);
    MailMessage message = null;
    for (MailMessage mailMessage : mailMessages) {
      if ( mailMessage.date.after(dataCur)){
        message = mailMessage;
        break;
      }
    }

    if (message == null){
      System.out.println("No mail after send");
    }

    String confirmationLink = findConfirmationLink(message);
    app.registration().finish(confirmationLink, password);
    HttpSession session = app.newSession();
    AssertJUnit.assertTrue(app.newSession().login(user, password));
  }

  private String findConfirmationLink(MailMessage mailMessage) {
    VerbalExpression regex = VerbalExpression.regex().find("http://").nonSpace().oneOrMore().build();
    return regex.getText(mailMessage.text);
  }

  public void stopMailServer(){
    app.mail().stop();
  }
}
