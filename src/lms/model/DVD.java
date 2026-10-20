package lms.model;

public class DVD extends LibraryItem {
    private int durationMinutes;

    public DVD(String id, String title, int durationMinutes) {
        super(id, title);

        if (durationMinutes <= 0) {
            throw new IllegalArgumentException("Duration must be positive");
        }

        this.durationMinutes = durationMinutes;
    }

    public int getDurationMinutes() {
        return durationMinutes;
    }

    @Override
    public double calculateLateFee(int daysLate) {
        return daysLate * 25.0;
    }
}
