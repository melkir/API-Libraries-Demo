package com.melkir.libraries.modules;

import com.melkir.libraries.BasePresenter;
import com.melkir.libraries.BaseView;
import com.melkir.libraries.model.Module;

import java.util.List;

/**
 * Created by melkir on 28/02/17.
 */

public interface ModulesContract {
    interface View extends BaseView<Presenter> {
        void showModules(List<Module> modules);
    }

    interface Presenter extends BasePresenter {
        void loadModules();
    }
}
