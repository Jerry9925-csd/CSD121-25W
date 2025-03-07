package lab4.ui;

import lab4.game.*;
import com.diogonunes.jcolor.Attribute;
import static com.diogonunes.jcolor.Ansi.colorize;

import java.util.Scanner;

/**
 * Helper methods for doing console-based user interaction
 */
public class Console {

    public static void println(String message) {
        System.out.println(colorize(message, Attribute.GREEN_TEXT())); // Informational messages in Green
    }

    public static String prompt(String promptMessage) {
        System.out.print(colorize(promptMessage, Attribute.BLUE_TEXT())); // Prompt messages in Blue
        var scanner = new Scanner(System.in);
        return scanner.nextLine();
    }

    public static void showBoard(Board board) {
        System.out.print(board);
    }

    public static Position promptForPosition(String prompt, Board board) {
        var scanner = new Scanner(System.in);
        final String helpMessage = colorize("Input must be in the format 'row column', e.g., '1 2' or 't m'", Attribute.RED_TEXT());

        while (true) {
            System.out.print(colorize(prompt, Attribute.BLUE_TEXT())); // Prompt in Blue
            var input = scanner.nextLine().trim();

            if (input.length() != 3) {
                System.out.println(helpMessage); // Error message in Red
                continue;
            }

            var parts = input.split(" ");

            if (parts.length != 2) {
                System.out.println(helpMessage);
                continue;
            }

            try {
                var pos = new Position(Row.from(parts[0]), Col.from(parts[1]));

                if (board.isOccupiedAt(pos)) {
                    System.out.println(colorize("That position is already taken.", Attribute.RED_TEXT()));
                    continue;
                }

                return pos;
            } catch (IllegalArgumentException e) {
                System.out.println(helpMessage);
            }
        }
    }
}
