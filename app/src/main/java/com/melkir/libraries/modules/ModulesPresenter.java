package com.melkir.libraries.modules;

import android.util.Log;

import com.android.annotations.NonNull;
import com.melkir.libraries.data.ModulesDataSource;
import com.melkir.libraries.model.Module;

import java.util.List;

import static com.google.common.base.Preconditions.checkNotNull;

public class ModulesPresenter implements ModulesContract.Presenter {
    public static final String TAG = ModulesPresenter.class.getSimpleName();

    private final ModulesDataSource mModulesRepository;
    private final ModulesContract.View mModulesView;
    private ModuleFilterType mCurrentFiltering = ModuleFilterType.ALL_CATEGORIES;

    public ModulesPresenter(@NonNull ModulesDataSource modulesRepository, @NonNull
            ModulesContract.View modulesView) {
        mModulesRepository = checkNotNull(modulesRepository, "modulesRepository cannot be null");
        mModulesView = checkNotNull(modulesView, "modulesView cannot be null");
        mModulesView.setPresenter(this);
    }

    @Override
    public void loadModules() {
        List<Module> modules = mModulesRepository.getModules();
        mModulesView.showModules(modules);
    }

    @Override
    public void openModuleDetails(@NonNull Module requestedModule) {
        checkNotNull(requestedModule, "requestedModule cannot be null!");
        mModulesView.showModuleDetailsUi(requestedModule);
    }

    @Override
    public void start() {
        loadModules();
    }

    /**
     * Sets the current module filtering type.
     *
     * @param requestModuleFilterType Can be {@link ModuleFilterType#ALL_CATEGORIES},
     *                                {@link ModuleFilterType#COMPONENT},
     *                                {@link ModuleFilterType#GAME}, or
     *                                {@link ModuleFilterType#DESIGN}
     */
    @Override
    public void setFiltering(ModuleFilterType requestModuleFilterType) {
        // restore all categories filter if the requested category filter is active
        // otherwise select the requested category
        mCurrentFiltering = mCurrentFiltering == requestModuleFilterType ?
                ModuleFilterType.ALL_CATEGORIES : requestModuleFilterType;
        mModulesView.filter(mCurrentFiltering);
        Log.d(TAG, "Filter active: " + mCurrentFiltering);
    }

    @Override
    public ModuleFilterType getFiltering() {
        return mCurrentFiltering;
    }
}
