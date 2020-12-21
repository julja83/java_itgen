package data.precondition;

import app.appmanager.HelperBase;

public class NewFamily extends TranzactionManager {

  protected static final DataManager data = new DataManager();
  HelperBase base = new HelperBase();

  /**
   * новая семья с учеником и одним родителе
   */
  public void set1_FamilyWithStudentAndParent() {
    trFamily().newFamily("newFamily", false, "HseKLp6muYbZQ3cZA");
    trStudent()
        .newStudent(
            "student",
            "Олег",
            "Олегов",
            "expert",
            "BL",
            "newFamily",
            "Europe/Minsk",
            2,
            base.DateWithCorrectionDays(-2460),
            "ru",
            "ru",
            "78787878i",
            "ru",
            "1",
            2,
            "noTrial"
        );
    trParent().newParent(
        "newParent",
        "Родитель",
        "Новый",
        "BL",
        "Europe/Minsk",
        "ru",
        "newFamily",
        "+97895554433",
        "julja@tt.ru",
        true
    );
  }
}