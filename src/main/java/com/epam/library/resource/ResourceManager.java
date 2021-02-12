package com.epam.library.resource;

import java.util.Locale;
import java.util.ResourceBundle;

public class ResourceManager {

    private static final String RESOURCE_NAME = "locale";

    private static ResourceManager instance;

    private ResourceBundle resourceBundle = ResourceBundle.getBundle(RESOURCE_NAME, Locale.getDefault());

    private ResourceManager() {
    }

    public static ResourceManager getInstance() {
        if (instance == null) {
            instance = new ResourceManager();
        }
        return instance;
    }

    public String getProperty(String key) {
        return resourceBundle.getString(key);
    }

    public void changeResource(Locale locale) {
        resourceBundle = ResourceBundle.getBundle(RESOURCE_NAME, locale);
    }
}