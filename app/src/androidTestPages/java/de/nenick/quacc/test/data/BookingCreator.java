package de.nenick.quacc.test.data;

import android.support.test.InstrumentationRegistry;

import org.joda.time.DateTime;

import java.util.Date;

import de.nenick.quacc.core.bookingentry.creation.CreateBookingEntryFunction_;
import de.nenick.quacc.core.bookingentry.direction.BookingDirectionOption;
import de.nenick.quacc.core.bookinginterval.BookingIntervalOption;

public class BookingCreator {

    private TestData.Account account = TestData.Account.Girokonto;
    private BookingDirectionOption direction = BookingDirectionOption.outgoing;
    private BookingIntervalOption interval = BookingIntervalOption.once;
    private TestData.Category category = TestData.Category.Allgemein;
    private Date date = DateTime.now().withTimeAtStartOfDay().toDate();
    private int amount = 40;
    private String comment = "dummy entry";

    public static BookingCreator prepare() {
        return new BookingCreator();
    }

    public BookingCreator account(TestData.Account account) {
        this.account = account;
        return this;
    }

    public BookingCreator direction(BookingDirectionOption direction) {
        this.direction = direction;
        return this;
    }

    public BookingCreator interval(BookingIntervalOption interval) {
        this.interval = interval;
        return this;
    }

    public BookingCreator category(TestData.Category category) {
        this.category = category;
        return this;
    }

    public BookingCreator date(Date date) {
        this.date = date;
        return this;
    }

    public BookingCreator amount(int amount) {
        this.amount = amount;
        return this;
    }

    public BookingCreator comment(String comment) {
        this.comment = comment;
        return this;
    }

    public void create() {
        InstrumentationRegistry.getInstrumentation().waitForIdleSync();
        CreateBookingEntryFunction_.getInstance_(InstrumentationRegistry.getContext())
                .apply(account.name(), direction.name(),
                        interval.name(), category.ordinal(),
                        date, amount, "");
    }
}
