package com.example.templating;

import freemarker.template.Template;
import freemarker.template.TemplateException;

import java.io.IOException;
import java.io.StringWriter;

public class Renderer {

    private static String process(Template template, Object modelData) throws TemplateException, IOException {
        StringWriter writer = new StringWriter();
        template.process(modelData, writer);
        return writer.toString();
    }

    public static String render(String template_name) throws IOException, TemplateException {
        return render(template_name, new Object());
    }

    public static String render(String template_name, Object modelData) throws IOException, TemplateException {
        Template template = FreemarkerConfiguration.get().getTemplate(template_name);
        return process(template, modelData);
    }
}
