package de.nenick.quacc.speechrecognition;

import android.content.Context;

import org.androidannotations.annotations.EBean;
import org.androidannotations.annotations.RootContext;

import de.nenick.quacc.R;
import de.nenick.quacc.accounting.type.AccountingType;

@EBean
public class RecognizeAccountingTypeFunction {

    @RootContext
    Context context;

    public SpeechResult apply(String recognizedText) {

        String incoming = getHumanReadableString(R.string.accounting_type_incoming);
        String outgoing = getHumanReadableString(R.string.accounting_type_outgoing);
        String transfer = getHumanReadableString(R.string.accounting_type_transfer);

        if (recognizedText.toLowerCase().contains(incoming.toLowerCase())) {
            return new SpeechResult(AccountingType.incoming.name(), recognizedText.toLowerCase().indexOf(incoming.toLowerCase()), incoming.length());
        }
        if (recognizedText.toLowerCase().contains(outgoing.toLowerCase())) {
            return new SpeechResult(AccountingType.outgoing.name(), recognizedText.toLowerCase().indexOf(outgoing.toLowerCase()), outgoing.length());
        }
        if (recognizedText.toLowerCase().contains(transfer.toLowerCase())) {
            return new SpeechResult(AccountingType.transfer.name(), recognizedText.toLowerCase().indexOf(transfer.toLowerCase()), transfer.length());
        }

        return null;
    }

    String getHumanReadableString(int accounting_type_incoming) {
        return context.getString(accounting_type_incoming);
    }

}
