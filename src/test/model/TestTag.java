package model;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class TestTag {
    private Tag tag;

    @BeforeEach
    public void runBefore() {
        tag = new Tag("given");
    }

    @Test
    public void testConstructor() {
        assertEquals("given", tag.getName());
    }
    @Test
    public void testToString() {
        assertEquals(tag.toString(), "#" + tag.getName());
    }
}
