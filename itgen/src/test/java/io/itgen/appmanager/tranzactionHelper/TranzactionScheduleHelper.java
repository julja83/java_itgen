package io.itgen.appmanager.tranzactionHelper;

import io.itgen.general.TimeGeneral;
import io.itgen.model.ScheduleData;
import io.itgen.model.schedule.C;
import io.itgen.model.schedule.FinishedSlots;
import io.itgen.model.schedule.ST;
import io.itgen.model.schedule.Slots;
import io.itgen.model.schedule.Times;
import io.itgen.services.ScheduleService;
import java.util.ArrayList;
import java.util.Arrays;

public class TranzactionScheduleHelper {

  // вчера завершенное первое пробное занятие (ученик русский)
  public void FinishingYesterdayFirstTrialLesson(
      TimeGeneral time,
      ScheduleService scheduleService,
      String periodFinish,
      String idSchedule,
      String idTrainer,
      String idStudent,
      String idSubject) {
    ArrayList<Slots> listSlots = new ArrayList<>();
    ScheduleData schedule =
        new ScheduleData()
            .withId(idSchedule)
            .withVer(0)
            .withFromDate(time.dateYesterday())
            .withSlots(listSlots)
            .withFinishedSlots(
                Arrays.asList(
                    new FinishedSlots()
                        .withId(idTrainer)
                        .withW(time.dateYesterday())
                        .withSt(
                            new ST()
                                .withS(time.StimeYesterday(periodFinish))
                                .withE(time.EtimeYesterday(periodFinish)))
                        .withC(
                            Arrays.asList(
                                new C()
                                    .withId(idStudent)
                                    .withType(3)
                                    .withSubject(idSubject)
                                    .withLang("ru")
                                    .withTrial(true)
                                    .withS("finished")
                                    .withScore(3)
                                    .withRating(4)))
                        .withStartedAt(time.StimeYesterday(periodFinish))
                        .withFinishedAt(time.EtimeYesterday(periodFinish))))
            .withTimes(
                new Times().withStart(time.start(periodFinish)).withEnd(time.finish(periodFinish)))
            .withSkypeId("1")
            .withOneTime(true);
    scheduleService.save(schedule);
  }

  // Завтра разовое занятие, на которое записан ученик, после первого успешного пробного
  public void SingleScheduleTomorrowWithStudent_ScratchRuLesson(
      TimeGeneral time,
      ScheduleService scheduleService,
      String period,
      String idSchedule,
      String idTrainer,
      String idStudent,
      String idSubject,
      String lang) {
    ScheduleData schedule =
        new ScheduleData()
            .withId(idSchedule)
            .withVer(0)
            .withFromDate(time.dateTomorrow())
            .withSlots(
                Arrays.asList(
                    new Slots()
                        .withId(idTrainer)
                        .withW(time.dateTomorrow())
                        .withSt(
                            new ST()
                                .withS(time.StimeTomorrow(period))
                                .withE(time.EtimeTomorrow(period)))
                        .withC(
                            Arrays.asList(
                                new C()
                                    .withId(idStudent)
                                    .withType(3)
                                    .withSubject(idSubject)
                                    .withLang(lang)
                                    .withS("normal")
                                    .withScore(3)))))
            .withTimes(new Times().withStart(time.start(period)).withEnd(time.finish(period)))
            .withSkypeId("1");
    scheduleService.save(schedule);
  }

  // Завтра разовое занятие без учеников
  public ScheduleData SingleScheduleTomorrowWithoutStudent(
      TimeGeneral time,
      ScheduleService scheduleService,
      String period,
      String idSchedule,
      String idTrainer) {
    ArrayList<C> listC = new ArrayList<>();
    ArrayList<FinishedSlots> listFSlots = new ArrayList<>();
    ScheduleData schedule =
        new ScheduleData()
            .withId(idSchedule)
            .withVer(0)
            .withFromDate(time.dateTomorrow())
            .withSlots(
                Arrays.asList(
                    new Slots()
                        .withId(idTrainer)
                        .withW(time.dateTomorrow())
                        .withSt(
                            new ST()
                                .withS(time.StimeTomorrow(period))
                                .withE(time.EtimeTomorrow(period)))
                        .withC(listC)))
            .withFinishedSlots(listFSlots)
            .withTimes(new Times().withStart(time.start(period)).withEnd(time.finish(period)))
            .withSkypeId("1");
    scheduleService.save(schedule);
    return schedule;
  }

