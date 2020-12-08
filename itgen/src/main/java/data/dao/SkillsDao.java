package data.dao;

import static data.connection.MFSessionFactory.morphiaSessionFactoryUtil;

import dev.morphia.Datastore;
import dev.morphia.query.Query;
import dev.morphia.query.UpdateOperations;
import data.model.skills.SkillsData;

public class SkillsDao {

  public void save(SkillsData skill) {
    Datastore datastore = morphiaSessionFactoryUtil();
    datastore.save(skill);
  }

  public void updateField(String idSkill, String nameFiled, String data) {
    Datastore datastore = morphiaSessionFactoryUtil();
    Query<SkillsData> query = datastore.createQuery(SkillsData.class).filter("id", idSkill);
    UpdateOperations ops = datastore.createUpdateOperations(SkillsData.class).set(nameFiled, data);
    datastore.update(query, (UpdateOperations<SkillsData>) ops);
  }

  public void drop() {
    Datastore datastore = morphiaSessionFactoryUtil();
    Query<SkillsData> query = datastore.createQuery(SkillsData.class);
    datastore.delete(query);
  }

  public void deleteField(String idSkills, String name) {
    Datastore datastore = morphiaSessionFactoryUtil();
    Query<SkillsData> query = datastore.createQuery(SkillsData.class).field("id").equal(idSkills);
    datastore.update(query, datastore.createUpdateOperations(SkillsData.class).unset(name));
  }
}