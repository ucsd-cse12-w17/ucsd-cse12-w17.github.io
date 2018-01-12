
class Time {
  int hour, minute;
  public Time(int hour, int minute) {
    this.hour = hour; this.minute = minute;
  }

}

public class Event {
  // Constraint: all times within a single day
  Time start;
  Time end;
  String room;

  public Event(Time start, Time end, String room) {
    this.start = start;
    this.end = end;
    this.room = room;
  }

  // Return true if the other event is in the
  // same room at an overlapping time
  public boolean conflict(Event other) {
    return false;
  }
}
