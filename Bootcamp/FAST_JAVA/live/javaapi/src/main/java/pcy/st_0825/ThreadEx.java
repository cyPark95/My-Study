package pcy.st_0825;

import pcy.st_0825.thread.AlphaData;

import java.util.stream.IntStream;

public class ThreadEx {

    public static void main(String[] args) {
        Thread currentThread = Thread.currentThread();
        System.out.println(currentThread.getName());        // main
        System.out.println(currentThread.getPriority());    // 1 ~ 10

        // A ~ Z까지 출력하세요.
        // 1. 작업객체 생성(job)
        Runnable target = new AlphaData();
        // 2. 스레드 생성
        Thread alphaThread = new Thread(target);  // 스레드와 작업을 연결
        // 3. 스레드 시작
        alphaThread.start();
        // run() 구동 -> ready 상태(Queue) -> running(실행) -> block(봉쇄) -> dead(소멸)

        // 1 ~ 10까지의 수를 출력하세요.
        IntStream.range(1, 11)
                .forEach(i -> {
                    System.out.print(i);
                    try {
                        Thread.sleep(500);  // 1000 -> 1초: Context Switching(문맥교환)
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                });
    }
}
// 프로세서
// Processor <---> Processor
// 스레드(경량 프로세서)
// Thread <---> Thread
