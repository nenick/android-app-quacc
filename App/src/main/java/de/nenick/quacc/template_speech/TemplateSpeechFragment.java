package de.nenick.quacc.template_speech;

import org.androidannotations.annotations.Bean;
import org.androidannotations.annotations.EFragment;

import de.nenick.quacc.R;
import de.nenick.quacc.mvp.BasePresenterFragment;
import de.nenick.quacc.mvp.BaseView;
import de.nenick.quacc.template.adapter.TemplatePlainAdapter;

@EFragment(R.layout.fragment_template_speech)
public class TemplateSpeechFragment extends BasePresenterFragment {

    @Bean
    TemplateSpeechView view;

    @Bean
    TemplatePlainAdapter templatePlainAdapter;

    @Override
    protected BaseView getBaseView() {
        return view;
    }

    @Override
    protected void onViewStart() {
        view.setTemplates(templatePlainAdapter);
    }
}
