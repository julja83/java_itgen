package io.itgen.appmanager;

import io.itgen.model.TrainerData;
import java.util.ArrayList;
import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class TrainerHelper extends HelperBase {
  public TrainerHelper(WebDriver wd) {
    super(wd);
  }

  public void btnDeleteTrainer() {
    WebElement dynamicElementTrainer =
        (new WebDriverWait(wd, 5))
            .until(
                ExpectedConditions.elementToBeClickable(
                    By.xpath("//button[contains(@class,'remove-user')]")));
    Actions actions = new Actions(wd);
    actions.moveToElement(dynamicElementTrainer).build().perform();
    dynamicElementTrainer.click();
  }

  public void alertDeleteSelectedTrainer() {
    click(By.cssSelector("div.modal-header"));
    click(By.cssSelector("div.modal-footer > button.btn.btn-danger"));
    noErrorMessage(); // проверка отсутствия сообщения об ошибке
  }

  public void bntModifyTrainer() {
    WebElement dynamicElement =
        (new WebDriverWait(wd, 10))
            .until(
                ExpectedConditions.elementToBeClickable(
                    By.xpath("//span[contains(@class,'pencil')]")));
    Actions actions = new Actions(wd);
    actions.moveToElement(dynamicElement).build().perform();
    dynamicElement.click();
  }

  public void btnSaveModify() {
    click(By.cssSelector("button.btn.btn-primary.btn-save-profile"));
    noErrorMessage(); // проверка отсутствия сообщения об ошибке
  }

  public void modifiTrainerForm(TrainerData trainerData) {
    type(By.name("profile-firstName"), trainerData.getFirstName());
    type(By.name("profile-lastName"), trainerData.getLastName());
    enterADate(By.name("profile-startWorkAt"), trainerData.getStartWorkUi());
    enterADate(By.name("profile-birthday"), trainerData.getBirthdayUi());
    dropDownList_Integer(By.id("profile-gender"), trainerData.getGender());

    // выпадающий список с чек-боксами
    click(By.cssSelector("button.btn.btn-default.dropdown-toggle"));
    click(By.name("skill_1"));
    click(By.name("skill_2"));
    click(By.name("skill_3"));
    click(By.name("skill_17"));
    click(By.name("skill_21"));
    click(By.name("skill_19"));
    click(By.name("skill_4"));
    click(By.name("skill_5"));
    click(By.name("skill_6"));
    click(By.name("skill_7"));

    // закрывает выпадающий список с чек-боксами
    Actions builder = new Actions(wd);
    wd.findElement(By.cssSelector("button.btn.btn-default.dropdown-toggle"));
    builder.click().perform();

    type(By.xpath("//input[@name='profile-maxSlots']"), String.valueOf(trainerData.getMaxSlots()));
    dropDownList(By.xpath("//select[contains(@id,'workloadLevel')]"), trainerData.getWorkloadLevel());
    dropDownList(By.id("profile-country"), trainerData.getCountry());
    type(By.name("profile-city"), trainerData.getCity());
    dropDownList(By.id("profile-timezone"), trainerData.getTimeZone());
    dropDownList(By.id("profile-locale"), trainerData.getLocate());

    // выбор языка обучения -- чек-боксы
    click(By.xpath("(//button[contains(@class,dropdown)])[2]"));
    click(By.xpath("//input[@data-id='ru']"));
    click(By.xpath("//input[@data-id='en']"));
    click(By.xpath("//div[@class='users-edit']"));

    click(By.id("profile-pay-base"));
    click(By.xpath("//select[@id='profile-pay-base']//option["+trainerData.getPayBase()+"]"));

    type(By.name("profile-contact-phone"), trainerData.getPhone());
    type(By.name("profile-contact-telegram"), trainerData.getTelegram());
    type(By.name("profile-contact-viber"), trainerData.getViber());
    click(By.cssSelector("a.btn-link.btn-show"));
    type(By.name("profile-contact-skype"), trainerData.getSkype());
    type(By.name("profile-contact-whatsapp"), trainerData.getWhatsapp());
    type(By.name("profile-contact-facebook"), trainerData.getFb());
    type(By.name("profile-contact-vk"), trainerData.getVk());
    type(By.name("profile-contact-ok"), trainerData.getOk());
    type(By.name("profile-contact-instagram"), trainerData.getInst());
    type(By.name("profile-note"), trainerData.getNote());
    type(By.name("profile-info"), trainerData.getInfo());
  }

  public void modifiLKTrainerForm(TrainerData trainerData) {
    type(By.name("profile-firstName"), trainerData.getFirstName());
    type(By.name("profile-lastName"), trainerData.getLastName());
    enterADate(By.name("profile-birthday"), trainerData.getBirthdayUi());
    dropDownList_Integer(By.id("profile-gender"), trainerData.getGender());
    dropDownList(By.xpath("//select[contains(@id,'workloadLevel')]"), trainerData.getWorkloadLevel());
    dropDownList(By.id("profile-country"), trainerData.getCountry());
    type(By.name("profile-city"), trainerData.getCity());
    dropDownList(By.id("profile-timezone"), trainerData.getTimeZone());
    dropDownList(By.id("profile-locale"), trainerData.getLocate());

    // выбор языка обучения -- чек-боксы
    click(By.xpath("(//button[contains(@class,dropdown)])[2]"));
    click(By.xpath("//input[@data-id='ru']"));
    click(By.xpath("//input[@data-id='en']"));
    click(By.xpath("//div[@class='users-edit']"));

    type(By.name("profile-contact-phone"), trainerData.getPhone());
    type(By.name("profile-contact-telegram"), trainerData.getTelegram());
    type(By.name("profile-contact-viber"), trainerData.getViber());
    click(By.cssSelector("a.btn-link.btn-show"));
    type(By.name("profile-contact-skype"), trainerData.getSkype());
    type(By.name("profile-contact-whatsapp"), trainerData.getWhatsapp());
    type(By.name("profile-contact-facebook"), trainerData.getFb());
    type(By.name("profile-contact-vk"), trainerData.getVk());
    type(By.name("profile-contact-ok"), trainerData.getOk());
    type(By.name("profile-contact-instagram"), trainerData.getInst());
  }

  public String create(TrainerData trainer) {
    bntAddTrainer();
    fillTrainerForm(trainer);
    bntCreation();
    String url = getURL();
    return url;
  }

  public void bntAddTrainer() {
    click(By.cssSelector("a.btn.btn-default"));
  }

  public void fillTrainerForm(TrainerData trainerData) {
    type(By.name("user-firstName"), trainerData.getFirstName());
    type(By.name("user-lastName"), trainerData.getLastName());
    type(By.name("user-email"), "eee+" + Math.round(Math.random() * 10000) + "@gmail.com");
    type(By.name("user-phone"), trainerData.getPhone());
    dropDownList(By.name("role"), trainerData.getRoleUi());
  }

  public void bntCreation() {
    click(By.xpath("//button[@class='btn btn-primary btn-create']"));
    noErrorMessage(); // проверка отсутствия сообщения об ошибке
  }

  // работник с пагинацией
  public List<TrainerData> list() {
    List<TrainerData> trainers = new ArrayList<TrainerData>();
    WebDriverWait wait = new WebDriverWait(wd, 2);
    wait.until(
        ExpectedConditions.presenceOfElementLocated(
            By.xpath("//ul[@class='pagination']//li[2]"))); // ждать пока не появится элемент
    String next =
        wd.findElement(By.xpath("//ul[@class='pagination']//li[2]")).getAttribute("class");
    List<WebElement> elements = wd.findElements(By.cssSelector("a.btn-link"));
    if (!next.equals("disabled")) {
      while (!next.equals("disabled")) {
        includeInListBaseWebElement(trainers, elements);
        wd.findElement(By.xpath("//span[contains(text(),'»')]")).click();
        elements.removeAll(elements);
        elements = wd.findElements(By.cssSelector("a.btn-link"));
        next = wd.findElement(By.xpath("//ul[@class='pagination']//li[2]")).getAttribute("class");
      }
    }
    includeInListBaseWebElement(trainers, elements);
    return trainers;
  }

  // из вэб-элементов на странице формируем список элементов типа StudentData, путем взятия id из
  // ссылки в атрибуте, а ФИ cо страницы ui
  private void includeInListBaseWebElement(List<TrainerData> trainers, List<WebElement> elements) {
    TrainerData trainer = null;
    for (WebElement element : elements) {
      String getId = element.getAttribute("href");
      String[] getIdSplit = getId.split("/");
      String id = getIdSplit[4]; // достали id
      String name = element.getText();
      String[] name_surname = name.split("\\s"); // разрезали Имя Фамилия
      if (name_surname.length == 2) {
        trainer =
            new TrainerData()
                .withId(id)
                .withFirstName(name_surname[1])
                .withLastName(name_surname[0]);
      } else {
        trainer = new TrainerData().withId(id).withFirstName(name_surname[0]).withLastName(null);
      }
      trainers.add(trainer);
    }
  }

  public void delete(TrainerData deletedTrainer) {
    selectTrainerById(deletedTrainer);
    btnDeleteTrainer();
    alertDeleteSelectedTrainer();
    noErrorMessage();
  }

  public void modify(TrainerData trainer) {
    bntModifyTrainer();
    modifiTrainerForm(trainer);
    btnSaveModify();
    noErrorMessage();
    refresh();
  }

  public void selectTrainerById(TrainerData deletedTrainer) {
    // находим пагинатор
    WebElement dynamicElement =
        (new WebDriverWait(wd, 10))
            .until(
                ExpectedConditions.elementToBeClickable(
                    By.xpath("//ul[@class='pagination']//li[2]")));
    String next = dynamicElement.getAttribute("class");
    // есть ли на первой странице наш работник
    List<WebElement> list =
        wd.findElements(By.cssSelector("a[href='/profile/" + deletedTrainer.getId() + "'"));
    if (list.size() > 0) {
      wd.findElement(By.cssSelector("a[href='/profile/" + deletedTrainer.getId() + "'")).click();
    } else {
      // если работник не на первой странице, надо нажать пагинатор, пока не найдем
      while (!next.equals("disabled")) {
        List<WebElement> list_pagin =
            wd.findElements(By.cssSelector("a[href='/profile/" + deletedTrainer.getId() + "'"));
        if (list_pagin.size() > 0) {
          wd.findElement(By.cssSelector("a[href='/profile/" + deletedTrainer.getId() + "'"))
              .click();
          break;
        } else {
          wd.findElement(By.xpath("//span[contains(text(),'»')]")).click();
        }
      }
    }
  }

  public void modifyInLk(TrainerData trainer) {
    bntOpenProfile();
    btnModify();
    modifiLKTrainerForm(trainer);
    btnSaveModify();
    noErrorMessage();

  }

  private void btnModify() {
    click(By.xpath("//a[contains(@class,'edit')]"));
    noErrorMessage();
  }

  private void bntOpenProfile() {
    click(By.xpath("//div[@class='head']"));
    click(By.xpath("//ul[@role='menu']//button"));
    noErrorMessage();
  }

  public void startLesson(String id) {
    selectLesson(id);
    btnStartLesson();
  }

  private void btnStartLesson() {
    click(By.xpath("//button[contains(@class,'start-lesson')]"));
    noErrorMessage();
  }

  private void selectLesson(String id) {
    click(By.xpath("//a[contains(@href,'"+id+"')]"));
    noErrorMessage();
  }

  public void finishedLessonWithWas(String idLesson) {
    selectLesson(idLesson);
    selectStudent();
    btnNotWas();
    btnFinishLesson();
    gotoTask();
  }

  private void gotoTask() {
    click(By.xpath("//a[@href='/tasks']"));
    noErrorMessage();
  }

  private void btnFinishLesson() {
    click(By.xpath("//button[contains(@class,'finish-lesson')]"));
    noErrorMessage();
  }

  private void btnNotWas() {
    click(By.xpath("//button[@data-action='skipped']"));
    noErrorMessage();
  }

  private void selectStudent() {
    click(By.xpath("//div[@class='child-list'][1]"));
    noErrorMessage();
  }

  public void menuSchedule() {
    click(By.xpath("//a[@href='/schedule']"));
    noErrorMessage();
  }
}
