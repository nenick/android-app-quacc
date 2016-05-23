package de.nenick.quacc.test.pages;


import de.nenick.espressomacchiato.elements.EspButton;
import de.nenick.espressomacchiato.elements.EspPage;
import de.nenick.espressomacchiato.elements.EspTextView;
import de.nenick.espressomacchiato.elements.EspView;
import de.nenick.espressomacchiato.elements.support.EspRecyclerView;
import de.nenick.espressomacchiato.elements.support.EspRecyclerViewItem;
import de.nenick.quacc.R;

import static android.support.test.espresso.matcher.ViewMatchers.withId;

public class EspBookingEntriesPage extends EspPage {


    public EspBookingEntriesPage() {
        super(R.id.fragment_booking_entries);
    }

    public EspBookingList list() {
        return new EspBookingList(EspRecyclerView.byId(R.id.recycler_view));
    }

    public EspBookingEntriesDrawer drawer() {
        return new EspBookingEntriesDrawer(R.id.activity_booking_entries, R.id.navigation_drawer);
    }

    public EspButton addButton() {
        return EspButton.byId(R.id.btn_add_booking);
    }

    public QuAccSwitchDate switchDate() {
        return new QuAccSwitchDate(EspView.byId(R.id.bookingDateSelectorLayout));
    }

    public QuAccToolbar toolbar() {
        return new QuAccToolbar();
    }

    public static class QuAccToolbar {

        public EspButton switchDateToggle() {
            return EspButton.byId(R.id.dateSelectorToggle);
        }

        public EspView report() {
            return EspView.byId(R.id.report);
        }
    }

    public static class QuAccSwitchDate extends EspView {

        public QuAccSwitchDate(EspView template) {
            super(template);
        }

        public EspTextView month() {
            return EspTextView.byId(R.id.month);
        }

        public EspTextView monthDown() {
            return EspTextView.byId(R.id.monthDown);
        }

        public EspTextView monthUp() {
            return EspTextView.byId(R.id.monthUp);
        }

        public EspTextView year() {
            return EspTextView.byId(R.id.year);
        }

        public EspTextView yearDown() {
            return EspTextView.byId(R.id.yearDown);
        }

        public EspTextView yearUp() {
            return EspTextView.byId(R.id.yearUp);
        }
    }

    public static class EspBookingList extends EspRecyclerView {

        public EspBookingList(EspRecyclerView base) {
            super(base.resourceId());
        }

        @Override
        public EspBookingListItem itemByVisibleIndex(int index) {
            return new EspBookingListItem(super.itemByVisibleIndex(index));
        }
    }

    public static class EspBookingListItem extends EspRecyclerViewItem {

        public EspBookingListItem(EspRecyclerViewItem base) {
            super(base);
        }

        public EspTextView category() {
            return new EspTextView(baseMatcherForItemChild(withId(R.id.category)));
        }

        public EspTextView date() {
            return new EspTextView(baseMatcherForItemChild(withId(R.id.date)));
        }

        public EspTextView dateSeparator() {
            return new EspTextView(baseMatcherForItemChild(withId(R.id.dateSeparator)));
        }

        public EspTextView endDate() {
            return new EspTextView(baseMatcherForItemChild(withId(R.id.endDate)));
        }

        public EspTextView amount() {
            return new EspTextView(baseMatcherForItemChild(withId(R.id.amount)));
        }
    }
}
