package io.itgen.tests.screenShotPar;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import io.itgen.appmanager.ApplicationManager;
import io.itgen.model.StudentData;
import io.itgen.tests.TestBase;
import java.util.HashSet;
import java.util.Set;
import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import ru.yandex.qatools.ashot.comparison.ImageDiff;

import java.awt.*;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

public class SshotAddNewStudentFormSecond extends TestBase {
  @DataProvider
  public Iterator<Object[]> StudentsFromJson() throws IOException {
    try (BufferedReader reader =
        new BufferedReader(
            new FileReader(
                new File("src/test/resources/testdata/sshot_creation_student_par_form1.json")))) {
      String json = "";
      String line = reader.readLine();
      while (line != null) {
        json += line;
        line = reader.readLine();
      }
      Gson gson = new Gson();
      List<StudentData> students =
          gson.fromJson(
              json, new TypeToken<List<StudentData>>() {}.getType()); // List<StudentData>.class
      return students.stream().map((s) -> new Object[] {s}).collect(Collectors.toList()).iterator();
    }
  }

  @Test(dataProvider = "StudentsFromJson")
  public void testAddNewStudentFormSecond(StudentData student) throws AWTException, IOException {
    app.lkParent().createSShotSecondForm(student);

    String name = "Parent_AddNewStudentForm2_RU_Chrome";
    Set<By> locatorIgnor = new HashSet<>();
    locatorIgnor.add(By.xpath("//div[@class='content']/*[3]"));

    ImageDiff diff =
        app.sshot()
            .getImageDiff(
                ApplicationManager.properties.getProperty("expected"),
                ApplicationManager.properties.getProperty("actual"),
                ApplicationManager.properties.getProperty("markedImages"),
                name,
                locatorIgnor,
                1.25f);
    app.lkParent().btnLogo();
    if (diff.getDiffSize() > 100) { // погрешность
      Assert.assertEquals(diff.getDiffSize(), 0);
    }
  }
}
