package lms.model;

/**
 * Member - parent of Student and Staff. Week 4: implements Comparable so a
 * list of members can be sorted with Collections.sort().
 */
public class Member implements Comparable<Member> {
    private String memberId;
    private String name;
    private int borrowedCount;
    private int maxBorrowLimit;

    public Member(String memberId, String name, int maxBorrowLimit) {
        setMemberId(memberId);
        setName(name);
        this.maxBorrowLimit = maxBorrowLimit;
        this.borrowedCount = 0;
    }

    public String getMemberId() { return memberId; }
    public String getName() { return name; }
    public int getBorrowedCount() { return borrowedCount; }
    public int getMaxBorrowLimit() { return maxBorrowLimit; }

    public void setMemberId(String memberId) {
        if (memberId == null || memberId.isBlank()) throw new IllegalArgumentException("Member ID cannot be blank");
        this.memberId = memberId;
    }

    public void setName(String name) {
        if (name == null || name.isBlank()) throw new IllegalArgumentException("Name cannot be blank");
        this.name = name;
    }

    public void setBorrowedCount(int borrowedCount) {
        if (borrowedCount < 0) throw new IllegalArgumentException("Borrowed count cannot be negative");
        this.borrowedCount = borrowedCount;
    }
    public void decrementBorrowedCount() {
        if (borrowedCount > 0) {
            borrowedCount--;
        }
    }

    public void incrementBorrowedCount() { setBorrowedCount(this.borrowedCount + 1); }

    /**
     * Natural order: MOST active member first (highest borrowedCount).
     * Collections.sort(list) calls this method to decide which of two
     * members comes first.
     */
    @Override
    public int compareTo(Member other) {
        return Integer.compare(other.borrowedCount, this.borrowedCount);
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() + "{id='" + memberId + "', name='" + name
                + "', borrowedCount=" + borrowedCount + ", limit=" + maxBorrowLimit + "}";
    }
}