package de.nenick.quacc.view.template_config;

import org.androidannotations.annotations.Bean;
import org.androidannotations.annotations.EFragment;

import de.nenick.quacc.common.mvp.BasePresenterFragment;
import de.nenick.quacc.common.mvp.BaseView;

@EFragment
public class TemplateConfigFragment extends BasePresenterFragment {

    @Bean
    TemplateConfigView view;

    @Override
    protected BaseView getBaseView() {
        return view;
    }
}
