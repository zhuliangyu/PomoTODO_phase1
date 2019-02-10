package model;

import model.exceptions.EmptyStringException;
import model.exceptions.NullArgumentException;

import java.util.*;

// Represents a Project, a collection of zero or more Tasks
// Class Invariant: no duplicated task; order in which tasks are added to project is preserved
public class Project {
    protected Collection<Task> tasksInProject;
    protected String description;


    // MODIFIES: this
    // EFFECTS: constructs a project with the given description
    //     the constructed project shall have no tasks.
    // if description is empty, it will throw  EmptyStringException
    public Project(String description) throws EmptyStringException {
        if (description == null || description.isEmpty()) {
            throw new EmptyStringException("The string is empty.");
        }


        this.description = description;
        this.tasksInProject = new LinkedHashSet<>();
    }

    // MODIFIES: this
    // EFFECTS: task is added to this project (if it was not already part of it)
    // if task = null, throw exception
    public void add(Task task) throws NullArgumentException {
        if (task == null) {
            throw new NullArgumentException();
        } else {
            tasksInProject.add(task);
        }


    }

    // MODIFIES: this
    // EFFECTS: removes task from this project
    // if task = null, throw exception
    public void remove(Task task) throws NullArgumentException {
        if (task == null) {
            throw new NullArgumentException();
        } else {
            tasksInProject.remove(task);
        }
    }

    // EFFECTS: returns the description of this project
    public String getDescription() {
        return this.description;
    }

    // EFFECTS: returns an unmodifiable list of tasks in this project.
    public List<Task> getTasks() {

        List<Task> tasksList = new ArrayList<Task>(tasksInProject);
        List<Task> unmodifiableList = Collections.unmodifiableList(tasksList);

        return unmodifiableList;
    }

    // EFFECTS: returns an integer between 0 and 100 which represents
    //     the percentage of completed tasks (rounded down to the closest integer).
    //     returns 100 if this project has no tasks!
    public int getProgress() {
        int countDone = 0;
        int total = 0;
        for (Task task : tasksInProject) {
            if (task.getStatus().equals(Status.DONE)) {
                countDone += 1;
            }
            total += 1;
        }

        if (total == 0) {
            return 100;
        } else {
            return countDone * 100 / total;
        }
    }

    // EFFECTS: returns the number of tasks in this project
    public int getNumberOfTasks() {
        return tasksInProject.size();
    }

    // EFFECTS: returns true if every task in this project is completed, and false otherwise
    //     If this project has no tasks, return false.
    public boolean isCompleted() {
        boolean completed = true;

        if (tasksInProject.size() == 0) {
            return false;
        } else {
            for (Task task : tasksInProject) {
                if (!task.getStatus().equals(Status.DONE)) {
                    completed = false;
                }
            }
            return completed;
        }
    }

    // EFFECTS: returns true if this project contains the task
    // IF task = null, it will throw exception
    public boolean contains(Task task) throws NullArgumentException {
        if (task == null) {
            throw new NullArgumentException();
        }
        return tasksInProject.contains(task);
    }


}