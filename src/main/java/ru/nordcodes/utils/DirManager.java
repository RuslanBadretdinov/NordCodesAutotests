package ru.nordcodes.utils;

import lombok.SneakyThrows;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Optional;

public class DirManager {

    @SneakyThrows
    public static String readFromClasspath(String relativePath) {
        if (relativePath == null || relativePath.isBlank()) {
            throw new IllegalArgumentException("Путь не может быть пустым");
        }

        String path = relativePath.startsWith("/") ? relativePath.substring(1) : relativePath;

        var url = DirManager.class.getClassLoader().getResource(path);

        if (url == null) {
            throw new IOException("Файл не найден в classpath по пути: " + relativePath);
        }

        Path filePath = Path.of(url.toURI());
        return Files.readString(filePath, StandardCharsets.UTF_8);
    }

    @SneakyThrows
    public static Optional<String> readFromClasspathOptional(String relativePath) {
        return Optional.of(readFromClasspath(relativePath));
    }
}
