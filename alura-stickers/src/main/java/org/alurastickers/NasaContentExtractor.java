package org.alurastickers;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class NasaContentExtractor implements ContentExtractor {
    public List<Content> contentExtractor(String json) {

        // Parse JSON response to extract relevant data (title, poster, and rating)
        var parser = new JsonParser();
        List<Map<String, String>> attributeList = parser.parse(json);

        List<Content> contents = new ArrayList<>();

        for (Map<String, String> attributes : attributeList) {
            String title = attributes.get("title");
            String urlImage = attributes.get("url");

            var content = new Content(title, urlImage);

            contents.add(content);
        }

        return contents;
    }
}
