package com.hydrozagadka.servlets;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.hydrozagadka.mappers.WaterContainerMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.hydrozagadka.dao.WaterContainerDao;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(urlPatterns = "/watercontainer")
public class WatercontainerServlet extends HttpServlet {
    private static Logger logger = LoggerFactory.getLogger(LoadServlet.class);
    @Inject
    private WaterContainerMapper mapper;
    @Inject
    private WaterContainerDao waterContainerDao;


    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        ObjectMapper objectMapper = new ObjectMapper();
        response.setContentType("application/json; charset=utf-8");
        objectMapper.enable(SerializationFeature.INDENT_OUTPUT);
        objectMapper.setVisibility(PropertyAccessor.FIELD, JsonAutoDetect.Visibility.ANY);
        String province = request.getParameter("name");
        PrintWriter pr = response.getWriter();
    //    String result = mapper.mapToWaterContainerView(waterContainerDao.getWaterContainerByProvince(province));
    //    pr.println(result);
        logger.info("Water containers filtred throught provinces");
        pr.close();
    }
}
