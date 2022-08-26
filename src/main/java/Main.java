import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;

import java.io.FileOutputStream;
import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {
        CloseableHttpClient httpClient = HttpClientBuilder.create()
                .setDefaultRequestConfig(RequestConfig.custom()
                        .setConnectTimeout(5000)
                        .setSocketTimeout(30000)
                        .setRedirectsEnabled(false)
                        .build())
                .build();
        HttpGet request = new HttpGet("https://api.nasa.gov/planetary/apod?api_key=xae6u1TWetLENo2Nlw0iRg7Jnw60bujtN5IVuIaR");
        CloseableHttpResponse response = httpClient.execute(request);
        ObjectMapper mapper = new ObjectMapper();
        NasaResponse nasaResponse = mapper.readValue(
                response.getEntity().getContent(),
                new TypeReference<>() {});
        String[] file = nasaResponse.getHdurl().split("/");
        String fileName = file[file.length - 1];

        try (FileOutputStream img = new FileOutputStream(fileName)) {
            request = new HttpGet(nasaResponse.getHdurl());
            response = httpClient.execute(request);
            img.write(response.getEntity().getContent().readAllBytes());
            img.flush();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
