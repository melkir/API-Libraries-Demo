package com.melkir.libraries.test;

import com.melkir.libraries.data.Module;
import com.melkir.libraries.moduledetail.ModuleDetailContract;
import com.melkir.libraries.moduledetail.ModuleDetailPresenter;

import org.junit.Rule;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;

import static org.mockito.Mockito.verify;

public class ModuleDetailPresenterTest {
    private static Module MODULE = new Module();

    @Rule
    public MockitoRule mockitoRule = MockitoJUnit.rule();

    @Mock
    ModuleDetailContract.View mModuleDetailView;

    private ModuleDetailPresenter mModuleDetailPresenter;

    @Test
    public void shouldPassModuleToView() {
        mModuleDetailPresenter = new ModuleDetailPresenter(MODULE, mModuleDetailView);

        mModuleDetailPresenter.loadModule();

        verify(mModuleDetailView).showModule(MODULE);
    }

    @Test
    public void shouldHandleNoModuleFound() {
        mModuleDetailPresenter = new ModuleDetailPresenter(null, mModuleDetailView);

        mModuleDetailPresenter.loadModule();

        verify(mModuleDetailView).showMissingModule();
    }
}
