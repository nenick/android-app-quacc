package de.nenick.quacc.view.template_speech;

import android.widget.ListAdapter;
import android.widget.ListView;

import org.androidannotations.annotations.EBean;
import org.androidannotations.annotations.ViewById;

import de.nenick.quacc.R;
import de.nenick.quacc.common.mvp.BaseView;

@EBean
public class TemplateSpeechView extends BaseView {

    @ViewById(R.id.list)
    ListView listView;

    public void setTemplates(ListAdapter adapter) {
        listView.setAdapter(adapter);
    }
}
