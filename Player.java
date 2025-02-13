public class Player {
    private String name;
    private boolean manualDice;
    private int experience;
    private int gold;
    private String weapon;
    private int difficultyModifier;

    public Player(String name, boolean manualDice) {
        this.name = name;
        this.manualDice = manualDice;
        this.experience = 0;
        this.gold = 100;
        this.weapon = "Inga vapen";
        this.difficultyModifier = 0;
    }

    public String getName() {
        return name;
    }

    public boolean isManualDice() {
        return manualDice;
    }

    public int getExperience() {
        return experience;
    }

    public void addExperience(int exp) {
        this.experience += exp;
    }

    public int getGold() {
        return gold;
    }

    public void addGold(int gold) {
        this.gold += gold;
    }

    public String getWeapon() {
        return weapon;
    }

    public void setWeapon(String weapon) {
        this.weapon = weapon;
    }

    public int getDifficultyModifier() {
        return difficultyModifier;
    }

    public void setDifficultyModifier(int difficultyModifier) {
        this.difficultyModifier = difficultyModifier;
    }
}