package com.melkir.libraries.modules;

import com.android.annotations.NonNull;
import com.melkir.libraries.data.ModulesDataSource;
import com.melkir.libraries.model.Module;

import java.util.List;

import static com.google.common.base.Preconditions.checkNotNull;

public class ModulesPresenter implements ModulesContract.Presenter {
    private final ModulesDataSource mModulesRepository;
    private final ModulesContract.View mModulesView;

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

}
