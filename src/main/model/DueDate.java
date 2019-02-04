package model;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

// Note: Any library in JDK 8 may be used to implement this class, however,
//     you must not use any third-party library in your implementation
// Hint: Explore https://docs.oracle.com/javase/8/docs/api/java/util/Calendar.html

// Represents the due date of a Task
public class DueDate {

    public Date myDueDate;
    public Date nowDate;

    // MODIFIES: this
    // EFFECTS: creates a DueDate with deadline at end of day today (i.e., today at 11:59 PM)
    public DueDate() {
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.HOUR_OF_DAY, 23);
        cal.set(Calendar.MINUTE, 59);
        cal.set(Calendar.SECOND, 0);
        cal.set(Calendar.MILLISECOND, 0);
        myDueDate = cal.getTime();
        nowDate = new Date();

    }

    // REQUIRES: date != null
    // MODIFIES: this
    // EFFECTS: creates a DueDate with deadline of the given date
    public DueDate(Date date) {
        this.myDueDate = date;
        this.nowDate = new Date();


    }

    // REQUIRES: date != null
    // MODIFIES: this
    // EFFECTS: changes the due date to the given date
    public void setDueDate(Date date) {
        myDueDate = date;

    }

    // REQUIRES: 0 <= hh <= 23 && 0 <= mm <= 59
    // MODIFIES: this
    // EFFECTS: changes the due time to hh:mm leaving the month, day and year the same
    public void setDueTime(int hh, int mm) {


        Calendar cal = Calendar.getInstance();
        cal.setTime(this.getDate());
        int year = cal.get(Calendar.YEAR);
        int month = cal.get(Calendar.MONTH);
        int day = cal.get(Calendar.DAY_OF_MONTH);
        cal.set(Calendar.YEAR, year);
        cal.set(Calendar.MONTH, month);
        cal.set(Calendar.DATE, day);
        cal.set(Calendar.HOUR_OF_DAY, hh);
        cal.set(Calendar.MINUTE, mm);
        cal.set(Calendar.SECOND, 0);
        cal.set(Calendar.MILLISECOND, 0);

        myDueDate = cal.getTime();

    }

    // MODIFIES: this
    // EFFECTS: postpones the due date by one day (leaving the time the same as
    //     in the original due date) based on the rules of the Gregorian calendar.
    public void postponeOneDay() {
        this.setDueDate(postponeNdays(myDueDate, 1));
    }

    // MODIFIES: this
    // EFFECTS: postpones the due date by 7 days
    //     (leaving the time the same as in the original due date)
    //     based on the rules of the Gregorian calendar.
    public void postponeOneWeek() {
//        this.setDueDate(postponeNdays(myDueDate, 7));
        this.setDueDate(postponeNdays(myDueDate, 7));
    }

    private Date postponeNdays(Date myDate, int n) {
        Calendar c = Calendar.getInstance();
        c.setTime(myDate);
        c.add(Calendar.DATE, n);
        Date newDate = c.getTime();
        return newDate;
    }

    // EFFECTS: returns the due date
    public Date getDate() {
        return myDueDate;
    }

    // EFFECTS: returns true if due date (and due time) is passed
    public boolean isOverdue() {
//        return myDueDate.before(nowDate);
        return nowDate.after(myDueDate);
    }


    // EFFECTS: returns true if due date is at any time today, and false otherwise
    public boolean isDueToday() {

        return compareDate(nowDate, myDueDate);
    }

    // EFFECTS: returns true if two dates are the same at year month and day, and false otherwise
    private boolean compareDate(Date newDate, Date myDueDate) {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        String stringNowDate = formatter.format(newDate);
        String stringDueDate = formatter.format(myDueDate);
        return stringDueDate.equals(stringNowDate);
    }

    // EFFECTS: returns true if due date is at any time tomorrow, and false otherwise
    public boolean isDueTomorrow() {
        Date dateTomorrow = postponeNdays(nowDate, 1);
        return compareDate(myDueDate, dateTomorrow);
    }

    // EFFECTS: returns true if due date is within the next seven days, irrespective of time of the day,
    // and false otherwise
    // Example, assume it is 8:00 AM on a Monday
    // then any task with due date between 00:00 AM today (Monday) and 11:59PM the following Sunday is due within a week
    public boolean isDueWithinAWeek() {

        Date nowAfterOneWeek = postponeNdays(nowDate, 8);
        Calendar cal = Calendar.getInstance();
        cal.setTime(nowAfterOneWeek);
        int year = cal.get(Calendar.YEAR);
        int month = cal.get(Calendar.MONTH);
        int day = cal.get(Calendar.DAY_OF_MONTH);
        cal.set(Calendar.YEAR, year);
        cal.set(Calendar.MONTH, month);
        cal.set(Calendar.DATE, day);
        cal.set(Calendar.HOUR_OF_DAY, 0);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.SECOND, 0);
        cal.set(Calendar.MILLISECOND, 0);
        nowAfterOneWeek = cal.getTime();
        return (myDueDate.before(nowAfterOneWeek));
    }

    // EFFECTS: returns a string representation of due date in the following format
    //     day-of-week month day year hour:minute
    //  example: Fri Jan 25 2019 10:30 AM
    @Override
    public String toString() {

        SimpleDateFormat formatter = new SimpleDateFormat("EEE MMM d yyyy h:mm a");
        String stringDueDate = formatter.format(myDueDate);
        return stringDueDate;
    }


    //Effect: convert date to String; Format: yyyymmdd
//    private String dateTpYMD(Date date) {
//        Calendar cal = Calendar.getInstance();
//        cal.setTime(myDueDate);
//        String year = String.valueOf(cal.get(Calendar.YEAR));
//        String month = String.valueOf(cal.get(Calendar.MONTH));
//        String day = String.valueOf(cal.get(Calendar.DAY_OF_MONTH));
//        return year + month + day;
//    }
}