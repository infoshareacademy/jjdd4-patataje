package com.hydrozagadka.Beans;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.hydrozagadka.dao.WaterContainerDao;
import com.hydrozagadka.mappers.WaterContainerMapper;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/")
public class WTbyProvinceRest {

    @Inject
    JsonParserBean jsonParserBean;

    @Inject
    WaterContainerDao waterContainerDao;

    @Inject
    WaterContainerMapper waterContainerMapper;

    @GET
    @Path("/province/{province}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getWTbyProvinces(@PathParam("province") String province) throws JsonProcessingException {
        return Response.ok(jsonParserBean.parseToJson(waterContainerMapper.mapToWaterContainerView(
                waterContainerDao.getWaterContainerByProvince(province)))).build();
    }
}
