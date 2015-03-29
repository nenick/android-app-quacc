package de.nenick.quacc.database;

import org.junit.Before;
import org.junit.Test;

import de.nenick.quacc.database.provider.accountingcategory.AccountingCategoryCursor;
import de.nenick.quacc.database.robolectric.RoboDatabaseTest;

import static org.assertj.core.api.Assertions.assertThat;

public class AccountingCategoryRepositoryTest extends RoboDatabaseTest {

    AccountingCategoryRepository repository;

    @Before
    public void setUp() {
        repository = AccountingCategoryRepository_.getInstance_(context);
    }

    @Test
    public void shouldReturnAccounts() {
        AccountingCategoryCursor accountings = repository.getAccountingCategories();
        assertThat(accountings.getCount()).isPositive();
    }
}