package com.melkir.libraries.moduledetail;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.melkir.libraries.data.Module;

import static com.google.common.base.Preconditions.checkNotNull;
import static com.melkir.libraries.moduledetail.ModuleDetailContract.Presenter;
import static com.melkir.libraries.moduledetail.ModuleDetailContract.View;

public class ModuleDetailPresenter implements Presenter {
    private static final String TAG = ModuleDetailPresenter.class.getSimpleName();

    private final Module mModule;
    private final View mModuleDetailView;

    public ModuleDetailPresenter(@Nullable Module module, @NonNull View moduleDetailView) {
        mModule = checkNotNull(module, "module cannot be null");
        mModuleDetailView = checkNotNull(moduleDetailView, "moduleDetailView cannot be null");

        mModuleDetailView.setPresenter(this);
    }

    @Override
    public void start() {
        loadModule();
    }

    private void loadModule() {
        if (null == mModule) {
            mModuleDetailView.showMissingModule();
        } else {
            mModuleDetailView.showModule(mModule);
        }
    }

    @Override
    public void startModule() {
        mModuleDetailView.startModule(mModule.getAction());
    }
}
