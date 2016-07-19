package org.chat;

import java.util.TimerTask;
import java.util.concurrent.TimeUnit;

/**
 * Created by A.V.Tsaplin on 18.07.2016.
 */
public class DelayThread implements Runnable {

    Thread thread;
    volatile boolean timeOut = false;
    int time = 1;

    public DelayThread(int time) {
        this.time = time;
        thread = new Thread(this, "delayServerThread");
        thread.start();
    }

    public boolean getTimeOut() {
        return timeOut;
    }

    @Override
    public void run() {
        try {
            TimeUnit.SECONDS.sleep(time);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            timeOut = true;
            System.out.println("Time out for user's authorization data receive!");
        }
    }
}
