import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.http.HttpEntity;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;

import java.io.IOException;
import java.util.List;

public class HttpRequest {

    final static String URL = "https://raw.githubusercontent.com/" +
            "netology-code/jd-homeworks/master/http/task1/cats";

    public static ObjectMapper mapper = new ObjectMapper();

    public static void main(String[] args) {

        CloseableHttpClient httpClient = HttpClientBuilder.create()
                .setDefaultRequestConfig(RequestConfig.custom()
                        .setConnectTimeout(5000)    // максимальное время ожидание подключения к серверу
                        .setSocketTimeout(30000)    // максимальное время ожидания получения данных
                        .setRedirectsEnabled(false) // возможность следовать редиректу в ответе
                        .build())
                .build();

        HttpGet request = new HttpGet(URL);

        try (CloseableHttpResponse response = httpClient.execute(request)){
            List<CatFact> cats = mapper.readValue(response.getEntity().getContent(), new TypeReference<List<CatFact>>() {});
            cats.stream().filter(value -> (value.getUpvotes() != null) && (value.getUpvotes() > 0))
                    .forEach(System.out::println);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }



}
