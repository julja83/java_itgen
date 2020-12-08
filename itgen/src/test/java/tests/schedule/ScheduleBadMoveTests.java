package tests.schedule;
// автотест проверяет подвижку разового расписания

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

import app.testbase.TestBase;
import core.general.RunTestAgain;
import core.general.TimeGeneral;
import data.model.schedule.C;
import data.model.schedule.ST;
import data.model.schedule.ScheduleData;
import data.model.schedule.Schedules;
import data.model.schedule.Slots;
import data.model.schedule.Times;
import data.services.ScheduleService;
import java.util.ArrayList;
import java.util.Arrays;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class ScheduleBadMoveTests extends TestBase {
  ArrayList<C> list = new ArrayList<>();
  String period = "21:00 - 23:00";
  Schedules after = null;
  Schedules before = null;

  @BeforeMethod
  public void ensurePreconditions() {
    TimeGeneral time = new TimeGeneral();
    ScheduleService scheduleService = new ScheduleService();
    ScheduleData schedule =
        new ScheduleData()
            .withId("scheduleSingleMove")
            .withVer(0)
            .withFromDate(time.date())
            .withSlots(
                Arrays.asList(
                    new Slots()
                        .withId("14")
                        .withW(time.date())
                        .withSt(new ST().withS(time.Stime(period)).withE(time.Etime(period)))
                        .withC(list)))
            .withTimes(new Times().withStart(time.start(period)).withEnd(time.finish(period)))
            .withWholeness(false)
            .withDuration(120)
            .withOneTime(true);
    scheduleService.save(schedule);
  }

  @Test(retryAnalyzer = RunTestAgain.class) // нельзя подвинуть на время в прошлом
  public void testBadTimeScheduleSingleMove() {
    app.goTo().menuSchedule();
    before = app.dbschedules().schedules();
    app.schedule().badMove("scheduleSingleMove");
    after = app.dbschedules().schedules();
    assertThat(after.size(), equalTo(before.size()));
    app.goTo().menuTasks();
  }

  @Test(alwaysRun = true)
  // нельзя подвинуть не поменяв дату и время
  public void testNoChangeDateTimeScheduleSingleMove() {
    app.goTo().menuSchedule();
    before = app.dbschedules().schedules();
    app.schedule().badMoveNotChangeDateTime("scheduleSingleMove");
    after = app.dbschedules().schedules();
    assertThat(after.size(), equalTo(before.size()));
    app.goTo().menuTasks();
  }

  @AfterMethod(alwaysRun = true)
  public void clean() {
    ScheduleService scheduleService = new ScheduleService();
    scheduleService.DeleteById("scheduleSingleMove");
  }
}