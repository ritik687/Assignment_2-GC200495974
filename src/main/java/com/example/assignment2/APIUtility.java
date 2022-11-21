package com.example.assignment2;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.file.Path;
import java.nio.file.Paths;

public class APIUtility {

        public static void getUsersFromURL(String searchTerm) throws IOException, InterruptedException {

            searchTerm =searchTerm.replaceAll(" ","%20");

            String url ="https://instagram-profile1.p.rapidapi.com/searchuser/"+searchTerm;
            HttpClient client=HttpClient.newHttpClient();
            HttpRequest httpRequest = HttpRequest.newBuilder()
                    .uri(URI.create(url))
                    .header("X-RapidAPI-Key", "62d27943fdmsha079c7bb702a2d3p1a7fa2jsn8b144eb6062f")
                    .header("X-RapidAPI-Host", "instagram-profile1.p.rapidapi.com")
                    .method("GET", HttpRequest.BodyPublishers.noBody())
                    .build();

            HttpResponse<Path> response = client.send(httpRequest,HttpResponse
                    .BodyHandlers
                    .ofFile(Paths.get("users.json")));


            HttpResponse<String> httpResponse = HttpClient.newHttpClient().send(httpRequest, HttpResponse.BodyHandlers.ofString());
            System.out.println(httpResponse.body());
        }
}
