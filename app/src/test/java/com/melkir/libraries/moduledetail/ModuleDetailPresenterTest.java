package com.melkir.libraries.moduledetail;

import com.melkir.libraries.data.Module;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;

public class ModuleDetailPresenterTest {
    private static Module MODULE = new Module();

    @Rule
    public MockitoRule mockitoRule = MockitoJUnit.rule();

    @Mock
    ModuleDetailContract.View mModuleDetailView;

    private ModuleDetailPresenter mModuleDetailPresenter;

    @Before
    public void setUp() {
        MODULE = new Module(1, "Barcode", "A barcode reader", "http://url.com", "barcode", null, 0);
        mModuleDetailPresenter = new ModuleDetailPresenter(MODULE, mModuleDetailView);
    }

    @Test
    public void shouldPassModuleToView() {
        mModuleDetailPresenter.loadModule();

        verify(mModuleDetailView).showModule(MODULE);
    }

    @Test
    public void shouldHandleNoModuleFound() {
        mModuleDetailPresenter = new ModuleDetailPresenter(null, mModuleDetailView);

        mModuleDetailPresenter.loadModule();

        verify(mModuleDetailView).showMissingModule();
    }

    @Test
    public void clickOnFab_LaunchModule() {
        mModuleDetailPresenter.startModule();

        verify(mModuleDetailView).startModule(any(String.class));
    }
}
