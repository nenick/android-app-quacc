package de.nenick.quacc.view.template.adapter;

import android.content.Context;
import android.widget.RelativeLayout;
import android.widget.TextView;

import org.androidannotations.annotations.EViewGroup;
import org.androidannotations.annotations.ViewById;

import de.nenick.quacc.R;

@EViewGroup(R.layout.item_accounting_template)
public class TemplatePlainItemView extends RelativeLayout {

    @ViewById(R.id.account)
    TextView account;

    @ViewById(R.id.interval)
    TextView interval;

    @ViewById(R.id.category)
    TextView category;

    @ViewById(R.id.comment)
    TextView comment;

    @ViewById(R.id.speechText)
    TextView speechText;

    public TemplatePlainItemView(Context context) {
        super(context);
    }

    public void setInterval(String txt) {
        interval.setText(txt);
    }

    public void setCategory(String txt) {
        category.setText(txt);
    }

    public void setComment(String txt) {
        comment.setText(txt);
    }

    public void setSpeechText(String txt) {
        speechText.setText(txt);
    }

    public void setAccount(String txt) {
        account.setText(txt);
    }

    public void showAsIncome() {
        setBackgroundColor(getResources().getColor(R.color.positiveBackground));
        interval.setTextColor(getResources().getColor(R.color.positiveText));
        account.setTextColor(getResources().getColor(R.color.positiveText));
        category.setTextColor(getResources().getColor(R.color.positiveText));
        comment.setTextColor(getResources().getColor(R.color.positiveTextSmall));
    }

    public void showAsOutgoing() {
        setBackgroundColor(getResources().getColor(R.color.negativeBackground));
        interval.setTextColor(getResources().getColor(R.color.negativeText));
        account.setTextColor(getResources().getColor(R.color.negativeText));
        category.setTextColor(getResources().getColor(R.color.negativeText));
        comment.setTextColor(getResources().getColor(R.color.negativeTextSmall));
    }

    public void showAsTransfer() {
        setBackgroundColor(getResources().getColor(R.color.neutralBackground));
        interval.setTextColor(getResources().getColor(R.color.neutralBackground));
        account.setTextColor(getResources().getColor(R.color.neutralBackground));
        category.setTextColor(getResources().getColor(R.color.neutralBackground));
        comment.setTextColor(getResources().getColor(R.color.neutralBackground));
    }
}
