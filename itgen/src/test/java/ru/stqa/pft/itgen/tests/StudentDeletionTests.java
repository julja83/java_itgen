package ru.stqa.pft.itgen.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.itgen.model.FamilyData;
import ru.stqa.pft.itgen.model.StudentData;
import ru.stqa.pft.itgen.model.Students;
import ru.stqa.pft.itgen.services.FamilyService;
import ru.stqa.pft.itgen.services.StudentService;

import java.util.Collections;
import java.util.Date;
import java.util.GregorianCalendar;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class StudentDeletionTests extends TestBase {

  @BeforeMethod
  public void ensurePreconditions() {
    if (app.db().students().size() == 0) {
      FamilyService familyService = new FamilyService();
      FamilyData family = new FamilyData().withId("222222").withTrialBonusOff(false).withTierId("txa")
              .withTierHistory(Collections.singletonList(new FamilyData.TierHistory().withTierHistory("")));
      familyService.create(family);

      StudentService studentService = new StudentService();
      StudentData student = new StudentData().withId("1111111").withFirstName("Маша").withLastName("Машина")
              .withRoles(Collections.singletonList(new StudentData.Roles().withRoles("child")))
              .withPclevel("expert").withCountry("AL").withTimeZone("Europe/Minsk").withGender(2)
              .withFamilyId("222222").withStudyLang("ru").withLocate("ru")
              .withBirthday(new Date(1556726891000L))
              .withLangs(Collections.singletonList(new StudentData.Langs().withLangs("ru")))
              .withContacts(Collections.singletonList(new StudentData.Contacts().withType("phone").withVal("1234567899")))
              .withDuration(2).withStatus(new StudentData.Status().withState("noTrial"));
      studentService.create(student);
    }
  }

  @Test
  public void testStudentDeletion() {
    app.goTo().menuTasks();
    app.goTo().menuStudents();
    app.student().addStudentInFamily(); //добавляем еще одного студента, чтоб при удалении одного из студентов одной семьи не появлялись фантомные записи в таблице family
    Students before = app.db().students();
    StudentData studentDeleted = app.student().delete();
    Students after = app.db().students();
    assertThat(after, equalTo(before.without(studentDeleted))); //список "после" и "до"без этого родителя
    verifyStudentsListInUI();
  }
}
