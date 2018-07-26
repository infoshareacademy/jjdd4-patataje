package com.hydrozagadka.Beans;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.hydrozagadka.Model.StationView;
import com.hydrozagadka.Model.WaterContainerView;
import com.hydrozagadka.WaterContainer;
import com.hydrozagadka.dao.WaterContainerDao;
import com.hydrozagadka.mappers.HistoryMapper;
import com.hydrozagadka.mappers.WaterContainerMapper;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;
import java.util.stream.Collectors;

@Path("/")
public class WTbyProvinceRest {

    @Inject
    JsonParserBean jsonParserBean;

    @Inject
    WaterContainerDao waterContainerDao;

    @Inject
    WaterContainerMapper waterContainerMapper;


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



}
