package com.example.templating;

import freemarker.template.Configuration;
import freemarker.template.TemplateExceptionHandler;

import java.io.File;
import java.io.IOException;

public class FreemarkerConfiguration {
    private static Configuration configuration = null;

    public static Configuration get() throws IOException {
        if(configuration == null) {
            configuration = new Configuration(Configuration.VERSION_2_3_31);
            configuration.setDirectoryForTemplateLoading(new File(System.getenv("JAVA_RESOURCES") + "/wsxml1/templates"));
            configuration.setDefaultEncoding("UTF-8");
            configuration.setTemplateExceptionHandler(TemplateExceptionHandler.HTML_DEBUG_HANDLER);
        }
        return configuration;
    }
}
