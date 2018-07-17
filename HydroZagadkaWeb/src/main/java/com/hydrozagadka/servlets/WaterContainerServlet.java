package com.hydrozagadka.servlets;

import com.hydrozagadka.History;
import com.hydrozagadka.WaterContainer;
import com.hydrozagadka.dao.WaterContainerDao;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@WebServlet(urlPatterns = "/waterContainer")
public class WaterContainerServlet extends HttpServlet {

    @Inject
    private WaterContainerDao waterContainerDao;

    @Override
    public void init() throws ServletException {
        List<History> list = new ArrayList<>();
        WaterContainer waterContainer = new WaterContainer(234L, "dwdwd", "dwd", "dwada", list);
        waterContainerDao.save(waterContainer);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        final String action = req.getParameter("action");
         if (action == null || action.isEmpty()) {
             resp.getWriter().write("Complete parameter");
             return;
         }
           if (action.equals("findAllContainers")) {
            findAllWaterContainers(req, resp);
        }
    }

    private void findAllWaterContainers(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        final List<WaterContainer> waterContainers = waterContainerDao.findAll();
        for (WaterContainer w: waterContainers) {
            resp.getWriter().write(w.toString() + "\n");
        }
    }


}
