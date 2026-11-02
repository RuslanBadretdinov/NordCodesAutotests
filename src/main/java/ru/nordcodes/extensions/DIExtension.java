package ru.nordcodes.extensions;

import com.google.inject.Guice;
import com.google.inject.Injector;
import lombok.Getter;
import org.junit.jupiter.api.extension.BeforeAllCallback;
import org.junit.jupiter.api.extension.BeforeEachCallback;
import org.junit.jupiter.api.extension.ExtensionContext;
import ru.nordcodes.modules.GuiceModule;

import java.util.Properties;

public class DIExtension implements BeforeAllCallback, BeforeEachCallback {
    private Injector guiceInjector;
    @Getter
    private Properties properties;

    @Override
    public void beforeAll(ExtensionContext context) throws Exception {
        GuiceModule helpersModule = new GuiceModule();
        guiceInjector = Guice.createInjector(helpersModule);
        guiceInjector.injectMembers(this);
    }

    @Override
    public void beforeEach(ExtensionContext extensionContext) throws Exception {
        extensionContext.getTestInstance().ifPresent(instance -> guiceInjector.injectMembers(instance));
    }
}
