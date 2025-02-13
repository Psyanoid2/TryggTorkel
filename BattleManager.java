import java.util.*;

public class BattleManager {
    private static final Map<Integer, String[]> battleEvents = new HashMap<>();
    private static final Random random = new Random();

    static {
        battleEvents.put(10001, new String[]{"Affärsinnehavare", "+1", "20"});
        battleEvents.put(10002, new String[]{"Alligator", "0", "60"});
        battleEvents.put(10003, new String[]{"Alv, svart", "-1", "200"});
    }

    public static void startBattle(Scanner scanner, Player player, int event) {
        if (battleEvents.containsKey(event)) {
            String[] battleData = battleEvents.get(event);
            String enemy = battleData[0];
            int difficulty = Integer.parseInt(battleData[1]) + player.getDifficultyModifier(); // Include player's modifier
            int expReward = Integer.parseInt(battleData[2]);

            int enemyCount = random.nextInt(6) + 1;
            System.out.println("\nStrid! Du möter " + enemyCount + " " + enemy + "!");
            int defeated = 0;

            for (int i = 0; i < enemyCount; i++) {
                int roll = GameUtils.rollDice(scanner, player.isManualDice()) + difficulty;
                if (roll >= 6) {
                    System.out.println("Du besegrade en " + enemy + "!");
                    defeated++;
                } else {
                    System.out.println("Du misslyckades med att besegra en " + enemy + "!");
                }
            }

            if (defeated == enemyCount) {
                player.addExperience(expReward);
                System.out.println("\nDu vann striden! Du fick " + expReward + " EXP!");
            } else {
                System.out.println("\nDu överlevde men besegrade inte alla fiender.");
            }

            System.out.println("Nuvarande status: " + player.getExperience() + " EXP, " + player.getGold() + " GP");
        }
    }

    public static boolean hasBattleEvent(int currentEvent) {
    }
}