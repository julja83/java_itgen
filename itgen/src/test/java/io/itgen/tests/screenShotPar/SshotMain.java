package io.itgen.tests.screenShotPar;
/* Скриншот страницы семьи. База изначально должна быть пустая. Тест создает семью, делает снимок,
   сравнивает его с эталонным.
 */


import io.itgen.appmanager.ApplicationManager;
import io.itgen.tests.TestBase;
import org.testng.Assert;
import org.testng.annotations.Test;
import ru.yandex.qatools.ashot.comparison.ImageDiff;

import java.awt.*;
import java.io.IOException;

public class SshotMain extends TestBase {


 @Test
  public void testSshotMain() throws AWTException, IOException {
   app.lkParent().btnLogo();
   String name = "Parent_Main_RU_Chrome";
   String[] locatorIgnor = null;

    ImageDiff diff = app.sshot().getImageDiff(ApplicationManager.properties.getProperty("expected")
            , ApplicationManager.properties.getProperty("actual")
            , ApplicationManager.properties.getProperty("markedImages")
            , name, locatorIgnor);
    Assert.assertEquals(diff.getDiffSize(), 0);
  }
}
