package lms.service;

import lms.model.Book;
import lms.model.DVD;
import lms.model.LibraryItem;
import lms.model.Member;

import java.util.ArrayList;
import java.util.List;

/**
 * Library — Week 3 version (Inheritance + Polymorphism combined).
 *
 * Two ideas land here in the same lab:
 *   Part 1 (Inheritance):  the fields below are List<LibraryItem> and
 *                           List<Member> — the PARENT types from
 *                           lms.model — not List<Book> or List<Student>.
 *   Part 2 (Polymorphism): printAllLateFees(), the addItem() overloads,
 *                           and streamIfPossible() below show what that
 *                           buys you.
 */
public class Library {

    private final List<LibraryItem> items = new ArrayList<>();
    private final List<Member> members = new ArrayList<>();

    // ---- Dynamic binding: one method, one loop, many behaviours ----

    /**
     * Prints every item's late fee. This method never mentions Book or
     * DVD by name — it only knows about LibraryItem. At runtime, Java
     * still calls the correct calculateLateFee() for each real object.
     * That's dynamic (runtime) binding — and it only works because of
     * the inheritance hierarchy you just built in Part 1.
     */
    public void printAllLateFees(int daysLate) {
        System.out.println("--- Late fees if " + daysLate + " day(s) late ---");
        for (LibraryItem item : items) {
            System.out.println(item.getTitle() + ": " + item.calculateLateFee(daysLate));
        }
    }

    // ---- Static binding: overloading resolved at compile time ----

    /** Generic entry point — works for any LibraryItem, including future subclasses. */
    public void addItem(LibraryItem item) {
        items.add(item);
        System.out.println("Added via addItem(LibraryItem): " + item.getTitle());
    }

    /**
     * Overload #1. The COMPILER decides which addItem() to call based on
     * the declared type of the argument at the call site — that decision
     * is made before the program ever runs. This is static binding.
     */
    public void addItem(Book book) {
        items.add(book);
        System.out.println("Added via addItem(Book) - extra step: logging to 'new arrivals': " + book.getTitle());
    }

    /** Overload #2 — DVDs get their own extra step instead. */
    public void addItem(DVD dvd) {
        items.add(dvd);
        System.out.println("Added via addItem(DVD) - extra step: checking disc condition: " + dvd.getTitle());
    }

    public void addMember(Member member) {
        members.add(member);
    }

    // ---- instanceof: only when you genuinely need the specific type ----

    /**
     * Streaming only makes sense for a DVD, not for every LibraryItem —
     * so this is exactly the situation where a safe instanceof check
     * (with pattern matching) is the right tool, not a design flaw.
     */
    public void streamIfPossible(LibraryItem item) {
        if (item instanceof DVD dvd) {
            System.out.println("Streaming \"" + dvd.getTitle() + "\" (" + dvd.getDurationMinutes() + " min)...");
        } else {
            System.out.println(item.getTitle() + " cannot be streamed - it isn't a DVD.");
        }
    }

    public void listItems() {
        System.out.println("--- Items in the library ---");
        for (LibraryItem item : items) {
            System.out.println(item);
        }
    }

    public void listMembers() {
        System.out.println("--- Registered members ---");
        for (Member m : members) {
            System.out.println(m);
        }
    }

    public List<LibraryItem> getItems() { return items; }
    public List<Member> getMembers() { return members; }
}
