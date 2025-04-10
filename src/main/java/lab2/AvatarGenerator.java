package lab2;

// Import the necessary classes

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class AvatarGenerator { // This is the main class responsible for generating avatars.

    public static void main(String[] args) { // Main method, entry point of the program.
        try {
            var avatarStream = AvatarGenerator.getRandomAvatarStream(); // Calls the static method to get a random avatar image as InputStream.
            AvatarGenerator.showAvatar(avatarStream); // Calls the showAvatar method, passing the avatar image stream to display the image.
        } catch (IOException | InterruptedException e) { // Handles exceptions for I/O and interruption errors.
            e.printStackTrace(); // Prints the stack trace if an exception occurs.
        }
    }

    public static InputStream getRandomAvatarStream() throws IOException, InterruptedException {
        // Method to fetch a random avatar from the DiceBear API.

        String[] styles = {
                "adventurer", "adventurer-neutral", "avataaars", "big-ears", "big-ears-neutral", "big-smile",
                "bottts", "croodles", "croodles-neutral", "fun-emoji", "icons", "identicon", "initials", "lorelei",
                "micah", "miniavs", "open-peeps", "personas", "pixel-art", "pixel-art-neutral"
        };
        // Declares an array of available avatar styles (reference type, array of Strings).

        var style = styles[(int)(Math.random() * styles.length)];
        // Picks a random avatar style using Math.random() to generate a random index.
        // `style` is a reference type (String) holding the selected avatar style.

        var seed = (int)(Math.random() * 10000);
        // Generates a random seed value (primitive int) used for the avatar creation.

        var uri = URI.create("https://api.dicebear.com/9.x/%s/png?seed=%d".formatted(style, seed));
        // Constructs a URI using the formatted style and seed. `URI.create()` is a static method that creates a URI object.

        var request = HttpRequest.newBuilder(uri).build();
        // Creates an HTTP request using the constructed URI. `HttpRequest.newBuilder()` creates a builder for the request.

        try (var client = HttpClient.newHttpClient()) {
            // Creates an HttpClient instance (automatic closing with try-with-resources).
            // `HttpClient.newHttpClient()` is a class method that returns an HttpClient instance.
            var response = client.send(request, HttpResponse.BodyHandlers.ofInputStream());
            // Sends the HTTP request and gets the response body as InputStream. `client.send()` is an instance method.
            return response.body();
            // Returns the response body (InputStream) which contains the image data.
        }
    }

    public static void showAvatar(InputStream imageStream) {
        // Method to display the fetched avatar image.

        JFrame frame = new JFrame("PNG Viewer");
        // Creates a JFrame object named "PNG Viewer" to display the image. `JFrame` is a class from `javax.swing` package.

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        // Sets the default close operation for the frame. `setDefaultCloseOperation()` is an instance method.

        frame.setResizable(false);
        // Makes the frame non-resizable. `setResizable()` is an instance method.

        frame.setSize(200, 200);
        // Sets the frame size to 200x200 pixels. `setSize()` is an instance method.

        frame.getContentPane().setBackground(Color.BLACK);
        // Sets the background color of the content pane to black. `getContentPane()` is an instance method of `JFrame`.

        try {
            // Tries to execute the following block where the image will be read.
            Image image = ImageIO.read(imageStream);
            // Reads the image from the InputStream using `ImageIO.read()`. Returns an Image object (from `java.awt` package).

            JLabel imageLabel = new JLabel(new ImageIcon(image));
            // Creates a JLabel to hold the image using the `ImageIcon` class, which is a reference type holding the image.
            frame.add(imageLabel, BorderLayout.CENTER);
            // Adds the label to the center of the frame. `add()` is an instance method of `JFrame`.
        } catch (IOException e) {
            // Catches any I/O exceptions if reading the image fails.
            e.printStackTrace();
            // Prints the stack trace of the exception.
        }

        frame.setVisible(true);
        // Makes the frame visible. `setVisible()` is an instance method of `JFrame`.
    }
}
