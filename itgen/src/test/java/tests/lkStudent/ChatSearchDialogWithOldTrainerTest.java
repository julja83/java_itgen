package tests.lkStudent;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

import app.testbase.TestBase;
import core.general.RunTestAgain;
import data.services.ChatMessageService;
import data.services.ChatRoomService;
import data.services.ChatSubscriptionService;
import data.services.StudentService;
import data.services.TrainerService;
import java.util.Date;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class ChatSearchDialogWithOldTrainerTest extends TestBase {

  StudentService studentService = new StudentService();
  ChatRoomService chatRoomService = new ChatRoomService();
  ChatMessageService chatMessageService = new ChatMessageService();
  ChatSubscriptionService chatSubscriptionService = new ChatSubscriptionService();
  TrainerService trainerService = new TrainerService();
  String messageOld = "Привет";


  @BeforeMethod
  public void ensurePreconditions() {
    Object[] users = new Object[2];
    users[0] = studentService.findById("21");
    users[1] = trainerService.findById("23");

    app.trChat().saveChatRoom("RoomMessage", new Date(), "d", "23", users, "Student_Trainer");
    app.trChat().saveChatMessage("MessageOnLesson", "RoomMessage", new Date(), messageOld, "21");
    app.trChat()
        .saveChatSubscription(
            "subsc1", new Date(), "d", "RoomMessage", "21", 1, false, "21", messageOld);
    app.trChat()
        .saveChatSubscription(
            "subsc2", new Date(), "d", "RoomMessage", "23", 1, true, "21", messageOld);
  }


  @Test(retryAnalyzer = RunTestAgain.class)
  public void testChatSearchDialogWithOldTrainer() {
    app.chat().btnOpenChat();
    String[] etalon = {"Дефолтный Тренер"};
    String[] dialogs = app.chat().searchPersonByStudent("Тренер");
    assertThat(dialogs.length, equalTo(1));
    assertThat(dialogs, equalTo(etalon));
    app.chat().btnCloseChat();
  }

  @AfterMethod(alwaysRun = true)
  public void clean() {
    chatRoomService.drop();
    chatMessageService.drop();
    chatSubscriptionService.drop();
  }
}
