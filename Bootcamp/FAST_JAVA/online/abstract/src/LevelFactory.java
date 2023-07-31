import level.AdvancedLevel;
import level.BeginnerLevel;
import level.Level;
import level.LevelCode;
import level.SuperLevel;

public class LevelFactory {

    private LevelFactory() {
    }

    public static Level create(LevelCode code) {
        return switch (code) {
            case BEGINNER -> new BeginnerLevel();
            case ADVANCED -> new AdvancedLevel();
            case SUPER -> new SuperLevel();
        };
    }
}
