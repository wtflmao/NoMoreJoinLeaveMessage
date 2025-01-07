package net.mrliam2614.nomorejoinleavemessage.config.annotation;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Retention(RetentionPolicy.RUNTIME)
public @interface ConfigField {
    String path();
}
