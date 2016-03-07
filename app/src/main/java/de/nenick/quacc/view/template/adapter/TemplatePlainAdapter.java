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

import de.nenick.quacc.core.bookingentry.direction.BookingDirectionOption;
import de.nenick.quacc.core.i18n.AccountingIntervalTranslator;
import de.nenick.quacc.core.template.GetBookingTemplatesFunction;
import de.nenick.quacc.database.bookingtemplatekeyword.BookingTemplateKeywordRepository;
import de.nenick.quacc.database.bookingtemplatekeyword.BookingTemplateKeywordSpecByTemplateId;
import de.nenick.quacc.database.provider.bookingtemplate.BookingTemplateCursor;
import de.nenick.quacc.database.provider.bookingtemplatekeyword.BookingTemplateKeywordCursor;
import de.nenick.toolscollection.amountparser.ParseValueFromIntegerFunction;

@EBean
public class TemplatePlainAdapter extends CursorAdapter {

    @RootContext
    Context context;

    @Bean
    GetBookingTemplatesFunction getBookingTemplatesFunction;

    @Bean
    AccountingIntervalTranslator accountingIntervalTranslator;

    @Bean
    BookingTemplateKeywordRepository bookingTemplateKeywordRepository;

    public TemplatePlainAdapter() {
        super(null, null, true);
    }

    @AfterInject
    protected void afterInject() {
        mContext = context;

        changeCursor(getBookingTemplatesFunction.apply());
    }

    @Override
    public View newView(Context context, Cursor cursor, ViewGroup parent) {
        return TemplatePlainItemView_.build(context);
    }

    @Override
    public void bindView(View view, Context context, Cursor cursor) {
        bindView((TemplatePlainItemView) view, (BookingTemplateCursor) cursor);
    }

    private void bindView(TemplatePlainItemView view, BookingTemplateCursor accountingTemplateCursor) {
        BookingDirectionOption bookingDirectionOption = BookingDirectionOption.valueOf(accountingTemplateCursor.getDirection());
        switch (bookingDirectionOption) {
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
        BookingTemplateKeywordCursor query = bookingTemplateKeywordRepository.query(new BookingTemplateKeywordSpecByTemplateId(accountingTemplateCursor.getId()));
        query.moveToNext();
        if(query.getCount() != 1) {
            throw new UnsupportedOperationException("more than one keyword not ready yet");
        }
        String speechText = query.getText();
        query.close();
        view.setSpeechText(speechText);
    }
}
