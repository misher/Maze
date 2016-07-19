package org.chat;

import java.util.TimerTask;
import java.util.concurrent.TimeUnit;

/**
 * Created by A.V.Tsaplin on 18.07.2016.
 */
public class DelayThread implements Runnable {

    private int time = 1;
    private Thread thread;
    private static volatile boolean timeOut = false;

    public DelayThread(int time) {
        this.time = time;
        thread = new Thread(this, "serverThread");
        thread.start();
    }

    public static boolean getTimeOut() {
        return timeOut;
    }

    public static void setTimeOut(boolean timeOut) {
        DelayThread.timeOut = timeOut;
    }

    @Override
    public void run() {
        try {
            TimeUnit.SECONDS.sleep(time);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        timeOut = true;
    }
}
