package level;

public class AdvancedLevel extends Level {

    @Override
    public void run() {
        System.out.println("빨리 달립니다.");
    }

    @Override
    public void jump() {
        System.out.println("높게 jump 합니다.");
    }

    @Override
    public void turn() {
        System.out.println("turn 할 수 없습니다.");
    }

    @Override
    public void showLevelMessage() {
        System.out.println("***** 중급자 레벨입니다. *****");
    }
}
