package com.melkir.libraries.moduledetail;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;

import com.melkir.libraries.moduledetail.ModuleDetailContract.View;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * Created by melkir on 02/03/17.
 */

public class ModuleDetailFragment extends Fragment implements View {
    private static final String ARGUMENT_MODULE = "MODULE";
    private ModuleDetailContract.Presenter mPresenter;

    public static ModuleDetailFragment newInstance(@Nullable String module) {
        Bundle arguments = new Bundle();
        arguments.putString(ARGUMENT_MODULE, module);
        ModuleDetailFragment fragment = new ModuleDetailFragment();
        fragment.setArguments(arguments);
        return fragment;
    }

    @Override
    public void onResume() {
        super.onResume();
        mPresenter.start();
    }

    @Override
    public void setPresenter(ModuleDetailContract.Presenter presenter) {
        mPresenter = checkNotNull(presenter);
    }

    @Override
    public void showMissingModule() {
        // TODO show a friendly message if module is missing
    }

    @Override
    public void showModule() {
        // TODO load module
    }
}
