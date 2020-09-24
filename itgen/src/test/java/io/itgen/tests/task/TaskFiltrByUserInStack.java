package io.itgen.tests.task;
 /*автотаска, которую взял пользователь будет всегда в стеке, пока не выполнится. Остальные автотаски
 * не отображаются  в стеке, т.к. не имеют создателя(авто) и пока исполнителя, но их количество указано в счетчике
 * в табе. Счетчик в меню (красный) считает те задачи, в которых пользователь исполнитель.*/
// в данном кейсе проверяется, что автотаска, взятая в работу есть в стеке, и по фильтру выходит
//1 ручная таска с исполтнителем - укзаный юзер

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

import io.itgen.services.FamilyService;
import io.itgen.services.StudentService;
import io.itgen.services.TaskService;
import io.itgen.tests.TestBase;
import java.util.Date;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class TaskFiltrByUserInStack extends TestBase {

  private final TaskService taskService = new TaskService();
  private final StudentService studentService = new StudentService();
  private final FamilyService familyService = new FamilyService();
  private final Date createAt = new Date();
  private final Date duoDateWithTimeFirst = new Date();
  private final Date duoDateWithTimeSecond = new Date(new Date().getTime() - 86400000);
  private final long duoDateSort = new Date().getTime();
  private  Date[] dates = null;
  private final String[] texts = null;
  private final String[] clients = null;
  private final String[] commentaries = null;

  @BeforeMethod
  public void ensurePreconditions() {
    app.trFamily().newFamily("Student", false, "txc");

    app.trStudent()
        .newStudent(
            "Student",
            "Маша",
            "Машина",
            "expert",
            "AL",
            "Europe/Minsk",
            2,
            "ru",
            "ru",
            "Student");

    app.trTask()
        .newManualTask(
            "FiltrTaskFirst",
            "777",
            "777",
            "Узнать почту у родителя",
            1,
            new Date(),
            "open",
            new Date(),
            new Date().getTime(),
            "21");
    app.trTask()
        .newAutoTask(
            "FiltrTaskAutoFirst",
            "contactForPayment",
            createAt,
            "open",
            duoDateWithTimeFirst,
            duoDateSort,
            "21",
            "21.00 : 23.00");
    taskService.deleteField("FiltrTaskAutoFirst","priority");
    app.trTask()
        .newManualTask(
            "FiltrTaskSecond",
            "777",
            "666",
            "Узнать почту у родителя",
            1,
            new Date(),
            "open",
            new Date(),
            new Date().getTime(),
            "Student");

    app.trTask()
        .newManualTask(
            "FiltrTaskThird",
            "666",
            "14",  //Настя Бокша - тренер
            "Проверить материалы",
            1,
            new Date(),
            "open",
            new Date(),
            new Date().getTime(),
            "Student");

    app.trTask()
        .saveAutoTask(
            "FiltrTaskAutoSecond",
            "contactForPayment",
            createAt,
            "inProgress",
            duoDateWithTimeSecond,
            duoDateSort,
            "666",
            "21",
            "21",
            "21.00 : 23.00",
            dates,
            texts,
            clients,
            commentaries,
            "newAutoTask_takeAutoTask");
    taskService.deleteField("FiltrTaskAutoSecond","priority");
  }

  @Test
  public void testFiltrByUserInStack() {
    app.goTo().menuTasks();
    app.task().filtrByUserInStek("Бокша");
    //проверка, что футере правильное количество найденных записей
    assertThat(app.task().getCountSearchUI(), equalTo(1));
    //проверка, что найдена именно Настя Бокша и она одна
    assertThat(app.task().getNameSearchUI(), equalTo("Бокша Настя"));
    //проверка, что счетчик задач в окне = 1
    assertThat(app.task().getCountInTabUI(), equalTo(1));
    //проверка, что красный счетчик в меню = 1
    assertThat(app.task().getCountInMenuUI(), equalTo(2));
    app.goTo().menuSchedule();
  }

  @AfterMethod(alwaysRun = true)
  public void clean() {
    taskService.drop();
    studentService.DeleteById("Student");
    familyService.DeleteById("Student");
  }
}
