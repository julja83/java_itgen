package ru.stqa.pft.itgen.model;

import com.google.gson.annotations.Expose;

public class WorkerData {
  @Expose
  private String firstName;
  @Expose
  private String lastName;
  @Expose
  private String email;
  @Expose
  private String role;
  @Expose
  private String startDay;
  @Expose
  private String birthDay;
  @Expose
  private String gender;
  @Expose
  private String country;
  @Expose
  private String city;
  @Expose
  private String timeZone;
  @Expose
  private String locate;
  @Expose
  private String phone;
  @Expose
  private String skype;
  @Expose
  private String viber;
  @Expose
  private String whatsapp;
  @Expose
  private String tg;
  @Expose
  private String fb;
  @Expose
  private String vk;
  @Expose
  private String ok;
  @Expose
  private String inst;

  public WorkerData withFirstName(String firstName) {
    this.firstName = firstName;
    return this;
  }

  public WorkerData withLastName(String lastName) {
    this.lastName = lastName;
    return this;
  }

  public WorkerData withEmail(String email) {
    this.email = email;
    return this;
  }

  public WorkerData withRole(String role) {
    this.role = role;
    return this;
  }

  public WorkerData withStartDay(String startDay) {
    this.startDay = startDay;
    return this;
  }

  public WorkerData withBirthDay(String birthDay) {
    this.birthDay = birthDay;
    return this;
  }

  public WorkerData withGender(String gender) {
    this.gender = gender;
    return this;
  }

  public WorkerData withCountry(String country) {
    this.country = country;
    return this;
  }

  public WorkerData withCity(String city) {
    this.city = city;
    return this;
  }

  public WorkerData withTimeZone(String timeZone) {
    this.timeZone = timeZone;
    return this;
  }

  public WorkerData withLocate(String locate) {
    this.locate = locate;
    return this;
  }

  public WorkerData withPhone(String phone) {
    this.phone = phone;
    return this;
  }

  public WorkerData withSkype(String skype) {
    this.skype = skype;
    return this;
  }

  public WorkerData withViber(String viber) {
    this.viber = viber;
    return this;
  }

  public WorkerData withWhatsapp(String whatsapp) {
    this.whatsapp = whatsapp;
    return this;
  }

  public WorkerData withTg(String tg) {
    this.tg = tg;
    return this;
  }

  public WorkerData withFb(String fb) {
    this.fb = fb;
    return this;
  }

  public WorkerData withVk(String vk) {
    this.vk = vk;
    return this;
  }

  public WorkerData withOk(String ok) {
    this.ok = ok;
    return this;
  }

  public WorkerData withInst(String inst) {
    this.inst = inst;
    return this;
  }

  public String getFirstName() {
    return firstName;
  }

  public String getLastName() {
    return lastName;
  }

  public String getEmail() {
    return email;
  }

  public String getRole() {
    return role;
  }

  public String getStartDay() {
    return startDay;
  }

  public String getBirthDay() {
    return birthDay;
  }

  public String getGender() {
    return gender;
  }

  public String getCountry() {
    return country;
  }

  public String getCity() {
    return city;
  }

  public String getTimeZone() {
    return timeZone;
  }

  public String getLocate() {
    return locate;
  }

  public String getPhone() {
    return phone;
  }

  public String getSkype() {
    return skype;
  }

  public String getViber() {
    return viber;
  }

  public String getWhatsapp() {
    return whatsapp;
  }

  public String getTg() {
    return tg;
  }

  public String getFb() {
    return fb;
  }

  public String getVk() {
    return vk;
  }

  public String getOk() {
    return ok;
  }

  public String getInst() {
    return inst;
  }

}
