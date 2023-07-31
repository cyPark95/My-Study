import level.Level;
import level.LevelCode;

public class Player {

    private Level level;

    public Player() {
        level = LevelFactory.create(LevelCode.BEGINNER);
        level.showLevelMessage();
    }

    public void upgradeLevel(LevelCode code) {
        level = LevelFactory.create(code);
        level.showLevelMessage();
    }

    public void play(int count) {
        level.go(count);
    }
}
