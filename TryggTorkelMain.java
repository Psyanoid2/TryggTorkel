import java.util.Scanner;

public class TryggTorkelMain {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        EventManager.initializeEvents();
        EventManager.initializeChoiceRewards();

        System.out.print("Ange ditt namn: ");
        String playerName = scanner.nextLine();

        System.out.print("Vill du slå en fysisk tärning? (ja/nej): ");
        boolean manualDice = scanner.nextLine().trim().equalsIgnoreCase("ja");

        Player player = new Player(playerName, manualDice);
        GameUtils.startGame(player, scanner);
    }
}