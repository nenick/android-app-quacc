package de.nenick.quacc.view.accounting_edit;

import android.app.Activity;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.Spy;

import java.util.Date;

import de.nenick.quacc.core.accounting.interval.AccountingInterval;
import de.nenick.quacc.core.accounting.type.AccountingType;
import de.nenick.quacc.core.common.util.QuAccDateUtil;
import de.nenick.quacc.database.accounting.AccountingDb_;
import de.nenick.quacc.database.category.CategoryDb_;
import de.nenick.quacc.database.provider.accounting.AccountingCursor;
import de.nenick.quacc.database.provider.category.CategoryCursor;
import de.nenick.robolectric.RoboComponentTestBase;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;

public class EditAccountingFragmentSpec extends RoboComponentTestBase {

    @Mock
    EditAccountingView view;

    @Spy
    Activity activity = new EditAccountingActivity();

    @InjectMocks
    EditAccountingFragment fragment;

    AccountingDb accountingDb;

    CategoryCursor categoryCursor;

    String valueString = "500";
    int value = 500;
    String account = "Bar";
    String type = AccountingType.incoming.name();
    String interval = AccountingInterval.once.name();
    Date date;
    Date endDate;
    String comment = "my comment";
    boolean isEndDateActive = false;
    AccountingCursor accountingCursor;

    @Before
    public void setup() {
        fragment = EditAccountingFragment_.builder().build();


        MockitoAnnotations.initMocks(this);
        fragment.onCreate(null);

        categoryCursor = CategoryDb_.getInstance_(context).getAll();
        categoryCursor.moveToNext();
        accountingDb = AccountingDb_.getInstance_(context);
        givenViewWillReturnProperties();
    }

    @After
    public void teardown() {
        categoryCursor.close();
        accountingCursor.close();
    }

    @Test
    public void testBlub() {
        fragment.onConfirm();
        accountingCursor = accountingDb.getAll();
        assertThat(accountingCursor.getCount()).isEqualTo(1);
    }

    protected void givenViewWillReturnProperties() {
        given(view.getAccountingCategory()).willReturn(categoryCursor);
        given(view.getAccountingInterval()).willReturn(interval);
        given(view.getDate()).willReturn(QuAccDateUtil.toString(date));
        given(view.getComment()).willReturn(comment);
        given(view.getValue()).willReturn(valueString);
        given(view.getAccount()).willReturn(account);
        given(view.getAccountingType()).willReturn(type);
        given(view.isEndDateActive()).willReturn(isEndDateActive);
        given(view.getEndDate()).willReturn(QuAccDateUtil.toString(endDate));
    }
}
