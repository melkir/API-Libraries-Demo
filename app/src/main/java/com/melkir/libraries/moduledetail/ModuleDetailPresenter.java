package com.melkir.libraries.moduledetail;

import android.support.annotation.Nullable;

import com.android.annotations.NonNull;

import static com.google.common.base.Preconditions.checkNotNull;
import static com.melkir.libraries.moduledetail.ModuleDetailContract.Presenter;
import static com.melkir.libraries.moduledetail.ModuleDetailContract.View;

public class ModuleDetailPresenter implements Presenter {
    private final String mModule;
    private final View mModuleDetailView;

    public ModuleDetailPresenter(@Nullable String module,
                                 @NonNull ModuleDetailContract.View moduleDetailView) {
        mModule = module;
        mModuleDetailView = checkNotNull(moduleDetailView, "moduleDetailView cannot be null");
    }

    @Override
    public void start() {
        openModule();
    }

    private void openModule() {
//        if (Strings.isNullOrEmpty(mModule)) {
//            mModuleDetailView.showMissingModule();
//        } else {
//            mModuleDetailView.showModule();
//        }
    }
}
