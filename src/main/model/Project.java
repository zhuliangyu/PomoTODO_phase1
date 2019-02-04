package model;

import java.util.*;

// Represents a Project, a collection of zero or more Tasks
// Class Invariant: no duplicated task; order in which tasks are added to project is preserved
public class Project {
    protected Collection<Task> tasksInProject;
    protected String description;


    // REQUIRES: description is non-empty
    // MODIFIES: this
    // EFFECTS: constructs a project with the given description
    //     the constructed project shall have no tasks.
    public Project(String description) {
        this.description = description;
//        this.tasksInProject = new HashSet<Task>();
        this.tasksInProject = new LinkedHashSet<>();
    }

    // REQUIRES: task != null
    // MODIFIES: this
    // EFFECTS: task is added to this project (if it was not already part of it)
    public void add(Task task) {
        tasksInProject.add(task);


    }

    // REQUIRES: task != null
    // MODIFIES: this
    // EFFECTS: removes task from this project
    public void remove(Task task) {
        tasksInProject.remove(task);

    }

    // EFFECTS: returns the description of this project
    public String getDescription() {
        return this.description;
    }

    // EFFECTS: returns an unmodifiable list of tasks in this project.
    public List<Task> getTasks() {

        List<Task> tasksList = new ArrayList<Task>(tasksInProject);

        return tasksList;
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

    // REQUIRES: task != null
    // EFFECTS: returns true if this project contains the task
    public boolean contains(Task task) {
        return tasksInProject.contains(task);
    }


}