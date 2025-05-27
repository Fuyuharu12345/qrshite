import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

/**
 * A simple QR code generator that creates a visual representation of a QR-like code
 * based on the hash of an input string.
 * 
 * <p><strong>Note:</strong> This is not a standard QR code implementation,
 * but rather a stylized visual representation for demonstration purposes.</p>
 */
public class QRCodeGenerator {
    private static final int QR_SIZE = 200;
    private static final int BORDER = 10;

    /**
     * Constructs a new QRCodeGenerator instance.
     */
    public QRCodeGenerator() {
    }

    /**
     * Generates a stylized QR-like code image based on the hash of the input string.
     * The generated image is a black and white pattern that visually represents the input data.
     * 
     * @param input The string data to encode visually into a QR-style pattern.
     * @return A BufferedImage containing the generated QR code pattern.
     */
    public BufferedImage generateQRCode(String input) {
        BufferedImage image = new BufferedImage(QR_SIZE, QR_SIZE, BufferedImage.TYPE_INT_RGB);
        Graphics2D graphics = image.createGraphics();

        // Fill background with white
        graphics.setColor(Color.WHITE);
        graphics.fillRect(0, 0, QR_SIZE, QR_SIZE);

        // Draw black border
        graphics.setColor(Color.BLACK);
        graphics.fillRect(0, 0, QR_SIZE, BORDER);
        graphics.fillRect(0, 0, BORDER, QR_SIZE);
        graphics.fillRect(QR_SIZE - BORDER, 0, BORDER, QR_SIZE);
        graphics.fillRect(0, QR_SIZE - BORDER, QR_SIZE, BORDER);

        int hash = Math.abs(input.hashCode());
        final int cellSize = 8;

        // Generate pattern based on hash and position
        for (int x = BORDER; x < QR_SIZE - BORDER; x += cellSize) {
            for (int y = BORDER; y < QR_SIZE - BORDER; y += cellSize) {
                if ((hash + x + y) % 3 == 0) {
                    graphics.fillRect(x, y, cellSize - 1, cellSize - 1);
                }
            }
        }

        // Draw three corner markers as in typical QR codes
        this.drawCornerMarker(graphics, 10, 10);
        this.drawCornerMarker(graphics, 160, 10);
        this.drawCornerMarker(graphics, 10, 160);

        graphics.dispose();
        return image;
    }

    /**
     * Draws a corner marker for the QR code consisting of three nested squares.
     * These markers mimic the positioning squares found in actual QR codes.
     * 
     * @param g The Graphics2D context to draw on.
     * @param x The x-coordinate of the top-left corner of the marker.
     * @param y The y-coordinate of the top-left corner of the marker.
     */
    private void drawCornerMarker(Graphics2D g, int x, int y) {
        final int markerSize = 30;
        g.fillRect(x, y, markerSize, markerSize);
        g.setColor(Color.WHITE);
        g.fillRect(x + 5, y + 5, markerSize - 10, markerSize - 10);
        g.setColor(Color.BLACK);
        g.fillRect(x + 10, y + 10, markerSize - 20, markerSize - 20);
    }

    /**
     * Saves the generated QR code image to the specified file path in PNG format.
     * Creates the necessary directories if they do not exist.
     * 
     * @param image    The BufferedImage representing the QR code to save.
     * @param filePath The full file path including filename and extension.
     * @return true if the image was saved successfully; false otherwise.
     */
    public boolean saveQRCode(BufferedImage image, String filePath) {
        try {
            File outputFile = new File(filePath);
            outputFile.getParentFile().mkdirs(); // Ensure parent directories exist
            return ImageIO.write(image, "PNG", outputFile);
        } catch (IOException e) {
            System.err.println("Error saving QR code: " + e.getMessage());
            return false;
        }
    }
}
