package de.nenick.quacc.view.accounting_edit;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import de.nenick.quacc.R;
import de.nenick.quacc.core.bookingentry.creation.CreateBookingEntryFunction;
import de.nenick.quacc.core.bookingentry.creation.CreateIntervalFunction;
import de.nenick.quacc.core.bookingentry.direction.BookingDirectionOption;
import de.nenick.quacc.core.bookinginterval.BookingIntervalOption;
import de.nenick.quacc.core.common.util.QuAccDateUtil;
import de.nenick.quacc.database.provider.category.CategoryCursor;
import de.nenick.quacc.tools.AmountParser;
import de.nenick.toolscollection.amountparser.AmountFromStringResult;
import de.nenick.toolscollection.amountparser.AmountFromStringResult.ParseResult;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.reset;
import static org.mockito.Mockito.verify;

public class CreateOrUpdateAccountingFeatureTest {

    @Mock
    EditAccountingView view;

    @Mock
    AmountParser amountParser;

    @Mock
    CreateBookingEntryFunction createBookingEntryFunction;

    @Mock
    CategoryCursor categoryCursor;

    @Mock
    CreateIntervalFunction createIntervalFunction;

    @InjectMocks
    CreateOrUpdateAccountingFeature createOrUpdateAccountingFeature;

    QuAccDateUtil quAccDateUtil = new QuAccDateUtil();

    Map<ParseResult, Integer> parsingErrors = new HashMap<>();

    AmountFromStringResult valueParseResult;
    String valueString = "500";
    int value = 500;
    String account = "Bar";
    String type = BookingDirectionOption.incoming.name();
    String interval = BookingIntervalOption.once.name();
    long categoryId = 12;
    Date date;
    Date endDate;
    String comment = "my comment";
    boolean isEndDateActive = false;

    @Before
    public void setupBase() {
        date = QuAccDateUtil.toDate(quAccDateUtil.currentDate());
        endDate = date;
        MockitoAnnotations.initMocks(this);
        givenViewWillReturnProperties();
        given(categoryCursor.getId()).willReturn(categoryId);
    }

    @Before
    public void setup() {
        givenParsingErrorMapWithExpectedErrorText();
        givenValueParseResultSuccess();
    }

    @Test
    public void shouldCloseKeyboard() throws Exception {
        whenCallFeatureForCreate();
        then(view).should().closeSoftKeyboard();
    }

    private void whenCallFeatureForCreate() {
        createOrUpdateAccountingFeature.apply(0, null, view);
    }

    @Test
    public void shouldFinishView() throws Exception {
        whenCallFeatureForCreate();
        then(view).should().finish();
    }

    @Test
    public void shouldShowValueParsingErrors() throws Exception {
        assertMapContainsAllParsingErrorTypes();
        CategoryCursor categoryCursor = mock(CategoryCursor.class);
        given(categoryCursor.getId()).willReturn(42l);

        for (Map.Entry<ParseResult, Integer> errorText : parsingErrors.entrySet()) {
            reset(view);
            given(view.getDate()).willReturn("01.01.2016");
            given(view.getAccountingInterval()).willReturn(BookingIntervalOption.once.name());
            given(view.getAccountingCategory()).willReturn(categoryCursor);

            givenValueParsResultFailed(errorText.getKey());
            whenCallFeatureForCreate();
            then(view).should().showValueParsingError(errorText.getValue());
        }
    }

    @Test
    public void shouldCreateOnlyOnceAccounting() {
        interval = BookingIntervalOption.once.name();
        givenViewWillReturnProperties();
        whenCallFeatureForCreate();
        verify(createBookingEntryFunction).apply(account, type, interval, categoryId, date, value, comment);
    }

    @Test
    public void shouldCreateIntervalAccounting() {
        interval = BookingIntervalOption.daily.name();
        givenViewWillReturnProperties();
        whenCallFeatureForCreate();
        verify(createIntervalFunction).apply(account, type, interval, categoryId, date, value, comment);
    }

    @Test
    public void shouldCreateIntervalAccountingWithEndDate() {
        interval = BookingIntervalOption.daily.name();
        isEndDateActive = true;
        givenViewWillReturnProperties();
        whenCallFeatureForCreate();
        verify(createIntervalFunction).applyWithEndDate(account, type, interval, categoryId, date, endDate, value, comment);
    }

    private void assertMapContainsAllParsingErrorTypes() {
        for (ParseResult parseResult : ParseResult.values()) {
            if(parseResult == ParseResult.Successful) continue;
            assertThat(parsingErrors).containsKey(parseResult);
        }
    }

    private void givenValueParseResultSuccess() {
        valueParseResult = new AmountFromStringResult(value);
        given(amountParser.asInteger(valueString)).willReturn(valueParseResult);
    }

    private void givenValueParsResultFailed(ParseResult parseResult) {
        given(view.getValue()).willReturn(valueString);
        valueParseResult = new AmountFromStringResult(parseResult);
        given(amountParser.asInteger(valueString)).willReturn(valueParseResult);
    }

    private void givenParsingErrorMapWithExpectedErrorText() {
        parsingErrors.put(ParseResult.EmptyInput, R.string.parse_error_missing_value);
        parsingErrors.put(ParseResult.InvalidChar, R.string.parse_error_invalid_char);
        parsingErrors.put(ParseResult.InvalidFormat, R.string.parse_error_invalid_format);
        parsingErrors.put(ParseResult.UnknownError, R.string.parse_error_unknown);
        parsingErrors.put(ParseResult.ZeroValue, R.string.parse_error_zero_value);
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