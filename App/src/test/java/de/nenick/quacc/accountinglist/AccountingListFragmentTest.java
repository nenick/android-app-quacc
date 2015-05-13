package de.nenick.quacc.accountinglist;

import org.junit.Test;

import java.util.Date;
import java.util.List;

import de.nenick.quacc.R;
import de.nenick.quacc.database.provider.accounting.AccountingCursor;
import de.nenick.quacc.database.provider.accounting.AccountingInterval;
import de.nenick.quacc.database.provider.accounting.AccountingType;
import de.nenick.quacc.common.util.QuAccDateFormatUtil;
import de.nenick.quacc.robolectric.RoboAppTest;
import de.nenick.quacc.robolectric.RoboSup;
import de.nenick.robolectricpages.components.RoboListViewEntry;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.Matchers.anyInt;
import static org.mockito.Mockito.mock;

public class AccountingListFragmentTest extends RoboAppTest {

    RoboSup<AccountingListActivity_, AccountingListFragment_> robo = new RoboSup<>();
    RoboAccountingListPage accountingListPage = new RoboAccountingListPage(robo);

    @Test
    public void blub() {

    }

}