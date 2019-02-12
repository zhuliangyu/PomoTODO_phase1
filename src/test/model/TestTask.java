package model;

import model.exceptions.EmptyStringException;
import model.exceptions.InvalidPriorityLevelException;
import model.exceptions.NullArgumentException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import parsers.exceptions.ParsingException;

import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

public class TestTask {

    private Task task;


    @BeforeEach
    public void runBefore() {
        try {
            task = new Task("test");
        } catch (EmptyStringException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testConstructorWithoutException() {
        try {
            task = new Task("test ## tomorrow; tag4; tag5; tomorrow");
        } catch (EmptyStringException e) {
            fail("it shouldn't throw exception");
        } catch (Exception e) {
            fail();
        }
        assertEquals("test ", task.getDescription());
        //task is set to have no due date,
        //status of 'To Do', and default priority level (i.e., not important nor urgent)

        assertEquals("TODO", task.getStatus().toString());
        assertEquals("DEFAULT", task.getPriority().toString());
//        assertEquals("DEFAULT",task.getDueDate().toString());


    }

    @Test
    public void testConstructorWithEmptyStringException() {
        try {
            task = new Task(null);
            fail("it didn't throw exception");
        } catch (EmptyStringException e) {
        } catch (Exception e) {
        }
    }

    @Test
    public void testConstructorWithEmptyStringException2() {
        try {
            task = new Task("");
            fail("it didn't throw exception");
        } catch (EmptyStringException e) {
        } catch (Exception e) {
        }
    }



    @Test
    public void testAddTagNoException() {

        try {
            task.addTag("test");
            task.addTag("test");
            assertEquals("[#test]", task.getTags().toString());
            task.addTag("test1");
            assertEquals("[#test, #test1]", task.getTags().toString());
        } catch (EmptyStringException e) {
            fail("It should be excepton here");
        }

    }

    @Test
    public void testAddTagWithException() {
        try {
            task.addTag("");
            fail("No exception throw");
        } catch (EmptyStringException e) {

        }

        try {
            task.addTag(null);
            fail("No exception throw");
        } catch (EmptyStringException e) {
        }

    }


    @Test
    public void testRemoveTagNoException() {

        try {
            task.addTag("test");
            task.addTag("test1");
            assertEquals("[#test, #test1]", task.getTags().toString());

            task.removeTag("test");
            assertEquals("[#test1]", task.getTags().toString());

            task.removeTag("te");
            assertEquals("[#test1]", task.getTags().toString());
        } catch (EmptyStringException e) {
            fail("It should be exception here");
        }
    }

    @Test
    public void testRemoveTagWithException() {
        try {
            task.removeTag("");
            fail("No exception throw");
        } catch (EmptyStringException e) {

        }

        try {
            task.removeTag(null);
            fail("No exception throw");
        } catch (EmptyStringException e) {
        }

    }

    @Test
    public void testGetTags() {
        try {
            task.addTag("test");
        } catch (EmptyStringException e) {
            e.printStackTrace();
        }
        try {
            task.addTag("test1");
        } catch (EmptyStringException e) {
            e.printStackTrace();
        }
        assertEquals("[#test, #test1]", task.getTags().toString());
        assertEquals(2, task.getTags().size());

        Set<Tag> unmodifiedSet = task.getTags();
        try {
            unmodifiedSet.add(new Tag("asdasd"));
            fail("The return Set is modified");
        } catch (UnsupportedOperationException | EmptyStringException e) {

        }
        assertEquals(2, unmodifiedSet.size());

    }

    @Test
    public void testSetPriorityNoException() {
        try {
            task.addTag("test");
            task.addTag("test1");
        } catch (EmptyStringException e) {
            e.printStackTrace();
        }

        try {
            task.setPriority(new Priority(1));
        } catch (NullArgumentException | InvalidPriorityLevelException e) {
            fail();
        }

        assertEquals("IMPORTANT & URGENT", task.getPriority().toString());
    }

    @Test
    public void testSetPriorityWithException() {
        try {
            task.addTag("test");
            task.addTag("test1");
        } catch (EmptyStringException e) {
            e.printStackTrace();
        }

        try {
            task.setPriority(null);
            fail();
        } catch (NullArgumentException e) {
        }
    }

    @Test
    public void testGetStatus() {
        try {
            task.addTag("test");
            task.addTag("test1");
        } catch (EmptyStringException e) {
            e.printStackTrace();
        }

        try {
            task.setStatus(Status.IN_PROGRESS);
        } catch (NullArgumentException e) {
            e.printStackTrace();
        }
        assertEquals("IN PROGRESS", task.getStatus().toString());
    }


    @Test
    public void testGetSetDescription() {

        try {
            task.setDescription("test");
        } catch (EmptyStringException e) {
            fail();
        }
        assertEquals("test", task.getDescription());
    }

    @Test
    public void testGetSetDescriptionWithException() {

        try {
            task.setDescription("");
            fail();
        } catch (EmptyStringException e) {
        }

        try {
            task.setDescription(null);
            fail();
        } catch (EmptyStringException e) {
        }
    }

    @Test
    public void testGetSetDueDate() {

        try {
            task.setDueDate(new DueDate(new GregorianCalendar(2019, Calendar.JANUARY, 25, 10, 30).getTime()));
        } catch (NullArgumentException e) {
            e.printStackTrace();
        }
        assertEquals("Fri Jan 25 2019 10:30 AM", task.getDueDate().toString());
    }


    @Test
    public void testContainsTagNoException() {
        try {
            task.addTag("test");
            task.addTag("test1");
        } catch (EmptyStringException e) {
            e.printStackTrace();
        }

        assertEquals("[#test, #test1]", task.getTags().toString());

        try {
            assertEquals(true, task.containsTag("test"));
            assertEquals(true, task.containsTag("test1"));
            assertEquals(false, task.containsTag("test2"));
        } catch (EmptyStringException e) {
            fail();
        }

    }

    @Test
    public void testContainsTagWithException() {
        try {
            task.addTag("test");
            task.addTag("test1");
        } catch (EmptyStringException e) {
            e.printStackTrace();
        }

        assertEquals("[#test, #test1]", task.getTags().toString());

        try {
            assertEquals(true, task.containsTag(""));
            fail();
        } catch (EmptyStringException e) {
        }
        try {
            assertEquals(true, task.containsTag(null));
            fail();
        } catch (EmptyStringException e) {
        }

    }

    @Test
    public void testSetStatusNoException(){
        try {
            task.setStatus(Status.IN_PROGRESS);
        } catch (NullArgumentException e) {
            fail();
        }

        assertEquals(Status.IN_PROGRESS.toString(), task.getStatus().toString());
    }

    @Test
    public void testSetStatusWithException(){
        try {
            task.setStatus(null);
            fail();
        } catch (NullArgumentException e) {

        }

    }

    @Test
    public void testToString() {

        try {
            task.setDescription("Read collaboration policy of the term project");
        } catch (EmptyStringException e) {
            e.printStackTrace();
        }
        try {
            task.setDueDate(new DueDate(new GregorianCalendar(2019, Calendar.FEBRUARY, 2, 11, 59).getTime()));
        } catch (NullArgumentException e) {
            e.printStackTrace();
        }
        try {
            task.setStatus(Status.IN_PROGRESS);
        } catch (NullArgumentException e) {
            e.printStackTrace();
        }
        try {
            task.setPriority(new Priority(1));
        } catch (NullArgumentException e) {
            e.printStackTrace();
        } catch (InvalidPriorityLevelException e) {
            e.printStackTrace();
        }
        try {
            task.addTag("cpsc210");
            task.addTag("project");

        } catch (EmptyStringException e) {
            e.printStackTrace();
        }

        assertEquals("{\n" +
                "\tDescription: Read collaboration policy of the term project \n" +
                "\tDue date: Sat Feb 02 2019 11:59 AM \n" +
                "\tStatus: IN PROGRESS \n" +
                "\tPriority: IMPORTANT & URGENT \n" +
                "\tTags: #project, #cpsc210\n" +
                "}", task.toString());

    }

//    @Test
//    public void testEqualsSameDescriptionDifferentDueDay() {
//        Task task = null;
//        try {
//            task = new Task("test");
//        } catch (EmptyStringException e) {
//            e.printStackTrace();
//        }
//
//        try {
//            task.setDueDate(new DueDate(new GregorianCalendar(2019, Calendar.JANUARY, 25, 10, 30).getTime()));
//        } catch (NullArgumentException e) {
//            e.printStackTrace();
//        }
//
//
//        Task task2 = null;
//        try {
//            task2 = new Task();
//        } catch (EmptyStringException e) {
//            e.printStackTrace();
//        }
//
//        try {
//            task2.setDueDate(new DueDate(new GregorianCalendar(2019, Calendar.JANUARY, 26, 10, 30).getTime()));
//        } catch (NullArgumentException e) {
//            e.printStackTrace();
//        }
//        assertFalse(task.equals(task2));
//    }

    @Test
    public void testEqualsSameDescriptionSameDueDay() {
        Task task = null;
        try {
            task = new Task("test##");
        } catch (EmptyStringException e) {
            fail();
        } catch (Exception e) {
            fail();
        }

        try {
            task.setDueDate(new DueDate(new GregorianCalendar(2019, Calendar.JANUARY, 25, 10, 30).getTime()));
        } catch (NullArgumentException e) {
            e.printStackTrace();
        }
        Task task2 = null;
        try {
            task2 = new Task("test##");
        } catch (EmptyStringException e) {
            fail();
        } catch (Exception e) {
            fail();
        }

        try {
            task2.setDueDate(new DueDate(new GregorianCalendar(2019, Calendar.JANUARY, 25, 10, 30).getTime()));
        } catch (NullArgumentException e) {
            e.printStackTrace();
        }
        assertTrue(task.equals(task2));
    }

    @Test
    public void testEqualsDiffDescriptionDiffDueDay() {
        Task task = null;
        try {
            task = new Task("test1##");
        } catch (EmptyStringException e) {
            fail();
        } catch (Exception e) {
            fail();
        }

        try {
            task.setDueDate(new DueDate(new GregorianCalendar(2019, Calendar.JANUARY, 25, 10, 30).getTime()));
        } catch (NullArgumentException e) {
            e.printStackTrace();
        }

        Task task2 = null;
        try {
            task2 = new Task("test##");
        } catch (EmptyStringException e) {
            fail();
        } catch (Exception e) {
            fail();
        }

        try {
            task2.setDueDate(new DueDate(new GregorianCalendar(2019, Calendar.JANUARY, 26, 10, 30).getTime()));
        } catch (NullArgumentException e) {
            e.printStackTrace();
        }
        assertFalse(task.equals(task2));
    }

//
//    @Test
//    public void testEqualsDiffDescriptionSameDueDay() {
//        Task task = null;
//        try {
//            task = new Task();
//        } catch (EmptyStringException e) {
//            e.printStackTrace();
//        }
//
//        try {
//            task.setDueDate(new DueDate(new GregorianCalendar(2019, Calendar.JANUARY, 25, 10, 30).getTime()));
//        } catch (NullArgumentException e) {
//            e.printStackTrace();
//        }
//        Task task2 = null;
//        try {
//            task2 = new Task();
//        } catch (EmptyStringException e) {
//            e.printStackTrace();
//        }
//
//        try {
//            task2.setDueDate(new DueDate(new GregorianCalendar(2019, Calendar.JANUARY, 25, 10, 30).getTime()));
//        } catch (NullArgumentException e) {
//            e.printStackTrace();
//        }
//        assertFalse(task.equals(task2));
//    }
//
//
}