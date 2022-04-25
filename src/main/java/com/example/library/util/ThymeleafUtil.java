package com.example.library.util;

import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;
import org.thymeleaf.templateresolver.ClassLoaderTemplateResolver;

import java.io.Writer;
import java.nio.charset.StandardCharsets;

public class ThymeleafUtil {
    private static TemplateEngine templateEngine;

    static {
        templateEngine = new TemplateEngine();
        ClassLoaderTemplateResolver templateResolver = new ClassLoaderTemplateResolver();
        templateResolver.setCharacterEncoding(StandardCharsets.UTF_8.name());
        templateEngine.setTemplateResolver(templateResolver);
    }

    public static void process(String template, Context context, Writer writer) {
        templateEngine.process(template, context, writer);
    }
}
