package ru.nordcodes.modules;

import com.google.inject.AbstractModule;
import com.google.inject.Provides;
import com.google.inject.Singleton;
import ru.nordcodes.helpers.MyServiceHelper;
import ru.nordcodes.utils.PropsStore;

public class GuiceModule extends AbstractModule {
    @Singleton
    @Provides
    public MyServiceHelper getEndpointHelper(PropsStore propsStore) { return new MyServiceHelper(propsStore);}

    @Provides
    @Singleton
    public PropsStore getPropStore() { return new PropsStore(System.getProperty("env", "localhost"));}
}
