package app.appmanager;

import static app.appmanager.ApplicationManager.properties;

import data.model.users.StudentData;
import data.model.users.Students;
import java.util.ArrayList;
import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class StudentHelper extends HelperBase {

  public StudentHelper(WebDriver wd) {
    super(wd);
  }

  public void btnAddStudent() {
    click(By.xpath("//span[@class='glyphicon glyphicon-plus-sign']"));
  }

  public void btnFamily() {
    click(By.xpath("//a[contains(@href, 'family')]"));
  }

  public void select() {
    click(By.cssSelector("a.btn-link"));
  }

  public void btnDeleteStudent() {
    click(By.xpath("//button[contains(@class, 'remove')]"));
  }

  public void assertDeleteSelectedStudent() {
    click(By.cssSelector("div.modal-header"));
    click(By.cssSelector("div.modal-footer > button.btn.btn-danger"));
    noErrorMessage(); // проверка отсутствия сообщения об ошибке
  }

  public void selectModifyStudent() {
    clickWithMoveToElementAndWait(5, By.xpath("//span[contains(@class,'pencil')]"));
  }

  public void btnSaveModify() {
    click(By.xpath("//button[contains(@class,'save')]"));
    noErrorMessage(); // проверка отсутствия сообщения об ошибке
  }

  public void openFiltr() {
    click(By.xpath("//button[@class='close']"));
  }

  public void changePol(int pol) {
    dropDownList_Integer(By.xpath("//select[@id='filter-gender']"), pol);
  }

  public void btnApply() {
    click(By.xpath("//button[contains(@class,'accept')]"));
  }

  public void fillStudentForm(StudentData studentData) {
    type(By.cssSelector("input[name='profile-firstName']"), studentData.getFirstname());
    type(By.cssSelector("input[name='profile-lastName']"), studentData.getLastname());
    dropDownList_Integer(By.cssSelector("#profile-gender"), studentData.getGender());
    //01.01.2000 - флаг, при этой дате не заполняем ui поле
    if (!DateISOToUsualDataString(studentData.getBirthday()).equals("01.01.2000")) {
      enterADate(By.cssSelector("input[name='profile-birthday']"),
          DateISOToUsualDataString(studentData.getBirthday()));
    }

    dropDownList(By.xpath("//select[@id='profile-pc-level']"), studentData.getPclevel());
    dropDownList(By.cssSelector("#profile-country"), studentData.getCountry());
    type(By.cssSelector("input[name='profile-city']"), studentData.getCity());
    dropDownList(By.cssSelector("#profile-timezone"), studentData.getTimezone());
    type(By.cssSelector("input[name='profile-contact-phone']"),
        studentData.getContacts().get(0).getVal());
    type(By.cssSelector("input[name='profile-contact-telegram']"),
        studentData.getContacts().get(2).getVal());
    type(By.cssSelector("input[name='profile-contact-viber']"),
        studentData.getContacts().get(3).getVal());
    type(By.cssSelector("input[name='profile-contact-c2d']"),
        studentData.getContacts().get(4).getVal());
    click(By.cssSelector("a.btn-link.btn-show"));
    type(By.cssSelector("input[name='profile-contact-skype']"),
        studentData.getContacts().get(5).getVal());
    type(By.cssSelector("input[name='profile-contact-whatsapp']"),
        studentData.getContacts().get(6).getVal());
    type(By.cssSelector("input[name='profile-contact-facebook']"),
        studentData.getContacts().get(7).getVal());
    type(By.cssSelector("input[name='profile-contact-vk']"),
        studentData.getContacts().get(8).getVal());
    type(By.cssSelector("input[name='profile-contact-ok']"),
        studentData.getContacts().get(9).getVal());
    type(By.cssSelector("input[name='profile-contact-instagram']"),
        studentData.getContacts().get(10).getVal());
  }


  public void ModifyStudentForm(StudentData studentData) {
    type(By.cssSelector("input[name='profile-firstName']"), studentData.getFirstname());
    type(By.cssSelector("input[name='profile-lastName']"), studentData.getLastname());
    enterADate(By.cssSelector("input[name='profile-birthday']"),
        DateISOToUsualDataString(studentData.getBirthday()));
    dropDownList(By.xpath("//select[@id='profile-pc-level']"), studentData.getPclevel());
    dropDownList_Integer(By.cssSelector("#profile-gender"), studentData.getGender());
    dropDownList(By.cssSelector("#profile-country"), studentData.getCountry());
    type(By.cssSelector("input[name='profile-city']"), studentData.getCity());
    dropDownList(By.cssSelector("#profile-timezone"), studentData.getTimezone());
    dropDownList(By.cssSelector("#profile-locale"), studentData.getLocate());
    dropDownList(By.cssSelector("#profile-study-lang"), studentData.getStudyLang());
    dropDownList_Integer(By.cssSelector("#profile-duration"), studentData.getDuration());
    type(By.cssSelector("input[name='profile-contact-phone']"),
        studentData.getContacts().get(0).getVal());
    type(By.cssSelector("input[name='profile-contact-telegram']"),
        studentData.getContacts().get(2).getVal());
    type(By.cssSelector("input[name='profile-contact-viber']"),
        studentData.getContacts().get(3).getVal());
    type(By.cssSelector("input[name='profile-contact-c2d']"),
        studentData.getContacts().get(4).getVal());
    click(By.cssSelector("a.btn-link.btn-show"));
    type(By.cssSelector("input[name='profile-contact-skype']"),
        studentData.getContacts().get(5).getVal());
    type(By.cssSelector("input[name='profile-contact-whatsapp']"),
        studentData.getContacts().get(6).getVal());
    type(By.cssSelector("input[name='profile-contact-facebook']"),
        studentData.getContacts().get(7).getVal());
    type(By.cssSelector("input[name='profile-contact-vk']"),
        studentData.getContacts().get(8).getVal());
    type(By.cssSelector("input[name='profile-contact-ok']"),
        studentData.getContacts().get(9).getVal());
    type(By.cssSelector("input[name='profile-contact-instagram']"),
        studentData.getContacts().get(10).getVal());
    type(By.cssSelector("input[name='profile-family-id']"), studentData.getFamilyId());
    type(By.cssSelector("textarea[name='profile-note']"), studentData.getNote());
  }

  public int getStudentCount() {
    return countingWithPaginated();
  }

  // студенты с пагинацией
  public List<StudentData> list() {
    List<StudentData> students = new ArrayList<>();
    WebDriverWait wait = new WebDriverWait(wd, 2);
    wait.until(
        ExpectedConditions.presenceOfElementLocated(
            By.xpath("//ul[@class='pagination']//li[2]"))); // ждать пока не появится элемент
    String next =
        wd.findElement(By.xpath("//ul[@class='pagination']//li[2]")).getAttribute("class");
    List<WebElement> elements = wd.findElements(By.cssSelector("a.btn-link"));
    if (!next.equals("disabled")) {
      while (!next.equals("disabled")) {
        includeInListBaseWebElement(students, elements);
        wd.findElement(By.xpath("//span[contains(text(),'»')]")).click();
        elements.removeAll(elements);
        elements = wd.findElements(By.cssSelector("a.btn-link"));
        next = wd.findElement(By.xpath("//ul[@class='pagination']//li[2]")).getAttribute("class");
      }
    }
    includeInListBaseWebElement(students, elements);
    return students;
  }

  // из вэб-элементов на странице формируем список элементов типа StudentData, путем взятия id из
  // ссылки в атрибуте, а ФИ cо страницы ui
  private void includeInListBaseWebElement(List<StudentData> students, List<WebElement> elements) {
    for (WebElement element : elements) {
      String getId = element.getAttribute("href");
      String[] getIdSplit = getId.split("/");
      String id = getIdSplit[4]; // достали id
      String name = element.getText();
      String[] name_surname = name.split("\\s"); // разрезали Имя Фамилия
      StudentData student =
          new StudentData().withId(id).withFirstName(name_surname[1]).withLastName(name_surname[0]);
      students.add(student);
    }
  }

  public void btnCreation() {
    click(By.cssSelector("button.btn.btn-primary.btn-create-family"));
    noErrorMessage(); // проверка отсутствия сообщения об ошибке
  }

  public void btnCreationBad() {
    click(By.cssSelector("button.btn.btn-primary.btn-create-family"));
    thereAreErrorMessages(); // проверка появления сообщения об ошибке
  }

  public void btnCreateFamily() {
    click(By.xpath("//a[@href='/createFamily']"));
  }

  public void create(StudentData student) {
    btnCreateFamily();
    btnAddStudent();
    fillStudentForm(student);
    btnCreation();
  }

  public void createBad(StudentData student) {
    btnCreateFamily();
    btnAddStudent();
    fillStudentForm(student);
    btnCreationBad();
  }

  public void selectedStudentAfterCreate() {
    click(By.xpath("(//div[@class='gena-panel-body'])[1]//a"));
  }

  public void modify(StudentData student) {
    selectModifyStudent();
    ModifyStudentForm(student);
    btnSaveModify();
  }

  public StudentData getNewStudentDB(Students before, Students after) {
    StudentData studentNew = null;
    for (StudentData studentListAfter : after) {
      if (!before.contains(studentListAfter)) {
        studentNew = studentListAfter;
        break;
      }
    }
    return studentNew;
  }

  public void delete() {
    btnDeleteStudent();
    assertDeleteSelectedStudent();
  }

  public void selectStudentInListUIById(String id) {
    // находим пагинатор
    String next =
        wd.findElement(By.xpath("//ul[@class='pagination']//li[2]")).getAttribute("class");
    // есть ли на первой странице наш студент
    List<WebElement> list = wd.findElements(By.cssSelector("a[href='/profile/" + id + "'"));
    if (list.size() > 0) {
      wd.findElement(By.cssSelector("a[href='/profile/" + id + "'")).click();
    } else {
      // если студент не на первой странице, надо нажать пагинатор, пока не найдем
      while (!next.equals("disabled")) {
        List<WebElement> list_pagin =
            wd.findElements(By.cssSelector("a[href='/profile/" + id + "'"));
        if (list_pagin.size() > 0) {
          wd.findElement(By.cssSelector("a[href='/profile/" + id + "'")).click();
          break;
        } else {
          wd.findElement(By.xpath("//span[contains(text(),'»')]")).click();
        }
      }
      waitVisibilityOfElementLocated(5, By.cssSelector("input[name=profile-firstName])"));
    }
  }

  public void logout() {
    click(By.xpath("//div[@class='arrow']"));
    click(By.xpath("//ul[contains(@class,'list')]/li[4]"));
  }

  public void goOnLesson() {
    maxBrowser();
    if (isElementPresent(By.xpath("//button[@id-qa='cancel']"))) {
      btnCloseTutorial();
    }
    btnGoToLesson();
    btnImReady();
  }

  private void btnImReady() {
    if (isElementPresent(By.xpath("//button[@id-qa='cancel']"))) {
      btnCloseTutorial();
    }
    click(By.xpath("//div[contains(@class,'preview')]//button"));
  }

  private void btnGoToLesson() {
    clickWithMoveToElementAndWait(5, By.xpath("//button[@tutorialtarget-id='child-go-to-lesson']"));
  }

  public void btnLogo() {
    click(By.xpath("//img[contains(@src,'logo')]"));
    noErrorMessage();
  }

  public String getMessageTrainer() {
    String message = wd.findElement(By.xpath("//div[contains(@class,'preview')]//span")).getText();
    btnLogo();
    return message;
  }

  public void goToStudentProfileTabTests() {
    btnCloseTutorial();
    click(By.xpath("//button[@id-qa='testing']"));
  }

  public void skipHelper() {
    maxBrowser();
    deleteAlerts();
    goInAccountStudentAfterChanged();
    btnCloseTutorial();
  }

  public void btnCloseTutorial() {
    try {
      clickWithMoveToElementAndWait(5, By.xpath("//button[@id-qa='cancel']"));
    } catch (TimeoutException e) {
      System.out.println("Исключение:" + e);
    }
  }

  public void goToHistory() {
    btnHistory();
    openDropDown();
    deleteAlerts();
  }

  private void openDropDown() {
    clickWithMoveToElementAndWait(5, By.xpath("//div[contains(@class,'dropdown')]"));
  }

  public void btnHistory() {
    clickWaitElementToBeClicable(5, By.xpath("//button[@id-qa='view-all']"));
  }

  public void closeHistory() {
    clickWithMoveToElementAndWait(7, By.xpath("(//button)[9]"));
  }

  public void goToCheckConnection() {
    clickWithMoveToElementAndWait(5, By.xpath("//button[@id-qa='settings']"));
  }

  public void openChat() {
    if (isElementPresent(By.xpath("//button[@id-qa='cancel']"))) {
      btnCloseTutorial();
    }
    clickWithMoveToElementAndWait(5, By.xpath("//div[@class='chat-button']"));
  }

  public void goToLesson() {
    btnGoToLesson();
    btnImReady();
  }

  public Boolean findTutorials() {
    return isElementPresent(By.xpath("//div[contains(@class,'tutorials')]"));
  }

  public void goInAccountStudentAfterChanged() {
    refresh();
    wd.get(address() + "/login");
    login(properties.getProperty("web.Login"), properties.getProperty("web.Password"));
  }

  public boolean openCheckConnection() {
    return isElementPresent(By.xpath("//h2"));
  }

  public void goToFeed() {
    click(By.xpath("//button[@id-qa='feed']"));
  }

  public boolean goOnSchedule() {
    maxBrowser();
    btnSchedule();
    assert (wd.findElement(By.xpath("//h2")).getText().equals("Мое расписание"));
    return isElementPresent(By.xpath("//h2"));
  }

  private void btnSchedule() {
    click(By.xpath("//button[@id-qa='schedule']"));
  }

  public void btnCommunities() {
    click(By.xpath("//button[@id-qa='communities']"));
  }

  public void subscribeOnCommunity() {
    btnCommunities();
    btnSubscribe();
  }

  private void btnSubscribe() {
    click(By.xpath("//button[@id-qa='subscribe']"));
  }

  public void unsubscribeOnCommunity() {
    btnCommunities();
    btnUnsubscribe();
  }

  private void btnUnsubscribe() {
    click(By.xpath("//button[@id-qa='unsubscribe']"));
  }

  public void addLike() {
    click(By.xpath("//span[@id-qa='like']"));
  }

  public void addCommentInFeed(String comment) {
    refresh();
    goToFeed();
    fillNewComment(comment);
    btnCreateComment();
  }

  private void btnCreateComment() {
    click(By.xpath("//button[@id-qa='create']"));
  }

  private void fillNewComment(String comment) {
    clickWaitElementToBeClicable(5, By.xpath("//div[@id-qa='wrap']"));
    wd.findElement(By.xpath("//div[@class='body']//textarea[1]")).sendKeys(comment);
  }

  public void tabAll() {
    click(By.xpath("//div[@role='tablist']//button[@id-qa='all']"));
  }

  public void btnPoint() {
    click(By.xpath("//div[@class='right']//button"));
  }

  public void goInProfile() {
    click(By.xpath("//div[@class='head']"));
    clickWithMoveToElementAndWait(5, By.xpath("//li[2]//button"));
  }

  public void tabAdministration() {
    click(By.xpath("//div[@role='tablist']//button[@id-qa='administration']"));
  }

  public void tabFeed() {
    click(By.xpath("//div[@role='tablist']//button[@id-qa='feed']"));
  }
}
