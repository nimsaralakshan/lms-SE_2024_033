package lms.model;

/**
 * Reservable - a second, separate contract.
 *
 * Only some items can be reserved (Books, but not DVDs or Magazines in
 * our library), so this capability does NOT belong on LibraryItem.
 * An interface lets us attach it to exactly the classes that need it.
 */
public interface Reservable {
    void reserve(Member member);
    void cancelReservation();
    boolean isReserved();
}