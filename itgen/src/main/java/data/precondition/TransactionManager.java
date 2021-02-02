package data.precondition;

import app.appmanager.transactionHelper.TrAchievementHelper;
import app.appmanager.transactionHelper.TrChatHelper;
import app.appmanager.transactionHelper.TrCommunityHelper;
import app.appmanager.transactionHelper.TrFamilyHelper;
import app.appmanager.transactionHelper.TrFinishedLessonHelper;
import app.appmanager.transactionHelper.TrMaterialHelper;
import app.appmanager.transactionHelper.TrParentHelper;
import app.appmanager.transactionHelper.TrPaymentHelper;
import app.appmanager.transactionHelper.TrRequestHelper;
import app.appmanager.transactionHelper.TrSkillHelper;
import app.appmanager.transactionHelper.TrStudentHelper;
import app.appmanager.transactionHelper.TrTestHelper;
import app.appmanager.transactionHelper.TrWorkerHelper;
import app.appmanager.transactionHelper.schedule.TrScheduleTodayHelper;
import app.appmanager.transactionHelper.schedule.TrScheduleTomorrowHelper;
import app.appmanager.transactionHelper.schedule.TrScheduleYesterdayHelper;
import core.general.TimeGeneral;

public class TransactionManager {

  private final TrScheduleYesterdayHelper trScheduleYesterdayHelper = new TrScheduleYesterdayHelper();
  private final TrScheduleTodayHelper trScheduleTodayHelper = new TrScheduleTodayHelper();
  private final TrScheduleTomorrowHelper trScheduleTomorrowHelper = new TrScheduleTomorrowHelper();
  private final TrStudentHelper trStudentHelper = new TrStudentHelper();
  private final TrChatHelper transactionChatHelper = new TrChatHelper();
  private final TrFamilyHelper transactionFamilyHelper = new TrFamilyHelper();
  private final TrParentHelper transactionParentHelper = new TrParentHelper();
  private final TrWorkerHelper transactionWorkerHelper = new TrWorkerHelper();
  private final TrPaymentHelper transactionPaymentHelper = new TrPaymentHelper();
  private final TrMaterialHelper transactionMaterialHelper = new TrMaterialHelper();
  private final TrFinishedLessonHelper transactionFinishedLessonHelper = new TrFinishedLessonHelper();
  private final TrSkillHelper transactionSkillHelper = new TrSkillHelper();
  private final TrTestHelper transactionTestHelper = new TrTestHelper();
  private final TrCommunityHelper transactionCommunityHelper = new TrCommunityHelper();
  private final TrAchievementHelper transactionAchievementHelper = new TrAchievementHelper();
  private final TrRequestHelper transactionRequestHelper = new TrRequestHelper();
  private final TimeGeneral time = new TimeGeneral();

  public TransactionManager() {
  }

  public TrScheduleYesterdayHelper trScheduleYesterday() {
    return trScheduleYesterdayHelper;
  }

  public TrScheduleTomorrowHelper trScheduleTomorrow() {
    return trScheduleTomorrowHelper;
  }

  public TrScheduleTodayHelper trScheduleToday() {
    return trScheduleTodayHelper;
  }

  public TrStudentHelper trStudent() {
    return trStudentHelper;
  }

  public TrChatHelper trChat() {
    return transactionChatHelper;
  }

  public TrCommunityHelper trCommunity() {
    return transactionCommunityHelper;
  }

  public TrFamilyHelper trFamily() {
    return transactionFamilyHelper;
  }

  public TrParentHelper trParent() {
    return transactionParentHelper;
  }

  public TrWorkerHelper trWorker() {
    return transactionWorkerHelper;
  }

  public TrPaymentHelper trPayment() {
    return transactionPaymentHelper;
  }

  public TrMaterialHelper trMaterial() {
    return transactionMaterialHelper;
  }

  public TrFinishedLessonHelper trFinishedLesson() {
    return transactionFinishedLessonHelper;
  }

  public TrSkillHelper trSkill() {
    return transactionSkillHelper;
  }

  public TrTestHelper trTest() {
    return transactionTestHelper;
  }

  public TrAchievementHelper trAchievement() {
    return transactionAchievementHelper;
  }

  public TrRequestHelper trRequest() {
    return transactionRequestHelper;
  }

  public TimeGeneral time() {
    return time;
  }
}
