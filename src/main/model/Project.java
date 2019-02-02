package model;

import java.util.List;

// Represents a Project, a collection of zero or more Tasks
// Class Invariant: no duplicated task; order in which tasks are added to project is preserved
public class Project {

    
    // REQUIRES: description is non-empty
    // MODIFIES: this
    // EFFECTS: constructs a project with the given description
    //     the constructed project shall have no tasks.
    public Project(String description) {

    }
    
    // REQUIRES: task != null
    // MODIFIES: this
    // EFFECTS: task is added to this project (if it was not already part of it)
    public void add(Task task) {

    }
    
    // REQUIRES: task != null
    // MODIFIES: this
    // EFFECTS: removes task from this project
    public void remove(Task task) {

    }
    
    // EFFECTS: returns the description of this project
    public String getDescription() {
        return null;
    }
    
    // EFFECTS: returns an unmodifiable list of tasks in this project.
    public List<Task> getTasks() {
        return null;
    }
    
    // EFFECTS: returns an integer between 0 and 100 which represents
    //     the percentage of completed tasks (rounded down to the closest integer).
    //     returns 100 if this project has no tasks!
    public int getProgress() {
        return 0;
    }
    
    // EFFECTS: returns the number of tasks in this project
    public int getNumberOfTasks() {
        return 0;
    }
    
    // EFFECTS: returns true if every task in this project is completed
    //     returns false if this project has no tasks!
    public boolean isCompleted() {
        return false;
    }
    
    // REQUIRES: task != null
    // EFFECTS: returns true if this project contains the task
    public boolean contains(Task task) {
        return false;
    }
}