package org.example;

import APIController.APIController;
import ScraperController.Scraper;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class Main {

    public static void main(String[] args) throws Exception {
        APIController apiController = new APIController();

        //apiController.postCleanCompanyJobs("ness");
        apiController.postJobs("ness");
           // Scraper scraper = new Scraper("romania","ness");
       // scraper.startScrape();
    }

}