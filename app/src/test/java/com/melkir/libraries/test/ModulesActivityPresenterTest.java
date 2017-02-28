package com.melkir.libraries.test;

import com.melkir.libraries.data.ModulesDataSource;
import com.melkir.libraries.model.Module;
import com.melkir.libraries.modules.ModulesContract;
import com.melkir.libraries.modules.ModulesPresenter;

import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class ModulesActivityPresenterTest {

    @Test
    public void shouldPassModulesToView() {
        // arrange
        ModulesContract.View view = new MockView();
        MockModulesRepository modulesRepository = new MockModulesRepository(true);
        // act
        ModulesPresenter presenter = new ModulesPresenter(modulesRepository, view);
        presenter.loadModules();
        // assert
        Assert.assertEquals(true, ((MockView) view).passed);
    }

    @Test
    public void shouldHandleNoModulesFound() {
        // arrange
        ModulesContract.View view = new MockView();
        MockModulesRepository modulesRepository = new MockModulesRepository(false);
        // act
        ModulesPresenter presenter = new ModulesPresenter(modulesRepository, view);
        presenter.loadModules();
        // assert
        Assert.assertEquals(true, ((MockView) view).noModules);
    }

    private class MockView implements ModulesContract.View {
        boolean passed;
        boolean noModules;

        @Override
        public void showModules(List<Module> moduleList) {
            if (3 == moduleList.size()) passed = true;
            else if (0 == moduleList.size()) noModules = true;
        }

        @Override
        public void setPresenter(ModulesContract.Presenter presenter) {
        }
    }

    private class MockModulesRepository implements ModulesDataSource {
        private boolean returnSomeModules;

        public MockModulesRepository(boolean returnSomeModules) {
            this.returnSomeModules = returnSomeModules;
        }

        @Override
        public List<Module> getModules() {
            if (returnSomeModules) {
                return Arrays.asList(new Module(), new Module(), new Module());
            } else {
                return Collections.emptyList();
            }
        }
    }
}