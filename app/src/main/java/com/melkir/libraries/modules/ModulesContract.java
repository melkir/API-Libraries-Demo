package com.melkir.libraries.modules;

import com.melkir.libraries.BasePresenter;
import com.melkir.libraries.BaseView;
import com.melkir.libraries.data.Module;

import java.util.List;

public interface ModulesContract {
    interface View extends BaseView<Presenter> {
        void showModules(List<Module> modules);

        void showNoModules();

        void showModuleDetailsUi(Module module);

        void showCardsViewUi();

        void showSearchViewUi();

        void filter(ModulesType requestType);

        void filter(String requestTitle);
    }

    interface Presenter extends BasePresenter {
        void loadModules();

        void openModuleDetails(Module clickedModule);

        void setFiltering(ModulesType requestType);

        void setFiltering(String requestTitle);

        void setDisplayTypeCards();

        void setDisplayTypeSearch();

        ModulesType getFiltering();
    }
}
