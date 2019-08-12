package org.rock.rest;

import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Set;
import java.util.Random;

import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.JsonArray;

import com.google.gson.stream.JsonReader;

import java.util.Arrays;

@Path("/api/v1/words")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class DataResource {

    @Inject
    CachedResponse response;

    public DataResource() {}

    @GET
    public DataPoint[] list() {
        DataPoint[] data = new DataPoint[10];

        try {

            // parse the JSON returned from the GrafZahl API to data points
            StringBuffer content = response.get();
            JsonObject jsonObject = new JsonParser().parse(content.toString()).getAsJsonObject();
            JsonElement categories = jsonObject.get("categories");
            JsonElement words = jsonObject.get("data").getAsJsonArray().get(0);
            JsonArray counts = words.getAsJsonArray();
            int i=0;
            for(JsonElement category : categories.getAsJsonArray()) {
               data[i] = new DataPoint(category.toString(),counts.get(i+1).getAsInt(),counts.get(i+1).getAsInt());
               i++;
            }
        } catch (Exception ex) {
          System.out.println(ex);
        }

        return data;
    }
}
