package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class TestProject {

    private Project project;


    @BeforeEach
    public void runBefore() {
        project = new Project("test");

    }

    @Test
    public void testConstructor() {
        assertEquals("test", project.description);
        assertEquals(0, project.getTasks().size());
    }

    @Test
    public void testAddTaskWithoutDueDate() {
        Task task = new Task("test");
        Task task2 = new Task("test");

        project.add(task);
        project.add(task2);

        assertEquals(1, project.getTasks().size());

        Task task3 = new Task("test3");
        project.add(task3);
        assertEquals(2, project.getTasks().size());

    }

    @Test
    public void testAddTaskWithDueDate() {
        Task task = new Task("test");
        task.setDueDate(new DueDate(new GregorianCalendar(2019, Calendar.JANUARY, 25, 10, 30).getTime()));
        Task task2 = new Task("test");
        task2.setDueDate(new DueDate(new GregorianCalendar(2019, Calendar.JANUARY, 26, 10, 30).getTime()));

        project.add(task);
        project.add(task2);

        Task task3 = new Task("test");
        task2.setDueDate(new DueDate(new GregorianCalendar(2019, Calendar.JANUARY, 26, 10, 30).getTime()));

        assertEquals(2, project.getTasks().size());

//        Task task3 = new Task("test3");
//        project.add(task3);
//        assertEquals(2, project.getTasks().size());

    }

    @Test
    public void testRemoveTask() {
        Task task = new Task("test");
        Task task2 = new Task("test");

        project.add(task);
        project.add(task2);

        assertEquals(1, project.getTasks().size());

        Task task3 = new Task("test3");
        project.add(task3);
        assertEquals(2, project.getTasks().size());

        project.remove(task3);
        assertEquals(1, project.getTasks().size());
    }

    @Test
    public void testGetDescriptionTask() {

        assertEquals("test", project.getDescription());

    }

    @Test
    public void testGetTasks() {
        Task task = new Task("test");
        Task task999 = new Task("999");
        Task task2 = new Task("test2");
        Task task3 = new Task("test3");

        project.add(task);
        project.add(task999);
        project.add(task2);
        project.add(task3);
        assertEquals(4, project.getTasks().size());
        assertEquals("test3", project.getTasks().get(3).getDescription());
        assertEquals("999", project.getTasks().get(1).getDescription());

        Task task4 = new Task("test4");
        List<Task> unmodifiedList = project.getTasks();


        try {
            unmodifiedList.add(task4);
            fail("The return list is modified");
        } catch (UnsupportedOperationException e) {

        }
        assertEquals(4, project.getTasks().size());

    }

    @Test
    public void testGetProgressFraction() {
        Task task = new Task("test");
        task.setStatus(Status.IN_PROGRESS);
        Task task2 = new Task("test2");
        task2.setStatus(Status.IN_PROGRESS);
        Task task3 = new Task("test3");
        task3.setStatus(Status.DONE);
        project.add(task);
        project.add(task2);
        project.add(task3);
        assertEquals(33, project.getProgress());

    }

    @Test
    public void testGetProgressFractionDown() {
        Task task = new Task("test");
        task.setStatus(Status.IN_PROGRESS);
        Task task2 = new Task("test2");
        task2.setStatus(Status.IN_PROGRESS);
        Task task3 = new Task("test3");
        task3.setStatus(Status.DONE);
        Task task4 = new Task("test4");
        task4.setStatus(Status.IN_PROGRESS);
        Task task5 = new Task("test5");
        task5.setStatus(Status.IN_PROGRESS);
        Task task6 = new Task("test6");
        task6.setStatus(Status.IN_PROGRESS);
        project.add(task);
        project.add(task2);
        project.add(task3);
        project.add(task4);
        project.add(task5);
        project.add(task6);
        assertEquals(16, project.getProgress());

    }

    @Test
    public void testGetProgressNoTask() {
        assertEquals(100, project.getProgress());
    }

    @Test
    public void testGetProgressAllDone() {
        Task task = new Task("test");
        task.setStatus(Status.DONE);
        Task task2 = new Task("test2");
        task2.setStatus(Status.DONE);
        Task task3 = new Task("test3");
        task3.setStatus(Status.DONE);
        project.add(task);
        project.add(task2);
        project.add(task3);
        assertEquals(100, project.getProgress());

    }

    @Test
    public void testGetNumberOfTasks() {
        Task task = new Task("test");
        task.setStatus(Status.DONE);
        Task task2 = new Task("test2");
        task2.setStatus(Status.DONE);
        Task task3 = new Task("test3");
        task3.setStatus(Status.DONE);
        project.add(task);
        project.add(task2);
        project.add(task3);
        assertEquals(3, project.getNumberOfTasks());

    }

    @Test
    public void testIsCompletedTrue() {
        Task task = new Task("test");
        task.setStatus(Status.DONE);
        Task task2 = new Task("test2");
        task2.setStatus(Status.DONE);
        Task task3 = new Task("test3");
        task3.setStatus(Status.DONE);
        project.add(task);
        project.add(task2);
        project.add(task3);
        assertEquals(true, project.isCompleted());

    }

    @Test
    public void testIsCompletedFalse() {
        Task task = new Task("test");
        task.setStatus(Status.IN_PROGRESS);
        Task task2 = new Task("test2");
        task2.setStatus(Status.DONE);
        Task task3 = new Task("test3");
        task3.setStatus(Status.DONE);
        project.add(task);
        project.add(task2);
        project.add(task3);
        assertEquals(false, project.isCompleted());

    }

    @Test
    public void testIsCompletedNoTask() {

        assertEquals(false, project.isCompleted());

    }

    @Test
    public void testContains() {
        Task task = new Task("test");
        task.setStatus(Status.IN_PROGRESS);
        Task task2 = new Task("test2");
        task2.setStatus(Status.DONE);
        Task task3 = new Task("test3");
        task3.setStatus(Status.DONE);
        project.add(task);
        project.add(task2);
//        project.add(task3);
        assertEquals(true, project.contains(task));
        assertEquals(true, project.contains(task2));
        assertEquals(false, project.contains(task3));

    }
}