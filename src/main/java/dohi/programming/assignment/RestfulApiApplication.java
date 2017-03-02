package dohi.programming.assignment;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Spring boot application
 * Implements RESTful service back-end
 *
 * Supports 4 API calls
 *
 * 1. Bundle creation done by call CreateBundleController
 * (Method: POST Url: https://{host}/rest/v1/create
 * body: Bundle Bundle in JSON format)
 * which responds Bundle id and creation message
 *
 * 2. Bundle deletion done by call DeleteBundleController
 * (Method: DELETE Url: https://{host}/rest/v1/delete?id={bundleId})
 * which which responds Bundle id and deletion message
 *
 * 3. Bundle update done by call UpdateBundleController
 * (Method: PUT Url: https://{host}/rest/v1/update
 * body: Bundle in JSON format)
 * which responds Bundle id and update message
 *
 * 4. Bundle retrieve done by call RetrieveBundleController
 * (Method: GET Url: https://{host}/rest/v1/retrieve?id={bundleId})
 * which responds Bundle in JSON format
 *
 */
@SpringBootApplication
public class RestfulApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(RestfulApiApplication.class, args);
	}
}
