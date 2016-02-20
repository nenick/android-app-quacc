package de.nenick.quacc.view.category;

import android.widget.EditText;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.SpinnerAdapter;

import org.androidannotations.annotations.EBean;
import org.androidannotations.annotations.ViewById;

import de.nenick.quacc.R;
import de.nenick.quacc.view.mvp.BaseView;

@EBean
public class CategoriesView extends BaseView {

    @ViewById(R.id.interval)
    Spinner accountingIntervalSpinner;

    @ViewById(R.id.category)
    Spinner accountingCategorySpinner;

    @ViewById(R.id.list)
    ListView categoryList;

    @ViewById(R.id.direction)
    Spinner directionSpinner;

    @ViewById(R.id.interval)
    Spinner intervalSpinner;

    @ViewById(R.id.section)
    Spinner sectionSpinner;

    @ViewById(R.id.label)
    EditText label;

    public void setCategories(ListAdapter adapter) {
        categoryList.setAdapter(adapter);
    }

    public void setTypes(SpinnerAdapter adapter) {
        directionSpinner.setAdapter(adapter);
    }

    public void setIntervals(SpinnerAdapter adapter) {
        intervalSpinner.setAdapter(adapter);
    }

    public void setSections(SpinnerAdapter adapter) {
        sectionSpinner.setAdapter(adapter);
    }

    public String getAndClearName() {
        String text = label.getText().toString();
        label.setText("");
        return text;
    }

    public String getInterval() {
        return intervalSpinner.getSelectedItem().toString();
    }

    public String getType() {
        return directionSpinner.getSelectedItem().toString();
    }

    public <T> T getSection() {
        //noinspection unchecked the caller should now what kind of item he expect
        return (T) sectionSpinner.getSelectedItem();
    }
}
