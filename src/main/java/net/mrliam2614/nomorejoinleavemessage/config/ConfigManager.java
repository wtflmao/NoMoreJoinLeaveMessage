package net.mrliam2614.nomorejoinleavemessage.config;

import net.mrliam2614.nomorejoinleavemessage.config.annotation.ConfigField;
import net.mrliam2614.nomorejoinleavemessage.config.annotation.ConfigFile;
import org.yaml.snakeyaml.Yaml;

import java.io.InputStream;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.lang.reflect.Field;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;

public class ConfigManager {
    public static <T> T loadConfig(Class<T> configClass) {
        T config = null;
        try {
            ConfigFile configFile = configClass.getAnnotation(ConfigFile.class);
            if (configFile != null) {
                Path configPath = Paths.get(configFile.path(), configFile.name());
                if (!Files.exists(configPath)) {
                    Files.createDirectories(configPath.getParent());
                    Files.createFile(configPath);
                    T defaultConfig = configClass.newInstance();
                    saveConfig(defaultConfig);
                }
                Yaml yaml = new Yaml();
                InputStream in = Files.newInputStream(Paths.get(configFile.path(), configFile.name()));
                try {
                    Map<String, Object> data = (Map<String, Object>) yaml.load(in);
                    config = configClass.newInstance();
                    for (Field field : configClass.getDeclaredFields()) {
                        ConfigField configField = field.getAnnotation(ConfigField.class);
                        if (configField != null) {
                            field.setAccessible(true);
                            field.set(config, data.get(configField.path()));
                        }
                    }
                    if (in != null)
                        in.close();
                } catch (Throwable throwable) {
                    if (in != null)
                        try {
                            in.close();
                        } catch (Throwable throwable1) {
                            throwable.addSuppressed(throwable1);
                        }
                    throw throwable;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return config;
    }

    public static <T> void saveConfig(T config) {
        try {
            ConfigFile configFile = config.getClass().getAnnotation(ConfigFile.class);
            if (configFile != null) {
                Yaml yaml = new Yaml();
                Map<String, Object> data = new HashMap<>();
                for (Field field : config.getClass().getDeclaredFields()) {
                    ConfigField configField = field.getAnnotation(ConfigField.class);
                    if (configField != null) {
                        field.setAccessible(true);
                        data.put(configField.path(), field.get(config));
                    }
                }
                Writer writer = new OutputStreamWriter(Files.newOutputStream(Paths.get(configFile.path(), configFile.name())));
                try {
                    yaml.dump(data, writer);
                    writer.close();
                } catch (Throwable throwable) {
                    try {
                        writer.close();
                    } catch (Throwable throwable1) {
                        throwable.addSuppressed(throwable1);
                    }
                    throw throwable;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
