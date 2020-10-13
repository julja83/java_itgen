package io.itgen.tests.testTypeform;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

import io.itgen.services.TestResultsService;
import io.itgen.services.TestService;
import io.itgen.tests.TestBase;
import java.util.Date;
import org.openqa.selenium.By;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class TestTypeformInProcess extends TestBase {

  private final TestService testService = new TestService();
  private final TestResultsService testResultsService = new TestResultsService();
  private final Date createAt = new Date();
  private String[] skills = null;

  @BeforeMethod
  public void ensurePreconditions() {
    skills = new String[]{"1"};
    app.trTest().saveTest("InProcess", "Тест", "111111", "ru",
        "Test на переход на новое направление", 5, 5, 10, skills, createAt,
        null);

    testService.deleteField("InProcess", "removedAt");

    app.trTest().saveResultTest("InProcess", "21", "InProcess", "Тест",
        "111111", skills, "ru", 5, 5, createAt, "");
  }

  @Test
  public void testTypeformInProcess() {
    app.test().goToStudentProfileTabTests();

    //есть лейбл В процессе
    assertThat(app.test().isElementPresent(By.xpath("//span[@class='in-process']")), equalTo(true));
    //есть кнопка Удалить и она не задизейблена
    assertThat(app.test().buttonAtributAvailable(By.xpath("//button[@id-qa='delete-test']")),
        equalTo(null));
    //что можно скопировать ссылку (копируется весь текст)
    // app.test().checkHrefInProfileStudent(By.xpath("//div[@id-qa='copy-url']//input"));
    app.goTo().menuTasks();
  }

  @AfterMethod(alwaysRun = true)
  public void clean() {
    testService.drop();
    testResultsService.drop();
  }
}