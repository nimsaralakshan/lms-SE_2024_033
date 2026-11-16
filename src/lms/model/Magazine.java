package lms.model;

/**
 * Magazine - a NEW kind of item added this week.
 *
 * Try this experiment: delete the calculateLateFee() method below and
 * try to compile. Because LibraryItem declares it abstract, the compiler
 * refuses: "Magazine is not abstract and does not override abstract method".
 */
public class Magazine extends LibraryItem {
    private int issueNumber;

    public Magazine(String id, String title, int issueNumber) {
        super(id, title);
        this.issueNumber = issueNumber;
    }

    public int getIssueNumber() { return issueNumber; }

    @Override
    public double calculateLateFee(int daysLate) {
        return daysLate * 5.0;
    }
}