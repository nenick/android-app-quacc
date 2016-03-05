package de.nenick.quacc.core.initialdata;

import android.database.sqlite.SQLiteDatabase;

import org.androidannotations.annotations.Bean;
import org.androidannotations.annotations.EBean;

import java.util.ArrayList;
import java.util.List;

import de.nenick.quacc.core.bookingentry.direction.BookingDirectionOption;
import de.nenick.quacc.core.bookinginterval.BookingIntervalOption;
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

        addCategory(BookingDirectionOption.transfer, BookingIntervalOption.all, "Allgemein", "Kontoausgleich");
        addCategory(BookingDirectionOption.outgoing, BookingIntervalOption.all, "Allgemein", "Barauszahlung");
        addCategory(BookingDirectionOption.all, BookingIntervalOption.all, "Allgemein", "Sonstiges");
        addCategory(BookingDirectionOption.incoming, BookingIntervalOption.all, "Allgemein", "Bareinzahlung");

        addCategory(BookingDirectionOption.outgoing, BookingIntervalOption.once, "Alltag", "Accessoirs");
        addCategory(BookingDirectionOption.outgoing, BookingIntervalOption.once, "Alltag", "Kleidung");
        addCategory(BookingDirectionOption.outgoing, BookingIntervalOption.all, "Alltag", "Schuhe");

        addCategory(BookingDirectionOption.incoming, BookingIntervalOption.all, "Auktionen", "eBay");

        addCategory(BookingDirectionOption.outgoing, BookingIntervalOption.all, "Auto", "Benzin");
        addCategory(BookingDirectionOption.outgoing, BookingIntervalOption.once, "Auto", "Werkstatt");
        addCategory(BookingDirectionOption.outgoing, BookingIntervalOption.once, "Auto", "Zubehörteile");
        addCategory(BookingDirectionOption.outgoing, BookingIntervalOption.all, "Auto", "Kfz-Versicherung");
        addCategory(BookingDirectionOption.outgoing, BookingIntervalOption.all, "Auto", "Kredit");
        addCategory(BookingDirectionOption.outgoing, BookingIntervalOption.all, "Auto", "Kfz-Steuer");
        addCategory(BookingDirectionOption.outgoing, BookingIntervalOption.once, "Auto", "Bußgeld");
        addCategory(BookingDirectionOption.outgoing, BookingIntervalOption.all, "Auto", "Automobilclub");

        addCategory(BookingDirectionOption.outgoing, BookingIntervalOption.all, "Bank", "Kontoführungsgebühren");
        addCategory(BookingDirectionOption.outgoing, BookingIntervalOption.all, "Bank", "Zinsen");

        addCategory(BookingDirectionOption.outgoing, BookingIntervalOption.all, "Beruf", "Fahrkosten");
        addCategory(BookingDirectionOption.outgoing, BookingIntervalOption.all, "Beruf", "Werbungskosten");
        addCategory(BookingDirectionOption.outgoing, BookingIntervalOption.all, "Beruf", "Serverkosten");
        addCategory(BookingDirectionOption.outgoing, BookingIntervalOption.all, "Beruf", "Fachbücher");
        addCategory(BookingDirectionOption.outgoing, BookingIntervalOption.all, "Beruf", "Sonstiges");

        addCategory(BookingDirectionOption.incoming, BookingIntervalOption.all, "Einkommen", "Lohn / Gehalt");
        addCategory(BookingDirectionOption.incoming, BookingIntervalOption.all, "Einkommen", "Mieteinnahmen");
        addCategory(BookingDirectionOption.incoming, BookingIntervalOption.monthly, "Einkommen", "Unterhalt");
        addCategory(BookingDirectionOption.incoming, BookingIntervalOption.monthly, "Einkommen", "Kindergeld");
        addCategory(BookingDirectionOption.incoming, BookingIntervalOption.once, "Einkommen", "Schenkung");
        addCategory(BookingDirectionOption.incoming, BookingIntervalOption.all, "Einkommen", "Taschengeld");
        addCategory(BookingDirectionOption.incoming, BookingIntervalOption.all, "Einkommen", "Freiberuflich");

        addCategory(BookingDirectionOption.outgoing, BookingIntervalOption.once, "Fortbildung", "Literatur");
        addCategory(BookingDirectionOption.outgoing, BookingIntervalOption.all, "Fortbildung", "Studiengebühren");
        addCategory(BookingDirectionOption.outgoing, BookingIntervalOption.once, "Fortbildung", "Fahrtkosten");
        addCategory(BookingDirectionOption.outgoing, BookingIntervalOption.once, "Fortbildung", "Anschaffungen");
        addCategory(BookingDirectionOption.outgoing, BookingIntervalOption.once, "Fortbildung", "Bücher und Schreibmaterial");

        addCategory(BookingDirectionOption.outgoing, BookingIntervalOption.once, "Freizeit", "Urlaub");
        addCategory(BookingDirectionOption.outgoing, BookingIntervalOption.once, "Freizeit", "Bücher");
        addCategory(BookingDirectionOption.outgoing, BookingIntervalOption.once, "Freizeit", "Kino / Theater");
        addCategory(BookingDirectionOption.outgoing, BookingIntervalOption.all, "Freizeit", "Zeitschriften");
        addCategory(BookingDirectionOption.outgoing, BookingIntervalOption.all, "Freizeit", "Fitnessstudio");
        addCategory(BookingDirectionOption.outgoing, BookingIntervalOption.all, "Freizeit", "Sport");
        addCategory(BookingDirectionOption.outgoing, BookingIntervalOption.once, "Freizeit", "Geschenke");
        addCategory(BookingDirectionOption.outgoing, BookingIntervalOption.once, "Freizeit", "Musik / Video");
        addCategory(BookingDirectionOption.outgoing, BookingIntervalOption.once, "Freizeit", "Software / Spiele");
        addCategory(BookingDirectionOption.outgoing, BookingIntervalOption.once, "Freizeit", "Computer");
        addCategory(BookingDirectionOption.outgoing, BookingIntervalOption.all, "Freizeit", "Vereinsbeträge");
        addCategory(BookingDirectionOption.outgoing, BookingIntervalOption.once, "Freizeit", "Frisör");
        addCategory(BookingDirectionOption.outgoing, BookingIntervalOption.all, "Freizeit", "Zigaretten");
        addCategory(BookingDirectionOption.outgoing, BookingIntervalOption.once, "Freizeit", "Glücksspiel");
        addCategory(BookingDirectionOption.outgoing, BookingIntervalOption.once, "Freizeit", "Anschaffungen");
        addCategory(BookingDirectionOption.outgoing, BookingIntervalOption.once, "Freizeit", "Sportgeräte / Sportbekleidung");
        addCategory(BookingDirectionOption.outgoing, BookingIntervalOption.once, "Freizeit", "Restaurant / Café");

        addCategory(BookingDirectionOption.incoming, BookingIntervalOption.once, "Geldanlage", "Zinseinkünfte");
        addCategory(BookingDirectionOption.incoming, BookingIntervalOption.once, "Geldanlage", "Dividende");
        addCategory(BookingDirectionOption.outgoing, BookingIntervalOption.all, "Geldanlage", "Bausparen");
        addCategory(BookingDirectionOption.outgoing, BookingIntervalOption.all, "Geldanlage", "Andere Geldanlagen");

        addCategory(BookingDirectionOption.outgoing, BookingIntervalOption.all, "Gesundheit", "Medizin");
        addCategory(BookingDirectionOption.outgoing, BookingIntervalOption.all, "Gesundheit", "Krankenkasse");
        addCategory(BookingDirectionOption.outgoing, BookingIntervalOption.all, "Gesundheit", "Zuzahlung Krankenkasse");

        addCategory(BookingDirectionOption.outgoing, BookingIntervalOption.all, "Haushalt", "Taschengeld");
        addCategory(BookingDirectionOption.outgoing, BookingIntervalOption.once, "Haushalt", "Anschaffungen");
        addCategory(BookingDirectionOption.outgoing, BookingIntervalOption.all, "Haushalt", "Getränke");
        addCategory(BookingDirectionOption.outgoing, BookingIntervalOption.once, "Haushalt", "Körperpflege");
        addCategory(BookingDirectionOption.outgoing, BookingIntervalOption.once, "Haushalt", "Lebensmittel");
        addCategory(BookingDirectionOption.outgoing, BookingIntervalOption.once, "Haushalt", "Medikamente");
        addCategory(BookingDirectionOption.outgoing, BookingIntervalOption.once, "Haushalt", "Genussmittel");
        addCategory(BookingDirectionOption.outgoing, BookingIntervalOption.once, "Haushalt", "Schule");
        addCategory(BookingDirectionOption.outgoing, BookingIntervalOption.all, "Haushalt", "Tiernahrung");
        addCategory(BookingDirectionOption.outgoing, BookingIntervalOption.once, "Haushalt", "Porto");
        addCategory(BookingDirectionOption.outgoing, BookingIntervalOption.all, "Haushalt", "Wasch und Putzmittel");

        addCategory(BookingDirectionOption.incoming, BookingIntervalOption.once, "Sonstiges", "Geldgeschenke");
        addCategory(BookingDirectionOption.incoming, BookingIntervalOption.once, "Sonstiges", "Geldgewinne");
        addCategory(BookingDirectionOption.incoming, BookingIntervalOption.once, "Sonstiges", "Fehlbetrag");
        addCategory(BookingDirectionOption.incoming, BookingIntervalOption.all, "Sonstiges", "Mietrückzahlung");

        addCategory(BookingDirectionOption.incoming, BookingIntervalOption.monthly, "Staat", "Rente");
        addCategory(BookingDirectionOption.incoming, BookingIntervalOption.monthly, "Staat", "Wohngeld");
        addCategory(BookingDirectionOption.incoming, BookingIntervalOption.monthly, "Staat", "Arbeitslosengeld");
        addCategory(BookingDirectionOption.incoming, BookingIntervalOption.once, "Staat", "Steuererstattung");

        addCategory(BookingDirectionOption.outgoing, BookingIntervalOption.all, "Steuer", "Hundesteuer");
        addCategory(BookingDirectionOption.outgoing, BookingIntervalOption.all, "Steuer", "Kirchensteuer");
        addCategory(BookingDirectionOption.outgoing, BookingIntervalOption.all, "Steuer", "Lohn / Einkommensteuer");

        addCategory(BookingDirectionOption.outgoing, BookingIntervalOption.all, "Versicherung", "Haftpflicht");
        addCategory(BookingDirectionOption.outgoing, BookingIntervalOption.all, "Versicherung", "Hausratversicherung");
        addCategory(BookingDirectionOption.outgoing, BookingIntervalOption.all, "Versicherung", "Lebensversicherung");
        addCategory(BookingDirectionOption.outgoing, BookingIntervalOption.all, "Versicherung", "Zusatzversicherung");
        addCategory(BookingDirectionOption.outgoing, BookingIntervalOption.all, "Versicherung", "Berufsunfähigkeitsversicherung");
        addCategory(BookingDirectionOption.outgoing, BookingIntervalOption.all, "Versicherung", "Sonstige");

        addCategory(BookingDirectionOption.outgoing, BookingIntervalOption.all, "Wohnung", "GEZ-Gebühren");
        addCategory(BookingDirectionOption.outgoing, BookingIntervalOption.monthly, "Wohnung", "Kaltmiete");
        addCategory(BookingDirectionOption.outgoing, BookingIntervalOption.monthly, "Wohnung", "Nebenkosten");
        addCategory(BookingDirectionOption.outgoing, BookingIntervalOption.monthly, "Wohnung", "Grundsteuer");
        addCategory(BookingDirectionOption.outgoing, BookingIntervalOption.monthly, "Wohnung", "Heizung");
        addCategory(BookingDirectionOption.outgoing, BookingIntervalOption.all, "Wohnung", "Internet und Telefon");
        addCategory(BookingDirectionOption.outgoing, BookingIntervalOption.all, "Wohnung", "Kreditabzahlung");
        addCategory(BookingDirectionOption.outgoing, BookingIntervalOption.all, "Wohnung", "Strom");
        addCategory(BookingDirectionOption.outgoing, BookingIntervalOption.all, "Wohnung", "Wasser");
        addCategory(BookingDirectionOption.outgoing, BookingIntervalOption.all, "Wohnung", "Miete");
        addCategory(BookingDirectionOption.outgoing, BookingIntervalOption.all, "Wohnung", "Reparaturen");

        insert(database, CategoryColumns.TABLE_NAME);
    }

    private void addCategory(BookingDirectionOption type, BookingIntervalOption interval, String section, String name) {
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
