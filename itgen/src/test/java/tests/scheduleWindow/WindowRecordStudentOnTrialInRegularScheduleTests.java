package tests.scheduleWindow;
// автотест проверяет запись платника на пробное занятие в постоянном расписании
// проверяет, что переключатель Пробное стоит по дефолту
// начальные данные: период, id тренера

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

import core.general.RunTestAgain;
import data.model.schedule.ScheduleData;
import data.model.schedule.Schedules;
import data.model.tasks.TaskData;
import data.model.tasks.Tasks;
import data.services.FamilyService;
import data.services.ScheduleService;
import data.services.StudentService;
import data.services.TaskService;
import app.testbase.TestBase;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class WindowRecordStudentOnTrialInRegularScheduleTests extends TestBase {

  String period = "18:00 - 20:00";
  String name = "Маша Машина";
  ScheduleService scheduleService = new ScheduleService();
  StudentService studentService = new StudentService();
  FamilyService familyService = new FamilyService();
  TaskService taskService = new TaskService();

  @BeforeMethod
  public void ensurePreconditions() {
    app.trScheduleTomorrow().RegularScheduleWithoutStudents(period, "recordStudentOnLesson", "14");

    app.trFamily().newFamily("recordStudent", false, "txc");

    app.trStudent()
        .NewStudent(
            "recordStudent",
            "Маша",
            "Машина",
            "expert",
            "BL",
            "recordStudent",
            "Europe/Minsk",
            2,
            app.base().DateWithCorrectionDays(-3650),
            "ru",
            "ru",
            "12345678i",
            "ru",
            "1",
            2);
  }

  @Test(retryAnalyzer = RunTestAgain.class)
  public void testWindowRecordStudentOnTrial() {
    app.goTo().menuSchedule();
    Schedules before = app.dbschedules().schedules();
    app.windowSchedule().recordStudentOnTrial(name, "14"); // имя ученика, id тренера
    Schedules after = app.dbschedules().schedules();
    assertThat(after.size(), equalTo(before.size()));
    check(before, after);
    app.goTo().menuTasks();
  }

  @AfterMethod(alwaysRun = true)
  public void clean() {
    scheduleService.DeleteById("recordStudentOnLesson");
    studentService.DeleteById("recordStudent");
    familyService.DeleteById("recordStudent");
    Tasks tasks = app.dbschedules().tasksComposition("recordStudent");
    for (TaskData taskClean : tasks) {
      taskService.DeleteById(taskClean.getId());
    }
  }

  private void check(Schedules before, Schedules after) {
    app.trScheduleTomorrow()
        .CombinationWithOneStudentOnRegularScredule_1(
            period, "recordStudentOnLesson", "14", "recordStudent", "1", "ru");
    ScheduleData scheduleAdd = scheduleService.findById("recordStudentOnLesson");

    for (ScheduleData scheduleBefore : before) {
      if (scheduleBefore.getId().equals("recordStudentOnLesson")) {
        assertThat(after, equalTo(before.without(scheduleBefore).withAdded(scheduleAdd)));
        return;
      }
    }
  }
}
