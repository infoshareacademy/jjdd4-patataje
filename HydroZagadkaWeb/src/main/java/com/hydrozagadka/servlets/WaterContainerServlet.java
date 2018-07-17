package com.hydrozagadka.servlets;

import com.hydrozagadka.WaterContainer;
import com.hydrozagadka.dao.WaterContainerDao;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(urlPatterns = "/waterContainer")
public class WaterContainerServlet extends HttpServlet {

    @Inject
    private WaterContainerDao waterContainerDao;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        final String action = req.getParameter("action");
//        if ()
//        if () {
//            findAllWaterContainers(req, resp);
//        }
    }

    private void findAllWaterContainers(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        final List<WaterContainer> waterContainers = waterContainerDao.findAll();
        for (WaterContainer w: waterContainers) {
            resp.getWriter().write(w.toString() + "\n");
        }
    }


}
