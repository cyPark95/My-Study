import java.io.IOException;

import scheduler.Scheduler;

public class Main {

    public static void main(String[] args) throws IOException {
        Scheduler scheduler = SchedulerFactory.create(inputType());
        if (scheduler == null) {
            System.out.println("지원되지 않는 기능입니다.");
            return;
        }

        scheduler.getNextCall();
        scheduler.sendCallToAgent();
    }

    private static int inputType() throws IOException {
        System.out.println("전화 상담원 할당 방식을 선택하세요.");
        System.out.println("R : 한명씩 차례대로");
        System.out.println("L : 대기가 적은 상담원 우선");
        System.out.println("P : 우선순위가 높은 고객우선 숙련도 높은 상담원");
        return System.in.read();
    }
}
