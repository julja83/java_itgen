package io.itgen.dao;

import dev.morphia.Datastore;
import dev.morphia.query.Query;
import io.itgen.connection.MFSessionFactory;
import io.itgen.model.FamilyData;

public class FamilyDao {

  public FamilyData findByIdAndDelete(String id) {
    Datastore datastore = MFSessionFactory.morphiaSessionFactoryUtil();
    Query<FamilyData> query = datastore.createQuery(FamilyData.class)
            .filter("id", id);
    FamilyData family = datastore.findAndDelete(query);
    return family;
  }

  public void save(FamilyData family) {
    Datastore datastore = MFSessionFactory.morphiaSessionFactoryUtil();
    datastore.save(family);
  }

  public void delete(FamilyData family) {
    Datastore datastore = MFSessionFactory.morphiaSessionFactoryUtil();
    datastore.delete(family);
  }

}