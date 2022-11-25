package com.example.assignment2;

import com.google.gson.Gson;
import com.google.gson.stream.JsonReader;

import java.io.FileReader;
import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.file.Path;
import java.nio.file.Paths;

public class APIUtility {



        public static APIResponse getUsersFromSearchTerm(String searchTerm) throws IOException, InterruptedException {

            searchTerm =searchTerm.replaceAll(" ","%20");

            String uri= "https://instagram-profile1.p.rapidapi.com/searchuser/"+searchTerm;
            HttpClient client=HttpClient.newHttpClient();
            HttpRequest httpRequest = HttpRequest.newBuilder()
                    .uri(URI.create(uri))
                    .header("X-RapidAPI-Key", "5a6139cb9dmsh3555dd78ae2540fp188b1ejsn3f275c01fe24")
                    .header("X-RapidAPI-Host", "instagram-profile1.p.rapidapi.com")
                    .method("GET", HttpRequest.BodyPublishers.noBody())
                    .build();

            /* Commenting this so as to avoid the overwriting the same file.,.
            HttpResponse<Path> response = client.send(httpRequest,HttpResponse
                    .BodyHandlers
                    .ofFile(Paths.get("users.json")));*/


            HttpResponse<String> httpResponse = HttpClient.newHttpClient().send(httpRequest, HttpResponse.BodyHandlers.ofString());
            System.out.println(httpResponse.body());

            Gson gson=new Gson();
            APIResponse apiResponse=gson.fromJson(httpResponse.body(),APIResponse.class);

            return apiResponse;

        }

    public static APIResponse getUsersFromFile() throws IOException, InterruptedException {

            Gson gson =new Gson();
            APIResponse apiResponse =null;

        try (
                // filereader knows how to access my file system that knows how to give me back the file While Jsonreader know how to parse that file for json data
                FileReader fileReader = new FileReader("users.json");
                JsonReader jsonReader = new JsonReader(fileReader);
        ) {
            // here we are converting the json into the apiresponse object.
            apiResponse = gson.fromJson(jsonReader, APIResponse.class);



        } catch (Exception e) {
            e.printStackTrace();
        }
        return apiResponse;


    }


}
