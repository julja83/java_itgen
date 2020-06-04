package ru.stqa.pft.itgen.tests.schedule;
//автотест проверяет отмену занятия у тренера в постоянном расписании

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.itgen.general.TimeGeneral;
import ru.stqa.pft.itgen.model.Schedule.C;
import ru.stqa.pft.itgen.model.Schedule.ST;
import ru.stqa.pft.itgen.model.Schedule.Slots;
import ru.stqa.pft.itgen.model.Schedule.Times;
import ru.stqa.pft.itgen.model.ScheduleData;
import ru.stqa.pft.itgen.model.Schedules;
import ru.stqa.pft.itgen.services.ScheduleService;
import ru.stqa.pft.itgen.tests.TestBase;

import java.util.ArrayList;
import java.util.Arrays;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ScheduleRegularCancelTests extends TestBase {
  ArrayList<C> list = new ArrayList();
  String period = "21:00 - 23:00";
  int week = 604800000;

  @BeforeMethod
  public void ensurePreconditions() {
    TimeGeneral time = new TimeGeneral();
    ScheduleService scheduleService = new ScheduleService();
    ScheduleData schedule = new ScheduleData()
            .withId("scheduleRegularCancel")
            .withVer(0)
            .withFromDate(time.date())
            .withSlots(Arrays.asList(new Slots()
                    .withId("14")
                    .withW(time.date())
                    .withSt(new ST().withS(time.Stime(period)).withE(time.Etime(period)))
                    .withC(list), new Slots()
                    .withId("14")
                    .withW(time.date() + week)
                    .withSt(new ST().withS(time.Stime(period) + week).withE(time.Etime(period) + week))
                    .withC(list), new Slots()
                    .withId("14")
                    .withW(time.date() + week * 2)
                    .withSt(new ST().withS(time.Stime(period) + week * 2).withE(time.Etime(period) + week * 2))
                    .withC(list), new Slots()
                    .withId("14")
                    .withW(time.date() + week * 3)
                    .withSt(new ST().withS(time.Stime(period) + week * 3).withE(time.Etime(period) + week * 3))
                    .withC(list), new Slots()
                    .withId("14")
                    .withW(time.date() + week * 4)
                    .withSt(new ST().withS(time.Stime(period) + week * 4).withE(time.Etime(period) + week * 4))
                    .withC(list), new Slots()
                    .withId("14")
                    .withW(time.date() + week * 5)
                    .withSt(new ST().withS(time.Stime(period) + week * 5).withE(time.Etime(period) + week * 5))
                    .withC(list)))
            .withTimes(new Times().withStart(time.start(period)).withEnd(time.finish(period)))
            .withSkypeId("1");
    scheduleService.save(schedule);

  }

  @Test
  public void testScheduleRegularCancel() {
    app.goTo().menuTasks();
    app.goTo().menuSchedule();
    Schedules before = app.dbschedules().schedules();
    app.schedule().cancel("scheduleRegularCancel");
    Schedules after = app.dbschedules().schedules();
    assertThat(after.size(), equalTo(before.size()));
    check(after, before);
  }

  @AfterMethod(alwaysRun = true)
  public void clean() {
    ScheduleService scheduleService = new ScheduleService();
    scheduleService.findByIdAndDelete("scheduleRegularCancel");
  }

  private void check(Schedules after, Schedules before) {
    TimeGeneral time = new TimeGeneral();
    ScheduleData scheduleAdd = new ScheduleData()
            .withId("scheduleRegularCancel")
            .withVer(0)
            .withFromDate(time.date())
            .withSlots(Arrays.asList(new Slots()
                    .withId("14")
                    .withW(time.date())
                    .withSt(new ST().withS(time.Stime(period)).withE(time.Etime(period)))
                    .withC(list).withCancelled(true), new Slots()
                    .withId("14")
                    .withW(time.date() + week)
                    .withSt(new ST().withS(time.Stime(period) + week).withE(time.Etime(period) + week))
                    .withC(list), new Slots()
                    .withId("14")
                    .withW(time.date() + week * 2)
                    .withSt(new ST().withS(time.Stime(period) + week * 2).withE(time.Etime(period) + week * 2))
                    .withC(list), new Slots()
                    .withId("14")
                    .withW(time.date() + week * 3)
                    .withSt(new ST().withS(time.Stime(period) + week * 3).withE(time.Etime(period) + week * 3))
                    .withC(list), new Slots()
                    .withId("14")
                    .withW(time.date() + week * 4)
                    .withSt(new ST().withS(time.Stime(period) + week * 4).withE(time.Etime(period) + week * 4))
                    .withC(list), new Slots()
                    .withId("14")
                    .withW(time.date() + week * 5)
                    .withSt(new ST().withS(time.Stime(period) + week * 5).withE(time.Etime(period) + week * 5))
                    .withC(list)))
            .withTimes(new Times().withStart(time.start(period)).withEnd(time.finish(period)))
            .withSkypeId("1");

    for (ScheduleData scheduleBefore : before) { //найти в списке "до" родителя с таким id
      if (scheduleBefore.getId().equals("scheduleRegularCancel")) {
        assertThat(after, equalTo(before.without(scheduleBefore).withAdded(scheduleAdd)));
        return;
      }
    }
  }

}
