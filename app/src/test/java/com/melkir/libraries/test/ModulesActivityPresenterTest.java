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

public class ModulesActivityPresenterTest {
    @Rule
    public MockitoRule mockitoRule = MockitoJUnit.rule();

    @Mock
    ModulesDataSource modulesRepository;

    @Mock
    ModulesContract.View view;

    private ModulesPresenter presenter;
    private final List<Module> MANY_MODULES = Arrays.asList(new Module(), new Module(), new Module());

    @Before
    public void setUp() {
        presenter = new ModulesPresenter(modulesRepository, view);
    }

    @Test
    public void shouldPassModulesToView() {
        // arrange
        when(modulesRepository.getModules()).thenReturn(MANY_MODULES);

        // act
        presenter.loadModules();

        // assert
        verify(view).showModules(MANY_MODULES);
    }

    @Test
    public void shouldHandleNoModulesFound() {
        when(modulesRepository.getModules()).thenReturn(Collections.<Module>emptyList());

        presenter.loadModules();

        verify(view).showNoModules();
    }
}