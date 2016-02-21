package de.nenick.quacc.core.accounting.create.functions;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Date;

import de.nenick.quacc.core.bookingentry.creation.CreateBookingEntryFunction;

import static org.assertj.core.api.Assertions.fail;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;

public class CreateBookingEntryFunctionTest {

    @Test
    public void work() {
        fail("not ready");
    }
    /*
    public static final String ACCOUNTING_TYPE = "Übertrag";
    public static final String ACCOUNTING_INTERVAL = "Monatlich";
    Date DATE = new Date();
    public static final String ACCOUNT = "Bar";
    public static final long CATEGORY_ID = 2l;
    public static final long ACCOUNT_ID = 1l;
    public static final String COMMENT = "money money";
    public static final int VALUE = 1212;
    @InjectMocks
    CreateBookingEntryFunction function;

    @Mock
    AccountRepository accountRepository;

    @Mock
    BookingEntryRepository accountingDb;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void shouldAddAccounting() {
        given(accountRepository.query(ACCOUNT)).willReturn(ACCOUNT_ID);
        function.apply(ACCOUNT, ACCOUNTING_TYPE, ACCOUNTING_INTERVAL, CATEGORY_ID, DATE, VALUE, COMMENT);
        verify(accountingDb).insert(ACCOUNT_ID, ACCOUNTING_TYPE, ACCOUNTING_INTERVAL, CATEGORY_ID, DATE, COMMENT, 1212);
    }
*/
}