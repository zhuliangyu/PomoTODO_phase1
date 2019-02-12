package parser;

import model.Task;
import model.exceptions.EmptyStringException;
import model.exceptions.InvalidTimeException;
import model.exceptions.NullArgumentException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import parsers.Parser;
import parsers.TagParser;
import parsers.exceptions.ParsingException;

import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import static org.junit.jupiter.api.Assertions.*;

public class TestParser {
    @BeforeEach
    void runBefore() {

//        today = new DueDate();
//        tomorrow = new DueDate(today.postponeNdays(new Date(), 1));
//        today = new GregorianCalendar(2019, Calendar.FEBRUARY, 7, 23, 59).getTime();
//        tomorrow = new GregorianCalendar(2019, Calendar.FEBRUARY, 8, 23, 59).getTime();
    }

    @Test
    void testGetDescription(){
        Parser p = new TagParser();
        Task t = new Task("My task");
        try {
            p.parse("Some description ## tag1; tag2", t);
        } catch (ParsingException e) {
            fail();
        }

        assertEquals("Some description ", p.getDescription());
    }

}
