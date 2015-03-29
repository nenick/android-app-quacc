package de.nenick.quacc.database.provider.testdata;

import com.getbase.android.forger.Forger;

import org.chalup.microorm.MicroOrm;
import org.chalup.thneed.ModelGraph;

import de.nenick.quacc.database.provider.accounting.AccountingColumns;
import de.nenick.quacc.database.provider.testdata.base.BaseTestModel;
import de.nenick.quacc.database.provider.testdata.base.DataModel;

public class TestDataGraph {
    public static BaseTestModel ACCOUNT = new BaseTestModel(Account.class);
    public static BaseTestModel ACCOUNTING = new BaseTestModel(Accounting.class);
    public static BaseTestModel ACCOUNTING_CATEGORY = new BaseTestModel(Accounting_Category.class);

    public static ModelGraph<DataModel> MODEL_GRAPH = ModelGraph.of(DataModel.class)
            .identifiedByDefault().by("_id")
            .where()
            .the(ACCOUNTING).references(ACCOUNT).by(AccountingColumns.ACCOUNT_ID)
            .the(ACCOUNTING).references(ACCOUNTING_CATEGORY).by(AccountingColumns.ACCOUNTING_CATEGORY_ID)
            .build();

    public static Forger<DataModel> access() {
         return new Forger<>(MODEL_GRAPH, new MicroOrm());
    }
}
