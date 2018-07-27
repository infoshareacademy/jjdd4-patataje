package com.hydrozagadka.Beans;

import com.hydrozagadka.Model.NewestWaterContainerData;
import com.hydrozagadka.WaterContainer;

import javax.ejb.Stateless;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Stateless
public class ApiConnector {
    Client client = ClientBuilder.newClient();


    public List<NewestWaterContainerData> load(){
        WebTarget webTarget = client.target("https://danepubliczne.imgw.pl/api/data/hydro/");

        Response response = webTarget.request().accept(MediaType.APPLICATION_JSON).get();


        if (response.getStatus() != 200) {
            throw new RuntimeException("Nie możemy pobrać danych");
        }
        return response.readEntity(new GenericType<List<NewestWaterContainerData>>() {});
    }
}
