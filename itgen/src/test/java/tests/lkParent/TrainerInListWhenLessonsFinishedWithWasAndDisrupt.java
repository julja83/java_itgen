package tests.lkParent;
/* T-72 */
/*
 * В дефолтную семью добавлен ученик с завершенным вчера занятием c тренером Настей и с
 * завершенным с Сорвано тоже с тренером Настей
 * Перейти в запись на постоянку и убедиться, что тренер Настя есть в списке тренеров под чертой.
 * Перейти в запись на разовое и убедиться, что тренер Настя есть в списке тренеров под чертой.
 */

import app.testbase.TestBase;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class TrainerInListWhenLessonsFinishedWithWasAndDisrupt extends TestBase {

  @BeforeMethod
  public void ensurePreconditions() {
    data.defFamily()
        .set15_YesterdayTwoLessonsWasAndAbort_StudentAddInDefaultFamily();
  }

  @Test
  public void testTrainerInListWhenLessonsFinishedWithWasAndDisrupt() {
    app.lkParentRecord().trainerInListOnRegularRecord();
    app.check().findElement(app.lkParentRecord().getTrainerNastyaInList());
    app.lkParentRecord().trainerInListOnSingleRecord();
    app.check().findElement(app.lkParentRecord().getTrainerNastyaInList());
  }

  @AfterMethod(alwaysRun = true)
  public void clean() {
    data.clean().taskAndSchedule().student();
  }
}
