package com.example.bio;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.content.Context;
import android.graphics.Rect;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.os.Looper;
import android.util.DisplayMetrics;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    private ImageButton imageButton;
    private ImageView heroview;
    private ImageView bullet;
    private ImageView door;
    private ImageView enemy;
    static String direction;
    static String bulletdirection;
    private ConstraintLayout constraintLayout;
    DisplayMetrics dimension= new DisplayMetrics();
    Hero hero = new Hero();
    Rect heroRect;
    Rect doorRect;
    Rect bulletRect;
    Rect enemyRect;
    final Handler handler = new Handler(Looper.getMainLooper());
    Random r = new Random();


    CountDownTimer moveTimer = new CountDownTimer(100000,hero.getMovespeed())
    {
        String direction = "up";
        ImageView heroview;

        @Override
        public void onTick(long l) {
            direction = MainActivity.direction;
            heroview = findViewById(R.id.hero);
            switch(direction){

                case "down":
                    heroview.setImageDrawable(getResources().getDrawable(R.drawable.herodown));
                    if(heroview.getY()+10< dimension.heightPixels-heroview.getHeight())  heroview.setY(heroview.getY()+10);
                    doors();
                    break;

                case "up":
                    heroview.setImageDrawable(getResources().getDrawable(R.drawable.heroup));
                    if(heroview.getY()+10>=0)heroview.setY(heroview.getY()-10);
                    doors();
                    break;

                case "left":
                    heroview.setImageDrawable(getResources().getDrawable(R.drawable.heroleft));
                    if(heroview.getX()>=10)  heroview.setX(heroview.getX()-10);
                    doors();
                    break;


                case "right":
                    heroview.setImageDrawable(getResources().getDrawable(R.drawable.heroright));
                    if(heroview.getX()+10<dimension.widthPixels-heroview.getWidth())heroview.setX(heroview.getX()+10);
                    doors();
                    break;

            }

        }

        @Override
        public void onFinish() {

        }
    };
    CountDownTimer shootTimer = new CountDownTimer(hero.getHitspeed()*hero.getRange(),hero.getHitspeed()) {

        ImageView bullet;
        @Override
        public void onTick(long l) {
            findViewById(R.id.shoot).setEnabled(false);
            bullet = findViewById(R.id.bullet);
            switch(bulletdirection){
                case "down":
                    if(bullet.getY()+10< dimension.heightPixels-bullet.getHeight())  bullet.setY(bullet.getY()+10);
                    break;

                case "up":
                    if(bullet.getY()+10>=0)bullet.setY(bullet.getY()-10);
                    break;

                case "left":
                    if(bullet.getX()>=10)  bullet.setX(bullet.getX()-10);
                    break;


                case "right":
                    if(bullet.getX()+10<dimension.widthPixels-bullet.getWidth())bullet.setX(bullet.getX()+10);
                    break;

            }
                shoot();
        }

        @Override
        public void onFinish() {
            findViewById(R.id.bullet).setVisibility(View.INVISIBLE);
            findViewById(R.id.shoot).setEnabled(true);
        }
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        heroview = findViewById(R.id.hero);
        bullet = findViewById(R.id.bullet);
        door = findViewById(R.id.door);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();
        getWindowManager().getDefaultDisplay().getMetrics(dimension);

        findViewById(R.id.downarrow).setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getAction() == MotionEvent.ACTION_DOWN) {
                    MainActivity.direction = "down";
                    moveTimer.start();

                } else if (event.getAction() == MotionEvent.ACTION_UP) {
                    moveTimer.cancel();
                }
                return false;
            }
        });
        findViewById(R.id.uparrow).setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getAction() == MotionEvent.ACTION_DOWN) {
                    MainActivity.direction = "up";
                    moveTimer.start();

                } else if (event.getAction() == MotionEvent.ACTION_UP) {
                    moveTimer.cancel();
                }
                return false;
            }
        });
        findViewById(R.id.rightarrow).setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getAction() == MotionEvent.ACTION_DOWN) {
                    MainActivity.direction = "right";
                    moveTimer.start();

                } else if (event.getAction() == MotionEvent.ACTION_UP) {
                    moveTimer.cancel();
                }
                return false;
            }
        });
        findViewById(R.id.leftarrow).setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getAction() == MotionEvent.ACTION_DOWN) {
                    MainActivity.direction = "left";
                    moveTimer.start();


                } else if (event.getAction() == MotionEvent.ACTION_UP) {
                    moveTimer.cancel();
                }
                return false;
            }
        });
        findViewById(R.id.shoot).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                bullet = findViewById(R.id.bullet);
                heroview = findViewById(R.id.hero);
                bulletdirection = direction;
                    bullet.setX(heroview.getX());
                    bullet.setY(heroview.getY());
                    bullet.setVisibility(View.VISIBLE);
                    shootTimer.start();
            }
        });

    }
    public void doors(){
        heroview=findViewById(R.id.hero);
        door=findViewById(R.id.door);
        bullet=findViewById(R.id.bullet);
        enemy = findViewById(R.id.enemy);

        heroRect = new Rect((int)heroview.getX(),
                (int) heroview.getY(),
                (int) heroview.getX()+heroview.getWidth(),
                (int)heroview.getY()+heroview.getHeight());
        doorRect = new Rect((int)door.getX(),
                (int) door.getY(),
                (int) door.getX()+door.getWidth(),
                (int)door.getY()+door.getHeight());
        constraintLayout = findViewById(R.id.root);
        if(Rect.intersects(heroRect,doorRect)){
            if(hero.getActroom()==1){

                constraintLayout.setBackground(getResources().getDrawable(R.drawable.backgrnd2));
                door.setImageDrawable(getResources().getDrawable(R.drawable.doorleft));
                door.setX(0);
                heroview.setX(0+door.getWidth());
                enemy.setVisibility(View.VISIBLE);

                enemy.setX(r.nextInt(1000)+600);
                enemy.setY(r.nextInt(dimension.heightPixels)-enemy.getHeight());
                enemy.setImageDrawable(getResources().getDrawable(R.drawable.devil));
                bullet.setVisibility(View.INVISIBLE);
                hero.setActroom(2);

            }
            else {
                constraintLayout.setBackground(getResources().getDrawable(R.drawable.backgrnd1));
                door.setImageDrawable(getResources().getDrawable(R.drawable.doorright));
                door.setX(1804);
                heroview.setX(door.getX()-heroview.getWidth());
                bullet.setVisibility(View.INVISIBLE);
                enemy.setVisibility(View.INVISIBLE);
                hero.setActroom(1);
            }
        }
    }
    public void shoot(){
        bullet = findViewById(R.id.bullet);
        enemy = findViewById(R.id.enemy);
        bulletRect = new Rect((int)bullet.getX(),
                (int) bullet.getY(),
                (int) bullet.getX()+bullet.getWidth(),
                (int)bullet.getY()+bullet.getHeight());
        enemyRect = new Rect((int)enemy.getX(),
                (int) enemy.getY(),
                (int) enemy.getX()+enemy.getWidth(),
                (int)enemy.getY()+enemy.getHeight());

        if(Rect.intersects(bulletRect,enemyRect)){
            Glide.with(this).load(R.drawable.explosion_boom).into(enemy);
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {

                }
            },5000);
            enemy.setVisibility(View.INVISIBLE);
        };
    }
}