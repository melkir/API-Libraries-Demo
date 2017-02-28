package com.melkir.libraries.test;

import com.melkir.libraries.data.ModulesDataSource;
import com.melkir.libraries.model.Module;
import com.melkir.libraries.modules.ModulesContract;
import com.melkir.libraries.modules.ModulesPresenter;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@RunWith(MockitoJUnitRunner.class)
public class ModulesActivityPresenterTest {

    @Mock
    ModulesDataSource modulesRepository;

    @Mock
    ModulesContract.View view;

    @Test
    public void shouldPassModulesToView() {
        // arrange
        List<Module> moduleList = Arrays.asList(new Module(), new Module(), new Module());
        Mockito.when(modulesRepository.getModules()).thenReturn(moduleList);

        // act
        ModulesPresenter presenter = new ModulesPresenter(modulesRepository, view);
        presenter.loadModules();

        // assert
        Mockito.verify(view).showModules(moduleList);
    }

    @Test
    public void shouldHandleNoModulesFound() {
        List<Module> emptyModuleList = Collections.emptyList();
        Mockito.when(modulesRepository.getModules()).thenReturn(emptyModuleList);

        ModulesPresenter presenter = new ModulesPresenter(modulesRepository, view);
        presenter.loadModules();

        Mockito.verify(view).showNoModules();
    }
}