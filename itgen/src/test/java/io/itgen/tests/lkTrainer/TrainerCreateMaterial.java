package io.itgen.tests.lkTrainer;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import io.itgen.model.materials.MaterialBranchData;
import io.itgen.model.materials.MaterialData;
import io.itgen.model.materials.Materials;
import io.itgen.services.MaterialBranchService;
import io.itgen.services.MaterialNewService;
import io.itgen.services.MaterialService;
import io.itgen.services.PaymentService;
import io.itgen.tests.TestBase;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class TrainerCreateMaterial extends TestBase {

  MaterialData materialClean = null;
  MaterialBranchData materialBranchClean = null;
  MaterialService materialService = new MaterialService();
  MaterialBranchService materialBranchService = new MaterialBranchService();
  PaymentService paymentService = new PaymentService();
  MaterialNewService materialNewService = new MaterialNewService();

  @BeforeMethod
  public void ensurePreconditions() {
    app.trMaterial().newMaterialBranch("1", "CreateNewMaterial", "Scratch");
  }

  @DataProvider
  public Iterator<Object[]> validMaterialFromJson() throws IOException {
    try (BufferedReader reader =
        new BufferedReader(
            new FileReader(
                new File("src/test/resources/testdata/trainer_material_creation_lk.json")))) {
      String json = "";
      String line = reader.readLine();
      while (line != null) {
        json += line;
        line = reader.readLine();
      }
      Gson gson = new Gson();
      List<MaterialData> materials =
          gson.fromJson(json, new TypeToken<List<MaterialData>>() {
          }.getType());
      return materials.stream()
          .map((s) -> new Object[]{s})
          .collect(Collectors.toList())
          .iterator();
    }
  }

  @Test(dataProvider = "validMaterialFromJson")
  public void testCreateMaterial(MaterialData material) {
    app.trainer().gotoMaterial();
    app.material().addNewMaterial(material);
    Materials materialAfter = app.dbmaterial().materials();
    materialClean = app.dbmaterial().lastMaterial();
    check(materialAfter, materialClean.getId(), material);
  }

  @AfterMethod(alwaysRun = true)
  public void clean() {
    materialService.DeleteById(materialClean.getId());
    materialBranchClean = app.dbmaterial().lastBranchMaterial();
    materialBranchService.DeleteById(materialBranchClean.getId());
    materialNewService.drop();
    paymentService.drop();
  }

  private void check(Materials after, String id, MaterialData material) {
    app.trMaterial()
        .newMaterial(
            id,
            "23",
            material.getTitle().trim(),
            "checking",
            material.getSkill(),
            "CreateNewMaterial",
            material.getType(),
            material.getLevel(),
            material.getLang(),
            material.getOriginality(),
            material.getMaterialLink(),
            material.getProjectLink(),
            material.getSourceLink(),
            material.getDesc().trim());

    MaterialData materialAdd = materialService.findById(id);

    for (MaterialData materialAfter : after) {
      if (materialAfter.getId().equals(id)) {
        assertThat(after, equalTo(after.without(materialAfter).withAdded(materialAdd)));
        return;
      }
    }
  }
}