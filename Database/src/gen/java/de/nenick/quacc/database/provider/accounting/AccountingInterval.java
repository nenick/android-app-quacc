package de.nenick.quacc.database.provider.accounting;

/**
 * Possible values for the {@code accounting_interval} column of the {@code accounting} table.
 */
public enum AccountingInterval {
    /**
     *
     */
    Einmahlig,

    /**
     *
     */
    Wöchentlich,

    /**
     *
     */
    Monatlich,

    /**
     *
     */
    Alle_3_Monate,

}