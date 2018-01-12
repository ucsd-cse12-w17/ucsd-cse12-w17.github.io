class DateTime {
  int day, month, year, hour, minute;
  public DateTime(int d, int m, int y, int h, int mi) {
    this.day = d; this.month = m; this.year = y; this.hour = h; this.minute = mi;
  }
}

public class Event {
  // Joe's decision:
  DateTime start;
  DateTime end;

  // Conflicts between events – that happen in the same room at the same time
  int getStartTime() {...};
  int getEndTime() {...};

  boolean getConflict(DateTime time, String location) { ... }

  boolean getConflict(Event other) { ... }

  // A suggestion was that two events, one 11:30 - 12:30, and one 12-1, need
  // BOTH start and end times to detect overlaps. Let's write a test that
  // creates corresponding objects to try it out.

  // IDEA: extend DateTime with a location (String)




  // What time it's at
  JDateTime time; // Java library
  int millisecondsStart;
  double time; // for example 11.59 could mean 59 minutes or 59% of the time to 12

  // Duration / when it ends
  int millsecondsEnd;

  DateTime duration;


  // If we have start and end, we can write...
  /*
    @return The number of milliseconds for the duration of the event
  */
  int getDuration() {
    return 0;
    // ... something that subtracts start from end ....
  }



}

