package de.nenick.quacc.categories;

import android.content.Context;
import android.widget.RelativeLayout;
import android.widget.TextView;

import org.androidannotations.annotations.EViewGroup;
import org.androidannotations.annotations.ViewById;

import de.nenick.quacc.R;

@EViewGroup(R.layout.item_category)
public class CategoryListItem extends RelativeLayout {

    @ViewById(R.id.type)
    TextView type;

    @ViewById(R.id.interval)
    TextView interval;

    @ViewById(R.id.category)
    TextView category;

    public CategoryListItem(Context context) {
        super(context);
    }

    public void setType(String txt) {
        type.setText(txt);
    }

    public void setInterval(String txt) {
        interval.setText(txt);
    }

    public void setCategory(String txt) {
        category.setText(txt);
    }
}
