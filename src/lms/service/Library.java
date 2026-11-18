package lms.service;

import lms.exception.ItemNotAvailableException;
import lms.exception.MemberNotFoundException;
import lms.model.Book;
import lms.model.DVD;
import lms.model.LibraryItem;
import lms.model.Loanable;
import lms.model.Member;
import lms.model.Reservable;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Library - Week 5 version (Exception Handling).
 *
 * Kept from Week 3: printAllLateFees(), the addItem() overloads.
 * New this week: methods that talk to INTERFACE types (Loanable,
 * Reservable) instead of concrete classes, and a Comparable-based sort.
 */
public class Library {

    private final List<LibraryItem> items = new ArrayList<>();
    private final List<Member> members = new ArrayList<>();

    // ---- from Week 3 (unchanged) ----

    public void addItem(LibraryItem item) {
        items.add(item);
        System.out.println("Added via addItem(LibraryItem): " + item.getTitle());
    }

    public void addItem(Book book) {
        items.add(book);
        System.out.println("Added via addItem(Book): " + book.getTitle());
    }

    public void addItem(DVD dvd) {
        items.add(dvd);
        System.out.println("Added via addItem(DVD): " + dvd.getTitle());
    }

    public void addMember(Member member) { members.add(member); }

    public void printAllLateFees(int daysLate) {
        System.out.println("--- Late fees if " + daysLate + " day(s) late ---");
        for (LibraryItem item : items) {
            System.out.println(item.getTitle() + ": " + item.calculateLateFee(daysLate));
        }
    }

    // ---- NEW in Week 4 ----

    /**
     * The parameter type is the INTERFACE Loanable, not LibraryItem.
     * This method would work for anything that promises to be loanable,
     * even a class that is not a LibraryItem at all.
     */
    public void lend(Loanable thing, Member member) throws ItemNotAvailableException {
        thing.loan(member);
    }

    // ---- NEW in Week 5 ----

    /** Looks a member up by id. Throws the UNCHECKED MemberNotFoundException. */
    public Member findMember(String memberId) {
        for (Member m : members) {
            if (m.getMemberId().equals(memberId)) return m;
        }
        throw new MemberNotFoundException(memberId);
    }

    /** Looks an item up by id. Throws the CHECKED ItemNotAvailableException. */
    public LibraryItem findItem(String itemId) throws ItemNotAvailableException {
        for (LibraryItem i : items) {
            if (i.getId().equals(itemId)) return i;
        }
        throw new ItemNotAvailableException("No item with id '" + itemId + "'");
    }

    /** The one-stop "borrow" operation the GUI will call later. */
    public void borrowItem(String itemId, String memberId) throws ItemNotAvailableException {
        Member member = findMember(memberId);
        LibraryItem item = findItem(itemId);
        item.loan(member);
    }

    public void returnItem(String itemId) throws ItemNotAvailableException {
        findItem(itemId).returnItem();
    }

    /**
     * Only some items are Reservable. Checking for the INTERFACE (not for
     * Book) means a future Reservable class works here with no changes.
     */
    public void reserveIfPossible(LibraryItem item, Member member) {
        if (item instanceof Reservable r) {
            r.reserve(member);
        } else {
            System.out.println(item.getTitle() + " cannot be reserved.");
        }
    }

    /** Prints members ranked by activity, using Member's compareTo(). */
    public void printMembersByActivity() {
        List<Member> sorted = new ArrayList<>(members);
        Collections.sort(sorted);
        System.out.println("--- Members ranked by activity ---");
        for (Member m : sorted) {
            System.out.println(m.getName() + " (" + m.getBorrowedCount() + " borrowed)");
        }
    }

    public void listItems() {
        System.out.println("--- Items in the library ---");
        for (LibraryItem item : items) System.out.println(item);
    }

    public void listMembers() {
        System.out.println("--- Registered members ---");
        for (Member m : members) System.out.println(m);
    }

    public List<LibraryItem> getItems() { return items; }
    public List<Member> getMembers() { return members; }
}