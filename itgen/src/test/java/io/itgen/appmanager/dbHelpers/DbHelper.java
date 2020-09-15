package io.itgen.appmanager.dbHelpers;

import static io.itgen.connection.MFSessionFactory.morphiaSessionFactoryUtil;

import dev.morphia.Datastore;
import dev.morphia.query.Query;
import io.itgen.model.Families;
import io.itgen.model.FamilyData;
import io.itgen.model.LeadData;
import io.itgen.model.Leads;
import io.itgen.model.ParentData;
import io.itgen.model.Parents;
import io.itgen.model.PaymentData;
import io.itgen.model.Payments;
import io.itgen.model.StudentData;
import io.itgen.model.Students;
import io.itgen.model.TrainerData;
import io.itgen.model.Trainers;
import io.itgen.model.WorkerData;
import io.itgen.model.Workers;
import io.itgen.model.materials.MaterialBranchData;
import io.itgen.model.materials.MaterialData;
import io.itgen.services.MaterialBranchService;
import java.util.List;

public class DbHelper {

  public Families families() {
    Datastore datastore = morphiaSessionFactoryUtil();
    Query<FamilyData> q = datastore.createQuery(FamilyData.class);
    List<FamilyData> family = q.find().toList();
    return new Families(family);
  }

  public FamilyData lastFamily() {
    Datastore datastore = morphiaSessionFactoryUtil();
    Query<FamilyData> q = datastore.createQuery(FamilyData.class);
    long count=q.count();
    List<FamilyData> family = q.find().toList();
    FamilyData lastFamily = family.get(Math.toIntExact(count - 1));
    return lastFamily;
  }

  public ParentData lastParent() {
    Datastore datastore = morphiaSessionFactoryUtil();
    Query<ParentData> q = datastore.createQuery(ParentData.class).filter("roles", "parent");;
    long count=q.count();
    List<ParentData> parent= q.find().toList();
    ParentData lastParent = parent.get(Math.toIntExact(count - 1));
    return lastParent;
  }

  public Students familyComposition(String id) {
    Datastore datastore = morphiaSessionFactoryUtil();
    Query<StudentData> q = datastore.createQuery(StudentData.class);
    q.or(
        (q.and(q.criteria("roles").equal("child"), q.criteria("familyId").equal(id))),
        q.and(q.criteria("roles").equal("parent"), q.criteria("familyId").equal(id)));

    List<StudentData> students = q.find().toList();
    return new Students(students);
  }

  public Parents parents() {
    Datastore datastore = morphiaSessionFactoryUtil();
    Query<ParentData> q = datastore.createQuery(ParentData.class).filter("roles", "parent");
    List<ParentData> parents = q.find().toList();
    return new Parents(parents);
  }

  public ParentData getTokenParent(String name, String surname, String role) {
    Datastore datastore = morphiaSessionFactoryUtil();
    Query<ParentData> q = datastore.createQuery(ParentData.class);

    q.and(
        q.criteria("roles").equal(role),
        q.criteria("firstName").equal(name),
        q.criteria("lastName").equal(surname));
    List<ParentData> parents = q.find().toList();
    return q.find().next();
  }

  public Workers workers() {
    Datastore datastore = morphiaSessionFactoryUtil();
    Query<WorkerData> q = datastore.createQuery(WorkerData.class);
    q.and(
        q.criteria("roles").notEqual("trainer"),
        q.criteria("roles").notEqual("child"),
        q.criteria("roles").notEqual("parent"));
    List<WorkerData> workers = q.find().toList();
    return new Workers(workers);
  }

  public Trainers trainers() {
    Datastore datastore = morphiaSessionFactoryUtil();
    Query<TrainerData> q = datastore.createQuery(TrainerData.class).filter("roles", "trainer");
    List<TrainerData> trainers = q.find().toList();
    return new Trainers(trainers);
  }

  public Leads leads() {
    Datastore datastore = morphiaSessionFactoryUtil();
    Query<LeadData> q = datastore.createQuery(LeadData.class);
    List<LeadData> leads = q.find().toList();
    return new Leads(leads);
  }

  public LeadData find(String id) {
    Datastore datastore = morphiaSessionFactoryUtil();
    Query<LeadData> q = datastore.createQuery(LeadData.class).filter("id", id);
    LeadData lead = q.find().next();
    return lead;
  }

  public Payments payments(String idFamily) {
    Datastore datastore = morphiaSessionFactoryUtil();
    Query<PaymentData> q = datastore.createQuery(PaymentData.class).filter("fId", idFamily);
    List<PaymentData> payments = q.find().toList();
    return new Payments(payments);
  }
}
