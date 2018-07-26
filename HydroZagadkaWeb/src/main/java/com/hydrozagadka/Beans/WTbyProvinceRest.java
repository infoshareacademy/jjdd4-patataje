package com.hydrozagadka.Beans;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.hydrozagadka.Model.WaterContainerView;
import com.hydrozagadka.WaterContainer;
import com.hydrozagadka.dao.WaterContainerDao;
import com.hydrozagadka.mappers.WaterContainerMapper;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

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
        return Response.ok(jsonParserBean.parseToJson(waterContainerMapper.mapToWaterContainerView(
                waterContainerDao.getWaterContainerByProvince(province)))).build();
    }

    @GET
    @Path("/{province}/{name}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getStationInWaterContainer(@PathParam("province") String province, @PathParam("name") String name) throws JsonProcessingException {
        List<WaterContainer> waterContainerList = waterContainerDao.getWaterContainerByProvinceAndwaterContainer(province,name);
        List<WaterContainerView> waterContainerViews = waterContainerMapper.mapToWaterContainerView(waterContainerList);
        return Response.ok(jsonParserBean.parseToJson(waterContainerViews)).build();
    }
}
