package com.example.bilal488.merapaisa;

import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import io.netopen.hotbitmapgg.library.view.RingProgressBar;

public class SplashActivity extends AppCompatActivity {

    RingProgressBar ringProgressBar2;

    int progress = 0;

    Handler myHandler = new Handler(){

        @Override
        public void handleMessage(Message msg) {
            if(msg.what == 0)
            {
                if(progress<100)
                {
                    progress++;
                    ringProgressBar2.setProgress(progress);
                }
            }
        }

    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        ringProgressBar2 = (RingProgressBar) findViewById(R.id.progress_bar_2);

        ringProgressBar2.setOnProgressListener(new RingProgressBar.OnProgressListener() {
            @Override
            public void progressToComplete() {
                /*Toast.makeText(MainActivity.this, "Completed !!!", Toast.LENGTH_SHORT).show();*/
                startActivity(new Intent(getApplicationContext(),FirstActivity.class));
                overridePendingTransition(R.anim.slide_in_from_right,R.anim.slide_out_to_left);
                finish();
            }

        });
        new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < 100; i++) {
                    try {
                        Thread.sleep(100);
                        myHandler.sendEmptyMessage(0);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }).start();

        /*Thread splash = new Thread(){

            public void run(){
                try{
                    sleep(3*1000);

                    startActivity(new Intent(getApplicationContext(),FirstActivity.class));
                    overridePendingTransition(R.anim.slide_in_from_right,R.anim.slide_out_to_left);

                    finish();
                }catch (Exception e){

                }
            }
        };

        splash.start();*/

    }
}
