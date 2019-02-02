package model;

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

        Priority priority1 = new Priority(1);
        assertEquals("IMPORTANT & URGENT",priority1.toString());
    }
    @Test
    public void testConstructorWithQuadrantInput2() {

        Priority priority2 = new Priority(2);
        assertEquals("IMPORTANT",priority2.toString());
    }

    @Test
    public void testConstructorWithQuadrantInput3() {

        Priority priority3 = new Priority(3);
        assertEquals("URGENT",priority3.toString());
    }
    @Test
    public void testConstructorWithQuadrantInput4() {

        Priority priority4 = new Priority(4);
        assertEquals("DEFAULT",priority4.toString());
    }


    @Test
    public void testSetImportant(){
        priority.setImportant(false);
        assertEquals(false, priority.isImportant());

        priority.setImportant(true);
        assertTrue(priority.isImportant());
    }

    @Test
    public void testSetUrgent(){
        priority.setUrgent(false);
        assertEquals(false, priority.isUrgent());

        priority.setUrgent(true);
        assertTrue(priority.isUrgent());
    }

}
