package com.melkir.libraries.moduledetail;

import com.melkir.libraries.BasePresenter;
import com.melkir.libraries.BaseView;

public interface ModuleDetailContract {
    interface View extends BaseView<Presenter> {
        void showMissingModule();

        void showModule();
    }

    interface Presenter extends BasePresenter {
    }
}
