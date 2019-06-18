package yarden.cerebri.server;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import yarden.cerebri.model.DuplicateFinder;
import yarden.cerebri.model.Product;

/**
 * An HTTP servlet responsible for serving a POST request
 */
public class PostServlet extends HttpServlet {

    private ObjectMapper mapper;

    private final static String CONTENT_TYPE_JSON = "application/json";
    private final static String ERROR_400_RESPONSE = "Bad Request.";
    private static final Logger LOGGER = Logger.getLogger(PostServlet.class.getName());

    public PostServlet() {
        mapper = new ObjectMapper().configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, true);
    }

    /**
     * Retrieves the input request and attempts to parse into an array of {@link Product}s.
     * Sends a 400 response if unable to parse request into products, otherwise a 200 response
     * with the duplicates found in the body of the message is returned.
     *
     * @param request  HTTP request
     * @param response HTTP response
     * @throws ServletException
     * @throws IOException
     */
    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Product[] products = null;
        try {
            products = parseRequest(request.getInputStream());
        } catch (IOException e) {
            LOGGER.log(Level.SEVERE, e.getMessage());
        }
        if (products == null || products.length == 0) {
            send400Response(response);
        } else {
            List<Product> duplicates = DuplicateFinder.findDuplicates(products);
            send200Response(response, duplicates);
        }
    }

    private Product[] parseRequest(InputStream request) throws IOException {
        return mapper.readValue(request, Product[].class);
    }

    private void send400Response(HttpServletResponse response) throws IOException {
        response.sendError(400, ERROR_400_RESPONSE);
    }

    private void send200Response(HttpServletResponse response, List<Product> duplicates) throws IOException {
        response.setContentType(CONTENT_TYPE_JSON);
        response.setStatus(HttpServletResponse.SC_OK);
        mapper.writeValue(response.getWriter(), duplicates);
    }
}
