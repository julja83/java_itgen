package tests.screenShotStudent;

import app.appmanager.ApplicationManager;
import app.testbase.TestBase;
import java.awt.AWTException;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;
import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;
import ru.yandex.qatools.ashot.comparison.ImageDiff;

public class Sshot3LookTests extends TestBase {

  @Test
  public void testSshot3LookTests() throws AWTException, IOException {
    String name = "Student_Tests_RU_Chrome";
    app.student().goToStudentProfileTabTests();
    app.student().deleteAlerts();

    Set<By> locatorIgnor = new HashSet<>();
    locatorIgnor.add(By.xpath("//div[contains(@class,'finished')]//span[@class='date']"));
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
  }

}
