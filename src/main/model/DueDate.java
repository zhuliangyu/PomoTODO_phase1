package model;

import model.exceptions.InvalidTimeException;
import model.exceptions.NullArgumentException;

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

    // MODIFIES: this
    // EFFECTS: creates a DueDate with deadline of the given date
    // if date == null, return NullPointerException
    public DueDate(Date date) throws NullArgumentException {
        if (date == null) {
            throw new NullArgumentException("DueDate class constructor Date is null");
        } else {
            this.myDueDate = date;
        }
        this.nowDate = new Date();
    }

    // MODIFIES: this
    // EFFECTS: changes the due date to the given date
    // if date == null, return NullPointerException
    public void setDueDate(Date date) throws NullArgumentException {
        if (date == null) {
            throw new NullArgumentException("DueDate class setDueDate Date is null");
        }
        myDueDate = date;
    }


    // MODIFIES: this
    // EFFECTS: changes the due time to hh:mm leaving the month, day and year the same
// if  input is not in the range of 0 <= hh <= 23 && 0 <= mm <= 59, it will throw InvalidTimeException
    public void setDueTime(int hh, int mm) throws InvalidTimeException {
        if (hh < 0 || hh > 23) {
            throw new InvalidTimeException("DueDate class setDueTime hh is invalid");
        }
        if (mm < 0 || mm > 59) {
            throw new InvalidTimeException("DueDate class setDueTime mm is invalid");
        }
        this.myDueDate = resetDate(getDate(), 0, hh, mm, 0, 0);
    }

    // MODIFIES: this
    // EFFECTS: postpones the due date by one day (leaving the time the same as
    //     in the original due date) based on the rules of the Gregorian calendar.
    public void postponeOneDay() {
        try {
            this.setDueDate(postponeNdays(myDueDate, 1));
        } catch (NullArgumentException e) {
            e.printStackTrace();
        }
    }

    // MODIFIES: this
    // EFFECTS: postpones the due date by 7 days
    //     (leaving the time the same as in the original due date)
    //     based on the rules of the Gregorian calendar.
    public void postponeOneWeek() {
        try {
            this.setDueDate(postponeNdays(myDueDate, 7));
        } catch (NullArgumentException e) {
            e.printStackTrace();
        }
    }


    public Date postponeNdays(Date myDate, int n) {
        // it is a help method for personal usage
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

    private boolean compareDate(Date newDate, Date myDueDate) {
        // EFFECTS: returns true if two dates are the same at year month and day, and false otherwise

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
        Calendar cal = Calendar.getInstance();

        //if today is Jan 7, it will reset the date to Jan 13 (a week after) 00:00:00
        Date nowAfterOneWeek;
        nowAfterOneWeek = resetDate(nowDate, 7,
                cal.getMinimum(Calendar.HOUR_OF_DAY),
                cal.getMinimum(Calendar.MINUTE),
                cal.getMinimum(Calendar.SECOND),
                cal.getMinimum(Calendar.MILLISECOND));


        //if today is Jan 7, it will reset the date to Jan 6(oen day before) 23:59:59
        Date nowTruncated;
        nowTruncated = resetDate(nowDate, -1,
                cal.getMaximum(Calendar.HOUR_OF_DAY),
                cal.getMaximum(Calendar.MINUTE),
                cal.getMaximum(Calendar.SECOND),
                cal.getMaximum(Calendar.MILLISECOND));

        return (myDueDate.before(nowAfterOneWeek) && myDueDate.after(nowTruncated));
    }

    private Date resetDate(Date nowDate, int postponeDays, int hour, int min, int sec, int millSec) {
        //reset the date according to input of days hour min sec and millsec
        Date nowTruncated;
        Calendar cal2 = Calendar.getInstance();
        cal2.setTime(postponeNdays(nowDate, postponeDays));
        int year2 = cal2.get(Calendar.YEAR);
        int month2 = cal2.get(Calendar.MONTH);
        int day2 = cal2.get(Calendar.DAY_OF_MONTH);
        cal2.set(Calendar.YEAR, year2);
        cal2.set(Calendar.MONTH, month2);
        cal2.set(Calendar.DATE, day2);
        cal2.set(Calendar.HOUR_OF_DAY, hour);
        cal2.set(Calendar.MINUTE, min);
        cal2.set(Calendar.SECOND, sec);
        cal2.set(Calendar.MILLISECOND, millSec);
        nowTruncated = cal2.getTime();
        return nowTruncated;
    }

    // EFFECTS: returns a string representation of due date in the following format
    //     day-of-week month day year hour:minute
    //  example: Fri Jan 25 2019 10:30 AM
    @Override
    public String toString() {

        SimpleDateFormat formatter = new SimpleDateFormat("EEE MMM dd yyyy hh:mm a");
        String stringDueDate = formatter.format(myDueDate);
        return stringDueDate;
    }


}