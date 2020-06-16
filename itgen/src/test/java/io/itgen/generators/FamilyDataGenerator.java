package io.itgen.generators;

import com.beust.jcommander.JCommander;
import com.beust.jcommander.Parameter;
import com.beust.jcommander.ParameterException;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import io.itgen.model.FamilyDataUI;
import io.itgen.tests.TestBase;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.ArrayList;
import java.util.List;

public class FamilyDataGenerator extends TestBase {
  // опции командной строки
  @Parameter(names = "-c", description = "Contact count")
  public int count;

  @Parameter(names = "-f", description = "Target file")
  public String file;

  @Parameter(names = "-d", description = "Data format")
  public String format;

  public static void main(String[] args) throws IOException {
    FamilyDataGenerator generator = new FamilyDataGenerator();
    JCommander jCommander = new JCommander(generator);
    try {
      jCommander.parse(args);
    } catch (ParameterException ex) {
      jCommander.usage();
      return;
    }
    generator.run();
  }

  //генерация тестовых данных
  private void run() throws IOException {
    List<FamilyDataUI> families = generateFamilies(count);
    if (format.equals("json")) {
      saveAsJson(families, new File(file));
    } else {
      System.out.println("Unrecognized format " + format);
    }
  }

  //сохранение этих данных в файл
  private void saveAsJson(List<FamilyDataUI> families, File file) throws IOException {
    Gson gson = new GsonBuilder().setPrettyPrinting().excludeFieldsWithoutExposeAnnotation().create();
    String json = gson.toJson(families);
    try (Writer writer = new FileWriter(file);) {
      writer.write(json);
    }
  }

  private List<FamilyDataUI> generateFamilies(int count) {
    List<FamilyDataUI> families = new ArrayList<FamilyDataUI>();
    for (int i = 0; i < count; i++) {
      families.add(new FamilyDataUI()
              // ученик
              .withFirstnameStudent(String.format("Вася-%s", i))
              .withLastnameStudent(String.format("Васин-%s", i))
              .withGenderStudent(Integer.valueOf(String.format("2", i)))
              .withBirthdayUiStudent(String.format("01.01.190%s", i))
              .withPclevelStudent(String.format("expert", i))
              .withCountryStudent(String.format("AM", i))
              .withCityStudent(String.format("Сватково %s", i))
              .withTimezoneStudent(String.format("Pacific/Honolulu", i))
              .withPhoneStudent(String.format("11111111111", i))
              .withTelegramStudent(String.format("tg %s", i))
              .withViberStudent(String.format("22222222222", i))
              .withC2dStudent(String.format("http:/%s", i))
              .withSkypeStudent(String.format("sk %s", i))
              .withWhatsappStudent(String.format("89629861121", i))
              .withFbStudent(String.format("fb %s", i))
              .withVkStudent(String.format("vk %s", i))
              .withOkStudent(String.format("ok %s", i))
              .withInstStudent(String.format("inst %s", i))
              // родитель
              .withFirstnameParent(String.format("Петя-%s", i))
              .withLastnameParent(String.format("Петров-%s", i))
              .withCountryParent(String.format("AL", i))
              .withCityParent(String.format("Пересвет %s", i))
              .withTimezoneParent(String.format("Pacific/Honolulu", i))
              .withPhoneParent(String.format("33333333333", i))
              .withTelegramParent(String.format("tg-%s", i))
              .withViberParent(String.format("44444444444", i))
              .withC2dParent(String.format("http:/0-%s", i))
              .withSkypeParent(String.format("sk-%s", i))
              .withWhatsappParent(String.format("55555555555", i))
              .withFbParent(String.format("fb-%s", i))
              .withVkParent(String.format("vk-%s", i))
              .withOkParent(String.format("ok-%s", i))
              .withInstParent(String.format("inst-%s", i)));
    }
    return families;
  }
}