package data.model.schedule;

import dev.morphia.annotations.Embedded;
import dev.morphia.annotations.Entity;
import dev.morphia.annotations.Id;
import dev.morphia.annotations.Property;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity("schedule")
public class ScheduleData {
  @Id
  @Property("_id")
  private String id;

  @Property("ver")
  private Integer ver;

  @Property("fromDate")
  private Double fromDate;

  @Embedded("slots")
  private List<Slots> slots = new ArrayList<Slots>();

  @Embedded("finishedSlots")
  private List<FinishedSlots> finishedSlots = new ArrayList<FinishedSlots>();

  @Embedded private Times times;

  @Property("oneTime")
  private Boolean oneTime;

  @Property("duration")
  private int duration;

  @Property("wholeness")
  private Boolean wholeness;

  @Property("lessonFormat")
  private int lessonFormat;

  @Property("archiveReason")
  private String archiveReason;

  @Property("archived")
  private boolean archived;

  public ScheduleData() {
  }

  public ScheduleData withId(String id) {
    this.id = id;
    return this;
  }

  public ScheduleData withVer(Integer ver) {
    this.ver = ver;
    return this;
  }

  public ScheduleData withFromDate(Double fromDate) {
    this.fromDate = fromDate;
    return this;
  }

  public ScheduleData withSlots(List<Slots> slots) {
    this.slots = slots;
    return this;
  }

  public ScheduleData withFinishedSlots(List<FinishedSlots> finishedSlots) {
    this.finishedSlots = finishedSlots;
    return this;
  }

  public ScheduleData withTimes(Times times) {
    this.times = times;
    return this;
  }

  public ScheduleData withOneTime(Boolean oneTime) {
    this.oneTime = oneTime;
    return this;
  }

  public ScheduleData withDuration(int duration) {
    this.duration = duration;
    return this;
  }

  public ScheduleData withWholeness(boolean wholeness) {
    this.wholeness = wholeness;
    return this;
  }

  public ScheduleData withLessonFormat(int lessonFormat) {
    this.lessonFormat = lessonFormat;
    return this;
  }

  public ScheduleData withArchiveReason(String archiveReason) {
    this.archiveReason = archiveReason;
    return this;
  }

  public ScheduleData withArchived(boolean archived) {
    this.archived = archived;
    return this;
  }

  public String getId() {
    return id;
  }

  public Integer getVer() {
    return ver;
  }

  public Double getFromDate() {
    return fromDate;
  }

  public List<Slots> getSlots() {
    return slots;
  }

  public List<FinishedSlots> getFinishedSlots() {
    return finishedSlots;
  }

  public Times getTimes() {
    return times;
  }

  public Boolean getOneTime() {
    return oneTime;
  }

  public int getDuration() {
    return duration;
  }

  public Boolean getWholeness() {
    return wholeness;
  }

  public int getLessonFormat() {
    return lessonFormat;
  }

  public String getArchiveReason() {
    return archiveReason;
  }

  public boolean isArchived() {
    return archived;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    ScheduleData that = (ScheduleData) o;
    return Objects.equals(id, that.id)
        && Objects.equals(fromDate, that.fromDate)
        && Objects.equals(slots, that.slots)
        && Objects.equals(times, that.times)
        && Objects.equals(duration, that.duration)
        && Objects.equals(wholeness, that.wholeness)
        && Objects.equals(oneTime, that.oneTime)
        && Objects.equals(archived, that.archived);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, fromDate, slots, times, duration, wholeness, oneTime, archived);
  }

  @Override
  public String toString() {
    return "ScheduleData{" +
        "id='" + id + '\'' +
        ", ver=" + ver +
        ", fromDate=" + fromDate +
        ", slots=" + slots +
        ", finishedSlots=" + finishedSlots +
        ", times=" + times +
        ", oneTime=" + oneTime +
        ", duration=" + duration +
        ", wholeness=" + wholeness +
        ", lessonFormat=" + lessonFormat +
        ", archiveReason='" + archiveReason + '\'' +
        ", archived=" + archived +
        '}';
  }
}
