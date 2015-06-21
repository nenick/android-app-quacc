package de.nenick.quacc.database.provider.templatematching;

import de.nenick.quacc.database.provider.base.BaseModel;

import java.util.Date;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

/**
 * Data model for the {@code template_matching} table.
 */
public interface TemplateMatchingModel extends BaseModel {

    /**
     * Get the {@code text} value.
     * Cannot be {@code null}.
     */
    @NonNull
    String getText();

    /**
     * Get the {@code accounting_template_id} value.
     */
    long getAccountingTemplateId();
}
