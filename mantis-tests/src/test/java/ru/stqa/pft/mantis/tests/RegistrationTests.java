package ru.stqa.pft.mantis.tests;

import org.testng.annotations.Test;


import static org.testng.AssertJUnit.assertTrue;
/**
 * Created by oasmir12 on 16.08.2017.
 */
public class RegistrationTests extends TestBase{

    @Test
    public void testRegistration() {
      app.registration().start("user1", "user1@localhost.localdomain");
    }

}
