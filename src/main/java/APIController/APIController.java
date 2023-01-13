package APIController;

import DTO.JobInformation;
import ScraperController.Scraper;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


public class APIController {

    public APIController(){

    }
    public static String BASE_URL = "https://api.peviitor.ro/v1/";
    public static String COMPANIES_URL ="companies/";

    public List<JobInformation> jobs = new ArrayList<>();

    public void testConnection(){

        RestAssured.baseURI = BASE_URL;
        RequestSpecification httpRequest = RestAssured.given().param("count","true");
        Response response = httpRequest.get(COMPANIES_URL);

        JsonPath jsonPathEvaluator = response.jsonPath();

        System.out.println(jsonPathEvaluator.get("companies").toString());


    }

    public void postJobs(String ness) throws Exception {
        startFatchingDataFrom("romania","ness");
        postCleanCompanyJobs("ness");
        RestAssured.baseURI = BASE_URL;
        RequestSpecification httpRequest = RestAssured.given().body(jobs);
        Response response = httpRequest.get("update/");

        System.out.println(response.statusCode());
        System.out.println(response.getBody().asString());
    }

    private void startFatchingDataFrom(String country, String company) throws Exception {
        Scraper scraper = new Scraper(country,company);
        scraper.startScrape();
        this.jobs = scraper.getJobsDTO();
    }

    public void postCleanCompanyJobs(String company) {
        RestAssured.baseURI = BASE_URL;
        HashMap<String ,String> data = new HashMap<>();
        data.put("company","ness");

        ObjectMapper objectMapper = new ObjectMapper();
        Response httpRequest = RestAssured.given()
                .contentType("application/x-www-form-urlencoded;charset=UTF-8")
                .accept("*/*")
                .formParam("company","ness").post("clean/");
        System.out.println(httpRequest.headers());
        System.out.println(httpRequest.getBody().asString());
        System.out.println();
        System.out.println();
        //Response response = httpRequest.get("clean/");


    }


    public void createJobTestInformation(){
        JobInformation job = new JobInformation();
        job
                .setJob_link("https./qweq")
                .setJob_title("etwetrew")
                .setCompany("tyer")
                .setCountry("2f3242")
                .setRemote("remote")
                .setCity("Cluj");

        jobs.add(job);
    }
}
