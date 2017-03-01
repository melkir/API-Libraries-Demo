package com.melkir.libraries.modules;

import com.melkir.libraries.BasePresenter;
import com.melkir.libraries.BaseView;
import com.melkir.libraries.cards.CardsFilterType;
import com.melkir.libraries.model.Module;

import java.util.List;

public interface ModulesContract {
    interface View extends BaseView<Presenter> {
        void showModules(List<Module> modules);

        void showNoModules();

        void showModuleDetailsUi(Module module);

        void filter(CardsFilterType requestType);
    }

    interface Presenter extends BasePresenter {
        void loadModules();

        void openModuleDetails(Module clickedModule);

        void setFiltering(CardsFilterType requestType);

        CardsFilterType getFiltering();
    }
}
