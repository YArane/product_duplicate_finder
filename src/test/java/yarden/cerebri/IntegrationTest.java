package yarden.cerebri;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import yarden.cerebri.server.WebServer;

import java.io.IOException;

public class IntegrationTest {

    private WebServer server;

    private static final int PORT = 8090;
    private static final String URL = "http://localhost:" + PORT;

    @Before
    public void setup() throws Exception {
        server = new WebServer(8090);
        server.start();
    }

    @Test
    public void testAllUnique() throws IOException {
        HttpClient client = HttpClientBuilder.create().build();
        StringEntity requestEntity = new StringEntity(
                "[\n" +
                        "{\"productId\": 1, \"skuId\": \"a10\"},\n" +
                        "{\"productId\": 2, \"skuId\": \"a11\"},\n" +
                        "{\"productId\": 3, \"skuId\": \"a12\"}\n" +
                        "]",
                ContentType.APPLICATION_JSON);

        HttpPost postMethod = new HttpPost(URL);
        postMethod.setEntity(requestEntity);

        HttpResponse response = client.execute(postMethod);

        String expectedResponse = "[]";
        Assert.assertEquals(expectedResponse, EntityUtils.toString(response.getEntity()));
    }

    @Test
    public void testProvidedExample() throws IOException {
        HttpClient client = HttpClientBuilder.create().build();
        StringEntity requestEntity = new StringEntity(
                "[\n" +
                        "{\"productId\": 1, \"skuId\": \"a10\"},\n" +
                        "{\"productId\": 2, \"skuId\": \"a10\"},\n" +
                        "{\"productId\": 3, \"skuId\": \"a11\"},\n" +
                        "{\"productId\": 4, \"skuId\": \"a12\"}\n" +
                        "]",
                ContentType.APPLICATION_JSON);

        HttpPost postMethod = new HttpPost(URL);
        postMethod.setEntity(requestEntity);

        HttpResponse response = client.execute(postMethod);

        String expectedResponse = "[{\"productId\":1,\"skuId\":\"a10\"},{\"productId\":2,\"skuId\":\"a10\"}]";
        Assert.assertEquals(expectedResponse, EntityUtils.toString(response.getEntity()));
    }

    @After
    public void tearDown() throws Exception {
        server.stop();
    }
}
