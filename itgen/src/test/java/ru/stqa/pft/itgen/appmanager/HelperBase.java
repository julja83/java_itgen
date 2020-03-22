package ru.stqa.pft.itgen.appmanager;

import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;

public class HelperBase {

  public WebDriver wd;

  public HelperBase(WebDriver wd) {
    this.wd=wd;
  }

  protected void click(By locator) {
    wd.findElement(locator).click();
  }

  protected void type(By locator, String text) {
    click(locator);
    if(text != null) {
      wd.findElement(locator).clear();
      wd.findElement(locator).sendKeys(text);
    }
  }

  public boolean isAlertPresent() {
    try {
      wd.switchTo().alert();
      return true;
    } catch (NoAlertPresentException ex) {
      return false;
    }
  }

  public boolean isElementPresent(By locator) {
    try {
      wd.findElement(locator);
      return true;
    } catch (NoSuchElementException ex) {
      return false;
    }
  }

  protected void dropDownList(By locator, String text) {
    if(text != null) {
      new Select(wd.findElement(locator)).selectByVisibleText(text);
    }
  }

  protected void enterADate(By locator, String date) {
    if(date != null) {
      click(locator);
      wd.findElement(locator).clear();
      click(locator);
      Actions builder = new Actions(wd); // Создаем объект класса Actions, с помощью которого будем генерировать действия
      builder.sendKeys(date).perform(); // исполнить нужную последовательность действий (ввести дату в поле)
    }
  }

  private boolean areElementsPresent(By locator) {
    return wd.findElements(locator).size() > 0;
  }

  public int countingWithPaginated() {
    int count = 0;
    String next = wd.findElement(By.xpath("//ul[@class='pagination']//li[2]")).getAttribute("class");
    if (!next.equals("disabled")) {
      while (!next.equals("disabled")) {
        count = count + wd.findElements(By.cssSelector("a.btn-link")).size();
        wd.findElement(By.xpath("//span[contains(text(),'»')]")).click();
        next = wd.findElement(By.xpath("//ul[@class='pagination']//li[2]")).getAttribute("class");
      }
    }
    return count + wd.findElements(By.cssSelector("a.btn-link")).size();
  }
}
