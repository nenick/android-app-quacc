package de.nenick.quacc.view.accounting_edit;

import org.joda.time.DateTimeZone;
import org.joda.time.tz.UTCProvider;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import de.nenick.quacc.R;
import de.nenick.quacc.core.accounting.creation.CreateAccountingFunction;
import de.nenick.quacc.core.accounting.creation.CreateIntervalFunction;
import de.nenick.quacc.core.accounting.interval.AccountingInterval;
import de.nenick.quacc.core.accounting.type.AccountingType;
import de.nenick.quacc.core.common.util.QuAccDateUtil;
import de.nenick.quacc.database.provider.category.CategoryCursor;
import de.nenick.quacc.valueparser.ParseValueFromStringFunction;
import de.nenick.quacc.valueparser.ParseValueFromStringFunction.ParseResult;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;
import static org.mockito.Mockito.reset;
import static org.mockito.Mockito.verify;

public class CreateOrUpdateAccountingFeatureTest {

    @Mock
    EditAccountingView view;

    @Mock
    ParseValueFromStringFunction parseValueFromStringFunction;

    @Mock
    CreateAccountingFunction createAccountingFunction;

    @Mock
    CategoryCursor categoryCursor;

    @Mock
    CreateIntervalFunction createIntervalFunction;

    @InjectMocks
    CreateOrUpdateAccountingFeature createOrUpdateAccountingFeature;

    Map<ParseResult, Integer> parsingErrors = new HashMap<>();

    ParseValueFromStringFunction.Result valueParseResult;
    String valueString = "500";
    int value = 500;
    String account = "Bar";
    String type = AccountingType.incoming.name();
    String interval = AccountingInterval.once.name();
    long categoryId = 12;
    Date date;
    Date endDate;
    String comment = "my comment";
    boolean isEndDateActive = false;

    @Before
    public void setupBase() {
        DateTimeZone.setProvider(new UTCProvider());
        date = QuAccDateUtil.toDate(QuAccDateUtil.currentDate());
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
        for (Map.Entry<ParseResult, Integer> errorText : parsingErrors.entrySet()) {
            reset(view);
            givenValueParsResultFailed(errorText.getKey());
            whenCallFeatureForCreate();
            then(view).should().showValueParsingError(errorText.getValue());
        }
    }

    @Test
    public void shouldCreateOnlyOnceAccounting() {
        interval = AccountingInterval.once.name();
        givenViewWillReturnProperties();
        whenCallFeatureForCreate();
        verify(createAccountingFunction).apply(account, type, interval, categoryId, date, value, comment);
    }

    @Test
    public void shouldCreateIntervalAccounting() {
        interval = AccountingInterval.daily.name();
        givenViewWillReturnProperties();
        whenCallFeatureForCreate();
        verify(createIntervalFunction).apply(account, type, interval, categoryId, date, value, comment);
    }

    @Test
    public void shouldCreateIntervalAccountingWithEndDate() {
        interval = AccountingInterval.daily.name();
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
        valueParseResult = new ParseValueFromStringFunction.Result(value);
        given(parseValueFromStringFunction.apply(valueString)).willReturn(valueParseResult);
    }

    private void givenValueParsResultFailed(ParseResult parseResult) {
        given(view.getValue()).willReturn(valueString);
        valueParseResult = new ParseValueFromStringFunction.Result(parseResult);
        given(parseValueFromStringFunction.apply(valueString)).willReturn(valueParseResult);
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