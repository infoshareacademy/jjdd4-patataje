package com.hydrozagadka.servlets;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.hydrozagadka.Beans.WaterContainerAndStationMapper;
import com.hydrozagadka.CSVLoader;
import com.hydrozagadka.FilterFiles;
import com.hydrozagadka.Model.WaterContainerView;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

@WebServlet(urlPatterns = "/watercontainer")
public class WatercontainerServlet extends HttpServlet {

    @Inject
    WaterContainerAndStationMapper mapper;
    CSVLoader csvLoader = new CSVLoader();
    FilterFiles filterFiles = new FilterFiles(csvLoader);

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.enable(SerializationFeature.INDENT_OUTPUT);
        objectMapper.setVisibility(PropertyAccessor.FIELD, JsonAutoDetect.Visibility.ANY);
        String province = request.getParameter("name");
        PrintWriter pr = response.getWriter();
        List<WaterContainerView> result = mapper.mapToWaterContainerView(filterFiles.showWaterContainersThroughProvince(province));
        String a =objectMapper.writeValueAsString(result);
        pr.println(a);
        pr.close();
    }
}
