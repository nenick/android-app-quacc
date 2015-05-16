package de.nenick.quacc.accounting.create;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.text.ParseException;

import de.nenick.quacc.R;
import de.nenick.quacc.accounting.create.functions.CreateAccountingFunction;
import de.nenick.quacc.accounting.create.functions.GetAccountingCategoriesFunction;
import de.nenick.quacc.accounting.create.functions.GetAccountingIntervalsFunction;
import de.nenick.quacc.accounting.create.functions.GetAccountingTypesFunction;
import de.nenick.quacc.accounting.create.functions.GetAccountsFunction;
import de.nenick.quacc.accounting.create.functions.ParseAccountingValueFunction;
import de.nenick.quacc.common.util.QuAccDateUtil;

import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;

public class CreateAccountingFragmentTest {

    public static final String DUMMY_VALUE = "dummyValue";

    @InjectMocks
    CreateAccountingFragment fragment;

    @Mock
    SpeechRecognitionFeature speechRecognitionFeature;

    @Mock
    GetAccountingCategoriesFunction getAccountingCategoriesFunction;

    @Mock
    GetAccountingIntervalsFunction getAccountingIntervalsFunction;

    @Mock
    GetAccountingTypesFunction getAccountingTypesFunction;

    @Mock
    GetAccountsFunction getAccountsFunction;

    @Mock
    CreateAccountingView view;

    @Mock
    ParseAccountingValueFunction parseAccountingValueFunction;

    @Mock
    CreateAccountingFunction createAccountingFunction;

    String accounts[] = new String[]{};
    String accountingTypes[] = new String[]{};
    String accountingIntervals[] = new String[]{};
    String accountingCategories[] = new String[]{};

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void shouldStopSpeechRecognitionOnPause() {
        fragment.onViewPause();
        verify(speechRecognitionFeature).onViewPause();
    }

    @Test
    public void shouldDelegateSpeechRecognitionFeature() {
        fragment.onViewStart();
        verify(speechRecognitionFeature).setView(view);
    }

    @Test
    public void shouldDestroySpeechRecognitionOnDestroy() {
        fragment.onViewFinish();
        verify(speechRecognitionFeature).onViewFinish();
    }

    @Test
    public void shouldShowCurrentDateOnStart() {
        fragment.onViewStart();
        verify(view).showDate(QuAccDateUtil.currentDate());
    }

    @Test
    public void shouldShowAccountsOnStart() {
        given(getAccountsFunction.apply()).willReturn(accounts);
        fragment.onViewStart();
        verify(view).showAccounts(accounts);
    }

    @Test
    public void shouldShowAccountingTypesOnStart() {
        given(getAccountingTypesFunction.apply()).willReturn(accountingTypes);
        fragment.onViewStart();
        verify(view).showAccountingTypes(accountingTypes);
    }

    @Test
    public void shouldShowAccountingIntervalsOnStart() {
        given(getAccountingIntervalsFunction.apply()).willReturn(accountingIntervals);
        fragment.onViewStart();
        verify(view).showAccountingIntervals(accountingIntervals);
    }

    @Test
    public void shouldShowAccountingCategoriesOnStart() {
        given(getAccountingCategoriesFunction.apply()).willReturn(accountingCategories);
        fragment.onViewStart();
        verify(view).showAccountingCategories(accountingCategories);
    }

    @Test
    public void shouldReportWhenValueIsEmpty() {
        given(view.getValue()).willReturn(DUMMY_VALUE);
        givenValueParseResult(ParseAccountingValueFunction.ParseResult.EmptyInput);
        fragment.onConfirmNewAccounting();
        verify(view).showValueParsingError(R.string.parse_error_missing_value);
    }

    @Test
    public void shouldReportWhenValueIsZero() {
        given(view.getValue()).willReturn(DUMMY_VALUE);
        givenValueParseResult(ParseAccountingValueFunction.ParseResult.ZeroValue);
        fragment.onConfirmNewAccounting();
        verify(view).showValueParsingError(R.string.parse_error_missing_value);
    }

    @Test
    public void shouldReportWhenValueInvalidChar() {
        given(view.getValue()).willReturn(DUMMY_VALUE);
        givenValueParseResult(ParseAccountingValueFunction.ParseResult.InvalidChar);
        fragment.onConfirmNewAccounting();
        verify(view).showValueParsingError(R.string.parse_error_invalid_char);
    }

    @Test
    public void shouldReportWhenValueInvalidFormat() {
        given(view.getValue()).willReturn(DUMMY_VALUE);
        givenValueParseResult(ParseAccountingValueFunction.ParseResult.InvalidFormat);
        fragment.onConfirmNewAccounting();
        verify(view).showValueParsingError(R.string.parse_error_invalid_format);
    }

    @Test
    public void shouldReportWhenValueHasUnknownError() {
        given(view.getValue()).willReturn(DUMMY_VALUE);
        givenValueParseResult(ParseAccountingValueFunction.ParseResult.UnknownError);
        fragment.onConfirmNewAccounting();
        verify(view).showValueParsingError(R.string.parse_error_unknown);
    }

    @Test
    public void shouldCreateAccountingWithGivenProperties() throws ParseException {
        given(view.getAccount()).willReturn("account");
        given(view.getAccountingType()).willReturn("accountingType");
        given(view.getAccountingInterval()).willReturn("accountingInterval");
        given(view.getAccountingCategory()).willReturn("accountingCategory");
        given(view.getDate()).willReturn("04.04.2015");
        given(view.getComment()).willReturn("comment");
        given(view.getValue()).willReturn(DUMMY_VALUE);
        givenValueParseResult(500);
        fragment.onConfirmNewAccounting();
        verify(createAccountingFunction).apply("account", "accountingType", "accountingInterval", "accountingCategory", QuAccDateUtil.parse("04.04.2015"), 500, "comment");
    }

    @Test
    public void shouldFinishTheViewWhenReady() {
        given(view.getValue()).willReturn(DUMMY_VALUE);
        given(view.getDate()).willReturn("04.04.2015");
        givenValueParseResult(ParseAccountingValueFunction.ParseResult.Successful);
        fragment.onConfirmNewAccounting();
        verify(view).finish();
    }

    private void givenValueParseResult(int value) {
        given(parseAccountingValueFunction.apply(DUMMY_VALUE)).willReturn(new ParseAccountingValueFunction.Result(value));
    }

    private void givenValueParseResult(ParseAccountingValueFunction.ParseResult type) {
        given(parseAccountingValueFunction.apply(DUMMY_VALUE)).willReturn(new ParseAccountingValueFunction.Result(type));
    }
}
