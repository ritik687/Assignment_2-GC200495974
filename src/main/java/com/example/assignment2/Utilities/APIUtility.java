package com.example.assignment2.Utilities;

import com.example.assignment2.Models.APIResponse;
import com.example.assignment2.Models.Media;
import com.example.assignment2.Models.UserProfileDetails;
import com.google.gson.Gson;
import com.google.gson.stream.JsonReader;

import javax.net.ssl.HttpsURLConnection;
import java.io.FileReader;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URI;
import java.net.URL;
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

    public static APIResponse getUserDetailsFromUserName(String userName) throws IOException, InterruptedException {

//        searchTerm =searchTerm.replaceAll(" ","%20");

        String uri= "https://instagram-profile1.p.rapidapi.com/getProfile/"+userName;
        HttpClient client=HttpClient.newHttpClient();
        HttpRequest httpRequest = HttpRequest.newBuilder()
                .uri(URI.create("https://instagram-profile1.p.rapidapi.com/getprofile/therock"))
                .header("X-RapidAPI-Key", "83b0d80e46msh31b77b24d70e08dp1f8d06jsn39771ea54da7")
                .header("X-RapidAPI-Host", "instagram-profile1.p.rapidapi.com")
                .method("GET", HttpRequest.BodyPublishers.noBody())
                .build();

         /*    Commenting this so as to avoid the overwriting the same file.,.*/
            HttpResponse<Path> response = client.send(httpRequest,HttpResponse
                    .BodyHandlers
                    .ofFile(Paths.get("userProfileDetails.json")));


        HttpResponse<String> httpResponse = HttpClient.newHttpClient().send(httpRequest, HttpResponse.BodyHandlers.ofString());
        System.out.println(httpResponse.body());

        Gson gson=new Gson();
        APIResponse apiResponse=gson.fromJson(httpResponse.body(),APIResponse.class);

        return apiResponse;

    }

    public static APIResponse getDataFromFile() throws IOException, InterruptedException {

            Gson gson =new Gson();
            APIResponse apiResponse =null;

        try (
                // filereader knows how to access my file system that knows how to give me back the file While Jsonreader know how to parse that file for json data
                FileReader fileReader = new FileReader("JSON-Files/users.json");
                JsonReader jsonReader = new JsonReader(fileReader);
        ) {
            // here we are converting the json into the apiresponse object.
            apiResponse = gson.fromJson(jsonReader, APIResponse.class);



        } catch (Exception e) {
            e.printStackTrace();
        }
        return apiResponse;


    }

    // this is the testing method for the detil view
    public static UserProfileDetails getUserProfileDetailsFromFile() throws IOException, InterruptedException {

        Gson gson =new Gson();
        UserProfileDetails userProfileDetails =null;

        try (
                // filereader knows how to access my file system that knows how to give me back the file While Jsonreader know how to parse that file for json data
                FileReader fileReader = new FileReader("JSON-Files/userProfileDetails.json");
                JsonReader jsonReader = new JsonReader(fileReader);
        ) {
            // here we are converting the json into the apiresponse object.
            userProfileDetails = gson.fromJson(jsonReader, UserProfileDetails.class);



        } catch (Exception e) {
            e.printStackTrace();
        }
        return userProfileDetails;


    }


    // this is the testing method for the media
    public static Media getMediaDetailsFromFile() throws IOException, InterruptedException {

        Gson gson =new Gson();
        Media medias =null;

        try (
                // filereader knows how to access my file system that knows how to give me back the file While Jsonreader know how to parse that file for json data
                FileReader fileReader = new FileReader("JSON-Files/userProfileDetails.json");
                JsonReader jsonReader = new JsonReader(fileReader);
        ) {
            // here we are converting the json into the apiresponse object.
            medias = gson.fromJson(jsonReader, UserProfileDetails.class);



        } catch (Exception e) {
            e.printStackTrace();
        }
        return medias;


    }



    // this method for the image that showing null for the image pattern that is used to fill the circle object
    public  static String sendGETRequest(String requestURL) {
        URL url;
        String response = "null";
        try {
            url = new URL(requestURL);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
//            conn.setReadTimeout(300);
//            conn.setConnectTimeout(10);
//            conn.setRequestMethod("GET");
//            conn.setDoInput(true);
//            conn.setDoOutput(true);

            int responseCode=conn.getResponseCode();
           /* if (responseCode == HttpsURLConnection.HTTP_OK) {
                BufferedReader br=new BufferedReader(new InputStreamReader(conn.getInputStream()));
                response = br.readLine();
            }*/
           if(responseCode !=HttpsURLConnection.HTTP_OK){
                response="Error"; // Here error is erroe while connecting or Content not found;
            }
        } catch (IOException e) {

        }
//        System.out.println(response);
        return response;
    }


}