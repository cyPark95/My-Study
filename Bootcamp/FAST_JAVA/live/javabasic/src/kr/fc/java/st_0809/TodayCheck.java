package kr.fc.java.st_0809;

public class TodayCheck {

    public static void main(String[] args) {
        // 이제 Guitar과 Flute 클래스는 공통된 인터페이스를 가지므로 Instrument 객체를 통해 playSound() 메서드를 호출하면, 각 클래스에 맞는 구현이 호출되며 다음과 같은 출력을 보여줍니다
        // 기타 소리 연주 중!
        // 플루트로 멜로디를 연주 중!
        Instrument guitar = new Guitar();
        Instrument flute = new Flute();
        guitar.playSound();
        flute.playSound();

        //Piano 클래스를 추가하고, playSound() 메서드를 호출하면 "피아노 연주 중!"을 출력합니다.
        Instrument piano = new Piano();
        piano.playSound();
    }

    // 1. Instrument라는 인터페이스를 선언하고, playSound() 메서드를 가지고 있습니다.
    private interface Instrument {
        void playSound();
    }

    // 비관련 클래스인 Guitar 클래스가 Instrument 인터페이스를 구현하고, 각자의 구체적인 playSound() 메서드 구현을 제공합니다.
    private static class Guitar implements Instrument {

        @Override
        public void playSound() {
            System.out.println("기타 소리 연주 중!");
        }
    }

    // 비관련 클래스인 Flute 클래스가 Instrument 인터페이스를 구현하고, 각자의 구체적인 playSound() 메서드 구현을 제공합니다.
    private static class Flute implements Instrument {

        @Override
        public void playSound() {
            System.out.println("플루트로 멜로디를 연주 중!");
        }
    }

    // 2. Instrument 인터페이스를 구현한 Piano 클래스를 추가하고 동작시키시오.
    // Piano 클래스는 playSound() 메서드에 대한 자체적인 구현을 제공하며, 콘솔에 "피아노 연주 중!"을 출력합니다.
    private static class Piano implements Instrument {

        @Override
        public void playSound() {
            System.out.println("피아노 연주 중!");
        }
    }
}
