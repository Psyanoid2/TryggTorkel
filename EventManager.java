import java.util.*;

public class EventManager {
    private static final Map<Integer, int[]> events = new HashMap<>();
    private static final Map<Integer, Map<Integer, Object[]>> choiceRewards = new HashMap<>();
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

    public static void initializeChoiceRewards() {
        // Event 1 rewards
        Map<Integer, Object[]> event1Rewards = new HashMap<>();
        event1Rewards.put(1, new Object[]{0, 0, null, 0}); // No weapon
        event1Rewards.put(2, new Object[]{0, 0, null, 0}); // No weapon
        event1Rewards.put(3, new Object[]{0, 0, null, 0}); // No weapon
        event1Rewards.put(4, new Object[]{0, -10, null, 0}); // Lose 10 GP
        event1Rewards.put(5, new Object[]{0, 0, null, 0}); // No weapon
        event1Rewards.put(6, new Object[]{5, 0, null, 0}); // 5 EXP
        choiceRewards.put(1, event1Rewards);

        // Event 3 rewards (weapon rewards)
        Map<Integer, Object[]> event3Rewards = new HashMap<>();
        event3Rewards.put(1, new Object[]{0, 0, "Ett svärd", 0}); // Weapon: Ett svärd, no modifier
        event3Rewards.put(2, new Object[]{0, 0, "Avbruten pil", 0}); // Weapon: Avbruten pil, no modifier
        event3Rewards.put(3, new Object[]{0, 5, "+1 tvåhands stekpanna", 1}); // Weapon: +1 tvåhands stekpanna, +1 modifier
        event3Rewards.put(4, new Object[]{0, 100, "Katapult", 0}); // Weapon: Katapult, no modifier
        event3Rewards.put(5, new Object[]{0, 200, "AK4", 2}); // Weapon: AK4, +2 modifier
        event3Rewards.put(6, new Object[]{0, 500, "+17 flugsmälla", 3}); // Weapon: +17 flugsmälla, +3 modifier
        choiceRewards.put(3, event3Rewards);

        // Add more events and their choice rewards as needed
    }

    public static void printEventChoices(int event, Player player) {
        System.out.println("\nEvent " + event + ":");
        if (isStarredEvent(event)) {
            lastStarredEvent = event;
            System.out.println("*!");
        }

        if (event == 1) {
            System.out.println(player.getName() + " på värdshuset \"Galna tunnan\":");
            System.out.println("1. Muckar gräl med storväxt person.  .............. (S)");
            System.out.println("2. Dricker öl.  .................................. (33)");
            System.out.println("3. Träffar 1d6 äventyrare.  ..................... (9)");
            System.out.println("4. Smiter från notan.  .......................... (11)");
            System.out.println("5. Betalar notan (-10 gp) och går ut på stan. .... (*8)");
            System.out.println("6. Smiter in i rökigt sidorum.  .................. (*201)");
        } else if (event == 3) {
            System.out.println(player.getName() + " hittar vapen:");
            System.out.println("1. Ett svärd. .................................. (5)");
            System.out.println("2. Avbruten pil, värd 0 gp. .................... (10)");
            System.out.println("3. +1 tvåhands stekpanna, värd 5 gp. ........... (26)");
            System.out.println("4. Katapult, värd 100 gp. ..................... (26)");
            System.out.println("5. AK4, värd 200 gp. .......................... (108)");
            System.out.println("6. +17 flugsmälla, värd 500 gp. ............... (26)");
        }
        // Add more events as needed
    }

    public static void applyChoiceRewards(int event, int choice, Player player) {
        if (choiceRewards.containsKey(event)) {
            Map<Integer, Object[]> rewards = choiceRewards.get(event);
            if (rewards.containsKey(choice)) {
                Object[] reward = rewards.get(choice);
                int exp = (int) reward[0];
                int gold = (int) reward[1];
                String weaponName = (String) reward[2];
                int difficultyModifier = (int) reward[3];

                player.addExperience(exp);
                player.addGold(gold);

                if (weaponName != null) {
                    player.setWeapon(weaponName);
                    player.setDifficultyModifier(difficultyModifier);
                    System.out.println("Du har fått ett vapen: " + weaponName + " (Modifier: +" + difficultyModifier + ")");
                }

                System.out.println("\nDu har fått " + exp + " EXP och " + gold + " guldpoäng!");
                System.out.println("Nuvarande status: " + player.getExperience() + " EXP, " + player.getGold() + " GP");
            }
        }
    }

    public static boolean hasWeaponReward(int event, int choice) {
        if (choiceRewards.containsKey(event)) {
            Map<Integer, Object[]> rewards = choiceRewards.get(event);
            if (rewards.containsKey(choice)) {
                Object[] reward = rewards.get(choice);
                return reward[2] != null; // Check if a weapon is present
            }
        }
        return false;
    }

    public static String getWeaponReward(int event, int choice) {
        if (choiceRewards.containsKey(event)) {
            Map<Integer, Object[]> rewards = choiceRewards.get(event);
            if (rewards.containsKey(choice)) {
                Object[] reward = rewards.get(choice);
                return (String) reward[2]; // Return the weapon name
            }
        }
        return null; // No weapon reward
    }

    public static boolean hasNextEvent(int event) {
        return events.containsKey(event); // Check if the event exists in the events map
    }

    public static boolean isStarredEvent(int event) {
        return starredEvents.contains(event);
    }

    public static int getNextEvent(int event, int choice) {
        int[] nextEvents = events.get(event);
        return nextEvents[choice - 1];
    }
}