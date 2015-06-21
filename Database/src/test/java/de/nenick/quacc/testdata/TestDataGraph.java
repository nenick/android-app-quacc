package de.nenick.quacc.testdata;

import com.getbase.android.forger.Forger;

import org.chalup.microorm.MicroOrm;
import org.chalup.thneed.ModelGraph;

import de.nenick.quacc.database.provider.accounting.AccountingColumns;
import de.nenick.quacc.database.provider.accountingtemplate.AccountingTemplateColumns;
import de.nenick.quacc.database.provider.templatematching.TemplateMatchingColumns;
import de.nenick.quacc.testdata.base.BaseTestModel;
import de.nenick.quacc.testdata.base.DataModel;

public class TestDataGraph {
    public static BaseTestModel ACCOUNT = new BaseTestModel(Account.class);
    public static BaseTestModel ACCOUNTING = new BaseTestModel(Accounting.class);
    public static BaseTestModel ACCOUNTING_CATEGORY = new BaseTestModel(Category.class);
    public static BaseTestModel ACCOUNTING_TEMPLATE = new BaseTestModel(Accounting_Template.class);
    public static BaseTestModel TEMPLATE_MATCHING = new BaseTestModel(Template_Matching.class);

    public static ModelGraph<DataModel> MODEL_GRAPH = ModelGraph.of(DataModel.class)
            .identifiedByDefault().by("_id")
            .where()
            .the(ACCOUNTING).references(ACCOUNT).by(AccountingColumns.ACCOUNT_ID)
            .the(ACCOUNTING).references(ACCOUNTING_CATEGORY).by(AccountingColumns.CATEGORY_ID)
            .the(ACCOUNTING_TEMPLATE).references(ACCOUNT).by(AccountingTemplateColumns.ACCOUNT_ID)
            .the(ACCOUNTING_TEMPLATE).references(ACCOUNTING_CATEGORY).by(AccountingTemplateColumns.CATEGORY_ID)
            .the(TEMPLATE_MATCHING).references(ACCOUNTING_TEMPLATE).by(TemplateMatchingColumns.ACCOUNTING_TEMPLATE_ID)
            .build();

    public static Forger<DataModel> access() {
        return new Forger<>(MODEL_GRAPH, new MicroOrm());
    }
}
