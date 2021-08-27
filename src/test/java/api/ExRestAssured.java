package api;/*

package API;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.restassured.config.LogConfig;
import io.restassured.config.RestAssuredConfig;
import io.restassured.specification.RequestSpecification;
import org.apache.commons.io.IOUtils;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.hamcrest.Matchers;
import org.json.JSONObject;
import org.junit.Test;

import java.io.IOException;
import java.net.URISyntaxException;
import java.net.http.HttpRequest;
import java.util.List;
import java.util.Map;

import static io.restassured.RestAssured.given;


*//*

*/
/*import org.testng.annotations.Test;*//*



import io.restassured.config.LogConfig;
import io.restassured.config.RestAssuredConfig;
import io.restassured.specification.RequestSpecification;
import org.hamcrest.Matchers;
import org.testng.annotations.Test;

import java.util.Map;

import static io.restassured.RestAssured.given;

public class TestRestAssured {
    @Test
    public static void requestAssuredTest () {
        RestAssuredConfig config = RestAssuredConfig.newConfig().logConfig(new LogConfig().defaultStream(System.out));
        var responseBody = prepareRequest(config)
                .when().get("http://ergast.com/api/f1/2017")
                .then().statusCode(201).header("bla", "bla").body("", Matchers.equalTo(""));//встроенные ассершн
    }

    private RequestSpecification prepareRequest (RestAssuredConfig config) {
            return given().headers(Map.of("dvcdsv", "65351351"))
                    .sessionId("gf").config(config).body("{\n" +
                    "                \"name\": \"Morpheus\", \n" +
                    "                \"job\": \"leader\" \n" +
                    "                \"}");//можно даже джейсонОбжект
        }

}


*/
