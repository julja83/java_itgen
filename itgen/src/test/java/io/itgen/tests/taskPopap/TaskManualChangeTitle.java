package io.itgen.tests.taskPopap;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

import io.itgen.model.tasks.TaskData;
import io.itgen.model.tasks.Tasks;
import io.itgen.services.TaskService;
import io.itgen.tests.TestBase;
import java.util.Date;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class TaskManualChangeTitle extends TestBase {
  private final TaskService taskService = new TaskService();
  private TaskData taskClean = null;
  private final Date createAt=new Date();
  private final Date duoDateWithTime =new Date();
  private final long duoDateSort = new Date().getTime();
  private final Date[] dates = null;
  private  String[] texts = null;
  private final String[] clients = null;
  private final String[] commentaries = null;

  @BeforeMethod
  public void ensurePreconditions() {
    app.trTask()
        .newManualTask(
            "PopupChangeTitleTask",
            "777",
            "666",
            "Записать на пробное",
            1,
            createAt,
            "open",
            duoDateWithTime,
            duoDateSort,
            "21");
  }

  @Test
  public void testTaskManualChangeTitle() {
    app.goTo().menuTasks();
    Tasks before=app.dbtasks().tasks();
    app.task().changeTitleManualTaskInPopup("Записать на новое пробное");
    Tasks after=app.dbtasks().tasks();
    taskClean = app.dbtasks().lastTask();
    assertThat(after.size(), equalTo(before.size()));
    check(after);
    app.goTo().menuSchedule();
  }

  private void check(Tasks after){
    texts = new String[]{"Записать на пробное","Записать на новое пробное"};
    app.trTask()
        .saveManualTask(
            "PopupChangeTitleTask",
            "Записать на новое пробное",
            createAt,
            "open",
            duoDateWithTime,
            duoDateSort,
            "666",
            "21",
            "777",
            "666",
            1,
            dates,
            texts,
            clients,
            commentaries,
            "newTask_changeTextTask");

    TaskData taskAdd = taskService.findById(taskClean.getId());

    for (TaskData taskAfter : after) {
      if (taskAfter.getId().equals(taskClean.getId())) {
        assertThat(after, equalTo(after.without(taskAfter).withAdded(taskAdd)));
        return;
      }
    }
  }

  @AfterMethod(alwaysRun = true)
  public void clean() {
    taskService.DeleteById(taskClean.getId());
  }
}