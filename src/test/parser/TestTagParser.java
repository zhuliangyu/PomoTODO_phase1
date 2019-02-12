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
    void testConstructorWithoutTag() {
        Task task = null;
        try {
            task = new Task(null);
            fail();
        } catch (Exception e) {
        }

    }


    @Test
    void test01TaskNoException() {
        //Register for the course. ## cpsc210; tomorrow; important; urgent; in progress

        //{
        //	Description: Register for the course.
        //	Due date: Fri Feb 08 2019 11:59 PM
        //	Status: IN PROGRESS
        //	Priority: IMPORTANT & URGENT
        //	Tags: #cpsc210
        //}

        Task task = null;
        try {
            task = new Task("Register for the course. ## cpsc210; tomorrow; important; urgent; in progress");
        } catch (EmptyStringException e) {
            fail();
        } catch (Exception e) {
            fail();
        }

        assertEquals("Register for the course. ", task.getDescription());
        assertEquals(getTomorrowDueDate().toString(), task.getDueDate().toString());
        assertEquals("IN PROGRESS", task.getStatus().toString());
        assertEquals("IMPORTANT & URGENT", task.getPriority().toString());
        assertEquals("[#cpsc210]", task.getTags().toString());

        System.out.println(task);

    }

    @Test
    void test02NoException() {
        TagParser tp = new TagParser();
        Task task = null;

        try {
            task = new Task("task1 ## today; tag1; tomorrow");
        } catch (EmptyStringException e) {
            fail();
        } catch (Exception e) {
            fail();
        }


        assertEquals("task1 ", task.getDescription());
        assertEquals(getTodayDueday().toString(), task.getDueDate().toString());
        assertEquals("TODO", task.getStatus().toString());
        assertEquals("DEFAULT", task.getPriority().toString());
        assertEquals("[#tag1, #tomorrow]", task.getTags().toString());

        System.out.println(task);

    }

    @Test
    void test03NoException() {
        TagParser tp = new TagParser();
        Task task = null;

        try {
            task = new Task("task2 ## in progress; tag2; up next");
        } catch (EmptyStringException e) {
            fail();
        } catch (Exception e) {
            fail();
        }


        assertEquals("task2 ", task.getDescription());
        assertEquals(null, task.getDueDate());
        assertEquals("IN PROGRESS", task.getStatus().toString());
        assertEquals("DEFAULT", task.getPriority().toString());
        assertEquals("[#up next, #tag2]", task.getTags().toString());

        System.out.println(task);

    }

    @Test
    void test04NoException() {
        TagParser tp = new TagParser();
        Task task = null;

        try {
            task = new Task("task3 ## important; tag3; important");
        } catch (EmptyStringException e) {
            fail();
        } catch (Exception e) {
            fail();
        }

        assertEquals("task3 ", task.getDescription());
        assertEquals(null, task.getDueDate());
        assertEquals("TODO", task.getStatus().toString());
        assertEquals("IMPORTANT", task.getPriority().toString());
        assertEquals("[#tag3]", task.getTags().toString());

        System.out.println(task);

    }

    @Test
    void test05TaskNoException() {
        TagParser tp = new TagParser();
        Task task = null;

        try {
            task = new Task("task4 ## tomorrow; tag4; tag5; tomorrow");
        } catch (Exception e) {
            fail();
        }


        assertEquals("task4 ", task.getDescription());
        assertEquals(getTomorrowDueDate().toString(), task.getDueDate().toString());
        assertEquals("TODO", task.getStatus().toString());
        assertEquals("DEFAULT", task.getPriority().toString());
        assertEquals("[#tag4, #tag5]", task.getTags().toString());

        System.out.println(task);

    }

    @Test
    void test06TaskNoException() {
        TagParser tp = new TagParser();
        Task task = null;

        try {
            task = new Task("This description has no tags.");
        } catch (EmptyStringException e) {
            fail();
        } catch (Exception e){

        }


        assertEquals("This description has no tags.", task.getDescription());
        assertEquals(null, task.getDueDate());
        assertEquals("TODO", task.getStatus().toString());
        assertEquals("DEFAULT", task.getPriority().toString());
        assertEquals("[]", task.getTags().toString());

        System.out.println(task);

    }

    @Test
    void test07TaskNoException() {
        TagParser tp = new TagParser();
        Task task = null;

        try {
            task = new Task("       Task description ## tag1; tag2; tag3");
        } catch (EmptyStringException e) {
            fail();
        } catch (Exception e){

        }


        assertEquals("       Task description ", task.getDescription());
        assertEquals(null, task.getDueDate());
        assertEquals("TODO", task.getStatus().toString());
        assertEquals("DEFAULT", task.getPriority().toString());
        assertEquals("[#tag1, #tag2, #tag3]", task.getTags().toString());

        System.out.println(task);

    }

    @Test
    void test08TaskNoException() {
        TagParser tp = new TagParser();
        Task task = null;

        try {
            task = new Task("Task description with tags but not delimiter. tag1; tag2");
        } catch (EmptyStringException e) {
            fail();
        } catch (Exception e){

        }

        assertEquals("Task description with tags but not delimiter. tag1; tag2", task.getDescription());
        assertEquals(null, task.getDueDate());
        assertEquals("TODO", task.getStatus().toString());
        assertEquals("DEFAULT", task.getPriority().toString());
        assertEquals("[]", task.getTags().toString());

        System.out.println(task);

    }

    @Test
    void test09TaskNoException() {
        TagParser tp = new TagParser();
        Task task = null;

        try {
            task = new Task("This description has just tag delimiter. ## ");
        } catch (EmptyStringException e) {
            fail();
        } catch (Exception e){

        }

        assertEquals("This description has just tag delimiter. ", task.getDescription());
        assertEquals(null, task.getDueDate());
        assertEquals("TODO", task.getStatus().toString());
        assertEquals("DEFAULT", task.getPriority().toString());
        assertEquals("[]", task.getTags().toString());

        System.out.println(task);

    }

    @Test
    void test10TaskNoException() {
        TagParser tp = new TagParser();
        Task task = null;

        try {
            task = new Task("Task description ## tag1; TAG1; tag1");
        } catch (EmptyStringException e) {
            fail();
        } catch (Exception e){

        }

        assertEquals("Task description ", task.getDescription());
        assertEquals(null, task.getDueDate());
        assertEquals("TODO", task.getStatus().toString());
        assertEquals("DEFAULT", task.getPriority().toString());
        assertEquals("[#tag1]", task.getTags().toString());

        System.out.println(task);

    }

    @Test
    void test11TaskNoException() {
        //Task t = new Task("Some description ## tag1     ; tag 1;     tag1;    ;   tag 2");
//        {
//            Description: Some description
//            Due date:
//            Status: TO DO
//            Priority: DEFAULT
//            Tags: #tag1, #tag 1, #tag 2
//        }
        TagParser tp = new TagParser();
        Task task = null;

        try {
            task = new Task("Some description ## tag1     ; tag 1;     tag1;    ;   tag 2");
        } catch (EmptyStringException e) {
            fail();
        } catch (Exception e){

        }

        assertEquals("Some description ", task.getDescription());
        assertEquals(null, task.getDueDate());
        assertEquals("TODO", task.getStatus().toString());
        assertEquals("DEFAULT", task.getPriority().toString());
        assertEquals("[#tag1, #tag 1, #tag 2]", task.getTags().toString());

        System.out.println(task);

    }

    @Test
    void test12TaskNoException() {
        //Task t = new Task("Some description ## tag1;today;urgent;in progress;important");
//        {
//            Description: Some description
//            Due date: Sat Feb 09 2019 11:59 PM
//            Status: IN PROGRESS
//            Priority: IMPORTANT & URGENT
//            Tags: #tag1
//        }
        TagParser tp = new TagParser();
        Task task = null;

        try {
            task = new Task("Some Some description ## tag1;today;urgent;in progress;important");
        } catch (EmptyStringException e) {
            fail();
        } catch (Exception e){

        }

        assertEquals("Some Some description ", task.getDescription());
        assertEquals(getTodayDueday().toString(), task.getDueDate().toString());
        assertEquals("IN PROGRESS", task.getStatus().toString());
        assertEquals("IMPORTANT & URGENT", task.getPriority().toString());
        assertEquals("[#tag1]", task.getTags().toString());

        System.out.println(task);

    }

    @Test
    void test13TaskNoException() {
//        Task t = new Task("Some description ## tag1;today;up next;tomorrow;in progress");
//        {
//            Description: Some description
//            Due date: Sat Feb 09 2019 11:59 PM
//            Status: UP NEXT
//            Priority: DEFAULT
//            Tags: #tag1, #tomorrow, #in progress
//        }


        TagParser tp = new TagParser();
        Task task = null;

        try {
            task = new Task("Some description ## tag1;today;up next;tomorrow;in progress");
        } catch (EmptyStringException e) {
            fail();
        } catch (Exception e){

        }

        assertEquals("Some description ", task.getDescription());
        assertEquals(getTodayDueday().toString(), task.getDueDate().toString());
        assertEquals("UP NEXT", task.getStatus().toString());
        assertEquals("DEFAULT", task.getPriority().toString());
        assertEquals("[#tag1, #in progress, #tomorrow]", task.getTags().toString());

        System.out.println(task);

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
