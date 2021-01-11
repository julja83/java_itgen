package tests.employers;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

import app.testbase.TestBase;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import core.general.RunTestAgain;
import data.model.users.TrainerData;
import data.model.users.Trainers;
import data.model.users.WorkerData;
import data.model.users.Workers;
import data.services.TrainerService;
import data.services.WorkerService;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class WorkerAndTrainerCreationTests extends TestBase {
  String idWorker = "null";
  String idTrainer = "null";

  @DataProvider
  public Iterator<Object[]> validWorkersFromJson() throws IOException {
    try (BufferedReader reader =
        new BufferedReader(
            new FileReader(new File("src/test/resources/testdata/workers_creation.json")))) {
      String json = "";
      String line = reader.readLine();
      while (line != null) {
        json += line;
        line = reader.readLine();
      }
      Gson gson = new Gson();
      List<WorkerData> workers =
          gson.fromJson(
              json, new TypeToken<List<WorkerData>>() {}.getType()); // List<WorkerData>.class
      return workers.stream().map((c) -> new Object[] {c}).collect(Collectors.toList()).iterator();
    }
  }

  @DataProvider
  public Iterator<Object[]> validWorkersAdminsFromJson() throws IOException {
    try (BufferedReader reader =
        new BufferedReader(
            new FileReader(new File("src/test/resources/testdata/workers_admins_creation.json")))) {
      String json = "";
      String line = reader.readLine();
      while (line != null) {
        json += line;
        line = reader.readLine();
      }
      Gson gson = new Gson();
      List<WorkerData> workers =
          gson.fromJson(
              json, new TypeToken<List<WorkerData>>() {}.getType()); // List<WorkerData>.class
      return workers.stream().map((w) -> new Object[] {w}).collect(Collectors.toList()).iterator();
    }
  }

  @DataProvider
  public Iterator<Object[]> validWorkersTrainersFromJson() throws IOException {
    try (BufferedReader reader =
        new BufferedReader(
            new FileReader(
                new File("src/test/resources/testdata/workers_trainers_creation.json")))) {
      String json = "";
      String line = reader.readLine();
      while (line != null) {
        json += line;
        line = reader.readLine();
      }
      Gson gson = new Gson();
      List<TrainerData> trainers =
          gson.fromJson(
              json, new TypeToken<List<TrainerData>>() {}.getType()); // List<TrainerData>.class
      return trainers.stream().map((w) -> new Object[] {w}).collect(Collectors.toList()).iterator();
    }
  }

  @Test(dataProvider = "validWorkersFromJson", retryAnalyzer = RunTestAgain.class)
  public void testWorkerCreation(WorkerData worker) {
    app.goTo().menuTrainers();
    app.goTo().menuWorkers();
    Workers before = app.db().workers();
    String url = app.worker().createWorker(worker);
    idWorker = app.worker().getId(url);
    Workers after = app.db().workers();
    assertThat(after.size(), equalTo(before.size() + 1));
    WorkerData workerAdd = worker.withId(idWorker);
    assertThat(after, equalTo(before.withAdded(workerAdd)));
    verifyWorkerListInUI();
  }

  @Test(dataProvider = "validWorkersAdminsFromJson", enabled = false)
  public void testWorkerAdminCreation(WorkerData worker) {
    app.goTo().menuTasks();
    app.goTo().menuWorkers();
    int before = app.worker().getWorkerCount();
    app.worker().createWorker(worker);
    int after = app.worker().getWorkerCount();
    Assert.assertEquals(after, before + 1);
  }

  @Test(dataProvider = "validWorkersTrainersFromJson", retryAnalyzer = RunTestAgain.class)
  public void testWorkerTrainerCreation(TrainerData trainer) {
    app.goTo().menuTasks();
    app.goTo().menuWorkers();
    Trainers before = app.db().trainers();
    String url = app.trainer().create(trainer);
    idTrainer = app.trainer().getId(url);
    Trainers after = app.db().trainers();
    assertThat(after.size(), equalTo(before.size() + 1));
    TrainerData trainerAdd = trainer.withId(idTrainer);
    assertThat(after, equalTo(before.withAdded(trainerAdd)));
    verifyTrainerListInUI();
  }

  @AfterMethod(alwaysRun = true)
  public void clean() {
    WorkerService workerService = new WorkerService();
    workerService.DeleteById(idWorker);
    TrainerService trainerService = new TrainerService();
    trainerService.DeleteById(idTrainer);
  }
}
