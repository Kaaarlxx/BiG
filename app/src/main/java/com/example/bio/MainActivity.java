package com.example.bio;

import androidx.annotation.Dimension;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.constraintlayout.widget.ConstraintSet;

import android.app.Activity;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Rect;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.util.DisplayMetrics;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.TranslateAnimation;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.Timer;

public class MainActivity extends AppCompatActivity {

    private ImageButton imageButton;
    private ImageView imageView;
    static String direction;
    private ConstraintLayout constraintLayout;
    DisplayMetrics dimension= new DisplayMetrics();
    Rect rc1 = new Rect();
    Rect rc2 = new Rect();


    CountDownTimer countDownTimer = new CountDownTimer(100000,10)
    {
        String direction;
        @Override
        public void onTick(long l) {
            direction = MainActivity.direction;
            imageView = findViewById(R.id.hero);
            switch(direction){

                case "down":
                    imageView.setImageDrawable(getResources().getDrawable(R.drawable.herodown));
                    if(imageView.getY()+10< dimension.heightPixels-imageView.getHeight()-60)  imageView.setY(imageView.getY()+10);
                    doors();
                    break;

                case "up":
                    imageView.setImageDrawable(getResources().getDrawable(R.drawable.heroup));
                    if(imageView.getY()+10>=0)imageView.setY(imageView.getY()-10);
                    doors();
                    break;

                case "left":
                    imageView.setImageDrawable(getResources().getDrawable(R.drawable.heroleft));
                    if(imageView.getX()>=10)  imageView.setX(imageView.getX()-10);
                    doors();
                    break;


                case "right":
                    imageView.setImageDrawable(getResources().getDrawable(R.drawable.heroright));
                    if(imageView.getX()+10<dimension.widthPixels-imageView.getWidth())imageView.setX(imageView.getX()+10);
                    doors();
                    break;

            }

        }

        @Override
        public void onFinish() {

        }
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        imageView = findViewById(R.id.hero);
        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();
        getWindowManager().getDefaultDisplay().getMetrics(dimension);
        findViewById(R.id.downarrow).setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getAction() == MotionEvent.ACTION_DOWN) {
                    MainActivity.direction = "down";
                    countDownTimer.start();

                } else if (event.getAction() == MotionEvent.ACTION_UP) {
                    countDownTimer.cancel();
                }
                return false;
            }
        });
        findViewById(R.id.uparrow).setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getAction() == MotionEvent.ACTION_DOWN) {
                    MainActivity.direction = "up";
                    countDownTimer.start();

                } else if (event.getAction() == MotionEvent.ACTION_UP) {
                    countDownTimer.cancel();
                }
                return false;
            }
        });
        findViewById(R.id.rightarrow).setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getAction() == MotionEvent.ACTION_DOWN) {
                    MainActivity.direction = "right";
                    countDownTimer.start();

                } else if (event.getAction() == MotionEvent.ACTION_UP) {
                    countDownTimer.cancel();
                }
                return false;
            }
        });
        findViewById(R.id.leftarrow).setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getAction() == MotionEvent.ACTION_DOWN) {
                    MainActivity.direction = "left";
                    countDownTimer.start();


                } else if (event.getAction() == MotionEvent.ACTION_UP) {
                    countDownTimer.cancel();
                }
                return false;
            }
        });
    }
    public void doors(){
        imageView = findViewById(R.id.hero);
        rc1 = new Rect((int)imageView.getX(),
                (int) imageView.getY(),
                (int) imageView.getX()+imageView.getWidth(),
                (int)imageView.getY()+imageView.getHeight());
        imageView = findViewById(R.id.imageView);
        rc2 = new Rect((int)imageView.getX(),
                (int) imageView.getY(),
                (int) imageView.getX()+imageView.getWidth(),
                (int)imageView.getY()+imageView.getHeight());
        if(Rect.intersects(rc1,rc2)){

            System.out.println("Collision");
            constraintLayout = findViewById(R.id.root);
            constraintLayout.setBackground(getResources().getDrawable(R.drawable.backgrnd2));
        }
    }
}