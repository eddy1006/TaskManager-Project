package com.example.taskmanager;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import com.airbnb.lottie.LottieAnimationView;

public class MainActivity4 extends AppCompatActivity {
    LottieAnimationView lottieAnimationView;
    Thread timer;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main4);
        lottieAnimationView = findViewById(R.id.lottie);
        timer = new Thread(){
            @Override
            public void run() {
                try{
                    synchronized (this){
                        wait(2000);
                    }
                }catch(InterruptedException e){
                    e.printStackTrace();
                } finally {
                    Intent intent = new Intent(MainActivity4.this,MainActivity2.class);
                    startActivity(intent);
                    finish();
                }
            }
        };
        timer.start();
    }
}