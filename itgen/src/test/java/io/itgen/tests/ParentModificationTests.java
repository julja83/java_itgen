package io.itgen.tests;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import io.itgen.general.RunTestAgain;
import io.itgen.model.FamilyData;
import io.itgen.model.ParentData;
import io.itgen.model.Parents;
import io.itgen.model.StudentData;
import io.itgen.model.users.Contacts;
import io.itgen.model.users.Status;
import io.itgen.services.FamilyService;
import io.itgen.services.ParentService;
import io.itgen.services.StudentService;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.Collections;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class ParentModificationTests extends TestBase {

  @DataProvider
  public Iterator<Object[]> validParentsFromJson() throws IOException {
    try (BufferedReader reader =
        new BufferedReader(
            new FileReader(new File("src/test/resources/testdata/parents_modification.json")))) {
      String json = "";
      String line = reader.readLine();
      while (line != null) {
        json += line;
        line = reader.readLine();
      }
      Gson gson = new Gson();
      List<ParentData> parents =
          gson.fromJson(
              json, new TypeToken<List<ParentData>>() {}.getType()); // List<ParentData>.class
      return parents.stream().map((p) -> new Object[] {p}).collect(Collectors.toList()).iterator();
    }
  }

  @BeforeMethod
  public void ensurePreconditions() {
    FamilyService familyService = new FamilyService();
    FamilyData family =
        new FamilyData().withId("parentModify").withTrialBonusOff(false).withTierId("txa");
    familyService.save(family);
    StudentService studentService = new StudentService();
    StudentData student =
        new StudentData()
            .withId("forParentModify")
            .withFirstName("Маша")
            .withLastName("Машина")
            .withRoles(Arrays.asList("child"))
            .withPclevel("expert")
            .withCountry("AL")
            .withTimeZone("Europe/Minsk")
            .withGender(2)
            .withFamilyId("parentModify")
            .withStudyLang("ru")
            .withLocate("ru")
            .withBirthday(new Date())
            .withLangs(Arrays.asList("ru"))
            .withContacts(
                Collections.singletonList(new Contacts().withType("phone").withVal("1234567899")))
            .withDuration(2)
            .withStatus(new Status().withState("noTrial"));
    studentService.save(student);
    ParentService parentService = new ParentService();
    ParentData parent =
        new ParentData()
            .withId("forParModify")
            .withFirstName("Зина")
            .withLastName("Зинина")
            .withRoles(Arrays.asList("parent"))
            .withCountry("AL")
            .withTimeZone("Europe/Minsk")
            .withFamilyId("parentModify")
            .withLocate("ru")
            .withContacts(
                Collections.singletonList(new Contacts().withType("phone").withVal("1234567899")));
    parentService.save(parent);
  }

  @Test(dataProvider = "validParentsFromJson", retryAnalyzer = RunTestAgain.class)
  public void testParentModification(ParentData parent) {
    app.goTo().menuTasks();
    app.goTo().menuStudents();
    Parents before = app.db().parents();
    app.student().selectStudentInListUIById("forParentModify");
    app.parent().modify(parent);
    Parents after = app.db().parents();
    assertThat(after.size(), equalTo(before.size()));

    for (ParentData parentModify : before) { // найти в списке "до" родителя с таким id
      if (parentModify.getId().equals("forParModify")) {
        ParentData parentAdd = parent.withId(parentModify.getId());
        assertThat(after, equalTo(before.without(parentModify).withAdded(parentAdd)));
        return;
      }
    }
  }

  @AfterMethod(alwaysRun = true)
  public void clean() {
    StudentService studentService = new StudentService();
    studentService.DeleteById("forParentModify");
    FamilyService familyService = new FamilyService();
    familyService.DeleteById("parentModify");
    ParentService parentService = new ParentService();
    parentService.DeleteById("forParModify");
  }
}
