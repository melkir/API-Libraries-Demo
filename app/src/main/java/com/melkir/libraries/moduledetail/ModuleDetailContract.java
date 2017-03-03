package com.melkir.libraries.moduledetail;

import com.melkir.libraries.BasePresenter;
import com.melkir.libraries.BaseView;
import com.melkir.libraries.data.Module;

public interface ModuleDetailContract {
    interface View extends BaseView<Presenter> {
        void showMissingModule();

        void showModule(Module module);

        void startModule(String moduleName);
    }

    interface Presenter extends BasePresenter {
        void startModule();
    }
}
