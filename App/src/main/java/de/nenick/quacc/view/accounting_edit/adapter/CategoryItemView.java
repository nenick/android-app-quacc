package de.nenick.quacc.view.accounting_edit.adapter;

import android.content.Context;
import android.widget.RelativeLayout;
import android.widget.TextView;

import org.androidannotations.annotations.EViewGroup;
import org.androidannotations.annotations.ViewById;

import de.nenick.quacc.R;

@EViewGroup(R.layout.view_category_dropdown)
public class CategoryItemView extends RelativeLayout {

    @ViewById(R.id.text0)
    TextView sub;

    @ViewById(R.id.text1)
    TextView textView;

    public CategoryItemView(Context context) {
        super(context);
    }

    public void setText(String text) {
        textView.setText(text);
    }

    public void setMainCategory() {
        sub.setVisibility(GONE);
    }

    public void setSubCategory() {
        sub.setVisibility(VISIBLE);
    }
}
