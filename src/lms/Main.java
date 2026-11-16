package lms;

import lms.model.*;
import lms.service.Library;

/**
 * Main — Week 3 demo: Inheritance + Polymorphism, combined.
 *
 * Try this yourself:
 *   1. Add a third item type of your own (e.g. Magazine extends LibraryItem)
 *      and confirm printAllLateFees() handles it with ZERO changes to
 *      that method — that's the payoff of Part 1's inheritance work.
 *   2. Comment out the addItem(Book) overload and re-run — notice
 *      addItem(b1) now falls back to addItem(LibraryItem), and the
 *      "new arrivals" logging step silently disappears. That's static
 *      binding: the compiler picked a different method entirely.
 *   3. Call streamIfPossible() with a Book instead of a DVD.
 */
public class Main {
    public static void main(String[] args) {
        Library library = new Library();

        Book b1 = new Book("B001", "Clean Code", "Robert C. Martin");
        Book b2 = new Book("B002", "Effective Java", "Joshua Bloch");
        DVD d1 = new DVD("D001", "Introduction to Algorithms", 120);

        // Static binding: the compiler picks the overload matching each
        // variable's DECLARED type (Book, Book, DVD) at compile time.
        library.addItem(b1);
        library.addItem(b2);
        library.addItem(d1);

        library.addMember(new Student("M001", "Nadeesha Perera"));
        library.addMember(new Staff("M002", "Mr. Kasun Silva"));

        library.listItems();
        library.listMembers();

        // Dynamic binding: same loop, different calculateLateFee() per object.
        library.printAllLateFees(3);

        // instanceof: only DVDs can be streamed.
        library.streamIfPossible(d1);
        library.streamIfPossible(b1);
    }
}