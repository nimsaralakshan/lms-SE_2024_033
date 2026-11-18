package lms.exception;

/**
 * CHECKED exception: thrown when an item cannot be loaned or returned
 * (already on loan, limit reached, not on loan, no such item).
 *
 * It extends Exception, so the compiler FORCES every caller to either
 * catch it or declare "throws ItemNotAvailableException".
 */
public class ItemNotAvailableException extends Exception {
    public ItemNotAvailableException(String message) {
        super(message);
    }
}
