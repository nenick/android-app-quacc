package de.nenick.quacc.test.pages;


import de.nenick.espressomacchiato.elements.EspButton;
import de.nenick.espressomacchiato.elements.EspPage;
import de.nenick.espressomacchiato.elements.EspTextView;
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
        return new EspBookingEntriesDrawer(R.id.drawer_layout, R.id.navigation_drawer);
    }

    public EspButton addButton() {
        return EspButton.byId(R.id.btn_add_booking);
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