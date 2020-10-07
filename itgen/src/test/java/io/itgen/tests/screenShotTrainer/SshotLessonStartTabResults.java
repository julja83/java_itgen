package io.itgen.tests.screenShotTrainer;

import io.itgen.appmanager.ApplicationManager;
import io.itgen.general.RunTestAgain;
import io.itgen.general.TimeGeneral;
import io.itgen.services.FamilyService;
import io.itgen.services.ScheduleService;
import io.itgen.services.StudentService;
import io.itgen.tests.TestBase;
import java.awt.AWTException;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.yandex.qatools.ashot.comparison.ImageDiff;

public class SshotLessonStartTabResults extends TestBase {

  ScheduleService scheduleService = new ScheduleService();
  StudentService studentService = new StudentService();
  FamilyService familyService = new FamilyService();
  private final TimeGeneral time = new TimeGeneral();
  private String period = "";
  private WebDriver wd;

  @BeforeMethod
  public void ensurePreconditions() {
    period = time.getPeriod(time.getTimeNow());
    app.trScheduleToday().SingleScheduleWithOneStudentOnTrail(period, "startLessonByTrainer",
        "23", "startLessonByTrainer", "1", "ru");

    app.trFamily().newFamily("startLessonByTrainer", false, "txc");

    app.trStudent()
        .newStudent(
            "startLessonByTrainer",
            "Маша",
            "Машина",
            "expert",
            "AL",
            "Europe/Minsk",
            2,
            "ru",
            "ru",
            "startLessonByTrainer");
  }

  @Test(retryAnalyzer = RunTestAgain.class)
  public void testSshotLessonStartTabResults() throws AWTException, IOException {
    app.trainer().maxBrowser();
    app.trainer().gotoSchedule();
    app.trainer().startLessonWithResuts("startLessonByTrainer");

    String name = "Trainer_StartLessonTabResults_RU_Chrome";
    Set<By> locatorIgnor = new HashSet<>();
    locatorIgnor.add(By.xpath("//div[@class='history-month-header']"));
    locatorIgnor.add(By.xpath("//div[contains(@id,'MeteorToys')]"));

    String[] deleteElements = {
        "//div[@class='text-capitalize'][2]",
        "//div[@class='text-muted']",
        "//div[@class='date']",
        "//div[@class='duration']",
        "//div[@class='time']"};

    app.sshot().deleteElements(deleteElements);

    app.sshot().changeTopBar();

    ImageDiff diff =
        app.sshot()
            .getImageDiff(
                ApplicationManager.properties.getProperty("expected"),
                ApplicationManager.properties.getProperty("actual"),
                ApplicationManager.properties.getProperty("markedImages"),
                name,
                locatorIgnor,
                1.93f);

    if (diff.getDiffSize() > 200) { // погрешность
      Assert.assertEquals(diff.getDiffSize(), 0);
    }
    app.trainer().gotoTask();
  }

  @AfterMethod(alwaysRun = true)
  public void clean() {
    scheduleService.drop();
    studentService.DeleteById("startLessonByTrainer");
    familyService.DeleteById("startLessonByTrainer");
  }
}
