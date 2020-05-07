package ru.stqa.pft.itgen.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.itgen.model.*;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ParentDeletionTests extends TestBase {

  @BeforeMethod
  public void ensurePreconditions() {
    if (app.db().families().size() == 0) {
      app.goTo().tasks();
      app.goTo().students();
      app.family().create(new FamilyDataUI().withFirstnameStudent("Маша").withLastnameStudent("Машина")
              .withBirthdayUiStudent("01.01.1987").withPclevelStudent("expert").withCountryStudent("AL")
              .withFirstnameParent("Олег").withLastnameParent("Машин").withCountryParent("AL").withPhoneParent("010101010101"));
    }
  }

  @Test
  public void testParentDeletion() {
    app.goTo().tasks();
    app.goTo().students();
    Parents before = null;
    String url = "";
    //находим студента c родителем, если такого нет, то создаем студенту без родителя родителя
    Students students = app.db().students();
    boolean a = true;
    for (StudentData student : students) { //проходим по всем студентам
      String idFamily = student.getFamilyId();// у всех по порядку берем FamilyID
      if (app.db().familyСomposition(idFamily).size() == 2) { //если в семье 2 человека
        app.goTo().students();// переходим в студенты
        app.student().selectStudentInStudentListUI(student);//выбираем этого студента в списке
        before = app.db().parents();// запоминаем список родителей До
        app.student().bntFamily();
        url = app.parent().delete();//удаляем
        a = false;
        break;
      }
    }
    if (a) {  //если у всех студентов нет родителя, то добавляем родителя к первому студенту
      app.parent().create(new ParentData().withFirstName("Папа").withLastName("Папа").withPhone("000000000"));
      before = app.db().parents();// берем список родителй ДО
      app.student().bntFamily();
      url = app.parent().delete();//удаляем , и знаем УРЛ удаленного родителя
    }
    Parents after = app.db().parents();
    assertThat(after.size(), equalTo(before.size() - 1));

    String idParent = app.parent().getId(url); //id удаленного родителя
    for (ParentData parent : before) { //найти в списке ДО родителя с таким id
      if (parent.getId().equals(idParent)) {
        assertThat(after, equalTo(before.without(parent))); //список После и ДО-этот родитель
        return;
      }
    }
  }


}
