package ru.stqa.pft.itgen.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import ru.stqa.pft.itgen.model.ParentData;
import ru.stqa.pft.itgen.model.StudentData;

public class StudentHelper extends HelperBase {

  public StudentHelper(WebDriver wd) {
    super(wd);
  }

  public void createFamily() {
    click(By.xpath("//a[@href='/createFamily']"));
  }

  public void addStudent() {
    click(By.xpath("//span[@class='glyphicon glyphicon-plus-sign']"));
  }

  public void fillStudentForm(StudentData studentData) {
    type(By.name("profile-firstName"), studentData.getFirsname());
    type(By.name("profile-lastName"), studentData.getLastname());
    click(By.id("profile-gender"));
    dropDownList(By.id("profile-gender"), studentData.getGender());
    click(By.name("profile-birthday")); // клик по полю ввода даты
    enterADate(studentData.getBirthday());
    click(By.name("profile-city"));
    dropDownList(By.id("profile-country"), studentData.getCountry());
    click(By.id("profile-country"));
    type(By.name("profile-city"), studentData.getCity());
    click(By.xpath("(.//*[normalize-space(text()) and normalize-space(.)='Страна'])[1]/following::div[1]"));
    dropDownList(By.id("profile-timezone"), studentData.getTimezone());
    click(By.id("profile-timezone"));
    type(By.name("profile-contact-phone"), studentData.getPhone());
    type(By.name("profile-contact-skype"), studentData.getSkype());
    type(By.name("profile-contact-c2d"), studentData.getC2d());
    click(By.linkText("Показать еще"));
    type(By.name("profile-contact-viber"), studentData.getViber());
    type(By.name("profile-contact-whatsapp"), studentData.getWhatsapp());
    type(By.name("profile-contact-telegram"), studentData.getTelegram());
    type(By.name("profile-contact-fb"), studentData.getFb());
    type(By.name("profile-contact-vk"), studentData.getVk());
    type(By.name("profile-contact-ok"), studentData.getOk());
    type(By.name("profile-contact-instagram"), studentData.getInst());
  }

  public void submitStudentCreation() {
    click(By.xpath("//button[@class='btn btn-primary btn-create-family']"));
    Assert.assertFalse(isElementPresent(By.cssSelector("[id^=alert]"))); // проверка появления сообщения об ошибке
  }

  public void selectedStudent() {
    click(By.cssSelector("a.btn-link"));
  }

  public void selectedFamily() {
    click(By.xpath("//div[@class='links']//a[3]"));
  }

  public void addParentInFamily() {
    click(By.cssSelector("div.gena-panel-btn.btn-add-parent > span.glyphicon.glyphicon-plus-sign"));
  }

  public void fillParentForm(ParentData parentData) {
    type(By.xpath("//li[@class='list-group-item create-family-parent-item']//input[@name='profile-firstName']"), parentData.getFirstName());
    type(By.xpath("//li[@class='list-group-item create-family-parent-item']//input[@name='profile-lastName']"), parentData.getLastName());
    type(By.name("profile-contact-phone"), parentData.getPhone());
    type(By.name("profile-contact-skype"), parentData.getSkype());
    type(By.name("profile-contact-email"), parentData.getEmail());
    type(By.name("profile-contact-c2d"), parentData.getC2d());
    click(By.cssSelector("a.btn-link.btn-show"));
    type(By.name("profile-contact-viber"), parentData.getViber());
    type(By.name("profile-contact-whatsapp"), parentData.getWhatsapp());
    type(By.name("profile-contact-telegram"), parentData.getTelegram());
    type(By.name("profile-contact-fb"), parentData.getFb());
    type(By.name("profile-contact-vk"), parentData.getVk());
    type(By.name("profile-contact-ok"), parentData.getOk());
    type(By.name("profile-contact-instagram"), parentData.getInst());
  }

  public void submitParentCreation() {
    click(By.cssSelector("button.btn.btn-primary.btn-create-family-member"));
    Assert.assertFalse(isElementPresent(By.cssSelector("[id^=alert]"))); // проверка появления сообщения об ошибке
  }

  public void addParent() {
    clickInActions(By.xpath("//button[@class='close btn-add-parent']"));
     }
}
