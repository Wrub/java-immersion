import java.io.File;
import java.io.InputStream;
import javax.imageio.ImageIO;
import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics2D;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;


public class stickerGenerator {
    
    public void create(InputStream inputStream , String fileName) throws Exception {
        // read image
        // InputStream inputStream = new FileInputStream("entry/movie1.jpg");
        // InputStream inputStream = new URL("https://raw.githubusercontent.com/alura-cursos/imersao-java-2-api/main/TopMovies_1.jpg").openStream();
        BufferedImage originalImage = ImageIO.read(inputStream);

        // create new image in cache
        int width = originalImage.getWidth();
        int height = originalImage.getHeight();
        int newHeight = height + 150;
        BufferedImage newImage = new BufferedImage(width, newHeight, BufferedImage.TRANSLUCENT);
         
        // copy original image to the new image (in cache)
        Graphics2D graphics = (Graphics2D) newImage.getGraphics();
        graphics.drawImage(originalImage, 0, 0, null);

        // font config
        Font font = new Font(Font.SANS_SERIF, Font.BOLD, 64);
        graphics.setColor(Color.CYAN);
        graphics.setFont(font);

        // write a phrase in the new image
        String text = "'O milhor'";
        FontMetrics fontMetrics = graphics.getFontMetrics();
        Rectangle2D rectangle = fontMetrics.getStringBounds(text, graphics);
        int textWidth = (int) rectangle.getWidth();
        int textPositionX = (width - textWidth) / 2;
        graphics.drawString(text, textPositionX , newHeight - 50);
        

        // put the new image in a file
        ImageIO.write(newImage, "png", new File(fileName));
    }

}
