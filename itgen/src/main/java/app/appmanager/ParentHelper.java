package app.appmanager;

import data.model.users.ParentData;
import data.model.users.Parents;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class ParentHelper extends HelperBase {

  public ParentHelper(WebDriver wd) {
    super(wd);
  }

  public void selectStudent() {
    click(By.cssSelector("a.btn-link"));
    noErrorMessage();
  }

  public void selectParentInFamily() {
    click(By.xpath("(//div[@class='gena-panel-body'])[2]//a"));
    noErrorMessage();
  }

  public void btnSelectFamily() {
    click(By.xpath("//a[contains(@href, 'family')]"));
    noErrorMessage();
  }

  public void addParentInFamily() {
    click(By.xpath("//div[contains(@class, 'add-parent')]/span"));
    noErrorMessage();
  }

  public void btnDeleteParent() {
    click(By.xpath("//button[contains(@class,'remove-user')]"));
    noErrorMessage();
  }

  public void btnFamily() {
    click(By.xpath("//a[contains(@href, 'family')]"));
    noErrorMessage();
  }

  public void alertDeleteSelectedParent() {
    click(By.cssSelector("div.modal-header"));
    click(By.cssSelector("div.modal-footer > button.btn.btn-danger"));
    noErrorMessage(); // проверка отсутствия сообщения об ошибке
  }

  public void btnSaveModify() {
    click(By.xpath("//button[contains(@class,'save')]"));
    noErrorMessage(); // проверка отсутствия сообщения об ошибке
  }

  public void submitParentCreation() {
    click(By.xpath("//button[contains(@class, 'family')]"));
    noErrorMessage(); // проверка отсутствия сообщения об ошибке
  }

  public void btnModificationParent() {
    click(By.xpath("//span[contains(@class,'pencil')]"));
    noErrorMessage();
  }

  public void create(ParentData parent) {
    btnSelectFamily();
    addParentInFamily();
    fillParentForm(parent);
    submitParentCreation();
    selectParentInFamily();
    noErrorMessage();
  }

  public void createForStudent(ParentData parent) {
    btnSelectFamily();
    addParentInFamily();
    fillParentForm(parent);
    submitParentCreation();
  }

  public void delete() {
    btnFamily();
    selectParentInFamily();
    btnDeleteParent();
    alertDeleteSelectedParent();
    noErrorMessage();
  }

  public void modify(ParentData parent) {
    btnFamily();
    selectParentInFamily();
    btnModificationParent();
    ModifyParentForm(parent);
    btnSaveModify();
    noErrorMessage();
  }

  public void ModifyParentForm(ParentData parentData) {
    type(By.cssSelector("input[name=\"profile-firstName\"]"), parentData.getFirstName());
    type(By.cssSelector("input[name=\"profile-lastName\"]"), parentData.getLastName());
    dropDownList(By.cssSelector("#profile-country"), parentData.getCountry());
    type(By.cssSelector("input[name=\"profile-city\"]"), parentData.getCity());
    dropDownList(By.cssSelector("#profile-timezone"), parentData.getTimeZone());
    dropDownList(By.cssSelector("#profile-locale"), parentData.getLocate());
    type(By.cssSelector("input[name=\"profile-contact-phone\"]"), parentData.getPhone());
    type(By.cssSelector("input[name=\"profile-contact-telegram\"]"), parentData.getTelegram());
    type(By.cssSelector("input[name=\"profile-contact-viber\"]"), parentData.getViber());
    type(By.cssSelector("input[name=\"profile-contact-c2d\"]"), parentData.getC2d());
    click(By.cssSelector("a.btn-link.btn-show"));
    type(By.cssSelector("input[name=\"profile-contact-skype\"]"), parentData.getSkype());
    type(By.cssSelector("input[name=\"profile-contact-whatsapp\"]"), parentData.getWhatsapp());
    type(By.cssSelector("input[name=\"profile-contact-facebook\"]"), parentData.getFb());
    type(By.cssSelector("input[name=\"profile-contact-vk\"]"), parentData.getVk());
    type(By.cssSelector("input[name=\"profile-contact-ok\"]"), parentData.getOk());
    type(By.cssSelector("input[name=\"profile-contact-instagram\"]"), parentData.getInst());
    type(By.cssSelector("input[name=\"profile-family-id\"]"), parentData.getFamilyId());
    type(By.cssSelector("textarea[name=\"profile-note\"]"), parentData.getNote());
    noErrorMessage();
  }

  public void fillParentForm(ParentData parentData) {
    type(By.cssSelector("input[name=\"profile-firstName\"]"), parentData.getFirstName());
    type(By.cssSelector("input[name=\"profile-lastName\"]"), parentData.getLastName());
    type(By.cssSelector("input[name=\"profile-contact-phone\"]"), parentData.getPhone());
    type(
        By.cssSelector("input[name=\"profile-contact-email\"]"),
        "eee+" + Math.round(Math.random() * 10000) + "@gmail.com");
    type(By.cssSelector("input[name=\"profile-contact-telegram\"]"), parentData.getTelegram());
    type(By.cssSelector("input[name=\"profile-contact-viber\"]"), parentData.getViber());
    type(By.cssSelector("input[name=\"profile-contact-c2d\"]"), parentData.getC2d());
    click(By.cssSelector("a.btn-link.btn-show"));
    type(By.cssSelector("input[name=\"profile-contact-skype\"]"), parentData.getSkype());
    type(By.cssSelector("input[name=\"profile-contact-whatsapp\"]"), parentData.getWhatsapp());
    type(By.cssSelector("input[name=\"profile-contact-facebook\"]"), parentData.getFb());
    type(By.cssSelector("input[name=\"profile-contact-vk\"]"), parentData.getVk());
    type(By.cssSelector("input[name=\"profile-contact-ok\"]"), parentData.getOk());
    type(By.cssSelector("input[name=\"profile-contact-instagram\"]"), parentData.getInst());
    noErrorMessage();
  }

  public String getIdNewParentDB(Parents before, Parents after) {
    return getNewParentDB(before, after).getId();
  }

  public ParentData getNewParentDB(Parents before, Parents after) {
    ParentData parentNew = null;
    for (ParentData parentListAfter : after) {
      if (!before.contains(parentListAfter)) {
        parentNew = parentListAfter;
        break;
      }
    }
    return parentNew;
  }
}
