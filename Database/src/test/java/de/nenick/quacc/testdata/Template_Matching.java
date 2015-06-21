package de.nenick.quacc.testdata;

import org.chalup.microorm.annotations.Column;

import de.nenick.quacc.database.provider.accounting.AccountingColumns;
import de.nenick.quacc.database.provider.templatematching.TemplateMatchingColumns;
import de.nenick.quacc.testdata.base.BaseModel;

public class Template_Matching extends BaseModel {

    @Column(TemplateMatchingColumns.ACCOUNTING_TEMPLATE_ID)
    long ACCOUNTING_TEMPLATE_ID;

    @Column(TemplateMatchingColumns.TEXT)
    String TEXT;
}
