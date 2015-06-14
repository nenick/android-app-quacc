package de.nenick.quacc.view.category;

import android.content.Intent;

import org.robolectric.RuntimeEnvironment;

import de.nenick.quacc.view.accounting_create.CreateAccountingActivity;
import de.nenick.robolectric.RoboSup;
import de.nenick.robolectric.RoboSupPage;

public class RoboCategoriesPage extends RoboSupPage<CategoriesActivity_, CategoriesFragment_> {

    public RoboCategoriesPage(RoboSup<CategoriesActivity_, CategoriesFragment_> robo) {
        super(robo, CreateAccountingActivity.TAG_FRAGMENT);
    }

    public static Intent Intent() {
        return CategoriesActivity_.intent(RuntimeEnvironment.application).get();
    }
}
