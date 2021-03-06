package app.appmanager;

import static app.appmanager.ApplicationManager.properties;

import data.model.users.StudentData;
import java.awt.Toolkit;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class LKParentHelper extends HelperBase {

  private final By btnRecordOnTrail = By.xpath("(//button[contains(@id-qa,'trial')])[2]");
  private final By btnRecordOnTrailInSchedule = By.xpath("//button[@id-qa='trial']");
  private final By btnLogo = By.xpath("//img[contains(@src,'logo')]");
  private final By btnSignUp = By.xpath("//button[contains(@id-qa,'signup')]");
  private final By selectLesson = By.className("list-of-times");
  private final By editNewStudent_firstName = By.xpath("//input[@id-qa='name']");
  private final By editNewStudent_lastName = By.xpath("//input[@id-qa='surname']");
  private final By editNewStudent_birthday = By.xpath("//input[@id-qa='birthday']");
  private final By editNewStudent_gender = By.xpath("//input[@id-qa='gender']/..");
  private final By editNewStudent_lang = By.xpath("//input[@id-qa='lang']/..");
  private final By editNewStudent_pcLevel = By.xpath("//input[@id-qa='pcLevel']/..");
  private final By btnNextOnForm = By.className("button-next");
  private final By btnAddNewStudent = By.xpath("//a[contains(@href,'addChild')]");
  private final By btnSkipHelper = By.xpath("//button[@title='Skip']");
  private final By btnTomorrowInPicker = By.xpath(
      "//div[@class='picker-item selected']/following-sibling::div");
  private final By btnTodayInPicker = By.xpath("//div[@class='picker-item selected']");
  private final By selectedIcon = By.xpath("//span[@class='selected-icon']");
  private final By btnSingleSchedule = By.xpath("(//div[@class='info'])[2]");
  private final By btnRecordOnLesson = By.xpath("//button//span[text()='Записаться на занятие']");
  private final By selectCheckBoxForSignup = By.xpath("//label[contains(@for,'signup')]");
  private final By btnNext = By.xpath("//button[contains(@class,'next')]");
  private final By editLeftFiltrTime = By.xpath("//div[@class='times-filter']//input[1]");
  private final By editRightFiltrTime = By.xpath("(//div[@class='times-filter']//input)[2]");
  private final By emptyAreaFiltrTime = By.xpath("//div[@class='times-filter']");
  private final By btnRecord = By.xpath("//div[contains(@class,'actions')]//button");
  private final By btnShowScheduleChild = By.xpath("//button[@id-qa='show-schedule']");
  private final By btnClickHistory = By.xpath("//div[contains(@class,'btn-toggle')]");
  private final By btnPrepare = By.xpath("//button[@id-qa='prepare']");
  private final By btnSchedule = By.xpath("//div[@class='child-schedule-btn']");
  private final By btnShowHistoryFirstChild = By.xpath("//button[@id-qa='show-history']");
  private final By btnShowHistorySecondChild = By.xpath("(//button[@id-qa='show-history'])[2]");
  private final By btnMainHelpCenter = By.xpath("//div[@class='help-center-btn']");
  private final By btnHelpCenterInMenu = By.xpath("//a[contains(@href,'itgen.io')]");
  private final By btnShowScheduleSecondChild = By.xpath(
      "(//div[@class='child-management'])[2]//button[@id-qa='show-schedule']");
  private final By btnTrialSecondChild = By.xpath("(//button[@id-qa='trial'])[2]");
  private final By tabSchedule = By.xpath("//button[@id-qa='tab-schedule']");
  private final By labelSchedulePeriodLesson = By.xpath("//div[@class='lesson-content-item'][1]");
  private final By labelScheduleTrainerOnLesson = By.xpath(
      "//div[@class='lesson-content-item'][2]");
  private final By labelScheduleSkillOnLesson = By.xpath("(//div[@class='flex-content']//span)[1]");
  private final By scheduleLabelNewOnLesson = By.xpath(
      "(//div[@class='lesson-label new-skill'])[1]");
  private final By scheduleLabelSkipped = By.xpath("(//div[@class='lesson-label skipped'])[1]");
  private final By scheduleLabelPlannedOnLesson = By.xpath("//div[@class='lesson-label planned']");
  private final By scheduleLabelFinished = By.xpath("//div[@class='lesson-label finished']");
  private final By tabSettings = By.xpath("//button[@id-qa='tab-settings']");
  private final By labelPersonalInformation = By.xpath("//div[@class='info']//h4");
  private final By labelLoginAndPassword = By.xpath("//div[@class='login-and-password']//h4");
  private final By labelHowToJoinInstruction = By.xpath(
      "//div[@class='how-to-join-instruction']//span");
  private final By labelFreeLessonsHeader = By.xpath("//div[@class='freeLessons-header']//h4");
  private final By btnShareHref = By.xpath("//div[@class='share-copyButton']");
  private final By btnFreeLessons = By.xpath("//div[@class='family-free-lessons']//button");
  private final By sectionSocialNetworks = By.xpath(" //div[@class='shareButtons']");
  private final By tabHistory = By.xpath("//button[@id-qa='tab-history']");
  private final By labelPeriodLessonInHistory = By.xpath("//div[@class='time']");
  private final By labelDayAndMonthLessonInHistory = By.xpath("//span[@class='day-of-month']");
  private final By labelProjectDoneInHistory = By.xpath(
      "//div[@class='material-project done']//span");
  private final By labelProjectNotStartedInHistory = By.xpath(
      "//div[@class='material-project not-started']//span");
  private final By labelGradeAboutLessonInHistory = By.xpath("//ul[@class='grade-texts']");
  private final By labelWaiteOnLessons = By.xpath("//h4");
  private final By labelRecomendationForFirstLesson = By.xpath("//div[@class='title']//span");
  private final By iconCalendar = By.xpath(
      "//a[contains(@href,'/newStudent')]//div[@class='child-schedule-btn']");
  private final By labelFutureLessonOnGeneralPage = By.xpath(
      "//span[@class='lesson-info gena-text-muted']");
  private final By btnHistoryBallans = By.xpath("//div[@class='gena-panel-btn']");
  private final By btnBlockHistoryBallans = By.xpath("//tbody");
  private final By btnInfoAboutReasonBallans = By.xpath("//tbody//td[2]");
  private final By btnTutorialByLkParent = By.xpath("//li[@id-qa='parent-cabinet']");
  private final By winTutorialByLkParent = By.xpath("//div[@class='tooltip-with-itgenik']");
  private final By btnTutorialBySwitchToStudent = By.xpath("//li[@id-qa='child-cabinet']");
  private final By winTutorialBySwitchToStudent = By.xpath(
      "//div[@class='base-tooltip-tutorials']");
  private final By btnFacebook = By.xpath("//button[@aria-label='facebook']");
  private final By btnInstall = By.xpath("//button[@id-qa='install']");
  private final By btnInstallInScheduleTomorrow = By.xpath(
      "//div[contains(@class,'lightUp')]//button[@id-qa='install']");
  private final By cellLightCalendar = By.xpath("//div[contains(@class,'lightUp')]");
  private final By btnAssignWorking = By.xpath("//div[contains(@class,'assign')]//button");
  private final By btnScroll = By.xpath("(//div[@class='header']//following-sibling::button)[5]");
  private final By btnPaginatorRight = By.xpath("//button[contains(@class,'pagination-right')]");
  private final By btnPaginatorLeft = By.xpath("//button[contains(@class,'pagination-left')]");
  private final By monthUI = By.xpath("//span[@class='date']");
  private final By labelWorkingOffOnThirdLessonInSchedule = By.xpath(
      "((//div[@class='lesson-content-client'])[3]//div[@class='lesson-label working-off'])[1]");
  private final By labelWorkingOff = By.xpath("(//div[@class='lesson-label working-off'])[1]");
  private final By clickByFirstStudent = By.xpath("//a[contains(@href,'21')]");
  private final By clickBySecondStudent = By.xpath("//a[contains(@href,'newStudent')]");
  private final By btnChangePassword = By.xpath("//div[@class='password']//button");
  private final By labelLoginInSettings = By.xpath(
      "(//div[@class='current'])[1]//div[@class='value']");
  private final By imageInstructionGif = By.xpath("//img[@class='instruction-gif']");
  private final By winTutorialsInSettings = By.xpath("//div[contains(@class,'tutorials')]");
  private final By editPasswordFirst = By.xpath("(//input[@type='password'])[1]");
  private final By editPasswordSecond = By.xpath("(//input[@type='password'])[2]");
  private final By btnCancelPassword = By.xpath("(//div[@class='password active']//button)[2]");
  private final By btnSavePassword = By.xpath("(//div[@class='password active']//button)[1]");
  private final By editLogin = By.xpath("//div[@class='login active']//input");
  private final By btnCancelLogin = By.xpath("(//div[@class='login active']//button)[2]");
  private final By btnSaveLogin = By.xpath("//div[@class='login active']//button");
  private final By btnChangeLogin = By.xpath("//div[@class='login']//button");
  private final By btnHeaderForLogout = By.xpath("//div[@class='head']");
  private final By closeFirstTitorialInSettings = By.xpath(
      "(//div[contains(@class,'dropdown')][1]//span//following::*[local-name()='svg'])[1]");
  private final By closeSecondTitorialInSettings = By.xpath(
      "(//div[contains(@class,'dropdown')][1]//span//following::*[local-name()='svg'])[2]");
  private final By btnRecordOnTrailOnMinecraft = By.xpath("//button[@id-qa='btn-21']");
  private final By labelResultTestPart1 = By
      .xpath("(//footer[@class='test-not-successful']//span)[1]");
  private final By labelResultTestPart2 = By
      .xpath("(//footer[@class='test-not-successful']//span)[2]");
  private final By labelNotAllowedRecordOnTestingSkillWithoutTest = By.xpath(
      "//div[@class='test-not-successful']//span");
  private final By dropDownSkillMinecraftInRecord = By.xpath("//div[text()='Minecraft']");
  private final By labelInHistoryMinecraft = By.xpath(
      "//div[@class='lesson-comment']//span[text()='Minecraft']");
  private final By labelInHistoryScratch = By.xpath(
      "//div[@class='lesson-comment']//span[text()='Scratch']");
  private final By btnCloseWinWorkingOffWithoutLesson = By.xpath(
      "//div[@class='modal-body']//button");
  private final String selectParent = "//span[text()='Parent']";

  public LKParentHelper(WebDriver wd) {
    super(wd);
  }

  public By getBtnLogo() {
    return btnLogo;
  }

  public By getShowHistorySecondChild() {
    return btnShowHistorySecondChild;
  }

  public By getShowScheduleSecondChild() {
    return btnShowScheduleSecondChild;
  }

  public By getBtnTrialSecondChild() {
    return btnTrialSecondChild;
  }

  public By getTabSchedule() {
    return tabSchedule;
  }

  public By getSchedulePeriodLesson() {
    return labelSchedulePeriodLesson;
  }

  public By getScheduleTrainerOnLesson() {
    return labelScheduleTrainerOnLesson;
  }

  public By getScheduleSkillOnLesson() {
    return labelScheduleSkillOnLesson;
  }

  public By getScheduleLabelNewOnLesson() {
    return scheduleLabelNewOnLesson;
  }

  public By getScheduleLabelPlannedOnLesson() {
    return scheduleLabelPlannedOnLesson;
  }

  public By getLabelPersonalInformation() {
    return labelPersonalInformation;
  }

  public By getTabSettings() {
    return tabSettings;
  }

  public By getLabelLoginAndPassword() {
    return labelLoginAndPassword;
  }

  public By getLabelHowToJoinInstruction() {
    return labelHowToJoinInstruction;
  }

  public By getLabelFreeLessonsHeader() {
    return labelFreeLessonsHeader;
  }

  public By getButtonShareHref() {
    return btnShareHref;
  }

  public By getSectionSocialNetworks() {
    return sectionSocialNetworks;
  }

  public By getTabHistory() {
    return tabHistory;
  }

  public By getLabelPeriodLessonInHistory() {
    return labelPeriodLessonInHistory;
  }

  public By getLabelDayAndMonthLessonInHistory() {
    return labelDayAndMonthLessonInHistory;
  }

  public By getLabelProjectDoneInHistory() {
    return labelProjectDoneInHistory;
  }

  public By getLabelProjectNotStartedInHistory() {
    return labelProjectNotStartedInHistory;
  }

  public By getLabelGradeAboutLessonInHistory() {
    return labelGradeAboutLessonInHistory;
  }

  public By getLabelWaiteOnLessons() {
    return labelWaiteOnLessons;
  }

  public By getLabelRecomendationForFirstLesson() {
    return labelRecomendationForFirstLesson;
  }

  public By getIconCalendar() {
    return iconCalendar;
  }

  public By getLabelFutureLessonOnGeneralPage() {
    return labelFutureLessonOnGeneralPage;
  }

  public By getBtnBlockHistoryBallans() {
    return btnBlockHistoryBallans;
  }

  public By getBtnInfoAboutReasonBallans() {
    return btnInfoAboutReasonBallans;
  }

  public By getWinTutorialByLkParent() {
    return winTutorialByLkParent;
  }

  public By getWinTutorialBySwitchToStudent() {
    return winTutorialBySwitchToStudent;
  }

  public By getBtnInstall() {
    return btnInstall;
  }

  public By getBtnInstallInScheduleTomorrow() {
    return btnInstallInScheduleTomorrow;
  }

  public By getCellLightCalendar() {
    return cellLightCalendar;
  }

  public By getBtnRecordOnTrailInSchedule() {
    return btnRecordOnTrailInSchedule;
  }

  public By getBtnRecordOnLesson() {
    return btnRecordOnLesson;
  }

  public By getBtnAssignWorking() {
    return btnAssignWorking;
  }

  public By getScheduleLabelSkipped() {
    return scheduleLabelSkipped;
  }

  public By getScheduleLabelFinished() {
    return scheduleLabelFinished;
  }

  public By getLabelWorkingOffOnThirdLessonInSchedule() {
    return labelWorkingOffOnThirdLessonInSchedule;
  }

  public By getLabelWorkingOff() {
    return labelWorkingOff;
  }

  public By getLabelLoginInSettings() {
    return labelLoginInSettings;
  }

  public By getImageInstructionGif() {
    return imageInstructionGif;
  }

  public By getWinTutorialsInSettings() {
    return winTutorialsInSettings;
  }

  public By getBtnRecordOnTrailOnMinecraft() {
    return btnRecordOnTrailOnMinecraft;
  }

  public By getLabelResultTestPart1() {
    return labelResultTestPart1;
  }

  public By getLabelResultTestPart2() {
    return labelResultTestPart2;
  }

  public By getLabelNotAllowedRecordOnTestingSkillWithoutTest() {
    return labelNotAllowedRecordOnTestingSkillWithoutTest;
  }

  public By getLabelInHistoryMinecraft() {
    return labelInHistoryMinecraft;
  }

  public By getLabelInHistoryScratch() {
    return labelInHistoryScratch;
  }

  public By getDropDownSkillMinecraftInRecord() {
    return dropDownSkillMinecraftInRecord;
  }

  public String getSelectParent() {
    return selectParent;
  }

  public void btnRecordOnTrail() {
    if (!isElementPresent(btnRecordOnTrail)) {
      refresh(); // для докера
    }
    clickWithMoveToElementAndWait(5, btnRecordOnTrail);
    noErrorMessage();
  }

  public void recordOnTrail(int idSkill) {
    skipHelper();
    btnLogo();
    btnRecordOnTrail();
    btnSelectSkill(idSkill);
    selectLesson();
    btnSignUp();
  }

  public void btnLogo() {
    clickWithMoveToElementAndWait(3, btnLogo);
    noErrorMessage();
  }

  public void clickByTabSchedule() {
    click(tabSchedule);
    noErrorMessage();
  }

  private void btnSignUp() {
    clickWaitElementToBeClicable(5, btnSignUp);
    noErrorMessage();
  }

  public void selectLesson() {
    click(selectLesson);
    noErrorMessage();
  }

  public void btnSelectSkill(int idSkill) {
    click(By.xpath("//button[@id-qa='btn-" + idSkill + "']"));
    noErrorMessage();
  }

  public void create(StudentData student) {
    btnAddNewStudent();
    fillStudentForm(student);
    btnNextOnForm();
  }

  private ExpectedCondition<WebElement> expectVisible(String locator) {
    return ExpectedConditions.visibilityOfElementLocated(By.xpath(locator));
  }

  public void fillStudentForm(StudentData studentData) {
    type(editNewStudent_firstName, studentData.getFirstname());
    type(editNewStudent_lastName, studentData.getLastname());
    if (!DateISOToUsualDataString(studentData.getBirthday()).equals("01.01.2000")) {
      type(editNewStudent_birthday, DateISOToUsualDataString(studentData.getBirthday()));
    }
    WebDriverWait wait = new WebDriverWait(wd, 3);
    click(editNewStudent_gender);
    String gender = "//li[@data-value='" + studentData.getGender() + "']";
    wait.until(this.expectVisible(gender));
    click(By.xpath(gender));

    click(editNewStudent_lang);
    String lang = "//li[@data-value='" + studentData.getStudyLang() + "']";
    click(By.xpath(lang));

    // pclevel может быть пустым, т.к. тест параметризован, в тестовых данных встречается вариант с
    // pclaval=""
    if (!studentData.getPclevel().equals("")) {
      click(editNewStudent_pcLevel);
      String pcLevel = "//li[@data-value='" + studentData.getPclevel() + "']";
      wait.until(this.expectVisible(pcLevel));
      clickWithMoveToElementAndWait(3, By.xpath(pcLevel));
    }
  }

  public void fillNewStudentForm() {
    type(editNewStudent_firstName, "Олег");
    type(editNewStudent_lastName, "Олегов");
    type(editNewStudent_birthday, "01012011");
    click(editNewStudent_gender);
    String gender = "//li[@data-value='1']";
    click(By.xpath(gender));
    click(editNewStudent_lang);
    String lang = "//li[@data-value='ru']";
    click(By.xpath(lang));
    click(editNewStudent_pcLevel);
    String pcLevel = "//li[@data-value='expert']";
    clickWithMoveToElementAndWait(3, By.xpath(pcLevel));
  }

  private void btnNextOnForm() {
    click(btnNextOnForm);
    noErrorMessage();
  }

  private void btnAddNewStudent() {
    if (!isElementPresent(btnAddNewStudent)) {
      btnLogo();
    }
    click(btnAddNewStudent);
    noErrorMessage();
  }

  public void skipHelper() {
    trySearchElementTwoTimesAndClickWithWaiteAndMove(5, btnSkipHelper);
  }

  private void btnTomorrowForRegular() {
    if (checkMatchTZServerUTC()) {
      if (isElementPresent(btnTomorrowInPicker)) {
        click(btnTomorrowInPicker);
      } else {
        click(btnTodayInPicker);
      }
      noErrorMessage();
    }
  }

  public void recordOnSingle() {
    btnShowSchedule();
    btnRecordOnLesson();
    btnSingleSchedule();
    // btnTomorrowForSingle();
    changeScrollTime();
    btnNext();
    selectCheckBox();
    btnRecord();
  }

  private void btnTomorrowForSingleSshot() {
    // находим активный элемент и берем следующий сестринский вниз по дереву
    click(btnTomorrowInPicker);
  }

  public void changeStyleDayOfTheWeek() {
    WebElement elementPicker = wd.findElement(btnTodayInPicker);
    ((JavascriptExecutor) wd)
        .executeScript("arguments[0].setAttribute('class', 'picker-item')", elementPicker);
    WebElement elementSelectedIcon = wd.findElement(selectedIcon);
    ((JavascriptExecutor) wd).executeScript("arguments[0].remove();", elementSelectedIcon);
  }

  public void GoToFiltrRecordRegular() {
    btnShowSchedule();
    btnRecordOnLesson();
    btnTomorrowForRegular();
    btnCloseTutorial();
    changeScrollTime();
    changeStyleDayOfTheWeek();
  }

  public void btnSingleSchedule() {
    click(btnSingleSchedule);
    noErrorMessage();
  }

  public void confirmRecordOnRegular() {
    btnCloseTutorial();
    btnShowSchedule();
    btnRecordOnLesson();
    btnTomorrowForRegular();
    btnCloseTutorial();
    changeScrollTime();
    btnNext();
    selectCheckBox();
    changeStyleDayOfTheWeek();
  }

  public void btnRecordOnLesson() {
    clickWaitElementToBeClicable(5, btnRecordOnLesson);
    noErrorMessage();
  }

  private void selectCheckBox() {
    click(selectCheckBoxForSignup);
    noErrorMessage();
  }

  private void btnNext() {
    click(btnNext);
    noErrorMessage();
  }

  private void changeScrollTime() {
    type(editLeftFiltrTime, "00:00");
    type(editRightFiltrTime, "24:00");
    click(emptyAreaFiltrTime); // щелкнуть на пустое место, чтоб обновился скролл
    noErrorMessage();
  }

  public void btnRecord() {
    click(btnRecord);
    noErrorMessage();
  }

  public void btnShowSchedule() {
    btnCloseTutorial();
    clickWithMoveToElementAndWait(10, btnShowScheduleChild);
    noErrorMessage();
  }

  public void btnClickHistory() {
    click(btnClickHistory);
    noErrorMessage();
  }

  public void btnPrepare() {
    click(btnPrepare);
  }

  public void reset() {
    btnLogo();
    refresh();
    click(btnFreeLessons);
    btnLogo();
  }

  public void resetWithoutLogo() {
    refresh();
    btnCloseTutorial();
    click(btnFreeLessons);
    btnLogo();
  }

  public void btnSchedule() {
    click(btnSchedule);
  }

  public void clickByNameFirstStudent() {
    click(clickByFirstStudent);
  }

  public void clickByNameSecondStudent() {
    btnCloseTutorial();
    click(clickBySecondStudent);
  }

  public void clickByShowHistorySecondChild() {
    click(btnShowHistorySecondChild);
  }

  public void clickByShowHistoryFirstChild() {
    click(btnShowHistoryFirstChild);
  }

  public void clickByFreeLesson() {
    click(btnFreeLessons);
  }

  public String goToHelpCenter() throws InterruptedException {
    click(btnMainHelpCenter);
    return goToNewWindowAndGoToBack(btnHelpCenterInMenu);
  }

  public void goToBallansHistory() {
    click(btnHistoryBallans);
    noErrorMessage();
  }

  public void goToTutorialByLkParent() {
    click(btnMainHelpCenter);
    click(btnTutorialByLkParent);
  }

  public void goToTutorialBySwitchToStudent() {
    click(btnMainHelpCenter);
    click(btnTutorialBySwitchToStudent);
  }

  public String getRefCode()
      throws IOException, UnsupportedFlavorException {
    click(btnFreeLessons);
    click(btnShareHref);
    if (!"".equals(properties.getProperty("selenium.server"))) {
      String id = String.valueOf(((RemoteWebDriver) wd).getSessionId());
      Process proc = Runtime.getRuntime()
          .exec("curl http://135.181.63.111:4444/clipboard/" + id);
      //https://www.twilio.com/blog/5-ways-to-make-http-requests-in-java
      ////http://selenoid:4444 ci.propeties->selenium server
      BufferedReader stdInput = new BufferedReader(new
          InputStreamReader(proc.getInputStream()));
      return stdInput.toString();
    } else {
      return (String) Toolkit.getDefaultToolkit()
          .getSystemClipboard()
          .getData(DataFlavor.stringFlavor);
    }
  }

  public String goToFacebook() throws InterruptedException {
    click(btnFreeLessons);
    String url = goToNewWindowAndGoToBack(btnFacebook);
    String[] words = url.split("/");
    return words[2];
  }

  public void clickByRightPaginator() {
    clickByShowHistorySecondChild();
    clickByTabSchedule();
    btnRightPaginator();
  }

  private void btnRightPaginator() {
    if (isElementPresent(btnScroll)) {
      click(btnScroll);
    }
    clickWithMoveToElementAndWait(5, btnPaginatorRight);
  }

  public void clickByLeftPaginator() {
    //сначала 1 раз вправо (следующий месяц) затем 2 влево (предыдущий месяц)
    clickWithMoveToElementAndWait(5, btnPaginatorLeft);
    clickWithMoveToElementAndWait(5, btnPaginatorLeft);
  }

  public String monthUI() {
    String data = wd.findElement(monthUI).getText();
    return data.split(" ")[0]; // достали id
  }

  public void goInWorkingOff() {
    clickByShowHistorySecondChild();
    clickByTabSchedule();
    clickByAssignWorking();
  }

  private void clickByAssignWorking() {
    click(btnAssignWorking);
    noErrorMessage();
  }

  public void clickByChangePassword() {
    refresh();
    clickWithMoveToElementAndWait(2, btnChangePassword);
  }

  public void typeNotSimilarPasswordInSettings() {
    clickByNameFirstStudent();
    btnCloseTutorial();
    clickByChangePassword();
    type(editPasswordFirst, "1234");
    type(editPasswordSecond, "1122");
    thereAreErrorMessages();
    clickByBadSavePassword();
  }

  public void changePasswordInSettings(String newPassword) {
    clickByNameSecondStudent();
    btnCloseTutorial();
    clickByChangePassword();
    type(editPasswordFirst, newPassword);
    type(editPasswordSecond, newPassword);
    clickBySavePassword();
  }

  public void cancelPasswordInSettings() {
    clickByNameSecondStudent();
    btnCloseTutorial();
    clickByChangePassword();
    type(editPasswordFirst, "123456");
    type(editPasswordSecond, "123456");
    clickByCancelPassword();
  }

  private void clickByCancelPassword() {
    click(btnCancelPassword);
    noErrorMessage();
  }

  private void clickBySavePassword() {
    click(btnSavePassword);
    noErrorMessage();
  }

  private void clickByBadSavePassword() {
    click(btnSavePassword);
    thereAreErrorMessages();
  }

  public void saveNewLogin(String loginNew) {
    refresh();
    clickByNameSecondStudent();
    btnCloseTutorial();
    clickByChangeLogin();
    type(editLogin, loginNew);
    clickBySaveLogin();
    noErrorMessage();
  }

  public void saveLoginAndPassword(String loginNew) {
    click(By.xpath("//a[text()='Олег']"));
    btnCloseTutorial();
    clickByChangeLogin();
    type(editLogin, loginNew);
    clickBySaveLogin();
    clickByChangePassword();
    type(editPasswordFirst, "123456");
    type(editPasswordSecond, "123456");
    clickBySavePassword();
  }

  public void cancelNewLogin(String loginNew) {
    clickByNameSecondStudent();
    btnCloseTutorial();
    clickByChangeLogin();
    type(editLogin, loginNew);
    clickByCancelLogin();
  }

  private void clickByCancelLogin() {
    click(btnCancelLogin);
  }

  private void clickBySaveLogin() {
    click(btnSaveLogin);
  }

  private void clickByChangeLogin() {
    click(btnChangeLogin);
  }

  public void clickByHeader() {
    click(btnHeaderForLogout);
  }

  public void clickByOleg() {
    click(By.xpath("//span[text()='Олегов Олег']"));
  }

  public void clickByFirstTutorial() {
    btnCloseTutorial();
    clickWithMoveToElementAndWait(2, closeFirstTitorialInSettings);
  }

  public void clickBySecondTutorial() {
    click(closeSecondTitorialInSettings);
  }

  public void closeWinWorkingOffWithoutLesson() {
    click(btnCloseWinWorkingOffWithoutLesson);
  }

  public void btnShowHistorySecondChild() {
    click(btnShowHistorySecondChild);
  }

  public void selectSkillInHistory(String skill) {
    click(By.xpath("//div[text()='Направление']"));
    click(By.xpath(" //div[text()='" + skill + "']"));
  }

  public void selectParent() {
    click(By.xpath(selectParent));
  }

  public By scheduleLabelSkipped() {
    if (!isElementPresent(scheduleLabelSkipped)) {
      click(btnPaginatorLeft);
    }
    return getScheduleLabelSkipped();
  }

  public By labelWorkingOffOnThirdLessonInSchedule() {
    if (!isElementPresent(scheduleLabelFinished)) {
      return getLabelWorkingOff();
    } else {
      return getLabelWorkingOffOnThirdLessonInSchedule();
    }
  }

  //если конец месяца - воскресенье
  public void foundSchedule() {
    if (!isElementPresent(labelSchedulePeriodLesson)) {
      click(btnPaginatorRight);
    }
  }
}
