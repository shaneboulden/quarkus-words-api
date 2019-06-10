package org.rock.rest;

import java.util.concurrent.atomic.AtomicReference;
import javax.enterprise.context.ApplicationScoped;
import io.quarkus.scheduler.Scheduled;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.HttpURLConnection;
import java.net.URL;


@ApplicationScoped
public class CachedResponse {

  private AtomicReference<StringBuffer> cache_data  = new AtomicReference<StringBuffer>();

  public CachedResponse() {
    StringBuffer bfr = new StringBuffer("");
    cache_data.set(bfr);
  }

  public StringBuffer get() {
    return cache_data.get();
  }

  @Scheduled(every="10s")
  public void get_data() {

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
            cache_data.set(content);
      }
      catch(Exception ex) {
          System.out.println(ex);
      }
   }
}
