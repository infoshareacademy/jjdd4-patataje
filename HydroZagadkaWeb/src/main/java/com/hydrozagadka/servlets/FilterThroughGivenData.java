package com.hydrozagadka.servlets;

import com.hydrozagadka.CSVLoader;
import com.hydrozagadka.FilterFiles;
import com.hydrozagadka.WaterContainer;
import com.hydrozagadka.freeMarkerConfig.FreeMarkerConfig;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@WebServlet("/filter")
public class FilterThroughGivenData extends HttpServlet {
    private Logger LOG = LoggerFactory.getLogger(FilterThroughGivenData.class);

    private CSVLoader csvLoader = new CSVLoader();
    private FilterFiles filterFiles = new FilterFiles(csvLoader);
    @Inject
    FreeMarkerConfig freeMarkerConfig;
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String state = req.getParameter("state");
        List<String> wt = filterFiles.showWaterContainersThroughProvince(state).stream()
                .map(WaterContainer::getContainerName)
                .collect(Collectors.toList());

        PrintWriter pr = resp.getWriter();
        Map<String, Object> model = new HashMap<>();
        model.put("provinces", csvLoader.getProvince());
        model.put("watercontainer",wt);
        Template template = freeMarkerConfig.getTemplate("index.ftlh", getServletContext());
        try {
            template.process(model, pr);
        } catch (TemplateException e) {
            e.printStackTrace();
        }
        pr.close();

    }
}