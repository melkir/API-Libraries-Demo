package com.melkir.libraries.modules;

import android.support.v4.app.Fragment;

import com.melkir.libraries.model.Module;

import java.util.List;

import static com.google.common.base.Preconditions.checkNotNull;

public class ModulesFragment extends Fragment implements ModulesContract.View {
    private ModulesContract.Presenter mPresenter;

    @Override
    public void showModules(List<Module> modules) {
    }

    @Override
    public void onResume() {
        super.onResume();
        mPresenter.start();
    }

    @Override
    public void setPresenter(ModulesContract.Presenter presenter) {
        mPresenter = checkNotNull(presenter);
    }
}
