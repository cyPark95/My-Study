package pcy.st_0823;

import pcy.st_0823.thread.DownloadRunnable;

public class RunnableEx {

    public static void main(String[] args) {
        // Thread 가 해야할 기능을 만들어 놓았다. -> Runnable -> DownloadRunnable
        Thread thread = new Thread(new DownloadRunnable());
        thread.start();
    }
}
