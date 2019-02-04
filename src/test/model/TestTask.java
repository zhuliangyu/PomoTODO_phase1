package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.HashSet;

import static org.junit.jupiter.api.Assertions.*;

public class TestTask {

    private Task task;


    @BeforeEach
    public void runBefore() {
        task = new Task("test");
    }

    @Test
    public void testConstructor() {
        assertEquals("test", task.getDescription());
    }

    @Test
    public void testAddTag() {

        task.addTag("test");
        task.addTag("test");
        assertEquals("[#test]", task.getTags().toString());

        task.addTag("test1");
        assertEquals("[#test, #test1]", task.getTags().toString());

    }

    @Test
    public void testRemoveTag() {

        task.addTag("test");
        task.addTag("test1");
        assertEquals("[#test, #test1]", task.getTags().toString());

        task.removeTag("test");
        assertEquals("[#test1]", task.getTags().toString());

        task.removeTag("te");
        assertEquals("[#test1]", task.getTags().toString());

    }

    @Test
    public void testGetTags() {
        task.addTag("test");
        task.addTag("test1");
        assertEquals("[#test, #test1]", task.getTags().toString());
        assertEquals(2, task.getTags().size());
    }

    @Test
    public void testSetPriority() {
        task.addTag("test");
        task.addTag("test1");
        task.setPriority(new Priority(1));
        assertEquals("IMPORTANT & URGENT", task.getPriority().toString());
    }

    @Test
    public void testGetStatus() {
        task.addTag("test");
        task.addTag("test1");
        task.setStatus(Status.IN_PROGRESS);
        assertEquals("IN PROGRESS", task.getStatus().toString());
    }


    @Test
    public void testGetSetDescription() {

        task.setDescription("test");
        assertEquals("test", task.getDescription());
    }

    @Test
    public void testGetSetDueDate() {

        task.setDueDate(new DueDate(new GregorianCalendar(2019, Calendar.JANUARY, 25, 10, 30).getTime()));
        assertEquals("Fri Jan 25 2019 10:30 AM", task.getDueDate().toString());
    }


    @Test
    public void testContainsTag() {

        task.addTag("test");
        task.addTag("test1");
        assertEquals("[#test, #test1]", task.getTags().toString());


        assertEquals(true, task.containsTag("test"));
        assertEquals(true, task.containsTag("test1"));
        assertEquals(false, task.containsTag("test2"));

    }

    @Test
    public void testToString() {

        task.setDescription("Read collaboration policy of the term project");
        task.setDueDate(new DueDate(new GregorianCalendar(2019, Calendar.FEBRUARY, 2, 11, 59).getTime()));
        task.setStatus(Status.IN_PROGRESS);
        task.setPriority(new Priority(1));
        task.addTag("cpsc210");
        task.addTag("project");

        assertEquals("{Description: Read collaboration policy of the term project Due date: Sat Feb 2 2019 11:59 AM Status: IN PROGRESS Priority: IMPORTANT & URGENT Tags: [#project, #cpsc210]}", task.toString());

    }


}