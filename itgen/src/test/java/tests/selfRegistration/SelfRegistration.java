package tests.selfRegistration;
/*кейс:
1. Нужно добавить родителя лида под суперадмином
2. Взять его id
3. Вставить в ссылку на саморегистрацию
4. Заполнить форму
5. должно отправится письмо на активацию - но т.к. локально получить письмо нельзя, то мокаем это и переходим
по созданной ссылке на активацию
6. Придумать пароль
7. Попасть в ЛК родителя
8. Затем вернуться в лк суперадмина
8. Провести проверки
 */

import static app.appmanager.ApplicationManager.properties;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

import app.testbase.TestBase;
import core.general.RunTestAgain;
import data.model.family.Families;
import data.model.family.FamilyData;
import data.model.lead.LeadData;
import data.model.lead.Leads;
import data.model.users.ParentData;
import data.model.users.Parents;
import data.model.users.StudentData;
import data.model.users.Students;
import data.provides.LocaleUtilsTestData;
import data.services.FamilyService;
import data.services.LeadService;
import data.services.ParentService;
import data.services.StudentService;
import data.services.TaskService;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class SelfRegistration extends TestBase {

  LeadService leadService = new LeadService();
  TaskService taskService = new TaskService();
  ParentService parentService = new ParentService();
  FamilyService familyService = new FamilyService();
  StudentService studentService = new StudentService();

  StudentData studentClean = null;
  ParentData parentClean = null;
  FamilyData familyClean = null;

  @BeforeMethod
  public void ensurePreconditions() {

    app.trLead()
        .leadParent(
            "selfRegistration",
            "Лид",
            "Лидов",
            "parent",
            "BY",
            "Europe/Minsk",
            "ru",
            "+7859561122",
            "mail@list.ru",
            "new",
            "site",
            "manual");
  }

  @Test(dataProvider = "validSelfRegistrationFromJson", dataProviderClass = LocaleUtilsTestData.class,
      retryAnalyzer = RunTestAgain.class)
  public void testSelfRegistration(StudentData student) throws InterruptedException {
    app.student().logout();

    Leads leadsBefore = app.db().leads();
    Students studentsBefore = app.dbstudents().students();
    Parents parentsBefore = app.db().parents();
    Families familyBefore = app.db().families();

    app.lkParentRecord().goHref();
    app.lkParentRecord().selfRegistration(student);

    Thread.sleep(3000); // необходимо, т.к. не успевает сформироваться токен в бд

    ParentData parent = app.db().getTokenParent("Лид", "Лидов", "parent");
    String token = parent.getServices().getPassword().getReset().getToken();
    app.lkParentRecord().activation(token);
    app.session()
        .login(properties.getProperty("web.Login"), properties.getProperty("web.Password"));

    Leads leadsAfter = app.db().leads();
    Students studentsAfter = app.dbstudents().students();
    Parents parentsAfter = app.db().parents();
    Families familyAfter = app.db().families();

    studentClean = app.dbstudents().lastStudent();
    parentClean = app.db().lastParent();
    familyClean = app.db().lastFamily();

    assertThat(leadsAfter.size(), equalTo(leadsBefore.size()));
    assertThat(studentsAfter.size(), equalTo(studentsBefore.size() + 1));
    assertThat(parentsAfter.size(), equalTo(parentsBefore.size() + 1));
    assertThat(familyAfter.size(), equalTo(familyBefore.size() + 1));
    checkStudent(studentsBefore, studentsAfter, student);
    checkParent(parentsBefore, parentsAfter);
    checkLead(leadsBefore, leadsAfter);
  }

  @AfterMethod(alwaysRun = true)
  public void clean() {
    leadService.DeleteById(leadService.findById("selfRegistration"));
    studentService.DeleteById(studentClean.getId());
    parentService.DeleteById(parentClean.getId());
    familyService.DeleteById(familyClean.getId());
    taskService.drop();
  }

  private void checkStudent(Students before, Students after, StudentData student) {
    app.trStudent()
        .newStudent(
            studentClean.getId(),
            student.getFirstname(),
            student.getLastname(),
            "expert",
            "BL",
            familyClean.getId(),
            "Europe/Minsk",
            2,
            app.base().DateWithCorrectionDays(-3650),
            "ru",
            "ru",
            "12345678i",
            "ru",
            new String[]{"1"},
            2,
            "noTrial");

    StudentData studentAdd = studentService.findById(studentClean.getId());
    for (StudentData studentBefore : before) {
      if (studentBefore.getId().equals(studentClean.getId())) {
        assertThat(after, equalTo(before.without(studentBefore).withAdded(studentAdd)));
        return;
      }
    }
  }

  private void checkParent(Parents before, Parents after) {
    app.trParent()
        .newParent(
            parentClean.getId(),
            "Лид",
            "Лидов",
            "BY",
            "Europe/Minsk",
            "ru",
            familyClean.getId(),
            "123456789",
            "mail@list.ru",
            true);
    ParentData parentAdd = parentService.findById(parentClean.getId());
    for (ParentData parentBefore : before) {
      if (parentBefore.getId().equals(parentClean.getId())) {
        assertThat(after, equalTo(before.without(parentBefore).withAdded(parentAdd)));
        return;
      }
    }
  }

  private void checkLead(Leads before, Leads after) {
    app.trLead()
        .leadParent(
            "selfRegistration",
            "Лид",
            "Лидов",
            "parent",
            "BY",
            "Europe/Minsk",
            "ru",
            "+7859561122",
            "mail@list.ru",
            "success",
            "site",
            "manual");

    LeadData leadAdd = leadService.findById("selfRegistration");
    for (LeadData leadBefore : before) {
      if (leadBefore.getId().equals("selfRegistration")) {
        assertThat(after, equalTo(before.without(leadBefore).withAdded(leadAdd)));
        return;
      }
    }
  }
}
