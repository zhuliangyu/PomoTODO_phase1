package model;

// Represents a tag having a name
public class Tag {

    public String name;

    // REQUIRES: name is non-empty
    // MODIFIES: this
    // EFFECTS: creates a Tag with the given name
    public Tag(String name) {
        this.name = name;

    }

    // EFFECTS: returns the name of this tag
    public String getName() {
        return name;
    }

    // EFFECTS: returns the tag name preceded by #
    @Override
    public String toString() {
        return "#" + name;
    }



    @Override
    public int hashCode() {
        return this.name.hashCode();
    }

    @Override
    public boolean equals(Object o) {
        Tag s = (Tag) o;
        return (s.name.equals(this.name));
    }
}
