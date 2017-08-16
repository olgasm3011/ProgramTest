package ru.stqa.pft.mantis.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

/**
 * Created by oasmir12 on 16.08.2017.
 */
public class RegistrationHelper {
  private WebDriver wd;
  private final ApplicationManager app;

  public RegistrationHelper(ApplicationManager app) {
    this.app = app;
    wd = app.getDriver();
  }

  public void start(String username, String email){
    wd.get(app.getProperty("web.baseUrl") + "/signup_page.php");

  }

}
