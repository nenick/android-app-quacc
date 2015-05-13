package de.nenick.quacc.accounting.create;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.text.ParseException;

import de.nenick.quacc.R;
import de.nenick.quacc.common.util.QuAccDateFormatUtil;
import de.nenick.quacc.core.accounting.AddNewAccountingUc;
import de.nenick.quacc.core.accounting.GetAccountingCategoriesUc;
import de.nenick.quacc.core.accounting.GetAccountingIntervalsUc;
import de.nenick.quacc.core.accounting.GetAccountingTypesUc;
import de.nenick.quacc.core.accounting.GetAccountsUc;
import de.nenick.quacc.core.accounting.ParseAccountingValueUc;
import de.nenick.quacc.robolectric.RoboAppTest;

import static org.mockito.BDDMockito.given;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.verify;

public class CreateAccountingFragmentTest extends RoboAppTest {

    public static final String DUMMY_VALUE = "dummyValue";
    @Mock
    SpeechRecognitionFeature speechRecognitionFeature;

    @Mock
    GetAccountingCategoriesUc getAccountingCategoriesUc;

    @Mock
    GetAccountingIntervalsUc getAccountingIntervalsUc;

    @Mock
    GetAccountingTypesUc getAccountingTypesUc;

    @Mock
    GetAccountsUc getAccountsUc;

    @Mock
    CreateAccountingView view;

    @Mock
    ParseAccountingValueUc parseAccountingValueUc;

    @Mock
    AddNewAccountingUc addNewAccountingUc;

    @InjectMocks
    CreateAccountingFragment fragment;

    String accounts[] = new String[] {};
    String accountingTypes[] = new String[] {};
    String accountingIntervals[] = new String[] {};
    String accountingCategories[] = new String[] {};

    ParseAccountingValueUc.Result parseErrorDotAndCommaMix = new ParseAccountingValueUc.Result(ParseAccountingValueUc.ParseResult.DotAndCommaMix);
    ParseAccountingValueUc.Result parseErrorUnknownError = new ParseAccountingValueUc.Result(ParseAccountingValueUc.ParseResult.UnknownError);
    ParseAccountingValueUc.Result parseSuccess = new ParseAccountingValueUc.Result(500);

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void shouldStopSpeechRecognitionOnPause() {
        fragment.onViewPause();
        verify(speechRecognitionFeature).stop();
    }

    @Test
    public void shouldDelegateSpeechRecognitionFeature() {
        fragment.onViewStart();
        verify(speechRecognitionFeature).setView(view);
    }
    
    @Test
    public void shouldDestroySpeechRecognitionOnDestroy() {
        fragment.onViewFinish();
        verify(speechRecognitionFeature).destroy();
    }
    
    @Test
    public void shouldShowCurrentDateOnStart() {
        fragment.onViewStart();
        verify(view).showDate(QuAccDateFormatUtil.currentDate());
    }

    @Test
    public void shouldShowAccountsOnStart() {
        given(getAccountsUc.apply()).willReturn(accounts);
        fragment.onViewStart();
        verify(view).showAccounts(accounts);
    }

    @Test
    public void shouldShowAccountingTypesOnStart() {
        given(getAccountingTypesUc.apply()).willReturn(accountingTypes);
        fragment.onViewStart();
        verify(view).showAccountingTypes(accountingTypes);
    }

    @Test
    public void shouldShowAccountingIntervalsOnStart() {
        given(getAccountingIntervalsUc.apply()).willReturn(accountingIntervals);
        fragment.onViewStart();
        verify(view).showAccountingIntervals(accountingIntervals);
    }

    @Test
    public void shouldShowAccountingCategoriesOnStart() {
        given(getAccountingCategoriesUc.apply()).willReturn(accountingCategories);
        fragment.onViewStart();
        verify(view).showAccountingCategories(accountingCategories);
    }

    @Test
    public void shouldReportWhenValueContainsDotAndCommaMix() {
        given(view.getValue()).willReturn(DUMMY_VALUE);
        given(parseAccountingValueUc.apply(DUMMY_VALUE)).willReturn(parseErrorDotAndCommaMix);
        fragment.onConfirmButton();
        verify(view).showValueParsingError(R.string.parse_error_mix_dot_and_comma);
    }

    @Test
    public void shouldReportWhenValueHasUnknownError() {
        given(view.getValue()).willReturn(DUMMY_VALUE);
        given(parseAccountingValueUc.apply(DUMMY_VALUE)).willReturn(parseErrorUnknownError);
        fragment.onConfirmButton();
        verify(view).showValueParsingError(R.string.parse_error_unknown);
    }

    @Test
    public void shouldCreateAccountingWithGivenProperties() throws ParseException {
        given(view.getAccount()).willReturn("account");
        given(view.getAccountingType()).willReturn("accountingType");
        given(view.getAccountingInterval()).willReturn("accountingInterval");
        given(view.getAccountingCategory()).willReturn("accountingCategory");
        given(view.getValue()).willReturn(DUMMY_VALUE);
        given(view.getDate()).willReturn("04.04.2015");
        given(view.getComment()).willReturn("comment");
        given(parseAccountingValueUc.apply(DUMMY_VALUE)).willReturn(parseSuccess);
        fragment.onConfirmButton();
        verify(addNewAccountingUc).apply("account", "accountingType", "accountingInterval", "accountingCategory", QuAccDateFormatUtil.parse("04.04.2015"), 500, "comment");
    }

    @Test
    public void shouldFinishTheViewWhenReady() {
        given(view.getValue()).willReturn(DUMMY_VALUE);
        given(view.getDate()).willReturn("04.04.2015");
        given(parseAccountingValueUc.apply(DUMMY_VALUE)).willReturn(parseSuccess);
        fragment.onConfirmButton();
        verify(view).finish();
    }
}
