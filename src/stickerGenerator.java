import java.io.File;
import javax.imageio.ImageIO;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;


public class stickerGenerator {
    
    public void create() throws Exception {
        // read image
        BufferedImage originalImage = ImageIO.read(new File("entry/movie1.jpg"));

        // create new image in cache
        int width = originalImage.getWidth();
        int height = originalImage.getHeight();
        int newHeight = height + 150;
        BufferedImage newImage = new BufferedImage(width, newHeight, BufferedImage.TRANSLUCENT);
         
        // copy original image to the new image (in cache)
        Graphics2D graphics = (Graphics2D) newImage.getGraphics();
        graphics.drawImage(originalImage, 0, 0, null);

        // write a phrase in the new image


        // put the new image in a file
        ImageIO.write(newImage, "png", new File("output/sticker.png"));
    }

    public static void main(String[] args) throws Exception {
        var generator = new stickerGenerator();
        generator.create();
    }

    // Aula assistida até os 32 min

}
