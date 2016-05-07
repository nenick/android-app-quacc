package de.nenick.quacc.view.accounting_overview;

import android.os.Environment;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.Toolbar;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Bean;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.OptionsItem;
import org.androidannotations.annotations.ViewById;

import de.nenick.quacc.R;
import de.nenick.quacc.core.backup.BackupFromJsonFileFunction;
import de.nenick.quacc.core.backup.BackupToJsonFileFunction;
import de.nenick.quacc.view.account.AccountsActivity_;
import de.nenick.quacc.view.category.CategoriesActivity_;
import de.nenick.quacc.view.template.TemplateActivity_;

@EActivity(R.layout.activity_accounting_list)
public class AccountingListActivity extends ActionBarActivity {

    public static final String TAG_FRAGMENT = AccountingListFragment.class.getSimpleName();

    private CharSequence mTitle;
    private long mAccount;

    @ViewById(R.id.activity_booking_entries)
    DrawerLayout drawerLayout;


    @Bean
    BackupFromJsonFileFunction backupFromJsonFileFunction;

    @Bean
    BackupToJsonFileFunction backupToJsonFileFunction;

    String backupPath = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS).getPath() + "/QuAcc-Backup.json";

    @AfterViews
    protected void onCreateView() {

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        mTitle = getTitle();

    }

    @OptionsItem(R.id.category)
    protected void onCategoriesEditor() {
        CategoriesActivity_.intent(this).start();
    }

    @OptionsItem(R.id.account)
    protected void onAccountsEditor() {
        AccountsActivity_.intent(this).start();
    }

    @OptionsItem(R.id.template)
    protected void onTemplates() {
        TemplateActivity_.intent(this).start();
    }

    @OptionsItem(R.id.import_data)
    protected void onImport() {
        backupFromJsonFileFunction.apply(backupPath);
    }

    @OptionsItem(R.id.export_data)
    protected void onExport() {
        backupToJsonFileFunction.apply(backupPath);
    }


}
