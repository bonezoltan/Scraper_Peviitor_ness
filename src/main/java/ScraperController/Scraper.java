package ScraperController;

import DTO.JobInformation;
import lombok.Getter;
import org.apache.commons.lang3.StringUtils;
import org.jsoup.Jsoup;
import org.jsoup.internal.StringUtil;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Random;

public class Scraper {

    String country;
    String company;

    @Getter
    private List<JobInformation> jobsDTO = new ArrayList<>();

    public Scraper(String country, String company){
        this.country = country;
        this.company = company;
    }

    public void startScrape() throws Exception{

        try {
            Document doc = Jsoup.connect("https://ness-usa.ttcportals.com/search/jobs/in/country/"+this.country).get();
            Elements jobs = doc.selectXpath("//div[@class=\"jobs-section__item padded-v-small\"]");

            for(Element job: jobs){
                Random random = new Random();
                JobInformation jobDTO = new JobInformation();
                String jobName = job.select("h2").text();
                String jobLocation = job.select("div.large-4").text();
                String jobLink = job.select("a").attr("href");

                jobDTO.setJob_title(jobName);
                jobDTO.setJob_link(jobLink);
                jobDTO.setCompany(this.company);
                jobDTO.setCountry(StringUtils.capitalize(this.country));
                jobDTO.setCity(StringUtils.capitalize(jobLocation.substring(jobLocation.indexOf(" "),jobLocation.indexOf(","))));
                jobDTO.setRemote("N/A");
                jobDTO.setId(Long.toString(random.nextLong()));

                jobsDTO.add(jobDTO);
            }
        } catch (Exception exception) {
            exception.printStackTrace();
        }
    }

}
