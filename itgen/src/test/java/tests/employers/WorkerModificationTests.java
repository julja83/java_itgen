package tests.employers;
// Тест на модификацию работника. Для подключения проверки на соответствие ui и бд в конфигурации
// запуска указываем -DverifyUI=true.

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

import app.testbase.TestBase;
import core.general.LocaleUtilsTestData;
import core.general.RunTestAgain;
import data.model.users.WorkerData;
import data.model.users.Workers;
import data.services.WorkerService;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class WorkerModificationTests extends TestBase {

  public WorkerData modifydWorker;
  WorkerService workerService = new WorkerService();

  @BeforeMethod
  public void ensurePreconditions() {
    app.trWorker()
        .saveNewWorker(
            "workerModify",
            "Маша",
            "Машина",
            "employee",
            "AL",
            "Europe/Minsk",
            "ru",
            "ru",
            "1234567899",
            "julja83@list.ru");
    modifydWorker = workerService.findById("workerModify");
  }

  @Test(dataProvider = "validWorkersFromJson", dataProviderClass = LocaleUtilsTestData.class,
      retryAnalyzer = RunTestAgain.class)
  public void testWorkerModification(WorkerData worker) {
    app.goTo().menuTasks();
    app.goTo().menuWorkers();
    Workers before = app.db().workers();
    app.worker().modificationWorker(worker, "workerModify");
    Workers after = app.db().workers();
    assertThat(after.size(), equalTo(before.size()));
    WorkerData workerAdd = worker.withId(modifydWorker.getId());
    assertThat(after, equalTo(before.without(modifydWorker).withAdded(workerAdd)));
    app.goTo().menuWorkers();
    verifyWorkerListInUI();
  }

  @AfterMethod(alwaysRun = true)
  public void clean() {
    workerService.DeleteById("workerModify");
  }
}
