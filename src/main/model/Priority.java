package model;

// To model priority of a task according to the Eisenhower Matrix
//     https://en.wikipedia.org/wiki/Time_management#The_Eisenhower_Method
public class Priority {
    
    // MODIFIES: this
    // EFFECTS: construct a default priority (i.e., not important nor urgent)
    public Priority() {

    }
    
    // REQUIRES: 1 <= quadrant <= 4
    // MODIFIES: this
    // EFFECTS: constructs a Priority according to the value of "quadrant"
    //     the parameter "quadrant" refers to the quadrants of the Eisenhower Matrix
    public Priority(int quadrant) {

    }
    
    // EFFECTS: returns the importance of Priority
    //     (i.e., whether it is "important" or not)
    public boolean isImportant() {
        return false;
    }
    
    // MODIFIES: this
    // EFFECTS: updates the importance of Priority
    public void setImportant(boolean important) {

    }
    
    // EFFECTS: returns the urgency of Priority
    //     (i.e., whether it is "urgent" or not)
    public boolean isUrgent() {
        return false;
    }
    
    // MODIFIES: this
    // EFFECTS: updates the urgency of Priority
    public void setUrgent(boolean urgent) {

    }
    
    // EFFECTS: returns one of the four string representation of Priority
    //    "IMPORTANT & URGENT",  "IMPORTANT", "URGENT", "DEFAULT"
    @Override
    public String toString() {
        return null;
    }
}