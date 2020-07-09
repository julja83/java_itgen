package io.itgen.tests.lkParent;
// отмена разового занятия

import io.itgen.general.TimeGeneral;
import io.itgen.model.ScheduleData;
import io.itgen.model.Schedules;
import io.itgen.model.TaskData;
import io.itgen.model.Tasks;
import io.itgen.services.ScheduleService;
import io.itgen.services.StudentService;
import io.itgen.services.TaskService;
import io.itgen.tests.TestBase;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class CancelOnSingle extends TestBase {
  String period = "18:00 - 20:00";

  // тестовая ситуация: есть дефолтная семья, к которой добавлен ученик, прошедший вчера пробное в
  // 18.00 и который записан на разовое расписание на завтра в 18.00
  @BeforeMethod
  public void ensurePreconditions() {
    TimeGeneral time = new TimeGeneral();
    ScheduleService scheduleService = new ScheduleService();
    StudentService studentService = new StudentService();
    // первое пробное занятие, которое завершил ученик с Был
    app.trScheduleYesterday()
        .FinishingFirstTrialLesson(
            time,
            scheduleService,
            period,
            "FinishedSchedule",
            "14",
            "LkCancelLessonInSingleSchedule",
            "1");

    // студент, добавленный в дефолтную семью, который прошел пробное успешно и записанный на
    // следующее занятие
    app.trStudent()
        .StudentAddDefaultFamily_FinishedTrailLesson_RecordSingle(
            studentService,
            "LkCancelLessonInSingleSchedule",
            "expert",
            "BL",
            "Europe/Minsk",
            2,
            "ru",
            "ru");

    // занятие, на которое записан ученик
    app.trScheduleTomorrow()
        .SingleScheduleWithOneStudent(
            time,
            scheduleService,
            period,
            "LkCancelLessonInSingleSchedule",
            "14",
            "LkCancelLessonInSingleSchedule",
            "1",
            "ru");
  }

  @Test()
  public void testCancelOnSingle() {
    Schedules before = app.dbschedules().schedules();
    app.lkParent().cancelLessonInSingleSchedule();
    Schedules after = app.dbschedules().schedules();
    assertThat(after.size(), equalTo(before.size()));
    check(before, after);
    app.lkParent().btnLogo();
  }

  @AfterMethod(alwaysRun = true)
  public void clean() {
    ScheduleService scheduleService = new ScheduleService();
    scheduleService.findByIdAndDelete("FinishedSchedule");
    scheduleService.findByIdAndDelete("LkCancelLessonInSingleSchedule");

    StudentService studentService = new StudentService();
    studentService.findByIdAndDelete("LkCancelLessonInSingleSchedule");

    Tasks tasks = app.dbschedules().tasksComposition("LkCancelLessonInSingleSchedule");
    TaskService taskService = new TaskService();
    for (TaskData taskClean : tasks) {
      taskService.findByIdAndDeleteTask(taskClean.getId());
    }
  }

  private void check(Schedules before, Schedules after) {
    TimeGeneral time = new TimeGeneral();
    ScheduleService scheduleService = new ScheduleService();

    app.trScheduleTomorrow()
        .SingleScheduleWithoutStudent(
            time, scheduleService, period, "LkCancelLessonInSingleSchedule", "14");

    ScheduleData scheduleAdd = scheduleService.findById("LkCancelLessonInSingleSchedule");
    for (ScheduleData scheduleBefore : before) {
      if (scheduleBefore.getId().equals("LkCancelLessonInSingleSchedule")) {
        Schedules befor11 = (before.without(scheduleBefore).withAdded(scheduleAdd));
        assertThat(after, equalTo(befor11));
        return;
      }
    }
  }
}
