package lms.model;

/**
 * LibraryItem - Week 4 version.
 *
 * Two changes from Week 3:
 *   1. It now IMPLEMENTS the Loanable interface (a contract it fulfils).
 *   2. calculateLateFee() is now truly ABSTRACT - no default body.
 *      Every subclass must write its own, or it will not compile.
 */
public abstract class LibraryItem implements Loanable {
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

    // ---- Loanable contract (shared implementation lives here) ----

    @Override
    public void loan(Member member) {
        if (isBorrowed) {
            System.out.println(title + " is already on loan.");
        } else if (member.getBorrowedCount() >= member.getMaxBorrowLimit()) {
            System.out.println(member.getName() + " has reached the limit of " + member.getMaxBorrowLimit() + " items.");
        } else {
            isBorrowed = true;
            member.incrementBorrowedCount();
            System.out.println(title + " loaned to " + member.getName());
        }
    }

    @Override
    public void returnItem() {
        isBorrowed = false;
        System.out.println(title + " returned.");
    }

    /** No body: each subclass MUST define its own late-fee rule. */
    public abstract double calculateLateFee(int daysLate);

    @Override
    public String toString() {
        return getClass().getSimpleName() + "{id='" + id + "', title='" + title + "', borrowed=" + isBorrowed + "}";
    }
}