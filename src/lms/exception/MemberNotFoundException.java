package lms.exception;

/**
 * UNCHECKED exception: thrown when a member id does not exist.
 *
 * It extends RuntimeException, so callers are NOT forced to handle it.
 * We use unchecked here because an unknown member id is usually a
 * programming or data mistake, not something a caller can recover from.
 */
public class MemberNotFoundException extends RuntimeException {
    private final String memberId;

    public MemberNotFoundException(String memberId) {
        super("No member with id '" + memberId + "'");
        this.memberId = memberId;
    }

    public String getMemberId() { return memberId; }
}