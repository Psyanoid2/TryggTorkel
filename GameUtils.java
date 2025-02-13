import java.util.Scanner;

public class GameUtils {
    public static int rollDice(Scanner scanner, boolean manualDice) {
        System.out.print("\nSlå en tärning (ange 1-6): ");
        while (true) {
            try {
                int roll = Integer.parseInt(scanner.nextLine().trim());
                if (roll >= 1 && roll <= 6) {
                    return roll;
                }
            } catch (NumberFormatException ignored) {}
            System.out.print("Ogiltigt värde, försök igen (1-6): ");
        }
    }

    public static void startGame(Player player, Scanner scanner) {
        int currentEvent = 1;
        while (currentEvent > 0 && currentEvent <= 217) {
            System.out.println("\n" + player.getName() + " är nu vid event " + currentEvent);
            int diceRoll = rollDice(scanner, player.isManualDice());
            currentEvent = EventManager.handleEvent(currentEvent, diceRoll, player);

            if (currentEvent == -1) {
                System.out.println("Spelet är slut! Ingen fortsättning från detta event.");
                break;
            }
        }

        System.out.println("Äventyret är över! Tack för att du spelade, " + player.getName() + "!");
    }
}