package de.nenick.quacc.view.template;

import android.widget.ListAdapter;
import android.widget.ListView;

import org.androidannotations.annotations.EBean;
import org.androidannotations.annotations.ViewById;

import de.nenick.quacc.R;
import de.nenick.quacc.view.mvp.BaseView;

@EBean
public class TemplateView extends BaseView {

    @ViewById(R.id.list)
    ListView listView;

    public void setTemplates(ListAdapter adapter) {
        listView.setAdapter(adapter);
    }
}
