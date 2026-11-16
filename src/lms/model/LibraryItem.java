package lms.model;

/**
 * LibraryItem — introduced this week as the shared parent of Book and DVD.
 *
 * Encapsulation (from Week 2) is still here — private fields, validated
 * setters — but the new idea this week is INHERITANCE: Book and DVD will
 * extend this class instead of repeating id/title/isBorrowed themselves.
 */
public abstract class LibraryItem {
    private String id;
    private String title;
    private boolean isBorrowed;

    public LibraryItem(String id, String title) {
        setId(id);
        setTitle(title);
        this.isBorrowed = false;
    }

    public String getId() { return id; }
    public String getTitle() { return title; }
    public boolean isBorrowed() { return isBorrowed; }

    public void setId(String id) {
        if (id == null || id.isBlank()) throw new IllegalArgumentException("Id cannot be blank");
        this.id = id;
    }

    public void setTitle(String title) {
        if (title == null || title.isBlank()) throw new IllegalArgumentException("Title cannot be blank");
        this.title = title;
    }

    public void borrowItem() { this.isBorrowed = true; }
    public void returnItem() { this.isBorrowed = false; }

    /** Every subclass may override this — that's what makes Part 2 (Polymorphism) below work. */
    public double calculateLateFee(int daysLate) {
        return daysLate * 10.0;
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() + "{id='" + id + "', title='" + title + "', borrowed=" + isBorrowed + "}";
    }
}