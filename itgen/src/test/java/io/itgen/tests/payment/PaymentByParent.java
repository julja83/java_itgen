package io.itgen.tests.payment;

import static io.itgen.appmanager.ApplicationManager.properties;
import static org.testng.AssertJUnit.assertEquals;

import io.itgen.model.TaskData;
import io.itgen.model.Tasks;
import io.itgen.services.ScheduleService;
import io.itgen.services.StudentService;
import io.itgen.services.TaskService;
import io.itgen.tests.TestBase;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class PaymentByParent extends TestBase {
  TaskService taskService = new TaskService();
  ScheduleService scheduleService = new ScheduleService();
  StudentService studentService = new StudentService();
  String period = "18:00 - 20:00";

  // тестовая ситуация: есть дефолтная семья, к которой добавлен ученик, прошедший вчера пробное в
  // 18.00
  @BeforeMethod
  public void ensurePreconditions() {
    app.trScheduleYesterday()
        .FinishingFirstTrialLesson(period, "FinishedSchedule", "14", "paymantByGuest", "1");

    app.trStudent()
        .StudentAddDefaultFamily_AfterTrial(
            "paymantByGuest", "expert", "BL", "Europe/Minsk", 2, "ru", "ru");
  }

  @Test
  public void testPaymentByParent() {
    app.payment().goToShopByParent();
    app.payment().paymentByParent();
    //  assertThat(app.student().findPictureSuccessPay(), equalTo(true)); разкоментить, когда будут
    // права
    // добавить проверку на пополнение баланса, когда будут права, а затем вернуть баланс в 0
    //  app.payment().goToBack("paymentAdmin");
    app.payment().goToBack("111");
  }

  @AfterMethod(alwaysRun = true)
  public void clean() {
    scheduleService.findByIdAndDelete("FinishedSchedule");
    studentService.findByIdAndDelete("paymantByGuest");

    Tasks tasks = app.dbschedules().tasksComposition("paymantByGuest");
    for (TaskData taskClean : tasks) {
      taskService.findByIdAndDeleteTask(taskClean.getId());
    }
  }
}