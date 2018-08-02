package com.hydrozagadka.Beans;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.hydrozagadka.History;
import com.hydrozagadka.DTO.ChartHistory;
import com.hydrozagadka.DTO.StationView;
import com.hydrozagadka.DTO.WaterContainerView;
import com.hydrozagadka.User;
import com.hydrozagadka.WaterContainer;
import com.hydrozagadka.dao.HistoryDao;
import com.hydrozagadka.dao.StatisticsDao;
import com.hydrozagadka.dao.UserDao;
import com.hydrozagadka.dao.WaterContainerDao;
import com.hydrozagadka.mappers.HistoryMapper;
import com.hydrozagadka.mappers.WaterContainerMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.transaction.Transactional;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.time.LocalDate;
import java.util.List;

@Path("/")
public class WTbyProvinceRest {

    @Inject
    private WaterContainerDao waterContainerDao;
    @Inject
    private WaterContainerMapper waterContainerMapper;
    @Inject
    private HistoryDao historyDao;
    @Inject
    private HistoryMapper historyMapper;
    @Inject
    private UserDao userDao;
    @Inject
    private StatisticsDao statisticsDao;

    private Logger logger = LoggerFactory.getLogger(WTbyProvinceRest.class);

    @GET
    @Path("/{province}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getWTbyProvinces(@PathParam("province") String province) throws JsonProcessingException {
        List<WaterContainer> waterContainerList = waterContainerDao.getWaterContainerByProvince(province);
        List<StationView> waterContainerViews = waterContainerMapper.mapToStationView(waterContainerList);
        logger.info("/rest/province filtrowanie danych po województwach zwrócono " + waterContainerViews.size() + " rekordów");
        return Response.ok(waterContainerViews).build();
    }

    @GET
    @Path("/{province}/{container}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getWTbyProvinces(@PathParam("province") String province, @PathParam("container") String container) throws JsonProcessingException {
        List<WaterContainer> waterContainers = waterContainerDao.getWaterContainerByProvinceAndwaterContainer(province, container);
        List<WaterContainerView> waterContainerViews = waterContainerMapper.mapToWaterContainerView(waterContainers);
        logger.info("/rest/province/container filtrowanie danych po województwie i zbiorniku zwrócono " + waterContainerViews.size() + " rekordów");
        return Response.ok(waterContainerViews).build();
    }

    @GET
    @Path("/id/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    @Transactional
    public Response getwaterContainerHistory(@PathParam("id") Long id,
                                             @QueryParam("startDate") String startdate,
                                             @QueryParam("endDate") String enddate,
                                             @QueryParam("check") boolean check,
                                             @Context HttpServletRequest request) throws JsonProcessingException {
        LocalDate startDate = LocalDate.of(1954, 01, 01);
        LocalDate endDate = LocalDate.now();
        addStat(id);
        if (isCorrectDate(startdate, enddate)) {
            startDate = LocalDate.parse(startdate);
            endDate = LocalDate.parse(enddate);
            logger.info("Dat nie podano");
        }
        if (check) {
            HttpSession httpSession =request.getSession();
            String token = (String) httpSession.getAttribute("token");
            addFavourite(id, token);
        }
        List<History> histories = historyDao.getHistoryByWaterContainerWithDates(id, startDate, endDate);
        List<ChartHistory> chartHistories = historyMapper.mapToChartHistory(histories);
        logger.info("/id/id zwrócenie historii danej stacji zwrócono " + chartHistories.size() + " rekordów");
        return Response.ok(chartHistories).build();
    }


    private boolean isCorrectDate(String startDate, String endDate) {
        return startDate != null && !startDate.isEmpty() && endDate != null && !endDate.isEmpty();
    }

    private void addStat(Long idWC){
        statisticsDao.update(idWC);
    }

    private void addFavourite(Long idWC, String userId) {
        User user = userDao.findById(userId);
        user.getWaterContainerId().add(waterContainerDao.findById(idWC));
        userDao.update(user);
    }
}
