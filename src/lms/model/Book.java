package lms.model;

/** Book - a LibraryItem that can ALSO be reserved (implements Reservable). */
public class Book extends LibraryItem implements Reservable {
    private String author;
    private Member reservedBy;

    public Book(String id, String title, String author) {
        super(id, title);
        setAuthor(author);
    }

    public String getAuthor() { return author; }

    public void setAuthor(String author) {
        if (author == null || author.isBlank()) throw new IllegalArgumentException("Author cannot be blank");
        this.author = author;
    }

    /** Books used to inherit a default rate; now each class states its own. */
    @Override
    public double calculateLateFee(int daysLate) {
        return daysLate * 10.0;
    }

    // ---- Reservable contract ----

    @Override
    public void reserve(Member member) {
        if (reservedBy != null) {
            System.out.println(getTitle() + " is already reserved by " + reservedBy.getName());
        } else {
            reservedBy = member;
            System.out.println(getTitle() + " reserved for " + member.getName());
        }
    }

    @Override
    public void cancelReservation() {
        reservedBy = null;
        System.out.println("Reservation cancelled for " + getTitle());
    }

    @Override
    public boolean isReserved() { return reservedBy != null; }
}