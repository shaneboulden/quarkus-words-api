package org.rock.rest;

import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Set;
import java.util.Random;

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

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.HttpURLConnection;
import java.net.URL;

import java.util.Arrays;

@Path("/api/v1/words")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class DataResource {

    public DataResource() {

    }

    @GET
    public DataPoint[] list() {
        DataPoint[] data = new DataPoint[10];

        // make a request to the API
        // TODO change this to an environment variable
        try {
            URL url = new URL("http://grafzahl:8080/data");
            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            con.setRequestMethod("GET");
            con.setRequestProperty("Content-Type", "application/json");
            con.setConnectTimeout(10000);
            con.setReadTimeout(10000);

            BufferedReader in = new BufferedReader(
            new InputStreamReader(con.getInputStream()));
            String inputLine;
            StringBuffer content = new StringBuffer();
            while ((inputLine = in.readLine()) != null) {
              content.append(inputLine);
            }
            in.close();

            // now let's parse the JSON to data points
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