  // Завтра постоянное занятие, на которое записан ученик, после первого успешного пробного
  public void RegularScheduleTomorrowWithStudent_ScratchRuLesson(
      TimeGeneral time,
      ScheduleService scheduleService,
      String period,
      String idSchedule,
      String idTrainer,
      String idStudent,
      String idSubject,
      String lang) {
    int week = 604800000;
    ArrayList<FinishedSlots> listFSlots = new ArrayList<>();

    ScheduleData schedule =
        new ScheduleData()
            .withId(idSchedule)
            .withVer(0)
            .withFromDate(time.dateTomorrow())
            .withSlots(
                Arrays.asList(
                    new Slots()
                        .withId(idTrainer)
                        .withW(time.dateTomorrow())
                        .withSt(new ST().withS(time.Stime(period)).withE(time.Etime(period)))
                        .withC(
                            Arrays.asList(
                                new C()
                                    .withId(idStudent)
                                    .withType(3)
                                    .withSubject(idSubject)
                                    .withLang(lang)
                                    .withS("normal")
                                    .withP(true))),
                    new Slots()
                        .withId(idTrainer)
                        .withW(time.dateTomorrow() + week)
                        .withSt(
                            new ST()
                                .withS(time.Stime(period) + week)
                                .withE(time.Etime(period) + week))
                        .withC(
                            Arrays.asList(
                                new C()
                                    .withId(idStudent)
                                    .withType(3)
                                    .withSubject(idSubject)
                                    .withLang(lang)
                                    .withS("normal")
                                    .withP(true))),
                    new Slots()
                        .withId(idTrainer)
                        .withW(time.dateTomorrow() + week * 2)
                        .withSt(
                            new ST()
                                .withS(time.Stime(period) + week * 2)
                                .withE(time.Etime(period) + week * 2))
                        .withC(
                            Arrays.asList(
                                new C()
                                    .withId(idStudent)
                                    .withType(3)
                                    .withSubject(idSubject)
                                    .withLang(lang)
                                    .withS("normal")
                                    .withP(true))),
                    new Slots()
                        .withId(idTrainer)
                        .withW(time.dateTomorrow() + week * 3)
                        .withSt(
                            new ST()
                                .withS(time.Stime(period) + week * 3)
                                .withE(time.Etime(period) + week * 3))
                        .withC(
                            Arrays.asList(
                                new C()
                                    .withId(idStudent)
                                    .withType(3)
                                    .withSubject(idSubject)
                                    .withLang(lang)
                                    .withS("normal")
                                    .withP(true)))))
            .withFinishedSlots(listFSlots)
            .withTimes(new Times().withStart(time.start(period)).withEnd(time.finish(period)))
            .withSkypeId("1");
    scheduleService.save(schedule);
  }

  // завтра регулярное занятие без ученика
  public ScheduleData RegularScheduleTomorrow(
      TimeGeneral time,
      ScheduleService scheduleService,
      String period,
      String idSchedule,
      String idTrainer) {
    int week = 604800000;
    ArrayList<C> listC = new ArrayList<>();
    ScheduleData schedule =
        new ScheduleData()
            .withId(idSchedule)
            .withVer(0)
            .withFromDate(time.dateTomorrow())
            .withSlots(
                Arrays.asList(
                    new Slots()
                        .withId(idTrainer)
                        .withW(time.dateTomorrow())
                        .withSt(new ST().withS(time.Stime(period)).withE(time.Etime(period)))
                        .withC(listC),
                    new Slots()
                        .withId(idTrainer)
                        .withW(time.dateTomorrow() + week)
                        .withSt(
                            new ST()
                                .withS(time.Stime(period) + week)
                                .withE(time.Etime(period) + week))
                        .withC(listC),
                    new Slots()
                        .withId(idTrainer)
                        .withW(time.dateTomorrow() + week * 2)
                        .withSt(
                            new ST()
                                .withS(time.Stime(period) + week * 2)
                                .withE(time.Etime(period) + week * 2))
                        .withC(listC),
                    new Slots()
                        .withId(idTrainer)
                        .withW(time.dateTomorrow() + week * 3)
                        .withSt(
                            new ST()
                                .withS(time.Stime(period) + week * 3)
                                .withE(time.Etime(period) + week * 3))
                        .withC(listC)))
            .withTimes(new Times().withStart(time.start(period)).withEnd(time.finish(period)))
            .withSkypeId("1");
    scheduleService.save(schedule);
    return schedule;
  }

  // завтра регулярное расписание, но без ученика на первом занятии
  public ScheduleData RegularScheduleTomorrowWithStudentOnFirstLesson(
      TimeGeneral time,
      ScheduleService scheduleService,
      String period,
      String idSchedule,
      String idTrainer,
      String idStudent,
      String idSubject,
      String lang) {
    int week = 604800000;
    ArrayList<C> listC = new ArrayList<>();
    ScheduleData schedule =
        new ScheduleData()
            .withId(idSchedule)
            .withVer(0)
            .withFromDate(time.dateTomorrow())
            .withSlots(
                Arrays.asList(
                    new Slots()
                        .withId(idTrainer)
                        .withW(time.dateTomorrow())
                        .withSt(new ST().withS(time.Stime(period)).withE(time.Etime(period)))
                        .withC(listC),
                    new Slots()
                        .withId(idTrainer)
                        .withW(time.dateTomorrow() + week)
                        .withSt(
                            new ST()
                                .withS(time.Stime(period) + week)
                                .withE(time.Etime(period) + week))
                        .withC(
                            Arrays.asList(
                                new C()
                                    .withId(idStudent)
                                    .withType(3)
                                    .withSubject(idSubject)
                                    .withLang(lang)
                                    .withS("normal")
                                    .withP(true))),
                    new Slots()
                        .withId(idTrainer)
                        .withW(time.dateTomorrow() + week * 2)
                        .withSt(
                            new ST()
                                .withS(time.Stime(period) + week * 2)
                                .withE(time.Etime(period) + week * 2))
                        .withC(
                            Arrays.asList(
                                new C()
                                    .withId(idStudent)
                                    .withType(3)
                                    .withSubject(idSubject)
                                    .withLang(lang)
                                    .withS("normal")
                                    .withP(true))),
                    new Slots()
                        .withId(idTrainer)
                        .withW(time.dateTomorrow() + week * 3)
                        .withSt(
                            new ST()
                                .withS(time.Stime(period) + week * 3)
                                .withE(time.Etime(period) + week * 3))
                        .withC(
                            Arrays.asList(
                                new C()
                                    .withId(idStudent)
                                    .withType(3)
                                    .withSubject(idSubject)
                                    .withLang(lang)
                                    .withS("normal")
                                    .withP(true)))))
            .withTimes(new Times().withStart(time.start(period)).withEnd(time.finish(period)))
            .withSkypeId("1");
    scheduleService.save(schedule);
    return schedule;
  }
}
