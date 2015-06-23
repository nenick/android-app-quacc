package de.nenick.quacc.view.template;

import org.androidannotations.annotations.Bean;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EFragment;

import de.nenick.quacc.R;
import de.nenick.quacc.common.mvp.BasePresenterFragment;
import de.nenick.quacc.common.mvp.BaseView;
import de.nenick.quacc.view.template.adapter.TemplatePlainAdapter;
import de.nenick.quacc.view.template_create.CreateTemplateActivity_;

@EFragment(R.layout.fragment_template)
public class TemplateFragment extends BasePresenterFragment {

    @Bean
    TemplateView view;

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

    @Click(R.id.confirm)
    protected void onClickAdd() {
        CreateTemplateActivity_.intent(getActivity()).start();
    }
}
