package com.example.iramli.tap;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;

public class MainActivity extends Activity {
    int[] tako;

    String ika;

    int kani;
    int Maxcombo;
    int timeLimit;
    int maxTimeLimit;
    int combo;
    int point;



    TextView textView;
    ProgressBar progress;

    //変数の宣言
    CountUp countUp;
    int bonus = 1800;

    final Handler handler = new Handler();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textView = (TextView) findViewById(R.id.textView);
        progress = (ProgressBar) findViewById(R.id.progress);


        combo =0;
        point =0;
        Maxcombo = 0;
        start();

        printLog("test");
        Intent intent = getIntent();
        timeLimit = intent.getIntExtra("minutes", 0);
        maxTimeLimit = timeLimit;
        bonus = timeLimit;

        progress.setMax(maxTimeLimit);
        progress.setProgress(timeLimit);


//インスタンスの生成
        countUp = new CountUp(100);
        countUp.setTimeLimit(timeLimit);

        countUp.setOnFinishListener(new CountUp.OnFinishListener() {
            @Override
            public void onFinish() {
                finishGame();
            }
        });

        countUp.setOnTickListener(new CountUp.OnTickListener() {
            @Override
            public void onTick(long millisUntilFinished) {
                //カウントダウン中の処理
                Log.d("Main", countUp.timeLimit + "");
                progress.setProgress(timeLimit - (int)millisUntilFinished);
            }
        });
        countUp.start();
    }


    private void finishGame() {

        //カウントダウン終了時の処理

        Toast.makeText(getApplicationContext(), "Time Up", Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(MainActivity.this, ResultActivity.class);
        intent.putExtra("point", point);
        intent.putExtra("combo", combo);
        intent.putExtra("Maxcombo", Maxcombo); //この行を追記

        startActivity(intent);
        stopTimer();
    }

    private void stopTimer() {
        // Handler should be defined in UI Thread
        // CountUp stop() method would work in UI Thread
        new Thread(new Runnable() {
            @Override
            public void run() {
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        countUp.stop();
                    }
                });
            }
        }).start();
    }

    private void printLog(String message) {
        Log.d("Main", message);
    }
    private void addTime() {
//        if(timeLimit >=  maxTimeLimit * 1/3){
//            timeLimit = maxTimeLimit;
//            printLog(String.valueOf(timeLimit));
//            countUp.setTimeLimit(timeLimit);
//        }else {
//            timeLimit += bonus * 2 / 3;
//            printLog(String.valueOf(timeLimit));
//            countUp.setTimeLimit(timeLimit);
//        }



        timeLimit += bonus * 2 / 3;
//        if(timeLimit >= maxTimeLimit){
//            timeLimit = maxTimeLimit;
//        }
        printLog(String.valueOf(timeLimit));
        countUp.setTimeLimit(timeLimit);
        //progress.setProgress(ebi);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        countUp.stop();
        countUp.reset();
        countUp = null;
    }

    public void start() {
        tako = new int[9];
        Random rand = new Random();
        for (int i = 0; i < tako.length; i++) {
            tako[i] = rand.nextInt(9) + 1;
        }
        ika =
                Integer.toString(tako[0])
                        + Integer.toString(tako[1])
                        + Integer.toString(tako[2])
                        + Integer.toString(tako[3])
                        + Integer.toString(tako[4])
                        + Integer.toString(tako[5])
        ;
        textView.setText(ika);
        kani = 0;
    }

    private void seikai() {
        combo = combo + 1;
        point = point + combo;

        if (combo > Maxcombo) { //もし、comboがMaxcomboより大きかったら
            Maxcombo = combo; //Maxcomboをcomboと同じ数にする
        }
        //逆にcomboがMaxcomboより小さかったらこのコードは読まれないので、最大コンボ数をそのままにしておくことができます。




        ika = ika.substring(1);
        textView.setText(ika);
        kani = kani + 1;
        if (kani == 6) {
            addTime();
            start();
        }
    }


    public void number1(View v) {
        if (tako[kani] == 1) {
            seikai();
        } else {
            combo=0;

            Toast.makeText(this, "ﾁｶﾞｲﾏｽ", Toast.LENGTH_SHORT).show();
        }

    }

    public void number2(View v) {
        if (tako[kani] == 2) {
            seikai();
        } else {
            combo=0;
            Toast.makeText(this, "違うやんけ", Toast.LENGTH_SHORT).show();
        }

    }

    public void number3(View v) {
        if (tako[kani] == 3) {
           seikai();
        } else {
            combo=0;
            Toast.makeText(this, "違うって", Toast.LENGTH_SHORT).show();
        }

    }

    public void number4(View v) {
        if (tako[kani] == 4) {
            seikai();
        } else {
            combo=0;
            Toast.makeText(this, "間違いです", Toast.LENGTH_SHORT).show();
        }

    }

    public void number5(View v) {
        if (tako[kani] == 5) {
            seikai();
        } else {
            combo=0;
            Toast.makeText(this, "違うです", Toast.LENGTH_SHORT).show();
        }

    }

    public void number6(View v) {
        if (tako[kani] == 6) {
            seikai();
        } else {
            combo=0;
            Toast.makeText(this, "違うみたい", Toast.LENGTH_SHORT).show();
        }

    }


    public void number7(View v) {
        if (tako[kani] == 7) {
            seikai();
        } else {
            combo=0;
            Toast.makeText(this, "違うよ", Toast.LENGTH_SHORT).show();
        }

    }

    public void number8(View v) {
        if (tako[kani] == 8) {
            seikai();
        } else {
            combo=0;
            Toast.makeText(this, "それは違う", Toast.LENGTH_SHORT).show();
        }

    }

    public void number9(View v) {
        if (tako[kani] == 9) {
            seikai();
        } else {
            combo=0;
            Toast.makeText(this, "数字が違うよ", Toast.LENGTH_SHORT).show();
        }

    }
}

