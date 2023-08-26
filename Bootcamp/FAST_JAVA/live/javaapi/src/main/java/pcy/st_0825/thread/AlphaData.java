package pcy.st_0825.thread;

import java.util.stream.IntStream;

public class AlphaData implements Runnable {

    @Override
    public void run() {
        IntStream.range(65, 91)
                .forEach(c -> System.out.print((char) c));
    }
}
