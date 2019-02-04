package model;

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

//        SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMddhh:mm");
//        String stringNowDate = formatter.format(dueDate.getDate());
//
//        Calendar cal = Calendar.getInstance();
//        cal.setTime(dueDate.getDate());
////        String year = String.valueOf(cal.get(Calendar.YEAR));
//        int year = cal.get(Calendar.YEAR);
////        String month = String.valueOf(cal.get(Calendar.MONTH)+1);
//        int month = cal.get(Calendar.MONTH)+1;
////        String day = String.valueOf(cal.get(Calendar.DAY_OF_MONTH));
//        int day = cal.get(Calendar.DAY_OF_MONTH);
//        int hh = 11;
//        int mm = 59;

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
        DueDate dueDate1 = new DueDate(new GregorianCalendar(2019, Calendar.JANUARY, 25, 10, 30).getTime());
        assertEquals("Fri Jan 25 2019 10:30 AM", dueDate1.toString());
    }

    @Test
    public void testSetDueTime() {
        DueDate dueDate1 = new DueDate(new GregorianCalendar(2019, Calendar.JANUARY, 25, 10, 30).getTime());
        dueDate1.setDueTime(23, 59);
        assertEquals("Fri Jan 25 2019 11:59 PM", dueDate1.toString());
    }

    @Test
    public void testPostponeOneDay() {

        DueDate dueDate1 = new DueDate(new GregorianCalendar(2019, Calendar.JANUARY, 25, 10, 30).getTime());
        dueDate1.postponeOneDay();
        assertEquals("Sat Jan 26 2019 10:30 AM", dueDate1.toString());

    }

    @Test
    public void testPostponeOneWeek() {
        DueDate dueDate1 = new DueDate(new GregorianCalendar(2019, Calendar.JANUARY, 20, 10, 30).getTime());
        dueDate1.postponeOneWeek();
        assertEquals("Sun Jan 27 2019 10:30 AM", dueDate1.toString());

    }

    @Test
    public void testIsOverDue() {
        DueDate dueDate1 = new DueDate(new GregorianCalendar(2019, Calendar.JANUARY, 20, 10, 30).getTime());

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
        DueDate dueDate1 = new DueDate(new GregorianCalendar(2019, Calendar.JANUARY, 20, 10, 30).getTime());

        dueDate1.nowDate = new GregorianCalendar(2019, 0, 20, 23, 50).getTime();

        assertTrue(dueDate1.isDueToday());

        dueDate1.nowDate = new GregorianCalendar(2019, 0, 10, 23, 50).getTime();

        assertFalse(dueDate1.isDueToday());
    }

    @Test
    public void testIsDueTomorrow() {
        DueDate dueDate1 = new DueDate(new GregorianCalendar(2019, Calendar.JANUARY, 20, 10, 30).getTime());

        dueDate1.nowDate = new GregorianCalendar(2019, 0, 19, 21, 50).getTime();

        assertTrue(dueDate1.isDueTomorrow());

        dueDate1.nowDate = new GregorianCalendar(2019, 0, 4, 23, 50).getTime();

        assertFalse(dueDate1.isDueTomorrow());
    }

    @Test
    public void testIsDueWithinAWeek() {
        DueDate dueDate1 = new DueDate(new GregorianCalendar(2019, Calendar.JANUARY, 7, 0, 0).getTime());

        dueDate1.nowDate = new GregorianCalendar(2019, 0, 7, 8, 50).getTime();

        assertTrue(dueDate1.isDueWithinAWeek());

        DueDate dueDate2 = new DueDate(new GregorianCalendar(2019, Calendar.JANUARY, 13, 23, 59).getTime());

        dueDate2.nowDate = new GregorianCalendar(2019, 0, 7, 8, 50).getTime();

        assertTrue(dueDate2.isDueWithinAWeek());

        DueDate dueDate3 = new DueDate(new GregorianCalendar(2019, Calendar.JANUARY, 24, 23, 59).getTime());

        dueDate3.nowDate = new GregorianCalendar(2019, 0, 7, 8, 50).getTime();

        assertFalse(dueDate3.isDueWithinAWeek());

    }

    @Test
    public void testToString() {
        DueDate dueDate1 = new DueDate(new GregorianCalendar(2019, Calendar.JANUARY, 25, 10, 30).getTime());

        assertEquals("Fri Jan 25 2019 10:30 AM", dueDate1.toString());

    }

}


