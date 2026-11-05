package ru.nordcodes.utils;

import lombok.Getter;
import lombok.SneakyThrows;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.Properties;


@Getter
public class PropsStore {
    private final Properties properties;

    @SneakyThrows
    public PropsStore(String env) {
        this.properties = loadByEnv(env);
    }

    private static Properties loadByEnv(String env) throws IOException {
        if (env == null || env.isBlank()) {
            throw new IllegalArgumentException("Контур не указан");
        }

        String fileName = "application-" + env + ".properties";
        URL url = PropsStore.class.getClassLoader().getResource(fileName);

        if (url == null) {
            throw new IOException("Файл конфигурации в папке src/test/resources не найден: " + fileName);
        }

        try (InputStream input = url.openStream()) {
            Properties props = new Properties();
            props.load(input);
            return props;
        }
    }
}
