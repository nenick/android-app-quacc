package de.nenick.quacc.test.pages;

import de.nenick.espressopages.pages.EspDrawer;
import de.nenick.espressopages.pages.EspNavigationMenuItem;

public class EspBookingEntriesDrawer extends EspDrawer {
    public EspBookingEntriesDrawer(int drawerLayout, int drawerContent) {
        super(drawerLayout, drawerContent);
    }

    public EspNavigationMenuItem accountGirokonto() {
        return navigationMenuItem("Girokonto");
    }

    public EspNavigationMenuItem accountBar() {
        return navigationMenuItem("Bar");
    }

    public EspNavigationMenuItem accountKreditkarte() {
        return navigationMenuItem("Kreditkarte");
    }
}
