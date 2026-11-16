package lms;

import lms.model.*;
import lms.service.Library;

/**
 * Main - Week 4 demo: Abstract Classes & Interfaces.
 *
 * Try this yourself:
 *   1. Delete calculateLateFee() from Magazine and compile. Read the error.
 *   2. Add "implements Reservable" to DVD and satisfy the compiler.
 *   3. Write a new class Poster that implements Loanable but is NOT a
 *      LibraryItem, then pass it to library.lend(...).
 *   4. Change compareTo() so the LEAST active member sorts first.
 */
public class Main {
    public static void main(String[] args) {
        Library library = new Library();

        Book book = new Book("B001", "Clean Code", "Robert C. Martin");
        DVD dvd = new DVD("D001", "Introduction to Algorithms", 120);
        Magazine mag = new Magazine("G001", "Software Weekly", 42);

        library.addItem(book);
        library.addItem(dvd);
        library.addItem(mag);

        Student nadeesha = new Student("M001", "Nadeesha Perera");
        Staff kasun = new Staff("M002", "Mr. Kasun Silva");
        library.addMember(nadeesha);
        library.addMember(kasun);

        // Abstract method: three classes, three different fee rules
        library.printAllLateFees(3);

        // Loanable interface
        System.out.println("--- Loans ---");
        library.lend(book, nadeesha);
        library.lend(book, kasun);      // already on loan
        library.lend(dvd, kasun);
        library.lend(mag, kasun);

        // Reservable interface: only Book qualifies
        System.out.println("--- Reservations ---");
        library.reserveIfPossible(book, kasun);
        library.reserveIfPossible(dvd, nadeesha);

        // Comparable
        library.printMembersByActivity();
    }
}
