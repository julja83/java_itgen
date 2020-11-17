package data.dao;

import static data.connection.MFSessionFactory.morphiaSessionFactoryUtil;

import dev.morphia.Datastore;
import dev.morphia.query.Query;
import data.connection.MFSessionFactory;
import data.model.payment.PaymentData;

public class PaymentDao {

  public void save(PaymentData payment) {
    Datastore datastore = MFSessionFactory.morphiaSessionFactoryUtil();
    datastore.save(payment);
  }

  public PaymentData findByIdAndDelete(String id) {
    Datastore datastore = MFSessionFactory.morphiaSessionFactoryUtil();
    Query<PaymentData> query = datastore.createQuery(PaymentData.class).filter("id", id);
    PaymentData payment = datastore.findAndDelete(query);
    return payment;
  }

  public PaymentData findById(String id) {
    Datastore datastore = morphiaSessionFactoryUtil();
    return datastore.find(PaymentData.class).field("id").equal(id).first();
  }

  public void drop() {
    Datastore datastore = morphiaSessionFactoryUtil();
    Query<PaymentData> query = datastore.createQuery(PaymentData.class);
    datastore.delete(query);
  }
}
