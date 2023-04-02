package org.alurastickers;

import java.util.List;

public interface ContentExtractor {
    List<Content> contentExtractor(String json);
}
