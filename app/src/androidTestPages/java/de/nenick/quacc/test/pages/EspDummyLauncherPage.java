package de.nenick.quacc.test.pages;

import de.nenick.espressomacchiato.elements.EspButton;
import de.nenick.espressomacchiato.elements.EspPage;
import de.nenick.quacc.R;

public class EspDummyLauncherPage extends EspPage {

    public EspDummyLauncherPage() {
        super(R.id.btn_start_app);
    }

    public void clickStartApp() {
        new EspButton(R.id.btn_start_app).click();
    }
}
