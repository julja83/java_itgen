package tests.lkStudent;
/*T-243
 * если у ученика не было пробного и не было никаких диалогов, то диалог тоько с айтигеником
 */

import static app.appmanager.ApplicationManager.properties;

import app.testbase.TestBase;
import core.general.RunTestAgain;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class ChatDialogOnlyWithGenaTest extends TestBase {

  @BeforeMethod
  public void ensurePreconditions() {
    data.postClean().chat();
    data.defFamily().set19_ChangeDefaultStudentInStart();
    app.base().goByHref(app.base().address() + "/login");
    app.student()
        .login(properties.getProperty("web.Login"), properties.getProperty("web.Password"));
  }

  @Test(retryAnalyzer = RunTestAgain.class)
  public void testChatDialogOnlyWithGena() {
    String[] dialogs = app.chat().getDialogs();
    app.check().equalityOfTwoElements(dialogs.length, 1);
    app.check().equalityOfTwoElements(dialogs[0], "Айтигеник");
    app.chat().btnCloseChat();
  }
}
