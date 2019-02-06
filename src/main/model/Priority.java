package model;

// To model priority of a task according to the Eisenhower Matrix
//     https://en.wikipedia.org/wiki/Time_management#The_Eisenhower_Method
public class Priority {

    protected boolean urgent;
    protected boolean important;
//    1 = urgent / important
//    2 = not urgent / important
//    3 = urgent / not important
//    4 = not urgent / not important


    // MODIFIES: this
    // EFFECTS: construct a default priority (i.e., not important nor urgent)
    public Priority() {
        this.important = false;
        this.urgent = false;

    }

    // REQUIRES: 1 <= quadrant <= 4
    // MODIFIES: this
    // EFFECTS: constructs a Priority according to the value of "quadrant"
    //     the parameter "quadrant" refers to the quadrants of the Eisenhower Matrix
    public Priority(int quadrant) {
        switch (quadrant) {
            case 1:
                urgent = true;
                important = true;
                break;
            case 2:
                urgent = false;
                important = true;
                break;
            case 3:
                urgent = true;
                important = false;
                break;
            case 4:
                urgent = false;
                important = false;
                break;
            default:
                urgent = false;
                important = false;
                break;
        }

    }

    // EFFECTS: returns the importance of Priority
    //     (i.e., whether it is "important" or not)
    public boolean isImportant() {
        return important;
    }

    // MODIFIES: this
    // EFFECTS: updates the importance of Priority
    public void setImportant(boolean important) {
        this.important = important;

    }

    // EFFECTS: returns the urgency of Priority
    //     (i.e., whether it is "urgent" or not)
    public boolean isUrgent() {
        return this.urgent;
    }

    // MODIFIES: this
    // EFFECTS: updates the urgency of Priority
    public void setUrgent(boolean urgent) {
        this.urgent = urgent;
    }

    // EFFECTS: returns one of the four string representation of Priority
    //    "IMPORTANT & URGENT",  "IMPORTANT", "URGENT", "DEFAULT"
    @Override
    public String toString() {
        if (urgent & important) {
            return "IMPORTANT & URGENT";
        } else if (important) {
            return "IMPORTANT";
        } else if (urgent) {
            return "URGENT";
        }
        return "DEFAULT";
    }
}