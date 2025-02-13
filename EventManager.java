import java.util.*;

public class EventManager {
    private static final Map<Integer, int[]> events = new HashMap<>();
    private static final Set<Integer> starredEvents = new HashSet<>(Arrays.asList(1, 7, 8, 18, 21, 24, 25, 32, 34, 45, 47, 50, 54, 58, 59, 64, 70, 71, 74, 80, 81, 84, 85, 86, 97, 100, 101, 105, 124, 132, 135, 136, 144, 146, 148, 149, 152, 157, 158, 159, 160, 161, 163, 167, 168, 174, 175, 177, 179, 180, 181, 182, 191, 192, 195, 196, 198, 199, 203, 209, 210, 213, 214, 215, 216, 217, 666, 1000, 1701));
    private static int lastStarredEvent = -1;

    public static void initializeEvents() {
        events.put(1, new int[]{10001, 33, 9, 11, 8, 201});
        events.put(2, new int[]{3, 173, 4, 16, 14, 15});
        events.put(3, new int[]{5, 10, 26, 26, 108, 26});
        events.put(4, new int[]{9, 0, 39, 38, 666, 6});
        events.put(5, new int[]{0, 10, 26, 26, 26, 0});
        events.put(6, new int[]{24, 91, 12, 0, 0, 69});
        events.put(7, new int[]{10002, 0, 168, 7, 45, 6});
    }

    public static int handleEvent(int event, int choice, Player player) {
        switch (event) {
            case 1:
                System.out.println(player.getName() + " på värdshuset \"Galna tunnan\":");
                switch (choice) {
                    case 1:
                        System.out.println("Du muckar gräl med en storväxt person. (S)");
                        player.addExperience(0);
                        player.addGold(0);
                        return 10001; // Next event
                    case 2:
                        System.out.println("Du dricker öl. (33)");
                        player.addExperience(0);
                        player.addGold(0);
                        return 33; // Next event
                    case 3:
                        System.out.println("Du träffar 1d6 äventyrare. (9)");
                        player.addExperience(10);
                        player.addGold(0);
                        return 9; // Next event
                    case 4:
                        System.out.println("Du smiter från notan. (11)");
                        player.addExperience(0);
                        player.addGold(-10);
                        return 11; // Next event
                    case 5:
                        System.out.println("Du betalar notan (-10 gp) och går ut på stan. (*8)");
                        player.addExperience(0);
                        player.addGold(-10);
                        return 8; // Next event
                    case 6:
                        System.out.println("Du smiter in i ett rökigt sidorum. (*201)");
                        player.addExperience(5);
                        player.addGold(0);
                        return 201; // Next event
                    default:
                        System.out.println("Ogiltigt val. Spelet avslutas.");
                        return -1; // End game
                }
            case 3:
                System.out.println(player.getName() + " hittar vapen:");
                switch (choice) {
                    case 1:
                        System.out.println("Du hittar ett svärd. (5)");
                        player.setWeapon("Ett svärd");
                        player.setDifficultyModifier(0);
                        return 5; // Next event
                    case 2:
                        System.out.println("Du hittar en avbruten pil. (10)");
                        player.setWeapon("Avbruten pil");
                        player.setDifficultyModifier(0);
                        return 10; // Next event
                    case 3:
                        System.out.println("Du hittar en +1 tvåhands stekpanna. (26)");
                        player.setWeapon("+1 tvåhands stekpanna");
                        player.setDifficultyModifier(1);
                        return 26; // Next event
                    case 4:
                        System.out.println("Du hittar en katapult. (26)");
                        player.setWeapon("Katapult");
                        player.setDifficultyModifier(0);
                        return 26; // Next event
                    case 5:
                        System.out.println("Du hittar en AK4. (108)");
                        player.setWeapon("AK4");
                        player.setDifficultyModifier(2);
                        return 108; // Next event
                    case 6:
                        System.out.println("Du hittar en +17 flugsmälla. (26)");
                        player.setWeapon("+17 flugsmälla");
                        player.setDifficultyModifier(3);
                        return 26; // Next event
                    default:
                        System.out.println("Ogiltigt val. Spelet avslutas.");
                        return -1; // End game
                }
                // Add more events here
            default:
                System.out.println("Event " + event + " är inte implementerat ännu.");
                return -1; // End game
        }
    }

    public static boolean isStarredEvent(int event) {
        return starredEvents.contains(event);
    }
}