package de.nenick.quacc.view.template.adapter;

import android.content.Context;
import android.database.Cursor;
import android.support.v4.widget.CursorAdapter;
import android.view.View;
import android.view.ViewGroup;

import org.androidannotations.annotations.AfterInject;
import org.androidannotations.annotations.Bean;
import org.androidannotations.annotations.EBean;
import org.androidannotations.annotations.RootContext;

import de.nenick.quacc.core.accounting.type.AccountingType;
import de.nenick.quacc.core.template.GetAccountingTemplatesFunction;
import de.nenick.quacc.database.provider.accountingtemplate.AccountingTemplateCursor;
import de.nenick.quacc.database.template.TemplateMatchingDb;
import de.nenick.quacc.core.i18n.AccountingIntervalTranslator;
import de.nenick.quacc.valueparser.ParseValueFromIntegerFunction;

@EBean
public class TemplatePlainAdapter extends CursorAdapter {

    @RootContext
    Context context;

    @Bean
    GetAccountingTemplatesFunction getAccountingTemplatesFunction;

    @Bean
    AccountingIntervalTranslator accountingIntervalTranslator;

    @Bean
    ParseValueFromIntegerFunction parseValueFromInteger;

    @Bean
    TemplateMatchingDb templateMatchingDb;

    public TemplatePlainAdapter() {
        super(null, null, true);
    }

    @AfterInject
    protected void afterInject() {
        mContext = context;
        AccountingTemplateCursor apply = getAccountingTemplatesFunction.apply();
        changeCursor(apply);
    }

    @Override
    public View newView(Context context, Cursor cursor, ViewGroup parent) {
        return TemplatePlainItemView_.build(context);
    }

    @Override
    public void bindView(View view, Context context, Cursor cursor) {
        bindView((TemplatePlainItemView) view, (AccountingTemplateCursor) cursor);
    }

    private void bindView(TemplatePlainItemView view, AccountingTemplateCursor accountingTemplateCursor) {
        AccountingType accountingType = AccountingType.valueOf(accountingTemplateCursor.getType());
        switch (accountingType) {
            case incoming:
                view.showAsIncome();
                break;
            case outgoing:
                view.showAsOutgoing();
        }

        view.setInterval(accountingIntervalTranslator.translate(accountingTemplateCursor.getInterval()));
        view.setCategory(accountingTemplateCursor.getCategoryName());
        view.setComment(accountingTemplateCursor.getComment());
        view.setAccount(accountingTemplateCursor.getAccountName());
        String speechText = templateMatchingDb.getSpeechTextForTemplate(accountingTemplateCursor.getId());
        view.setSpeechText(speechText);
    }
}
