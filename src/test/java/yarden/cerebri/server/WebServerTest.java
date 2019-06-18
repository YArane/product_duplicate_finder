package yarden.cerebri.server;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClientBuilder;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;

public class WebServerTest {

    private WebServer server;

    private static final int PORT = 8090;
    private static final String URL = "http://localhost:" + PORT;

    @Before
    public void setup() throws Exception {
        server = new WebServer(8090);
        server.start();
    }

    @Test
    public void testPostEmptyBody() throws IOException {
        HttpClient client = HttpClientBuilder.create().build();
        HttpPost request = new HttpPost(URL);

        HttpResponse response = client.execute(request);

        Assert.assertEquals(400, response.getStatusLine().getStatusCode());
    }

    @Test
    public void testPostEmptyArray() throws IOException {
        HttpClient client = HttpClientBuilder.create().build();
        StringEntity requestEntity = new StringEntity(
                "[]",
                ContentType.APPLICATION_JSON);

        HttpPost postMethod = new HttpPost(URL);
        postMethod.setEntity(requestEntity);

        HttpResponse response = client.execute(postMethod);

        Assert.assertEquals(400, response.getStatusLine().getStatusCode());
    }

    @Test
    public void testPostNoArray() throws IOException {
        HttpClient client = HttpClientBuilder.create().build();
        StringEntity requestEntity = new StringEntity(
                "{\"productId\": 1, \"skuId\": \"a10\"}",
                ContentType.APPLICATION_JSON);

        HttpPost postMethod = new HttpPost(URL);
        postMethod.setEntity(requestEntity);

        HttpResponse response = client.execute(postMethod);

        Assert.assertEquals(400, response.getStatusLine().getStatusCode());
    }

    @Test
    public void testMalformedRequest() throws IOException {
        HttpClient client = HttpClientBuilder.create().build();
        StringEntity requestEntity = new StringEntity(
                "[\n" +
                        "\"productId\": 1, \"skuId\": \"a10\",\n" +
                        "]",
                ContentType.APPLICATION_JSON);

        HttpPost postMethod = new HttpPost(URL);
        postMethod.setEntity(requestEntity);

        HttpResponse response = client.execute(postMethod);

        Assert.assertEquals(400, response.getStatusLine().getStatusCode());
    }

    @Test
    public void testNonmappableRequest() throws IOException {
        HttpClient client = HttpClientBuilder.create().build();
        StringEntity requestEntity = new StringEntity(
                "[\n" +
                        "{\"roductId\": 1, \"kuId\": \"a10\"},\n" +
                        "]",
                ContentType.APPLICATION_JSON);

        HttpPost postMethod = new HttpPost(URL);
        postMethod.setEntity(requestEntity);

        HttpResponse response = client.execute(postMethod);

        Assert.assertEquals(400, response.getStatusLine().getStatusCode());
    }

    @Test
    public void testGet() throws IOException {
        HttpClient client = HttpClientBuilder.create().build();
        HttpGet request = new HttpGet(URL);

        HttpResponse response = client.execute(request);

        Assert.assertEquals(200, response.getStatusLine().getStatusCode());
    }

    @After
    public void tearDown() throws Exception {
        server.stop();
    }

}
