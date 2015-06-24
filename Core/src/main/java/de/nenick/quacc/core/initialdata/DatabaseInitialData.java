package de.nenick.quacc.core.initialdata;

import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

import de.nenick.quacc.core.accounting.interval.AccountingInterval;
import de.nenick.quacc.core.accounting.type.AccountingType;
import de.nenick.quacc.database.provider.account.AccountColumns;
import de.nenick.quacc.database.provider.account.AccountContentValues;
import de.nenick.quacc.database.provider.base.AbstractContentValues;
import de.nenick.quacc.database.provider.category.CategoryColumns;
import de.nenick.quacc.database.provider.category.CategoryContentValues;

public class DatabaseInitialData {
    public void insert(SQLiteDatabase database) {
        addAccountData(database);
        addCategoryData(database);
    }

    List<AbstractContentValues> data = new ArrayList<>();

    private void addCategoryData(SQLiteDatabase database) {

        addMainCategory(AccountingType.all, AccountingInterval.all, "Allgemein");
        addSubCategory(AccountingType.transfer, AccountingInterval.all, "Allgemein", "Kontoausgleich");
        addSubCategory(AccountingType.outgoing, AccountingInterval.all, "Allgemein", "Barauszahlung");
        addSubCategory(AccountingType.all, AccountingInterval.all, "Allgemein", "Sonstiges");
        addSubCategory(AccountingType.incoming, AccountingInterval.all, "Allgemein", "Bareinzahlung");

        addMainCategory(AccountingType.all, AccountingInterval.all, "Alltag");
        addSubCategory(AccountingType.outgoing, AccountingInterval.once, "Alltag", "Accessoirs");
        addSubCategory(AccountingType.outgoing, AccountingInterval.once, "Alltag", "Kleidung");
        addSubCategory(AccountingType.outgoing, AccountingInterval.all, "Alltag", "Schuhe");

        addMainCategory(AccountingType.all, AccountingInterval.all, "Auktionen");
        addSubCategory(AccountingType.incoming, AccountingInterval.all, "Auktionen", "eBay");

        addMainCategory(AccountingType.all, AccountingInterval.all, "Auto");
        addSubCategory(AccountingType.outgoing, AccountingInterval.all, "Auto", "Benzin");
        addSubCategory(AccountingType.outgoing, AccountingInterval.once, "Auto", "Werkstatt");
        addSubCategory(AccountingType.outgoing, AccountingInterval.once, "Auto", "Zubehörteile");
        addSubCategory(AccountingType.outgoing, AccountingInterval.all, "Auto", "Kfz-Versicherung");
        addSubCategory(AccountingType.outgoing, AccountingInterval.all, "Auto", "Kredit");
        addSubCategory(AccountingType.outgoing, AccountingInterval.all, "Auto", "Kfz-Steuer");
        addSubCategory(AccountingType.outgoing, AccountingInterval.once, "Auto", "Bußgeld");
        addSubCategory(AccountingType.outgoing, AccountingInterval.all, "Auto", "Automobilclub");

        addMainCategory(AccountingType.all, AccountingInterval.all, "Bank");
        addSubCategory(AccountingType.outgoing, AccountingInterval.all, "Bank", "Kontoführungsgebühren");
        addSubCategory(AccountingType.outgoing, AccountingInterval.all, "Bank", "Zinsen");

        addMainCategory(AccountingType.all, AccountingInterval.all, "Beruf");
        addSubCategory(AccountingType.outgoing, AccountingInterval.all, "Beruf", "Fahrkosten");
        addSubCategory(AccountingType.outgoing, AccountingInterval.all, "Beruf", "Werbungskosten");
        addSubCategory(AccountingType.outgoing, AccountingInterval.all, "Beruf", "Serverkosten");
        addSubCategory(AccountingType.outgoing, AccountingInterval.all, "Beruf", "Fachbücher");
        addSubCategory(AccountingType.outgoing, AccountingInterval.all, "Beruf", "Sonstiges");

        addMainCategory(AccountingType.all, AccountingInterval.all, "Einkommen");
        addSubCategory(AccountingType.incoming, AccountingInterval.all, "Einkommen", "Lohn / Gehalt");
        addSubCategory(AccountingType.incoming, AccountingInterval.all, "Einkommen", "Mieteinnahmen");
        addSubCategory(AccountingType.incoming, AccountingInterval.monthly, "Einkommen", "Unterhalt");
        addSubCategory(AccountingType.incoming, AccountingInterval.monthly, "Einkommen", "Kindergeld");
        addSubCategory(AccountingType.incoming, AccountingInterval.once, "Einkommen", "Schenkung");
        addSubCategory(AccountingType.incoming, AccountingInterval.all, "Einkommen", "Taschengeld");
        addSubCategory(AccountingType.incoming, AccountingInterval.all, "Einkommen", "Freiberuflich");

        addMainCategory(AccountingType.all, AccountingInterval.all, "Fortbildung");
        addSubCategory(AccountingType.outgoing, AccountingInterval.once, "Fortbildung", "Literatur");
        addSubCategory(AccountingType.outgoing, AccountingInterval.all, "Fortbildung", "Studiengebühren");
        addSubCategory(AccountingType.outgoing, AccountingInterval.once, "Fortbildung", "Fahrtkosten");
        addSubCategory(AccountingType.outgoing, AccountingInterval.once, "Fortbildung", "Anschaffungen");
        addSubCategory(AccountingType.outgoing, AccountingInterval.once, "Fortbildung", "Bücher und Schreibmaterial");

        addMainCategory(AccountingType.all, AccountingInterval.all, "Freizeit");
        addSubCategory(AccountingType.outgoing, AccountingInterval.once, "Freizeit", "Urlaub");
        addSubCategory(AccountingType.outgoing, AccountingInterval.once, "Freizeit", "Bücher");
        addSubCategory(AccountingType.outgoing, AccountingInterval.once, "Freizeit", "Kino / Theater");
        addSubCategory(AccountingType.outgoing, AccountingInterval.all, "Freizeit", "Zeitschriften");
        addSubCategory(AccountingType.outgoing, AccountingInterval.all, "Freizeit", "Fitnessstudio");
        addSubCategory(AccountingType.outgoing, AccountingInterval.all, "Freizeit", "Sport");
        addSubCategory(AccountingType.outgoing, AccountingInterval.once, "Freizeit", "Geschenke");
        addSubCategory(AccountingType.outgoing, AccountingInterval.once, "Freizeit", "Musik / Video");
        addSubCategory(AccountingType.outgoing, AccountingInterval.once, "Freizeit", "Software / Spiele");
        addSubCategory(AccountingType.outgoing, AccountingInterval.once, "Freizeit", "Computer");
        addSubCategory(AccountingType.outgoing, AccountingInterval.all, "Freizeit", "Vereinsbeträge");
        addSubCategory(AccountingType.outgoing, AccountingInterval.once, "Freizeit", "Frisör");
        addSubCategory(AccountingType.outgoing, AccountingInterval.all, "Freizeit", "Zigaretten");
        addSubCategory(AccountingType.outgoing, AccountingInterval.once, "Freizeit", "Glücksspiel");
        addSubCategory(AccountingType.outgoing, AccountingInterval.once, "Freizeit", "Anschaffungen");
        addSubCategory(AccountingType.outgoing, AccountingInterval.once, "Freizeit", "Sportgeräte / Sportbekleidung");
        addSubCategory(AccountingType.outgoing, AccountingInterval.once, "Freizeit", "Restaurant / Café");

        addMainCategory(AccountingType.all, AccountingInterval.all, "Geldeinlage");
        addSubCategory(AccountingType.incoming, AccountingInterval.once, "Geldanlage", "Zinseinkünfte");
        addSubCategory(AccountingType.incoming, AccountingInterval.once, "Geldanlage", "Dividende");
        addSubCategory(AccountingType.outgoing, AccountingInterval.all, "Geldanlage", "Bausparen");
        addSubCategory(AccountingType.outgoing, AccountingInterval.all, "Geldanlage", "Andere Geldanlagen");

        addMainCategory(AccountingType.all, AccountingInterval.all, "Gesundheit");
        addSubCategory(AccountingType.outgoing, AccountingInterval.all, "Gesundheit", "Medizin");
        addSubCategory(AccountingType.outgoing, AccountingInterval.all, "Gesundheit", "Krankenkasse");
        addSubCategory(AccountingType.outgoing, AccountingInterval.all, "Gesundheit", "Zuzahlung Krankenkasse");

        addMainCategory(AccountingType.all, AccountingInterval.all, "Haushalt");
        addSubCategory(AccountingType.outgoing, AccountingInterval.all, "Haushalt", "Taschengeld");
        addSubCategory(AccountingType.outgoing, AccountingInterval.once, "Haushalt", "Anschaffungen");
        addSubCategory(AccountingType.outgoing, AccountingInterval.all, "Haushalt", "Getränke");
        addSubCategory(AccountingType.outgoing, AccountingInterval.once, "Haushalt", "Körperpflege");
        addSubCategory(AccountingType.outgoing, AccountingInterval.once, "Haushalt", "Lebensmittel");
        addSubCategory(AccountingType.outgoing, AccountingInterval.once, "Haushalt", "Medikamente");
        addSubCategory(AccountingType.outgoing, AccountingInterval.once, "Haushalt", "Genussmittel");
        addSubCategory(AccountingType.outgoing, AccountingInterval.once, "Haushalt", "Schule");
        addSubCategory(AccountingType.outgoing, AccountingInterval.all, "Haushalt", "Tiernahrung");
        addSubCategory(AccountingType.outgoing, AccountingInterval.once, "Haushalt", "Porto");
        addSubCategory(AccountingType.outgoing, AccountingInterval.all, "Haushalt", "Wasch und Putzmittel");

        addMainCategory(AccountingType.all, AccountingInterval.all, "Sonstiges");
        addSubCategory(AccountingType.incoming, AccountingInterval.once, "Sonstiges", "Geldgeschenke");
        addSubCategory(AccountingType.incoming, AccountingInterval.once, "Sonstiges", "Geldgewinne");
        addSubCategory(AccountingType.incoming, AccountingInterval.once, "Sonstiges", "Fehlbetrag");
        addSubCategory(AccountingType.incoming, AccountingInterval.all, "Sonstiges", "Mietrückzahlung");

        addMainCategory(AccountingType.all, AccountingInterval.all, "Staat");
        addSubCategory(AccountingType.incoming, AccountingInterval.monthly, "Staat", "Rente");
        addSubCategory(AccountingType.incoming, AccountingInterval.monthly, "Staat", "Wohngeld");
        addSubCategory(AccountingType.incoming, AccountingInterval.monthly, "Staat", "Arbeitslosengeld");
        addSubCategory(AccountingType.incoming, AccountingInterval.once, "Staat", "Steuererstattung");

        addMainCategory(AccountingType.all, AccountingInterval.all, "Steuer");
        addSubCategory(AccountingType.outgoing, AccountingInterval.all, "Steuer", "Hundesteuer");
        addSubCategory(AccountingType.outgoing, AccountingInterval.all, "Steuer", "Kirchensteuer");
        addSubCategory(AccountingType.outgoing, AccountingInterval.all, "Steuer", "Lohn / Einkommensteuer");

        addMainCategory(AccountingType.all, AccountingInterval.all, "Versicherung");
        addSubCategory(AccountingType.outgoing, AccountingInterval.all, "Versicherung", "Haftpflicht");
        addSubCategory(AccountingType.outgoing, AccountingInterval.all, "Versicherung", "Hausratversicherung");
        addSubCategory(AccountingType.outgoing, AccountingInterval.all, "Versicherung", "Lebensversicherung");
        addSubCategory(AccountingType.outgoing, AccountingInterval.all, "Versicherung", "Zusatzversicherung");
        addSubCategory(AccountingType.outgoing, AccountingInterval.all, "Versicherung", "Berufsunfähigkeitsversicherung");
        addSubCategory(AccountingType.outgoing, AccountingInterval.all, "Versicherung", "Sonstige");

        addMainCategory(AccountingType.all, AccountingInterval.all, "Wohnung");
        addSubCategory(AccountingType.outgoing, AccountingInterval.all, "Wohnung", "GEZ-Gebühren");
        addSubCategory(AccountingType.outgoing, AccountingInterval.monthly, "Wohnung", "Kaltmiete");
        addSubCategory(AccountingType.outgoing, AccountingInterval.monthly, "Wohnung", "Nebenkosten");
        addSubCategory(AccountingType.outgoing, AccountingInterval.monthly, "Wohnung", "Grundsteuer");
        addSubCategory(AccountingType.outgoing, AccountingInterval.monthly, "Wohnung", "Heizung");
        addSubCategory(AccountingType.outgoing, AccountingInterval.all, "Wohnung", "Internet und Telefon");
        addSubCategory(AccountingType.outgoing, AccountingInterval.all, "Wohnung", "Kreditabzahlung");
        addSubCategory(AccountingType.outgoing, AccountingInterval.all, "Wohnung", "Strom");
        addSubCategory(AccountingType.outgoing, AccountingInterval.all, "Wohnung", "Wasser");
        addSubCategory(AccountingType.outgoing, AccountingInterval.all, "Wohnung", "Miete");
        addSubCategory(AccountingType.outgoing, AccountingInterval.all, "Wohnung", "Reparaturen");

        insert(database, CategoryColumns.TABLE_NAME);
    }

    private void addSubCategory(AccountingType type, AccountingInterval interval, String section, String name) {
        data.add(new CategoryContentValues()
                .putName(name)
                .putInterval(interval.name())
                .putType(type.name())
                .putSection(section)
                .putLevel(1));
    }

    private void addMainCategory(AccountingType type, AccountingInterval interval, String section) {
        data.add(new CategoryContentValues()
                .putName(section)
                .putInterval(interval.name())
                .putType(type.name())
                .putSection(section)
                .putLevel(0));
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
