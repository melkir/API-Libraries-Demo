package com.melkir.libraries.test;

import com.melkir.libraries.data.Module;
import com.melkir.libraries.data.ModulesDataSource;
import com.melkir.libraries.modules.ModulesContract;
import com.melkir.libraries.modules.ModulesPresenter;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class ModulesPresenterTest {
    private static List<Module> MODULES;

    @Rule
    public MockitoRule mockitoRule = MockitoJUnit.rule();

    @Mock
    ModulesDataSource mModulesRepository;

    @Mock
    ModulesContract.View mModulesView;

    private ModulesPresenter mModulesPresenter;

    @Before
    public void setUp() {
        mModulesPresenter = new ModulesPresenter(mModulesRepository, mModulesView);
        MODULES = Arrays.asList(new Module(), new Module(), new Module());
    }

    @Test
    public void shouldPassModulesToView() {
        // arrange
        when(mModulesRepository.getModules()).thenReturn(MODULES);

        // act
        mModulesPresenter.loadModules();

        // assert
        verify(mModulesView).showModules(MODULES);
    }

    @Test
    public void shouldHandleNoModulesFound() {
        when(mModulesRepository.getModules()).thenReturn(Collections.emptyList());

        mModulesPresenter.loadModules();

        verify(mModulesView).showNoModules();
    }
}