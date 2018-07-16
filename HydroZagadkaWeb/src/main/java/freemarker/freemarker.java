package freemarker;


import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateExceptionHandler;

import javax.enterprise.context.ApplicationScoped;
import javax.servlet.ServletContext;
import java.io.IOException;


@ApplicationScoped
public class freemarker {
    private final String TEMPLATES_DIRECTORY_PATH = "WEB-INF/templates";

    private Configuration configuration;


    public Template getTemplate(ServletContext servletContext, String templateName) throws IOException {

        if (configuration == null) {
            configuration = new Configuration(Configuration.VERSION_2_3_28);
            configuration.setDefaultEncoding("UTF-8");
            configuration.setTemplateExceptionHandler(TemplateExceptionHandler.RETHROW_HANDLER);
            configuration.setLogTemplateExceptions(false);
            configuration.setWrapUncheckedExceptions(true);
        }
        configuration.setServletContextForTemplateLoading(servletContext, "WEB-INF/templates");


        return configuration.getTemplate(templateName);
    }
}

