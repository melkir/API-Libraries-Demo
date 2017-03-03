package com.melkir.libraries.modules;

import android.support.annotation.NonNull;
import com.melkir.libraries.data.ModulesDataSource;
import com.melkir.libraries.data.Module;

import java.util.List;

import static com.google.common.base.Preconditions.checkNotNull;
import static com.melkir.libraries.modules.ModulesContract.*;

public class ModulesPresenter implements Presenter {
    private static final String TAG = ModulesPresenter.class.getSimpleName();

    private final ModulesDataSource mModulesRepository;
    private final View mModulesView;
    private ModulesType mCurrentFiltering = ModulesType.ALL_CATEGORIES;

    public ModulesPresenter(@NonNull ModulesDataSource modulesRepository, @NonNull View modulesView) {
        mModulesRepository = checkNotNull(modulesRepository, "modulesRepository cannot be null");
        mModulesView = checkNotNull(modulesView, "modulesView cannot be null");
        mModulesView.setPresenter(this);
    }

    @Override
    public void loadModules() {
        List<Module> modules = mModulesRepository.getModules();
        if (modules.isEmpty()) mModulesView.showNoModules();
        else mModulesView.showModules(modules);
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
     * @param requestModuleFilterType Can be {@link ModulesType#ALL_CATEGORIES},
     *                                {@link ModulesType#COMPONENT},
     *                                {@link ModulesType#GAME}, or
     *                                {@link ModulesType#DESIGN}
     */
    @Override
    public void setFiltering(ModulesType requestModuleFilterType) {
        // restore all categories filter if the requested category filter is active
        // otherwise select the requested category
        mCurrentFiltering = mCurrentFiltering == requestModuleFilterType ?
                ModulesType.ALL_CATEGORIES : requestModuleFilterType;
        mModulesView.filter(mCurrentFiltering);
    }

    @Override
    public void setFiltering(String requestTitle) {
        mModulesView.filter(requestTitle);
    }

    @Override
    public void setDisplayTypeCards() {
        mModulesView.showCardsViewUi();
    }

    @Override
    public void setDisplayTypeSearch() {
        mModulesView.showSearchViewUi();
    }

    @Override
    public ModulesType getFiltering() {
        return mCurrentFiltering;
    }
}
