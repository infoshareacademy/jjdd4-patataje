package com.hydrozagadka.Beans;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.hydrozagadka.History;
import com.hydrozagadka.Model.ChartHistory;
import com.hydrozagadka.Model.WaterContainerView;
import com.hydrozagadka.WaterContainer;
import com.hydrozagadka.dao.HistoryDao;
import com.hydrozagadka.dao.WaterContainerDao;
import com.hydrozagadka.mappers.HistoryMapper;
import com.hydrozagadka.mappers.WaterContainerMapper;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.time.LocalDate;
import java.util.List;

@Path("/")
public class WTbyProvinceRest {

    @Inject
    JsonParserBean jsonParserBean;

    @Inject
    WaterContainerDao waterContainerDao;

    @Inject
    WaterContainerMapper waterContainerMapper;

    @Inject
    HistoryDao historyDao;

    @Inject
    HistoryMapper historyMapper;

    @GET
    @Path("/{province}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getWTbyProvinces(@PathParam("province") String province) throws JsonProcessingException {
        List<WaterContainer> waterContainerList = waterContainerDao.getWaterContainerByProvince(province);
        List<WaterContainerView> waterContainerViews = waterContainerMapper.mapToWaterContainerView(waterContainerList);
        return Response.ok(jsonParserBean.parseToJson(waterContainerViews)).build();
    }

    @GET
    @Path("/{province}/{container}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getWTbyProvinces(@PathParam("province") String province, @PathParam("container") String container) throws JsonProcessingException {
        return Response.ok(jsonParserBean.parseToJson(waterContainerMapper.mapToStationView(
                waterContainerDao.getWaterContainerByProvinceAndwaterContainer(province,container)))).build();
    }

    @GET
    @Path("/id/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getwaterContainerHistory(@PathParam("id") Long id, @QueryParam("startDate") String startdate, @QueryParam("endDate") String enddate) throws JsonProcessingException {
        LocalDate startDate = LocalDate.of(1954, 01, 01);
        LocalDate endDate = LocalDate.now();
        if (isCorrectDate(startdate, enddate)) {
             startDate = LocalDate.parse(startdate);
             endDate = LocalDate.parse(enddate);
        }
        List<History> histories = historyDao.getHistoryByWaterContainerWithDates(id,startDate,endDate);
        List<ChartHistory> chartHistories = historyMapper.mapToChartHistory(histories);
        return Response.ok(jsonParserBean.parseToJson(chartHistories)).build();
    }


    private boolean isCorrectDate(String startDate, String endDate) {
        if (startDate == null || startDate.isEmpty()) {
            return false;
        }
        if (endDate == null || endDate.isEmpty()) {
            return false;
        }
        return true;
    }

}
