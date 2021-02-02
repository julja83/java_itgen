package tests.lkTrainer;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

import app.testbase.TestBase;
import core.general.TimeGeneral;
import data.model.schedule.Comments;
import data.model.schedule.FinishedChildLessons;
import data.model.schedule.FinishedLessons;
import data.model.schedule.Schedules;
import data.services.CommentService;
import data.services.FamilyService;
import data.services.FinishedChildLessonService;
import data.services.FinishedLessonService;
import data.services.ScheduleService;
import data.services.StudentService;
import data.services.TaskService;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class TrainerFinishedLessonWithStudentWas extends TestBase {
  ScheduleService scheduleService = new ScheduleService();
  StudentService studentService = new StudentService();
  FamilyService familyService = new FamilyService();
  TaskService taskService = new TaskService();
  CommentService commentService = new CommentService();
  private final TimeGeneral time = new TimeGeneral();
  FinishedChildLessonService finishedChildLessonService = new FinishedChildLessonService();
  FinishedLessonService finishedLessonService = new FinishedLessonService();
  private String period = "";
  private final long alreadyRun = 7200000; // 2 часа идет занятие

  @BeforeMethod
  public void ensurePreconditions() {
    period = time.getPeriod(time.getTimeNow() - alreadyRun);
    app.trScheduleToday()
        .StartSingleScheduleWithOneStudentOnTrail(
            (double) alreadyRun,
            period,
            "finishLessonByTrainer",
            "23",
            "finishLesson",
            "1",
            "ru");

    app.trFamily().newFamily("finishLessonByTrainer", false, "RHCtjnpq5oTfhKPQs");

    app.trStudent()
        .newStudent(
            "finishLesson",
            "Маша",
            "Машина",
            "expert",
            "BL",
            "finishLessonByTrainer",
            "Europe/Minsk",
            2,
            app.base().DateWithCorrectionDays(-3650),
            "ru",
            "ru",
            "12345678i",
            "ru",
            new String[]{"1"},
            2,
            "noTrial");
  }

  @Test
  public void testTrainerFinishedLessonWithStudentWas() {
    Schedules before = app.dbschedules().schedules();
    FinishedChildLessons finishChildBefore = app.dbschedules().finishedChildLessons();
    FinishedLessons finishBefore = app.dbschedules().finishedLessons();
    Comments commentsBefore = app.dbschedules().comments();
    app.trainer().gotoSchedule();
    app.trainer().finishedLessonWithWas("finishLessonByTrainer");

    Schedules after = app.dbschedules().schedules();
    FinishedChildLessons finishChildAfter = app.dbschedules().finishedChildLessons();
    FinishedLessons finishAfter = app.dbschedules().finishedLessons();
    Comments commentsAfter = app.dbschedules().comments();

    assertThat(after.size(), equalTo(before.size()));
    assertThat(finishChildAfter.size(), equalTo(finishChildBefore.size() + 1));
    assertThat(finishAfter.size(), equalTo(finishBefore.size() + 1));
    assertThat(commentsAfter.size(), equalTo(commentsBefore.size() + 1));
    check(after);
  }

  @AfterMethod(alwaysRun = true)
  public void clean() {
    familyService.DeleteById("finishLessonByTrainer");
    scheduleService.drop();
    finishedChildLessonService.drop();
    finishedLessonService.drop();
    taskService.drop();
    commentService.drop();
    studentService.DeleteById("finishLesson");
  }

  private void check(Schedules after) {
    scheduleService.DeleteById("finishLessonByTrainer");
    app.trScheduleToday()
        .FinishedSingleScheduleWithOneStudentOnTrail(
            (double) alreadyRun,
            period,
            "finishLessonByTrainer",
            "23",
            "finishLesson",
            "1",
            "ru",
            "finished");
    Schedules afterNew = app.dbschedules().schedules();
    assertThat(after, equalTo(afterNew));
  }
}
