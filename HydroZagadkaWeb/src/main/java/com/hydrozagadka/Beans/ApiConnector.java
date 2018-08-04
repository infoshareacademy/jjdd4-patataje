package com.hydrozagadka.Beans;

import com.hydrozagadka.Model.NewestWaterContainerData;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

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
    private Client client = ClientBuilder.newClient();
    private Logger logger = LoggerFactory.getLogger(ApiConnector.class);

    public List<NewestWaterContainerData> load() {
        WebTarget webTarget = client.target("https://danepubliczne.imgw.pl/api/data/hydro/");
        logger.info("pobieranie danych z https://danepubliczne.imgw.pl/api/data/hydro/");
        Response response = webTarget.request().accept(MediaType.APPLICATION_JSON).get();
        if (response.getStatus() != 200) {
            throw new RuntimeException("Nie możemy pobrać danych");
        }

        List<NewestWaterContainerData> result = response.readEntity(new GenericType<List<NewestWaterContainerData>>() {
        });
        logger.info("status połącznia : " + response.getStatus());
        logger.info("Pobrano łącznie " + result.size() + " rekordów");
        return result;
    }
}
