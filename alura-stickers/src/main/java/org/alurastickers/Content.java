package org.alurastickers;

import lombok.Getter;

@Getter
public class Content {
    private final String title;
    private final String urlImage;

    public Content(String title, String urlImage) {
        this.title = title;
        this.urlImage = urlImage;
    }
}
