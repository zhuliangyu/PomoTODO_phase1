package model;
import model.exceptions.EmptyStringException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class TestTag {
    private Tag tag;

    @BeforeEach
    public void runBefore() {
        try {
            tag = new Tag("given");
        } catch (EmptyStringException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testConstructorNoException() {
        try {
            tag = new Tag("given");
        } catch (EmptyStringException e) {
            fail();
        }
        assertEquals("given", tag.getName());
    }

    @Test
    public void testConstructorWithException() {
        try {
            tag = new Tag("");
            fail();
        } catch (EmptyStringException e) {
        }

        try {
            tag = new Tag(null);
            fail();
        } catch (EmptyStringException e) {
        }

    }
    @Test
    public void testToString() {
        assertEquals(tag.toString(), "#" + tag.getName());
    }
}
