package model;

import model.exceptions.InvalidPriorityLevelException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;


public class TestPriority {

    private Priority priority;


    @BeforeEach
    public void runBefore() {
        priority = new Priority();
    }

    @Test
    public void testConstructorWithEmptyParam() {
        assertFalse(priority.important);
        assertFalse(priority.urgent);

    }

    @Test
    public void testConstructorWithQuadrantInput1() {

        Priority priority1 = null;
        try {
            priority1 = new Priority(1);
        } catch (InvalidPriorityLevelException e) {
            fail("It shouldn't throw exception");
        }
        assertEquals("IMPORTANT & URGENT", priority1.toString());
    }

    @Test
    public void testConstructorWithInValidException() {

        Priority priority1 = null;
        try {
            priority1 = new Priority(0);
            fail("It didn't throw exception");
        } catch (InvalidPriorityLevelException e) {
        }

        try {
            priority1 = new Priority(5);
            fail("It didn't throw exception");
        } catch (InvalidPriorityLevelException e) {
        }
    }

    @Test
    public void testConstructorWithQuadrantInput2() {

        Priority priority2 = null;
        try {
            priority2 = new Priority(2);
        } catch (InvalidPriorityLevelException e) {
            e.printStackTrace();
        }
        assertEquals("IMPORTANT", priority2.toString());
    }

    @Test
    public void testConstructorWithQuadrantInput3() {

        Priority priority3 = null;
        try {
            priority3 = new Priority(3);
        } catch (InvalidPriorityLevelException e) {
            e.printStackTrace();
        }
        assertEquals("URGENT", priority3.toString());
    }

    @Test
    public void testConstructorWithQuadrantInput4() {

        Priority priority4 = null;
        try {
            priority4 = new Priority(4);
        } catch (InvalidPriorityLevelException e) {
            e.printStackTrace();
        }
        assertEquals("DEFAULT", priority4.toString());
    }


    @Test
    public void testSetImportant() {
        priority.setImportant(false);
        assertEquals(false, priority.isImportant());

        priority.setImportant(true);
        assertTrue(priority.isImportant());
    }

    @Test
    public void testSetUrgent() {
        priority.setUrgent(false);
        assertEquals(false, priority.isUrgent());
        priority.setUrgent(true);
        assertTrue(priority.isUrgent());
    }

}
