package de.nenick.quacc.core.speechinterpreter;

import android.content.Context;

import org.androidannotations.annotations.EBean;
import org.androidannotations.annotations.RootContext;

import de.nenick.quacc.core.R;
import de.nenick.quacc.core.bookinginterval.BookingIntervalOption;

@EBean
public class RecognizeAccountingIntervalFunction {

    public static class IntervalResult extends SpeechResult {
        public IntervalResult(BookingIntervalOption value, int start, int length) {
            super(value.name(), start, length);
        }
    }

    @RootContext
    Context context;

    public SpeechResult apply(String recognizedText) {

        String once = context.getResources().getString(R.string.accounting_interval_once);
        String daily = context.getResources().getString(R.string.accounting_interval_daily);
        String weekly = context.getResources().getString(R.string.accounting_interval_weekly);
        String eachSecondWeek = context.getResources().getString(R.string.accounting_interval_eachSecondWeek);
        String monthly = context.getResources().getString(R.string.accounting_interval_monthly);
        String eachSecondMonth = context.getResources().getString(R.string.accounting_interval_eachSecondMonth);
        String eachThirdMonth = context.getResources().getString(R.string.accounting_interval_eachThirdMonth);
        String eachSixMonth = context.getResources().getString(R.string.accounting_interval_eachSixMonth);
        String yearly = context.getResources().getString(R.string.accounting_interval_yearly);

        if (recognizedText.toLowerCase().contains(once.toLowerCase())) {
            return new IntervalResult(BookingIntervalOption.once, recognizedText.toLowerCase().indexOf(once.toLowerCase()), once.length());
        }
        if (recognizedText.toLowerCase().contains(daily.toLowerCase())) {
            return new IntervalResult(BookingIntervalOption.daily, recognizedText.toLowerCase().indexOf(daily.toLowerCase()), daily.length());
        }
        if (recognizedText.toLowerCase().contains(weekly.toLowerCase())) {
            return new IntervalResult(BookingIntervalOption.weekly, recognizedText.toLowerCase().indexOf(weekly.toLowerCase()), weekly.length());
        }
        if (recognizedText.toLowerCase().contains(eachSecondWeek.toLowerCase())) {
            return new IntervalResult(BookingIntervalOption.eachSecondWeek, recognizedText.toLowerCase().indexOf(eachSecondWeek.toLowerCase()), eachSecondWeek.length());
        }
        if (recognizedText.toLowerCase().contains("monatliche")) {
            return new IntervalResult(BookingIntervalOption.monthly, recognizedText.toLowerCase().indexOf("monatliche"), "monatliche".length());
        }
        if (recognizedText.toLowerCase().contains(monthly.toLowerCase())) {
            return new IntervalResult(BookingIntervalOption.monthly, recognizedText.toLowerCase().indexOf(monthly.toLowerCase()), monthly.length());
        }
        if (recognizedText.toLowerCase().contains("jeden monat")) {
            return new IntervalResult(BookingIntervalOption.monthly, recognizedText.toLowerCase().indexOf("jeden monat"), "jeden monat".length());
        }
        if (recognizedText.toLowerCase().contains(eachSecondMonth.toLowerCase())) {
            return new IntervalResult(BookingIntervalOption.eachSecondMonth, recognizedText.toLowerCase().indexOf(eachSecondMonth.toLowerCase()), eachSecondMonth.length());
        }
        if (recognizedText.toLowerCase().contains(eachThirdMonth.toLowerCase())) {
            return new IntervalResult(BookingIntervalOption.eachThirdMonth, recognizedText.toLowerCase().indexOf(eachThirdMonth.toLowerCase()), eachThirdMonth.length());
        }
        if (recognizedText.toLowerCase().contains(eachSixMonth.toLowerCase())) {
            return new IntervalResult(BookingIntervalOption.eachSixMonth, recognizedText.toLowerCase().indexOf(eachSixMonth.toLowerCase()), eachSixMonth.length());
        }
        if (recognizedText.toLowerCase().contains(yearly.toLowerCase())) {
            return new IntervalResult(BookingIntervalOption.yearly, recognizedText.toLowerCase().indexOf(yearly.toLowerCase()), yearly.length());
        }

        return null;
    }

}
