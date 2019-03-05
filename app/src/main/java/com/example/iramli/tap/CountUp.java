package com.example.iramli.tap;

/**
 * Created by ilmari on 2018/08/11.
 */

public class CountUp extends CountUpTimer{
    int timeLimit;
    OnFinishListener onFinishListener;
    OnTickListener onTickListener;

    public void setOnFinishListener(OnFinishListener listener) {
        this.onFinishListener = listener;
    }


    public void setOnTickListener(OnTickListener listener) {
        this.onTickListener = listener;
    }

    public void setTimeLimit(int newLimit) {
        timeLimit = newLimit;
    }
    public CountUp(long interval) {
        super(interval);
    }

    @Override
    public void start() {
        super.start();
    }

    @Override
    public void restart() {
        super.restart();
    }

    @Override
    public void stop() {
        super.stop();
    }

    @Override
    public void reset() {
        super.reset();
    }

    @Override
    public void onTick(long elapsedTime) {
//        Log.d("event", "経過時間: " + elapsedTime);
        if (onTickListener != null) onTickListener.onTick(elapsedTime);

        if (elapsedTime > timeLimit) {
            onFinishListener.onFinish();
        }
    }


    public interface OnFinishListener {
        void onFinish();
    }

    public interface OnTickListener {
        void onTick(long millisUntilFinished);
    }
}
