package org.alurastickers;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.InputStream;
import java.net.URL;

public class StickerGenerator {

    public void createSticker(InputStream inputStream, String nomeArquivo) throws Exception {

        // image reading
        // InputStream inputStream = new FileInputStream(new File("input/movie.jpg"));
        // InputStream inputStream = new URL("https://m.media-amazon.com/images/M/MV5BNDE3ODcxYzMtY2YzZC00NmNlLWJiNDMtZDViZWM2MzIxZDYwXkEyXkFqcGdeQXVyNjAwNDUxODI@.jpg")
        // .openStream();
        BufferedImage originalImage = ImageIO.read(inputStream);

        // creates a new in-memory image with transparency and custom size
        int width = originalImage.getWidth();
        int height = originalImage.getHeight();
        int newHeight = height + 200;
        BufferedImage newImage = new BufferedImage(width, newHeight, BufferedImage.TRANSLUCENT);

        // copy the original image to the new image (in memory)
        Graphics2D graphics = (Graphics2D) newImage.getGraphics();
        graphics.drawImage(originalImage, 0, 0, null);

        // set font style
        var font = new Font(Font.MONOSPACED, Font.BOLD, 100);
        graphics.setColor(Color.YELLOW);
        graphics.setFont(font);

        // write some text on the new image
        // calculate x coordinate to center the text horizontally
        FontMetrics fontMetrics = graphics.getFontMetrics();
        int textWidth = fontMetrics.stringWidth("TOPZERA");
        int textX = (newImage.getWidth() - textWidth) / 2;

        // write some text on the new image
        graphics.drawString("TOPZERA", textX, newHeight - 100);

        // write the new image to a file
        File outputImage = new File("output\\" + nomeArquivo + ".png");

        // checks if the parent directory of the `outputImage` file exists.
        // if it doesn't exist, the code creates the directory using the `mkdirs()` method.
        File parentDir = outputImage.getParentFile();
        if (parentDir != null && !parentDir.exists()) {
            if (!parentDir.mkdirs()) {
                throw new Exception("Erro ao criar diretório.");
            }
        }

        ImageIO.write(newImage, "png", outputImage);
    }


}
