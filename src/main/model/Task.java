package model;

import model.exceptions.EmptyStringException;
import model.exceptions.NullArgumentException;
import parsers.TagParser;
import parsers.exceptions.ParsingException;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;


// Represents a Task having a description, status, priorities, set of tags and due date.
public class Task {
    public static final DueDate NO_DUE_DATE = null;

    private String descriptionTask;
    private DueDate dueDateTask;
    private Status statusTask;
    private Set<Tag> setTagTask;
    private Priority priorityTask;

    // MODIFIES: this
    // EFFECTS: constructs a task with the given description
    //    parses the description to extract meta-data (i.e., tags, status, priority and deadline).
    //    If description does not contain meta-data, the task is set to have no due date,
    //    status of 'To Do', and default priority level (i.e., not important nor urgent)
    //    if description is non-empty, it will throw exception
    public Task(String description) throws EmptyStringException, NullArgumentException {
        setTagTask = new HashSet<Tag>();

        if (description == null || description.isEmpty()) {
            throw new EmptyStringException("The string is empty.");
        } else {
            TagParser tp = new TagParser();
            try {
                tp.parse(description, this);
            } catch (ParsingException e) {
                //    If description does not contain meta-data, the task is set to have no due date,
                //    status of 'To Do', and default priority level (i.e., not important nor urgent)
                //    if description is non-empty, it will throw exception
                dueDateTask = NO_DUE_DATE;
                statusTask = Status.TODO;
                priorityTask = new Priority();
            }


        }


    }


    // MODIFIES: this
    // EFFECTS: creates a tag with name tagName and adds it to this task
    // Note: no two tags are to have the same name
    // if  name is non-empty, it will throw exception
    public void addTag(String tagName) throws EmptyStringException {
        if (tagName == null || tagName.isEmpty()) {
            throw new EmptyStringException("The string is empty.");
        } else {
            Tag tag = new Tag(tagName);
            setTagTask.add(tag);
        }

    }

    // MODIFIES: this
    // EFFECTS: removes the tag with name tagName from this task
    // if name is non-empty, throw exception
    public void removeTag(String tagName) throws EmptyStringException {
        if (tagName == null || tagName.isEmpty()) {
            throw new EmptyStringException("The string is empty.");
        } else {
            setTagTask.remove(new Tag(tagName));
        }
    }

    // EFFECTS: returns an unmodifiable set of tags
    public Set<Tag> getTags() {
        return Collections.unmodifiableSet(this.setTagTask);
    }

    // EFFECTS: returns the priority of this task
    public Priority getPriority() {
        return this.priorityTask;
    }

    // MODIFIES: this
    // EFFECTS: sets the priority of this task
    // if      priority != null, throw exception
    public void setPriority(Priority priority) throws NullArgumentException {
        if (priority == null) {
            throw new NullArgumentException();
        } else {
            this.priorityTask = priority;
        }
    }

    // EFFECTS: returns the status of this task
    public Status getStatus() {
        return this.statusTask;
    }

    // MODIFIES: this
    // EFFECTS: sets the status of this task
    // if status = null, throw exception
    public void setStatus(Status status) throws NullArgumentException {
        if (status == null) {
            throw new NullArgumentException();
        }

        this.statusTask = status;

    }

    // EFFECTS: returns the description of this task
    public String getDescription() {
        return this.descriptionTask;
    }

    // MODIFIES: this
    // EFFECTS:  sets the description of this task
    //     parses the description to extract meta-data (i.e., tags, status, priority
    //     and deadline).
    //     if description is empty, throw exception
    public void setDescription(String description) throws EmptyStringException {
        if (description == null || description.isEmpty()) {
            throw new EmptyStringException();
        }
        this.descriptionTask = description;

    }

    // EFFECTS: returns the due date of this task
    public DueDate getDueDate() {
        return this.dueDateTask;
    }

    // MODIFIES: this
    // EFFECTS: sets the due date of this task
    public void setDueDate(DueDate dueDate) {
        if (dueDate == null) {
            this.dueDateTask = NO_DUE_DATE;
        }

        this.dueDateTask = dueDate;
    }

    // EFFECTS: returns true if task contains a tag with tagName,
    //     returns false otherwise
    public boolean containsTag(String tagName) throws EmptyStringException {
        if (tagName == null || tagName.isEmpty()) {
            throw new EmptyStringException();
        } else {
            return (setTagTask.contains(new Tag(tagName)));
        }


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
        String strTags = buildTagString();

        // build dueDateString
        String strDueDate = "";
        if (this.dueDateTask != null) {
            strDueDate = this.dueDateTask.toString();
        }

        String str = String.format("{\n\tDescription: %s "
                        + "\n\tDue date: %s \n\tStatus: %s "
                        + "\n\tPriority: %s \n\tTags: %s\n}",
                this.descriptionTask, strDueDate, this.statusTask, this.priorityTask, strTags);
        return str;
    }

    private String buildTagString() {
        //build tags string
        StringBuilder strTagbuilder = new StringBuilder();
        ArrayList<String> arrTags = new ArrayList<>();
        for (Tag tag :
                this.setTagTask) {
            arrTags.add(tag.toString());
        }

        for (int i = 0; i < this.setTagTask.size(); i++) {
            if (i != arrTags.size() - 1) {
                strTagbuilder.append(arrTags.get(i) + ", ");
            } else {
                strTagbuilder.append(arrTags.get(i));
            }
        }

        return strTagbuilder.toString();
    }

    @Override
    public int hashCode() {
        if (this.dueDateTask != null) {
            return (this.descriptionTask.hashCode() + this.dueDateTask.hashCode());
        } else {
            return (this.descriptionTask.hashCode());
        }

    }

    @Override
    public boolean equals(Object obj) {
        Task s = (Task) obj;

        if (this.dueDateTask != null) {
            return (s.descriptionTask.equals(this.descriptionTask)
                    && (s.dueDateTask.getDate().equals((this.dueDateTask.getDate()))));
        } else {
            return (s.descriptionTask.equals(this.descriptionTask));
        }

    }


}
