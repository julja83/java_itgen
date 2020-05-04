package ru.stqa.pft.itgen.tests;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import ru.stqa.pft.itgen.model.StudentData;
import ru.stqa.pft.itgen.model.WorkerData;
import ru.stqa.pft.itgen.model.Workers;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class WorkerModificationTests extends TestBase {

  @DataProvider
  public Iterator<Object[]> validWorkersFromJson() throws IOException {
    try(BufferedReader reader = new BufferedReader(new FileReader(new File("src/test/resources/testdata/workers_modification.json")))) {
      String json = "";
      String line = reader.readLine();
      while (line != null) {
        json += line;
        line = reader.readLine();
      }
      Gson gson = new Gson();
      List<WorkerData> workers = gson.fromJson(json, new TypeToken<List<WorkerData>>(){}.getType()); // List<WorkerData>.class
      return workers.stream().map((w) -> new Object[] {w}).collect(Collectors.toList()).iterator();
    }
  }
  @BeforeMethod
  public void ensurePreconditions() {
    if (app.db().workers().size() == 0) {
      app.goTo().tasks();
      app.goTo().gotoWorker();
      app.workers().createFirstWorker(new WorkerData().withFirstName("Маша").withLastName("Машина").withRole("empolyee")
              .withPhone("8962988888888"));
    }
  }

  @Test (dataProvider = "validWorkersFromJson")
  public void testWorkerModification(WorkerData worker) {
    app.goTo().tasks();
    app.goTo().gotoWorker();
    Workers before = app.db().workers();
    WorkerData modifydWorker = before.iterator().next();
    app.workers().selectedWorkerById(modifydWorker);
    app.workers().modificationWorker(worker);
    Workers after = app.db().workers();
    assertThat(after.size(), equalTo(before.size()));
    WorkerData workerAdd = worker.withId(modifydWorker.getId());
    assertThat(after, equalTo(before.without(modifydWorker).withAdded(workerAdd)));
    verifyWorkerListInUI();
  }


}
