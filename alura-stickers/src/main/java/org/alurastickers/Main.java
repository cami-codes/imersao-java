package org.alurastickers;

import java.io.InputStream;
import java.net.URL;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;

public class Main {
    public static void main(String[] args) throws Exception {

        // Build URL and send HTTP request to get top 250 movies from IMDB API
        // String url = "https://raw.githubusercontent.com/alura-cursos/imersao-java-2-api/main/NASA-APOD.json";
        // NasaContentExtractor extractor = new NasaContentExtractor();

        String url = "https://raw.githubusercontent.com/alura-cursos/imersao-java-2-api/main/TopMovies.json";
        ContentExtractor extractor = new ImDbContentExtractor();

        var http = new HttpClient();
        String json = http.findData(url);

        // 4. Display and manipulate the data

        List<Content> contents = extractor.contentExtractor(json);

        var stickerGenerator = new StickerGenerator();

        for (int i = 0; i < 3; i++) {

            Content content = contents.get(i);

            InputStream inputStream = new URL(content.getUrlImage()).openStream();
            String fileName = content.getTitle();
            // Print content title, poster image, and rating

            stickerGenerator.createSticker(inputStream, fileName);

            System.out.println("\033[1m" + content.getTitle() + "\033[0m");
            // System.out.println(content.getUrlImage());
        }
    }
}