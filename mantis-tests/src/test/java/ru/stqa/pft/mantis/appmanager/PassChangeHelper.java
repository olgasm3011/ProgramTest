package ru.stqa.pft.mantis.appmanager;

import org.openqa.selenium.By;

/**
 * Created by oasmir12 on 16.08.2017.
 */
public class PassChangeHelper extends HelperBase{
  public PassChangeHelper(ApplicationManager app){
    super(app);
  }

  public void login(String username, String password){
    wd.get(app.getProperty("web.baseUrl"));
    type(By.name("username"), username);
    type(By.name("password"), password);
    click(By.cssSelector("input[value='Login']"));
  }

  public void goToManagePage () {
    wd.get(app.getProperty("web.baseUrl") + "/manage_user_page.php");
  }

  public void selectUser(String username){
    click(By.linkText(username));
  }

  public void submitResetPassword(){
    click(By.cssSelector("input[value='Reset Password']"));
  }

  public void resetPassword(String username){
    goToManagePage();
    selectUser(username);
    submitResetPassword();
  }
}
