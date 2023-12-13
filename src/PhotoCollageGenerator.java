import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.SwingUtilities;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.io.InputStream;

public class PhotoCollageGenerator {

    private static final String API_KEY = "UCmypRke7nyzUhXquX23wdkv5TuePe2bhg5m6oWPTozjkYFtAwtidvfM";
    private static final String API_URL = "https://api.pexels.com/v1/curated?page=2&per_page=1";
    private static final int NUM_PHOTOS = 5;

    public static void main(String[] args) {
        try {
            List<String> photoUrls = getPhotoUrls();
           List<BufferedImage> images = downloadImages(photoUrls);
           System.out.println(images);
           BufferedImage collage = createCollage(images);
           displayCollage(collage);
            //saveCollage(collage, "collage.jpg");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static List<String> getPhotoUrls() throws IOException, InterruptedException, Exception {
        URL url = new URL(API_URL);
    HttpURLConnection connection = (HttpURLConnection) url.openConnection();

    connection.setRequestMethod("GET");
     connection.setRequestProperty("User-Agent",
     "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/58.0.3029.110 Safari/537.36");
    connection.setRequestProperty("Authorization", API_KEY);

    int responseCode = connection.getResponseCode();
    if (responseCode == 200) {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()))) {
            StringBuilder response = new StringBuilder();
            String line;

            while ((line = reader.readLine()) != null) {
                response.append(line);
            }
            return parsePhotoIds(response.toString());
        } finally {
            connection.disconnect();
        }
    } else {
        throw new IOException("Failed to retrieve photos from Pexels API. Response Code: " + responseCode);
    }
    }

    private static List<String> parsePhotoIds(String responseBody) {
        List<String> photoIds = new ArrayList<>();
        String s = "\"id\":\"";
        String[] parts = responseBody.split("\"id\":");

        for (int i = 1; i < parts.length && i <= NUM_PHOTOS; i++) {
            String photoId = parts[i].split(",")[0];
            photoIds.add(photoId);
        }

        return photoIds;
    }
    
    private static String parsePhotosOriginalUrl(String responseBody) {
        String s = "\"src\":";
        String[] parts = responseBody.split(s);
        

            String photo = parts[1].split("original\":")[1];
            System.out.println(photo.split("\"")[1]);


        return photo.split("\"")[1];
    }

    private static List<BufferedImage> downloadImages(List<String> photoIds) throws IOException, InterruptedException {
        List<BufferedImage> images = new ArrayList<>();

        for (String id : photoIds) {
            URL imageUrl = new URL("https://api.pexels.com/v1/photos/" + id);
            HttpURLConnection connection = (HttpURLConnection) imageUrl.openConnection();
    
            try {
                connection.setRequestMethod("GET");
                connection.setRequestProperty("Authorization", API_KEY);
                connection.setRequestProperty("User-Agent",
                	     "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/58.0.3029.110 Safari/537.36");
                int responseCode = connection.getResponseCode();
                if (responseCode == 200) {
                    try (BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()))) {
                    	 StringBuilder response = new StringBuilder();
                         String line;

                         while ((line = reader.readLine()) != null) {
                             response.append(line);
                         }
                         
                         
                         String originalUrl = parsePhotosOriginalUrl(response.toString());
                         
                         URL imageOriginalUrl = new URL(originalUrl);
                         HttpURLConnection connectionimageOriginalUrl = (HttpURLConnection) imageOriginalUrl.openConnection();
                         
                         
                         try {
                        	 connectionimageOriginalUrl.setRequestMethod("GET");
                        	 connectionimageOriginalUrl.setRequestProperty("Authorization", API_KEY);
                        	 connectionimageOriginalUrl.setRequestProperty("User-Agent",
                             	     "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/58.0.3029.110 Safari/537.36");                         
                         
                         
                         
                         
                         
                         
                      
                         
                         
                         
                         
                         
                         
                         
                        BufferedImage image = ImageIO.read(connectionimageOriginalUrl.getInputStream());
                        images.add(image);
                         }catch(Exception e) {
                        	 System.out.println(e.getMessage());
                         }
                    }
                } else {
                    throw new IOException("Failed to download image from URL: " + imageUrl + ". Response Code: " + responseCode);
                }
            } finally {
                connection.disconnect();
            }
        }
        return images;
    }

    private static BufferedImage createCollage(List<BufferedImage> images) {
        int collageWidth = 800;
        int collageHeight = 600;

        BufferedImage collage = new BufferedImage(collageWidth, collageHeight, BufferedImage.TYPE_INT_RGB);
        Graphics2D g = collage.createGraphics();

        int x = 0;
        for (BufferedImage image : images) {
            g.drawImage(image, x, 0, null);
            x += image.getWidth();
        }

        g.dispose();
        return collage;
    }

    private static void displayCollage(BufferedImage collage) {
        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame("Photo Collage");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

            JLabel label = new JLabel(new ImageIcon(collage));
            frame.getContentPane().add(label);

            frame.pack();
            frame.setLocationRelativeTo(null);
            frame.setVisible(true);
        });
    }

    private static void saveCollage(BufferedImage collage, String fileName) throws IOException {
        ImageIO.write(collage, "jpg", new File(fileName));
    }
}
