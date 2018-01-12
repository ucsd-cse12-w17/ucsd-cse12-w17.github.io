public class MyDateTime {
  int year, month, day, hours, minutes;
}
public class Event {

  // When is it?

  JDateTime time; // JDateTime maybe not the real name
  MyDateTime time;
  int millisecondsSinceJan11970Start;      // long

  ArrayList<Integer> time;
  int[] time;

  // Duration/when it ends

  JDuration duration; // JDuration maybe not the real name

  MyDateTime end;
  int millisecondsSinceJan11970End;

  String room;

  boolean hasConflict(DateTime time, String room) {
    ...
  }

  boolean hasConflict(Event other) {
    // ... compare rooms, and compare datetimes for overlap ...
  }

  // The ability to check for conflict between events:
  // do they happen at overlapping times in the same room?

  // IDEA: Keep track of all events, check in constructor
  // NOTE: start <-> end maybe more useful than start + duration

  // IDEA: Make a boolean-returning method that looks like:
  //
  // boolean hasConflict(Event other) { ... comparisons ... }

  // IDEA: static method
  //
  // static boolean hasConflict(Event e1, Event e2) { ... }

  // IDEA: if you have a lot of events, it's “expensive” to check



}

