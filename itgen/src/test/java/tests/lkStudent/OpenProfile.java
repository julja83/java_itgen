package tests.lkStudent;
/* Кейс: открыть профайл через быстрые переходы */

import app.testbase.TestBase;
import core.general.RunTestAgain;
import org.openqa.selenium.By;
import org.testng.annotations.Test;

public class OpenProfile extends TestBase {

  @Test(retryAnalyzer = RunTestAgain.class)
  public void testOpenProfile() {
    app.student().goInProfile();
    app.check().textElement(By.xpath("(//h4)[1]"), "Дефолтный Ребенок");
  }

}