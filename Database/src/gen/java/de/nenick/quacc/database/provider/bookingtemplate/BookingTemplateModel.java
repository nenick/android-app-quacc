package de.nenick.quacc.database.provider.bookingtemplate;

import de.nenick.quacc.database.provider.base.BaseModel;

import java.util.Date;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

/**
 * Prepared booking entry.
 */
public interface BookingTemplateModel extends BaseModel {

    /**
     * Referenced account.
     */
    long getAccountId();

    /**
     * Referenced category.
     */
    long getCategoryId();

    /**
     * Optional description for this template.
     * Can be {@code null}.
     */
    @Nullable
    String getComment();

    /**
     * Possible booking interval for this template.
     * Cannot be {@code null}.
     */
    @NonNull
    String getInterval();

    /**
     * Possible booking direction for this template.
     * Cannot be {@code null}.
     */
    @NonNull
    String getDirection();
}
