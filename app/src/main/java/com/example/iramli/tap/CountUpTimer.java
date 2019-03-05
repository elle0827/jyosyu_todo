package com.example.iramli.tap;

import android.os.Handler;
import android.os.Message;
import android.os.SystemClock;

/**
 * Created by ilmari on 2018/08/11.
 */

public abstract class CountUpTimer {

    private final long interval;
    private long base;
    private long elapsedTime;

    public CountUpTimer(long interval) {
        this.interval = interval;
    }

    public void start() {
        base = SystemClock.elapsedRealtime();
        handler.sendMessage(handler.obtainMessage(MSG));
    }

    public void restart() {
        base = SystemClock.elapsedRealtime() - elapsedTime;
        handler.sendMessage(handler.obtainMessage(MSG));
    }

    public void stop() {
        handler.removeMessages(MSG);
    }

    public void reset() {
        synchronized (this) {
            base = SystemClock.elapsedRealtime();
        }
    }

    abstract public void onTick(long elapsedTime);

    private static final int MSG = 1;

    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            synchronized (CountUpTimer.this) {
                long elapsedTime = SystemClock.elapsedRealtime() - base;
                CountUpTimer.this.elapsedTime = elapsedTime;
                onTick(elapsedTime);
                sendMessageDelayed(obtainMessage(MSG), interval);
            }
        }
    };
}
