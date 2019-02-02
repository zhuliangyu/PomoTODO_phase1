package model;

import java.util.Set;

// Represents a Task having a description, status, priorities, set of tags and due date.
public class Task {
    public static final DueDate NO_DUE_DATE = null;


    // REQUIRES: description is non-empty
    // MODIFIES: this
    // EFFECTS: constructs a task with the given description
    //    parses the description to extract meta-data (i.e., tags, status, priority and deadline).
    //    If description does not contain meta-data, the task is set to have no due date,
    //    status of 'To Do', and default priority level (i.e., not important nor urgent)
    public Task(String description) {

    }

    // REQUIRES: name is non-empty
    // MODIFIES: this
    // EFFECTS: creates a tag with name tagName and adds it to this task
    // Note: no two tags are to have the same name
    public void addTag(String tagName) {

    }

    // REQUIRES: name is non-empty
    // MODIFIES: this
    // EFFECTS: removes the tag with name tagName from this task
    public void removeTag(String tagName) {

    }

    // EFFECTS: returns an unmodifiable set of tags
    public Set<Tag> getTags() {
        return null;
    }

    // EFFECTS: returns the priority of this task
    public Priority getPriority() {
        return null;
    }

    // REQUIRES: priority != null
    // MODIFIES: this
    // EFFECTS: sets the priority of this task
    public void setPriority(Priority priority) {

    }

    // EFFECTS: returns the status of this task
    public Status getStatus() {
        return null;
    }

    // REQUIRES: status != null
    // MODIFIES: this
    // EFFECTS: sets the status of this task
    public void setStatus(Status status) {

    }

    // EFFECTS: returns the description of this task
    public String getDescription() {
        return null;
    }

    // REQUIRES: description is non-empty
    // MODIFIES: this
    // EFFECTS:  sets the description of this task
    //     parses the description to extract meta-data (i.e., tags, status, priority and deadline).
    public void setDescription(String description) {

    }

    // EFFECTS: returns the due date of this task
    public DueDate getDueDate() {
        return null;
    }

    // MODIFIES: this
    // EFFECTS: sets the due date of this task
    public void setDueDate(DueDate dueDate) {

    }

    // EFFECTS: returns true if task contains a tag with tagName,
    //     returns false otherwise
    public boolean containsTag(String tagName) {
        return false;
    }

    //EFFECTS: returns a string representation of this task in the following format
    //    {
    //        Description: Read collaboration policy of the term project
    //        Due date: Sat Feb 2 2019 11:59
    //        Status: IN PROGRESS
    //        Priority: IMPORTANT & URGENT
    //        Tags: #cpsc210, #project
    //    }
    @Override
    public String toString() {
        return null;
    }
}
