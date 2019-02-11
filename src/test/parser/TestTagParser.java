package parser;

import model.DueDate;
import model.Task;
import model.exceptions.EmptyStringException;
import model.exceptions.InvalidTimeException;
import model.exceptions.NullArgumentException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import parsers.TagParser;
import parsers.exceptions.ParsingException;

import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import static org.junit.jupiter.api.Assertions.*;

public class TestTagParser {

    private DueDate today;
    private DueDate tomorrow;

    @BeforeEach
    void runBefore() {

//        today = new DueDate();
//        tomorrow = new DueDate(today.postponeNdays(new Date(), 1));
//        today = new GregorianCalendar(2019, Calendar.FEBRUARY, 7, 23, 59).getTime();
//        tomorrow = new GregorianCalendar(2019, Calendar.FEBRUARY, 8, 23, 59).getTime();
    }

    @Test
    void test01NoException() {
        //Register for the course. ## cpsc210; tomorrow; important; urgent; in progress

        //{
        //	Description: Register for the course.
        //	Due date: Fri Feb 08 2019 11:59 PM
        //	Status: IN PROGRESS
        //	Priority: IMPORTANT & URGENT
        //	Tags: #cpsc210
        //}

        TagParser tp = new TagParser();
        Task task = null;

        try {
            task = new Task();
        } catch (EmptyStringException e) {
            e.printStackTrace();
        }

        try {
            tp.parse("Register for the course. ## cpsc210; tomorrow; important; urgent; in progress", task);
        } catch (ParsingException e) {
            fail();
        }

        assertEquals("Register for the course.", task.getDescription());
        assertEquals(getTomorrowDueDate().toString(), task.getDueDate().toString());
        assertEquals("IN PROGRESS", task.getStatus().toString());
        assertEquals("IMPORTANT & URGENT", task.getPriority().toString());
        assertEquals("[#cpsc210]", task.getTags().toString());

    }

    @Test
    void test02NoException() {
        TagParser tp = new TagParser();
        Task task = null;

        try {
            task = new Task();
        } catch (EmptyStringException e) {
            e.printStackTrace();
        }

        try {
            tp.parse("task1 ## today; tag1; tomorrow", task);
        } catch (ParsingException e) {
            fail();
        }

        assertEquals("task1", task.getDescription());
        assertEquals(getTodayDueday().toString(), task.getDueDate().toString());
        assertEquals("TODO", task.getStatus().toString());
        assertEquals("DEFAULT", task.getPriority().toString());
        assertEquals("[#tag1, #tomorrow]", task.getTags().toString());
    }

    @Test
    void test03NoException() {
        TagParser tp = new TagParser();
        Task task = null;

        try {
            task = new Task();
        } catch (EmptyStringException e) {
            e.printStackTrace();
        }

        try {
            tp.parse("task2 ## in progress; tag2; up next", task);
        } catch (ParsingException e) {
            fail();
        }

        assertEquals("task2", task.getDescription());
        assertEquals(null, task.getDueDate());
        assertEquals("IN PROGRESS", task.getStatus().toString());
        assertEquals("DEFAULT", task.getPriority().toString());
        assertEquals("[#up next, #tag2]", task.getTags().toString());
    }

    @Test
    void test04NoException() {
        TagParser tp = new TagParser();
        Task task = null;

        try {
            task = new Task();
        } catch (EmptyStringException e) {
            e.printStackTrace();
        }

        try {
            tp.parse("task3 ## important; tag3; important", task);
        } catch (ParsingException e) {
            fail();
        }

        assertEquals("task3", task.getDescription());
        assertEquals(null, task.getDueDate());
        assertEquals("TODO", task.getStatus().toString());
        assertEquals("IMPORTANT", task.getPriority().toString());
        assertEquals("[#tag3]", task.getTags().toString());
    }

    @Test
    void test04Task() {
        TagParser tp = new TagParser();
        Task task = null;

        try {
            task = new Task("task3 ## important; tag3; important");
        } catch (EmptyStringException e) {
            fail();
        }

        assertEquals("task3", task.getDescription());
        assertEquals(null, task.getDueDate());
        assertEquals("TODO", task.getStatus().toString());
        assertEquals("IMPORTANT", task.getPriority().toString());
        assertEquals("[#tag3]", task.getTags().toString());
    }

    @Test
    void test05NoException() {
        TagParser tp = new TagParser();
        Task task = null;

        try {
            task = new Task();
        } catch (EmptyStringException e) {
            e.printStackTrace();
        }

        try {
            tp.parse("task4 ## tomorrow; tag4; tag5; tomorrow", task);
        } catch (ParsingException e) {
            fail();
        }

        assertEquals("task4", task.getDescription());
        assertEquals(getTomorrowDueDate().toString(), task.getDueDate().toString());
        assertEquals("TODO", task.getStatus().toString());
        assertEquals("DEFAULT", task.getPriority().toString());
        assertEquals("[#tag4, #tag5]", task.getTags().toString());
    }

    @Test
    void test05Task() {
        TagParser tp = new TagParser();
        Task task = null;

        try {
            task = new Task("task4 ## tomorrow; tag4; tag5; tomorrow");
        } catch (EmptyStringException e) {
            e.printStackTrace();
        }


        assertEquals("task4", task.getDescription());
        assertEquals(getTomorrowDueDate().toString(), task.getDueDate().toString());
        assertEquals("TODO", task.getStatus().toString());
        assertEquals("DEFAULT", task.getPriority().toString());
        assertEquals("[#tag4, #tag5]", task.getTags().toString());
    }
























    private DueDate getTodayDueday() {
        // today 23:59:59
        Calendar cal = new GregorianCalendar();
        // reset hour, minutes, seconds and millis
        cal.set(Calendar.HOUR_OF_DAY, cal.getMaximum(Calendar.HOUR_OF_DAY));
        cal.set(Calendar.MINUTE, cal.getMaximum(Calendar.MINUTE));
        cal.set(Calendar.SECOND, cal.getMaximum(Calendar.SECOND));
        cal.set(Calendar.MILLISECOND, cal.getMaximum(Calendar.MILLISECOND));
        return new DueDate(cal.getTime());

    }

    private DueDate getTomorrowDueDate() {
        // tomorrow 23:59:59
        Calendar cal = new GregorianCalendar();
        // reset hour, minutes, seconds and millis
        cal.set(Calendar.HOUR_OF_DAY, cal.getMaximum(Calendar.HOUR_OF_DAY));
        cal.set(Calendar.MINUTE, cal.getMaximum(Calendar.MINUTE));
        cal.set(Calendar.SECOND, cal.getMaximum(Calendar.SECOND));
        cal.set(Calendar.MILLISECOND, cal.getMaximum(Calendar.MILLISECOND));

        cal.add(Calendar.DAY_OF_MONTH, 1);

        return new DueDate(cal.getTime());
    }

}
