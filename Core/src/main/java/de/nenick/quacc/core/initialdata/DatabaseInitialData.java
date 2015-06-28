package de.nenick.quacc.core.initialdata;

import android.database.sqlite.SQLiteDatabase;

import org.androidannotations.annotations.Bean;
import org.androidannotations.annotations.EBean;

import java.util.ArrayList;
import java.util.List;

import de.nenick.quacc.core.accounting.interval.AccountingInterval;
import de.nenick.quacc.core.accounting.type.AccountingType;
import de.nenick.quacc.core.category.CreateCategoryFunction;
import de.nenick.quacc.database.provider.account.AccountColumns;
import de.nenick.quacc.database.provider.account.AccountContentValues;
import de.nenick.quacc.database.provider.base.AbstractContentValues;
import de.nenick.quacc.database.provider.category.CategoryColumns;

@EBean
public class DatabaseInitialData {

    @Bean
    CreateCategoryFunction categoryFunction;

    public void insert(SQLiteDatabase database) {
        addAccountData(database);
        addCategoryData(database);
    }

    List<AbstractContentValues> data = new ArrayList<>();

    private void addCategoryData(SQLiteDatabase database) {

        addCategory(AccountingType.transfer, AccountingInterval.all, "Allgemein", "Kontoausgleich");
        addCategory(AccountingType.outgoing, AccountingInterval.all, "Allgemein", "Barauszahlung");
        addCategory(AccountingType.all, AccountingInterval.all, "Allgemein", "Sonstiges");
        addCategory(AccountingType.incoming, AccountingInterval.all, "Allgemein", "Bareinzahlung");

        addCategory(AccountingType.outgoing, AccountingInterval.once, "Alltag", "Accessoirs");
        addCategory(AccountingType.outgoing, AccountingInterval.once, "Alltag", "Kleidung");
        addCategory(AccountingType.outgoing, AccountingInterval.all, "Alltag", "Schuhe");

        addCategory(AccountingType.incoming, AccountingInterval.all, "Auktionen", "eBay");

        addCategory(AccountingType.outgoing, AccountingInterval.all, "Auto", "Benzin");
        addCategory(AccountingType.outgoing, AccountingInterval.once, "Auto", "Werkstatt");
        addCategory(AccountingType.outgoing, AccountingInterval.once, "Auto", "Zubehörteile");
        addCategory(AccountingType.outgoing, AccountingInterval.all, "Auto", "Kfz-Versicherung");
        addCategory(AccountingType.outgoing, AccountingInterval.all, "Auto", "Kredit");
        addCategory(AccountingType.outgoing, AccountingInterval.all, "Auto", "Kfz-Steuer");
        addCategory(AccountingType.outgoing, AccountingInterval.once, "Auto", "Bußgeld");
        addCategory(AccountingType.outgoing, AccountingInterval.all, "Auto", "Automobilclub");

        addCategory(AccountingType.outgoing, AccountingInterval.all, "Bank", "Kontoführungsgebühren");
        addCategory(AccountingType.outgoing, AccountingInterval.all, "Bank", "Zinsen");

        addCategory(AccountingType.outgoing, AccountingInterval.all, "Beruf", "Fahrkosten");
        addCategory(AccountingType.outgoing, AccountingInterval.all, "Beruf", "Werbungskosten");
        addCategory(AccountingType.outgoing, AccountingInterval.all, "Beruf", "Serverkosten");
        addCategory(AccountingType.outgoing, AccountingInterval.all, "Beruf", "Fachbücher");
        addCategory(AccountingType.outgoing, AccountingInterval.all, "Beruf", "Sonstiges");

        addCategory(AccountingType.incoming, AccountingInterval.all, "Einkommen", "Lohn / Gehalt");
        addCategory(AccountingType.incoming, AccountingInterval.all, "Einkommen", "Mieteinnahmen");
        addCategory(AccountingType.incoming, AccountingInterval.monthly, "Einkommen", "Unterhalt");
        addCategory(AccountingType.incoming, AccountingInterval.monthly, "Einkommen", "Kindergeld");
        addCategory(AccountingType.incoming, AccountingInterval.once, "Einkommen", "Schenkung");
        addCategory(AccountingType.incoming, AccountingInterval.all, "Einkommen", "Taschengeld");
        addCategory(AccountingType.incoming, AccountingInterval.all, "Einkommen", "Freiberuflich");

        addCategory(AccountingType.outgoing, AccountingInterval.once, "Fortbildung", "Literatur");
        addCategory(AccountingType.outgoing, AccountingInterval.all, "Fortbildung", "Studiengebühren");
        addCategory(AccountingType.outgoing, AccountingInterval.once, "Fortbildung", "Fahrtkosten");
        addCategory(AccountingType.outgoing, AccountingInterval.once, "Fortbildung", "Anschaffungen");
        addCategory(AccountingType.outgoing, AccountingInterval.once, "Fortbildung", "Bücher und Schreibmaterial");

        addCategory(AccountingType.outgoing, AccountingInterval.once, "Freizeit", "Urlaub");
        addCategory(AccountingType.outgoing, AccountingInterval.once, "Freizeit", "Bücher");
        addCategory(AccountingType.outgoing, AccountingInterval.once, "Freizeit", "Kino / Theater");
        addCategory(AccountingType.outgoing, AccountingInterval.all, "Freizeit", "Zeitschriften");
        addCategory(AccountingType.outgoing, AccountingInterval.all, "Freizeit", "Fitnessstudio");
        addCategory(AccountingType.outgoing, AccountingInterval.all, "Freizeit", "Sport");
        addCategory(AccountingType.outgoing, AccountingInterval.once, "Freizeit", "Geschenke");
        addCategory(AccountingType.outgoing, AccountingInterval.once, "Freizeit", "Musik / Video");
        addCategory(AccountingType.outgoing, AccountingInterval.once, "Freizeit", "Software / Spiele");
        addCategory(AccountingType.outgoing, AccountingInterval.once, "Freizeit", "Computer");
        addCategory(AccountingType.outgoing, AccountingInterval.all, "Freizeit", "Vereinsbeträge");
        addCategory(AccountingType.outgoing, AccountingInterval.once, "Freizeit", "Frisör");
        addCategory(AccountingType.outgoing, AccountingInterval.all, "Freizeit", "Zigaretten");
        addCategory(AccountingType.outgoing, AccountingInterval.once, "Freizeit", "Glücksspiel");
        addCategory(AccountingType.outgoing, AccountingInterval.once, "Freizeit", "Anschaffungen");
        addCategory(AccountingType.outgoing, AccountingInterval.once, "Freizeit", "Sportgeräte / Sportbekleidung");
        addCategory(AccountingType.outgoing, AccountingInterval.once, "Freizeit", "Restaurant / Café");

        addCategory(AccountingType.incoming, AccountingInterval.once, "Geldanlage", "Zinseinkünfte");
        addCategory(AccountingType.incoming, AccountingInterval.once, "Geldanlage", "Dividende");
        addCategory(AccountingType.outgoing, AccountingInterval.all, "Geldanlage", "Bausparen");
        addCategory(AccountingType.outgoing, AccountingInterval.all, "Geldanlage", "Andere Geldanlagen");

        addCategory(AccountingType.outgoing, AccountingInterval.all, "Gesundheit", "Medizin");
        addCategory(AccountingType.outgoing, AccountingInterval.all, "Gesundheit", "Krankenkasse");
        addCategory(AccountingType.outgoing, AccountingInterval.all, "Gesundheit", "Zuzahlung Krankenkasse");

        addCategory(AccountingType.outgoing, AccountingInterval.all, "Haushalt", "Taschengeld");
        addCategory(AccountingType.outgoing, AccountingInterval.once, "Haushalt", "Anschaffungen");
        addCategory(AccountingType.outgoing, AccountingInterval.all, "Haushalt", "Getränke");
        addCategory(AccountingType.outgoing, AccountingInterval.once, "Haushalt", "Körperpflege");
        addCategory(AccountingType.outgoing, AccountingInterval.once, "Haushalt", "Lebensmittel");
        addCategory(AccountingType.outgoing, AccountingInterval.once, "Haushalt", "Medikamente");
        addCategory(AccountingType.outgoing, AccountingInterval.once, "Haushalt", "Genussmittel");
        addCategory(AccountingType.outgoing, AccountingInterval.once, "Haushalt", "Schule");
        addCategory(AccountingType.outgoing, AccountingInterval.all, "Haushalt", "Tiernahrung");
        addCategory(AccountingType.outgoing, AccountingInterval.once, "Haushalt", "Porto");
        addCategory(AccountingType.outgoing, AccountingInterval.all, "Haushalt", "Wasch und Putzmittel");

        addCategory(AccountingType.incoming, AccountingInterval.once, "Sonstiges", "Geldgeschenke");
        addCategory(AccountingType.incoming, AccountingInterval.once, "Sonstiges", "Geldgewinne");
        addCategory(AccountingType.incoming, AccountingInterval.once, "Sonstiges", "Fehlbetrag");
        addCategory(AccountingType.incoming, AccountingInterval.all, "Sonstiges", "Mietrückzahlung");

        addCategory(AccountingType.incoming, AccountingInterval.monthly, "Staat", "Rente");
        addCategory(AccountingType.incoming, AccountingInterval.monthly, "Staat", "Wohngeld");
        addCategory(AccountingType.incoming, AccountingInterval.monthly, "Staat", "Arbeitslosengeld");
        addCategory(AccountingType.incoming, AccountingInterval.once, "Staat", "Steuererstattung");

        addCategory(AccountingType.outgoing, AccountingInterval.all, "Steuer", "Hundesteuer");
        addCategory(AccountingType.outgoing, AccountingInterval.all, "Steuer", "Kirchensteuer");
        addCategory(AccountingType.outgoing, AccountingInterval.all, "Steuer", "Lohn / Einkommensteuer");

        addCategory(AccountingType.outgoing, AccountingInterval.all, "Versicherung", "Haftpflicht");
        addCategory(AccountingType.outgoing, AccountingInterval.all, "Versicherung", "Hausratversicherung");
        addCategory(AccountingType.outgoing, AccountingInterval.all, "Versicherung", "Lebensversicherung");
        addCategory(AccountingType.outgoing, AccountingInterval.all, "Versicherung", "Zusatzversicherung");
        addCategory(AccountingType.outgoing, AccountingInterval.all, "Versicherung", "Berufsunfähigkeitsversicherung");
        addCategory(AccountingType.outgoing, AccountingInterval.all, "Versicherung", "Sonstige");

        addCategory(AccountingType.outgoing, AccountingInterval.all, "Wohnung", "GEZ-Gebühren");
        addCategory(AccountingType.outgoing, AccountingInterval.monthly, "Wohnung", "Kaltmiete");
        addCategory(AccountingType.outgoing, AccountingInterval.monthly, "Wohnung", "Nebenkosten");
        addCategory(AccountingType.outgoing, AccountingInterval.monthly, "Wohnung", "Grundsteuer");
        addCategory(AccountingType.outgoing, AccountingInterval.monthly, "Wohnung", "Heizung");
        addCategory(AccountingType.outgoing, AccountingInterval.all, "Wohnung", "Internet und Telefon");
        addCategory(AccountingType.outgoing, AccountingInterval.all, "Wohnung", "Kreditabzahlung");
        addCategory(AccountingType.outgoing, AccountingInterval.all, "Wohnung", "Strom");
        addCategory(AccountingType.outgoing, AccountingInterval.all, "Wohnung", "Wasser");
        addCategory(AccountingType.outgoing, AccountingInterval.all, "Wohnung", "Miete");
        addCategory(AccountingType.outgoing, AccountingInterval.all, "Wohnung", "Reparaturen");

        insert(database, CategoryColumns.TABLE_NAME);
    }

    private void addCategory(AccountingType type, AccountingInterval interval, String section, String name) {
        categoryFunction.apply(section, name, interval.name(), type.name());
    }

    private void addAccountData(SQLiteDatabase database) {
        data.add(new AccountContentValues().putName("Girokonto").putInitialvalue(0));
        data.add(new AccountContentValues().putName("Bar").putInitialvalue(0));
        data.add(new AccountContentValues().putName("Tagesgeldkonto").putInitialvalue(0));
        insert(database, AccountColumns.TABLE_NAME);
    }

    private void insert(SQLiteDatabase database, String tableName) {
        for (AbstractContentValues entry : data) {
            database.insertOrThrow(tableName, null, entry.values());
        }
        data.clear();
    }
}
