package ru.stqa.pft.itgen.tests;

import org.testng.annotations.Test;

public class ParentDeletionTests extends TestBase {

  @Test
  public void testParentDeletion() {
    app.goTo().tasks();
    app.goTo().students();
    app.students().selectedStudent();
    app.students().selectedFamily();
    app.students().selectedParent();
    app.students().deleteParent();
    app.students().assertDeleteSelectedParent();
  }

}
