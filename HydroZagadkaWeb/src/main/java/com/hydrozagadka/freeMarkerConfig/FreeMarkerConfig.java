package com.hydrozagadka.freeMarkerConfig;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateExceptionHandler;

import javax.enterprise.context.ApplicationScoped;
import javax.servlet.ServletContext;
import java.io.IOException;

@ApplicationScoped
public class FreeMarkerConfig {
    private Configuration configuration;
    private final String TEMPLATES_DIRECTORY_PATH = "WEB-INF/templates/";

    public Template getTemplate(String templateName, ServletContext servletContext) throws IOException {
        if (configuration == null) {
            configuration = new Configuration(Configuration.VERSION_2_3_28);
            configuration.setServletContextForTemplateLoading(servletContext, TEMPLATES_DIRECTORY_PATH);
            configuration.setDefaultEncoding("UTF-8");
            configuration.setTemplateExceptionHandler(TemplateExceptionHandler.RETHROW_HANDLER);
            configuration.setLogTemplateExceptions(false);
            configuration.setWrapUncheckedExceptions(true);
        }
        return configuration.getTemplate(templateName);
    }

}
