package com.example.isthesiteup.controllers;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UrlCheckController {
    private final String SITE_UP = "Site is up!";
    private final String SITE_DOWN = "Site is down!";
    private final String INVALID_URL = "URL is wrong!";

    @GetMapping("/check")
    public String getUrlStatus(@RequestParam String url) {
        String returnMessage = "";
        try {
            URL urlObj = new URL(url);
            HttpURLConnection conn = (HttpURLConnection) urlObj.openConnection();
            conn.setRequestMethod("GET");
            conn.connect();
            int resCat = conn.getResponseCode() / 100;
            if(resCat != 2 && resCat != 3) {
                returnMessage = SITE_DOWN;
            } else {
                returnMessage = SITE_UP;
            }
        } catch (MalformedURLException e) {
            returnMessage = INVALID_URL;
        } catch (IOException e) {
            returnMessage = SITE_DOWN;
        }
        return returnMessage;
    }
}
