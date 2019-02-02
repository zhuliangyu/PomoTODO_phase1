package model;

import java.util.Date;

// Note: Any library in JDK 8 may be used to implement this class, however,
//     you must not use any third-party library in your implementation
// Hint: Explore https://docs.oracle.com/javase/8/docs/api/java/util/Calendar.html

// Represents the due date of a Task
public class DueDate {
    // MODIFIES: this
    // EFFECTS: creates a DueDate with deadline at end of day today (i.e., today at 11:59 PM)
    public DueDate() {

    }

    // REQUIRES: date != null
    // MODIFIES: this
    // EFFECTS: creates a DueDate with deadline of the given date
    public DueDate(Date date) {

    }

    // REQUIRES: date != null
    // MODIFIES: this
    // EFFECTS: changes the due date to the given date
    public void setDueDate(Date date) {

    }

    // REQUIRES: 0 <= hh <= 23 && 0 <= mm <= 59
    // MODIFIES: this
    // EFFECTS: changes the due time to hh:mm leaving the month, day and year the same
    public void setDueTime(int hh, int mm) {

    }

    // MODIFIES: this
    // EFFECTS: postpones the due date by one day (leaving the time the same as
    //     in the original due date) based on the rules of the Gregorian calendar.
    public void postponeOneDay() {

    }

    // MODIFIES: this
    // EFFECTS: postpones the due date by 7 days
    //     (leaving the time the same as in the original due date)
    //     based on the rules of the Gregorian calendar.
    public void postponeOneWeek() {

    }

    // EFFECTS: returns the due date
    public Date getDate() {
        return null;
    }

    // EFFECTS: returns true if due date (and due time) is passed
    public boolean isOverdue() {
        return false;
    }

    // EFFECTS: returns true if due date is at any time today, and false otherwise
    public boolean isDueToday() {
        return false;
    }

    // EFFECTS: returns true if due date is at any time tomorrow, and false otherwise
    public boolean isDueTomorrow() {
        return false;
    }

    // EFFECTS: returns true if due date is within the next seven days, irrespective of time of the day,
    // and false otherwise
    // Example, assume it is 8:00 AM on a Monday
    // then any task with due date between 00:00 AM today (Monday) and 11:59PM the following Sunday is due within a week
    public boolean isDueWithinAWeek() {
        return false;
    }

    // EFFECTS: returns a string representation of due date in the following format
    //     day-of-week month day year hour:minute
    //  example: Sun Jan 25 2019 10:30 AM
    @Override
    public String toString() {
        return null;
    }
}