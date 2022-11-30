package com.example.assignment2.Utilities;

import com.example.assignment2.Models.*;
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
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.DecimalFormat;

public class APIUtility {



        public static void getUsersFromSearchTerm(String searchTerm) throws IOException, InterruptedException {

            searchTerm =searchTerm.replaceAll(" ","%20");

            String uri= "https://instagram-profile1.p.rapidapi.com/searchuser/"+searchTerm;
            HttpClient client=HttpClient.newHttpClient();
            HttpRequest httpRequest = HttpRequest.newBuilder()
                    .uri(URI.create(uri))
                    .header("X-RapidAPI-Key", "83b0d80e46msh31b77b24d70e08dp1f8d06jsn39771ea54da7")
                    .header("X-RapidAPI-Host", "instagram-profile1.p.rapidapi.com")
                    .method("GET", HttpRequest.BodyPublishers.noBody())
                    .build();

            // this two lines of code is for when file is overwrited then sometimes json file gets error so thats why here i am first deleting the file.
            Path path=Path.of("users.json");
            Files.delete(path);

            //this takes whatever is returned and saves it to a file called "users.json"
            HttpResponse<Path> response = client.send(httpRequest,HttpResponse
                                                .BodyHandlers
                                                .ofFile(Paths.get("users.json")));
        }


    public static void getUserProfileDetailsFromUserName(String userName) throws IOException, InterruptedException {

        String uri= "https://instagram-profile1.p.rapidapi.com/getprofile/"+userName;
        HttpClient client=HttpClient.newHttpClient();
        HttpRequest httpRequest = HttpRequest.newBuilder()
                .uri(URI.create(uri))
                .header("X-RapidAPI-Key", "83b0d80e46msh31b77b24d70e08dp1f8d06jsn39771ea54da7")
                .header("X-RapidAPI-Host", "instagram-profile1.p.rapidapi.com")
                .method("GET", HttpRequest.BodyPublishers.noBody())
                .build();


        Path path=Path.of("userProfileDetails.json");
        Files.delete(path);

        //this takes whatever is returned and saves it to a file called "userProfileDetails.json"
        HttpResponse<Path> response = client.send(httpRequest,HttpResponse
                .BodyHandlers
                .ofFile(Paths.get("userProfileDetails.json")));

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

    public static UserProfileDetails getUserProfileDetailsFromFile() throws IOException, InterruptedException {

        Gson gson =new Gson();
        UserProfileDetails userProfileDetails =null;

        try (
                // filereader knows how to access my file system that knows how to give me back the file While Jsonreader know how to parse that file for json data
                FileReader fileReader = new FileReader("userProfileDetails.json");
                JsonReader jsonReader = new JsonReader(fileReader);
        ) {
            // here we are converting the json into the userProfileDetails object.
            userProfileDetails = gson.fromJson(jsonReader, UserProfileDetails.class);



        } catch (Exception e) {
            e.printStackTrace();
        }
        return userProfileDetails;


    }


    // this method for the image that showing null for the image pattern that is used to fill the circle object
    // this will check for the image nullable value.
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
                response="Error"; // Here error is error while connecting or Content not found;
            }
        } catch (IOException e) {

        }
//        System.out.println(response);
        return response;
    }



    private static String[] suffixCharacter = new String[]{"","K", "M", "B", "T"};
    private static int maxLength = 4;

    // this method is used to format the long number to short form
    public static String formatNumber(double number) {
        String value = new DecimalFormat("##0E0").format(number);
        value = value.replaceAll("E[0-9]", suffixCharacter[Character.getNumericValue(value.charAt(value.length() - 1)) / 3]);
        while(value.length() > maxLength || value.matches("[0-9]+\\.[a-z]")){
            value = value.substring(0, value.length()-2) + value.substring(value.length() - 1);
        }
        return value;
    }

}
