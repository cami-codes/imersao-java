package org.alurastickers;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;

public class Main {
    public static void main(String[] args) throws IOException, InterruptedException {

        // 1. Get API key from config file
        String key = ResourceBundle.getBundle("config").getString("api.key");

        // 2. Build URL and send HTTP request to get top 250 movies from IMDB API
        String urlTop250Movies = "https://imdb-api.com/en/API/Top250Movies/" + key;
        URI addressMovies = URI.create(urlTop250Movies);

        var client = HttpClient.newHttpClient();
        var requestMovies = HttpRequest.newBuilder(addressMovies).GET().build();
        HttpResponse<String> response = client.send(requestMovies, HttpResponse.BodyHandlers.ofString());
        String body = response.body();

        // 3. Parse JSON response to extract relevant data (title, poster, and rating)
        var parser = new JsonParser();
        List<Map<String, String>> moviesList = parser.parse(body);

        // 4. Display and manipulate the data
        for (Map<String, String> movie : moviesList) {
            String title = movie.get("title");
            String image = movie.get("image");
            String ratingString = movie.get("imDbRating");

            // Add movie and rating emojis to title string
            title += " 🎬";
            ratingString += " ⭐";

            // Parse rating string to a double value and calculate number of stars
            double ratingParse = Double.parseDouble(ratingString.replaceAll("[^0-9.]", ""));
            int starCount = 10;

            // Build star string based on number of stars and rating
            String stars = "";
            for (int i = 1; i <= starCount; i++) {
                if (i <= ratingParse) {
                    stars += "★";
                } else {
                    stars += "☆";
                }
            }

            // Build rating string with star string and original rating string
            ratingString = "Classificação: " + stars + " (" + ratingString + ")";

            // Print movie title, poster image, and rating
            System.out.println("\033[1m" + title + "\033[0m");
            System.out.println(image);
            System.out.println("\033[33m" + ratingString + "\033[0m");
            System.out.println();
        }
    }
}