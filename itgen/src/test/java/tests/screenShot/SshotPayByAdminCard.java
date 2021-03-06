package tests.screenShot;
/* Скриншот страницы с учениками. База изначально должна быть пустая. Тест создает ученика, делает снимок,
  сравнивает его с эталонным. Для запуска в режиме снятия эталонного снимка запускаем конфигурацию запуска
  со свойством -Detalon=true.
*/

import app.appmanager.ApplicationManager;
import app.testbase.TestBase;
import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;
import ru.yandex.qatools.ashot.comparison.ImageDiff;

import java.awt.*;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

public class SshotPayByAdminCard extends TestBase {

  @Test
  public void testSshotPayByAdminCard() throws AWTException, IOException {
    String name = "Admin_PayCard_RU_Chrome";
    Set<By> locatorIgnor = new HashSet<>();
    app.goTo().menuTasks();
    app.goTo().menuStudents();
    app.payment().paymentAdminCard("21");

    ImageDiff diff =
        app.sshot()
            .getImageDiff(
                ApplicationManager.properties.getProperty("expected"),
                ApplicationManager.properties.getProperty("actual"),
                ApplicationManager.properties.getProperty("markedImages"),
                name,
                locatorIgnor,
                1.25f);

    if (diff.getDiffSize() > 125) {
      Assert.assertEquals(diff.getDiffSize(), 0);
    }
    app.payment().goToFamily("paymentAdmin");
  }
}
