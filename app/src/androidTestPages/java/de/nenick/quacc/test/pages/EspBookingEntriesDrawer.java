package de.nenick.quacc.test.pages;

import de.nenick.espressomacchiato.elements.EspDrawer;
import de.nenick.espressomacchiato.elements.support.EspNavigationMenuItem;

public class EspBookingEntriesDrawer extends EspDrawer {
    public EspBookingEntriesDrawer(int drawerLayout, int drawerContent) {
        super(drawerLayout, drawerContent);
    }

    public EspNavigationMenuItem accountGirokonto() {
        return new EspNavigationMenuItem("Girokonto");
    }

    public EspNavigationMenuItem accountBar() {
        return new EspNavigationMenuItem("Bar");
    }

    public EspNavigationMenuItem accountKreditkarte() {
        return new EspNavigationMenuItem("Kreditkarte");
    }
}
