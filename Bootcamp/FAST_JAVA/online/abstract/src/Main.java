import level.LevelCode;

public class Main {

    public static void main(String[] args) {
        Player player = new Player();
        player.play(1);

        player.upgradeLevel(LevelCode.ADVANCED);
        player.play(2);

        player.upgradeLevel(LevelCode.SUPER);
        player.play(3);
    }
}
