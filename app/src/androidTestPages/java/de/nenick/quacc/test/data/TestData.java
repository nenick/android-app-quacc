package de.nenick.quacc.test.data;

public class TestData {

    public enum Account {
        Bar, Girokonto, Kreditkarte
    }

    public enum Category {
        none,
        Allgemein
    }

    public static BookingCreator bookingEntry() {
        return BookingCreator.prepare();
    }
}
