package lms.model;

import lms.exception.ItemNotAvailableException;

/**
 * LibraryItem - Week 5 version (Exception Handling).
 *
 * Two changes from Week 3:
 *   1. It now IMPLEMENTS the Loanable interface (a contract it fulfils).
 *   2. calculateLateFee() is now truly ABSTRACT - no default body.
 *      Every subclass must write its own, or it will not compile.
 *
 * Week 5: loan() and returnItem() no longer PRINT failures - they THROW
 * ItemNotAvailableException so the caller decides what to do about it.
 */
public abstract class LibraryItem implements Loanable {
    private String id;
    private String title;
    private boolean isBorrowed;
    private Member borrower;   // who currently has it (null if on the shelf)

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
    public void loan(Member member) throws ItemNotAvailableException {
        if (isBorrowed) {
            throw new ItemNotAvailableException(title + " is already on loan.");
        }
        if (member.getBorrowedCount() >= member.getMaxBorrowLimit()) {
            throw new ItemNotAvailableException(member.getName()
                    + " has reached the limit of " + member.getMaxBorrowLimit() + " items.");
        }
        isBorrowed = true;
        borrower = member;
        member.incrementBorrowedCount();
        System.out.println(title + " loaned to " + member.getName());
    }

    @Override
    public void returnItem() throws ItemNotAvailableException {
        if (!isBorrowed) {
            throw new ItemNotAvailableException(title + " is not on loan.");
        }
        borrower.decrementBorrowedCount();   // free the member's slot
        borrower = null;
        isBorrowed = false;
        System.out.println(title + " returned.");
    }

    public Member getBorrower() { return borrower; }

    /** No body: each subclass MUST define its own late-fee rule. */
    public abstract double calculateLateFee(int daysLate);

    @Override
    public String toString() {
        return getClass().getSimpleName() + "{id='" + id + "', title='" + title + "', borrowed=" + isBorrowed + "}";
    }
}