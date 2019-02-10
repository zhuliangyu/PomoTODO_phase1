package model;

import model.exceptions.InvalidTimeException;
import model.exceptions.NullArgumentException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import static org.junit.jupiter.api.Assertions.*;

public class TestDueDate {
    private DueDate dueDate;


    @BeforeEach
    public void runBefore() {
        dueDate = new DueDate();
    }

    @Test
    public void testConstructorWithoutParameter() {
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.HOUR_OF_DAY, 23);
        cal.set(Calendar.MINUTE, 59);
        cal.set(Calendar.SECOND, 0);
        cal.set(Calendar.MILLISECOND, 0);
        Date testDueDate = cal.getTime();

        assertEquals(testDueDate, dueDate.getDate());

    }

    @Test
    public void testConstructorWithParameter() {
        DueDate dueDate1 = null;
        try {
            dueDate1 = new DueDate(new GregorianCalendar(2019, Calendar.JANUARY, 25, 10, 30).getTime());
        } catch (NullArgumentException e) {
            fail("It shouldn't throw exception");
        }

        assertEquals("Fri Jan 25 2019 10:30 AM", dueDate1.toString());
    }

    @Test
    public void testConstructorWithNullException() {
        DueDate dueDate1 = null;
        try {
            dueDate1 = new DueDate(null);
            fail("It didn't throw expected exception");
        } catch (NullArgumentException e) {

        }

    }

    @Test
    public void testSetDueTimeProperInput() {
        DueDate dueDate1 = null;
        try {
            dueDate1 = new DueDate(new GregorianCalendar(2019, Calendar.JANUARY, 25, 10, 30).getTime());
        } catch (NullArgumentException e) {
            e.printStackTrace();
        }

        try {
            dueDate1.setDueTime(23, 59);
        } catch (InvalidTimeException e) {
            fail("It shouldn't throw expcetion here");
        }

        assertEquals("Fri Jan 25 2019 11:59 PM", dueDate1.toString());
    }

    @Test
    public void testSetDueTimeWithException() {
        DueDate dueDate1 = null;
        try {
            dueDate1 = new DueDate(new GregorianCalendar(2019, Calendar.JANUARY, 25, 10, 30).getTime());
        } catch (NullArgumentException e) {
            e.printStackTrace();
        }

        try {
            dueDate1.setDueTime(-15, 59);
            fail("It didn't throw exception here");
        } catch (InvalidTimeException e) {
        }

        try {
            dueDate1.setDueTime(25, 59);
            fail("It didn't throw exception here");
        } catch (InvalidTimeException e) {
        }

        try {
            dueDate1.setDueTime(23, -59);
            fail("It didn't throw exception here");
        } catch (InvalidTimeException e) {
        }

        try {
            dueDate1.setDueTime(23, 70);
            fail("It didn't throw exception here");
        } catch (InvalidTimeException e) {
        }
    }

    @Test
    public void testSetDueDateNoException() {

        Date newDate = new GregorianCalendar(2019, Calendar.JANUARY, 25, 10, 30).getTime();

//        DueDate dueDate1 = null;
//        try {
//            dueDate1 = new DueDate(new GregorianCalendar(2019, Calendar.JANUARY, 25, 10, 30).getTime());
//        } catch (NullArgumentException e) {
//            e.printStackTrace();
//        }

        try {
            dueDate.setDueDate(newDate);
        } catch (NullArgumentException e) {
            fail();
        }
        assertEquals(newDate, dueDate.getDate());

    }

    @Test
    public void testSetDueDateWithException() {

        Date newDate = null;

        try {
            dueDate.setDueDate(newDate);
            fail();
        } catch (NullArgumentException e) {
        }


    }


    @Test
    public void testPostponeOneDay() {

        DueDate dueDate1 = null;
        try {
            dueDate1 = new DueDate(new GregorianCalendar(2019, Calendar.JANUARY, 25, 10, 30).getTime());
        } catch (NullArgumentException e) {
            e.printStackTrace();
        }

        dueDate1.postponeOneDay();
        assertEquals("Sat Jan 26 2019 10:30 AM", dueDate1.toString());

    }



    @Test
    public void testPostponeOneWeek() {
        DueDate dueDate1 = null;
        try {
            dueDate1 = new DueDate(new GregorianCalendar(2019, Calendar.JANUARY, 20, 10, 30).getTime());
        } catch (NullArgumentException e) {
            e.printStackTrace();
        }
        dueDate1.postponeOneWeek();
        assertEquals("Sun Jan 27 2019 10:30 AM", dueDate1.toString());

    }

    @Test
    public void testIsOverDue() {
        DueDate dueDate1 = null;
        try {
            dueDate1 = new DueDate(new GregorianCalendar(2019, Calendar.JANUARY, 20, 10, 30).getTime());
        } catch (NullArgumentException e) {
            e.printStackTrace();
        }

        LocalDateTime now = LocalDateTime.now();
        int year = now.getYear();
        int month = now.getMonthValue();
        int day = now.getDayOfMonth();
        int hour = now.getHour();
        int minute = now.getMinute();

        dueDate1.nowDate = new GregorianCalendar(year, month, day, hour, minute).getTime();
        dueDate1.isOverdue();
        assertTrue(dueDate1.isOverdue());

    }

    @Test
    public void testIsDueToday() {
        DueDate dueDate1 = null;
        try {
            dueDate1 = new DueDate(new GregorianCalendar(2019, Calendar.JANUARY, 20, 10, 30).getTime());
        } catch (NullArgumentException e) {
            e.printStackTrace();
        }

        dueDate1.nowDate = new GregorianCalendar(2019, 0, 20, 23, 50).getTime();

        assertTrue(dueDate1.isDueToday());

        dueDate1.nowDate = new GregorianCalendar(2019, 0, 10, 23, 50).getTime();

        assertFalse(dueDate1.isDueToday());
    }

    @Test
    public void testIsDueTomorrow() {
        DueDate dueDate1 = null;
        try {
            dueDate1 = new DueDate(new GregorianCalendar(2019, Calendar.JANUARY, 20, 10, 30).getTime());
        } catch (NullArgumentException e) {
            e.printStackTrace();
        }

        dueDate1.nowDate = new GregorianCalendar(2019, 0, 19, 21, 50).getTime();

        assertTrue(dueDate1.isDueTomorrow());

        dueDate1.nowDate = new GregorianCalendar(2019, 0, 4, 23, 50).getTime();

        assertFalse(dueDate1.isDueTomorrow());
    }

    @Test
    public void testIsDueWithinAWeek() {
        DueDate dueDate1 = null;
        try {
            dueDate1 = new DueDate(new GregorianCalendar(2019, Calendar.JANUARY, 7, 0, 1).getTime());
        } catch (NullArgumentException e) {
            e.printStackTrace();
        }

        dueDate1.nowDate = new GregorianCalendar(2019, Calendar.JANUARY, 7, 10, 30).getTime();
        assertTrue(dueDate1.isDueWithinAWeek());


        DueDate dueDate2 = null;
        try {
            dueDate2 = new DueDate(new GregorianCalendar(2019, Calendar.JANUARY, 13, 23, 59).getTime());
        } catch (NullArgumentException e) {
            e.printStackTrace();
        }

        dueDate2.nowDate = new GregorianCalendar(2019, 0, 7, 10, 30).getTime();

        assertTrue(dueDate2.isDueWithinAWeek());

        DueDate dueDate3 = null;
        try {
            dueDate3 = new DueDate(new GregorianCalendar(2019, Calendar.JANUARY, 6, 23, 59).getTime());
        } catch (NullArgumentException e) {
            e.printStackTrace();
        }

        dueDate3.nowDate = new GregorianCalendar(2019, 0, 7, 10, 30).getTime();

        assertFalse(dueDate3.isDueWithinAWeek());

        DueDate dueDate4 = null;
        try {
            dueDate4 = new DueDate(new GregorianCalendar(2019, Calendar.JANUARY, 14, 0, 0).getTime());
        } catch (NullArgumentException e) {
            e.printStackTrace();
        }

        dueDate4.nowDate = new GregorianCalendar(2019, 0, 7, 10, 30).getTime();

        assertFalse(dueDate4.isDueWithinAWeek());

    }


    @Test
    public void testToString() {
        DueDate dueDate1 = null;
        try {
            dueDate1 = new DueDate(new GregorianCalendar(2019, Calendar.JANUARY, 25, 10, 30).getTime());
        } catch (NullArgumentException e) {
            e.printStackTrace();
        }

        assertEquals("Fri Jan 25 2019 10:30 AM", dueDate1.toString());

        DueDate dueDate2 = null;
        try {
            dueDate2 = new DueDate(new GregorianCalendar(2019, Calendar.JANUARY, 1, 10, 30).getTime());
        } catch (NullArgumentException e) {
            e.printStackTrace();
        }

        assertEquals("Tue Jan 01 2019 10:30 AM", dueDate2.toString());

        DueDate dueDate3 = null;
        try {
            dueDate3 = new DueDate(new GregorianCalendar(2019, Calendar.JANUARY, 1, 1, 3).getTime());
        } catch (NullArgumentException e) {
            e.printStackTrace();
        }

        assertEquals("Tue Jan 01 2019 01:03 AM", dueDate3.toString());

    }

}


