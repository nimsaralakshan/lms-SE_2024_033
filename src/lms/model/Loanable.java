package lms.model;

/**
 * Loanable - a contract: "anything that can be loaned to a Member".
 *
 * An interface says WHAT must be possible, never HOW. Any class that
 * implements Loanable promises to provide both methods below.
 */
public interface Loanable {
    void loan(Member member);
    void returnItem();
}