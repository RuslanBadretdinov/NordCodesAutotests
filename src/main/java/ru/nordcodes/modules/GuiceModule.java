package ru.nordcodes.modules;

import com.google.inject.AbstractModule;
import com.google.inject.Provides;
import com.google.inject.Singleton;
import ru.nordcodes.helpers.EndpointHelper;
import ru.nordcodes.utils.props.PropsStore;

public class GuiceModule extends AbstractModule {
    @Singleton
    @Provides
    public EndpointHelper getEndpointHelper(PropsStore propsStore) { return new EndpointHelper(propsStore);}

    @Provides
    @Singleton
    public PropsStore getPropStore() { return new PropsStore(System.getProperty("env", "localhost"));}
}
