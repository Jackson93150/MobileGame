package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.animation.Animator;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.airbnb.lottie.LottieAnimationView;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

import pl.droidsonroids.gif.GifDrawable;

public class MainActivity2 extends AppCompatActivity {

    Animation haut;
    LottieAnimationView countdown_animation;
    private Button carer;
    private Button traingleb;
    private Button rondb;
    private LottieAnimationView replay;
    private ImageView square;
    private ImageView triangle;
    private ImageView like;
    private ImageView dislike;
    private ImageView rond;
    private ImageView bien;
    private ImageView faux;
    boolean state = false;
    boolean clickstate = false;
    List<String> list;
    List<String> list2;
    Handler handler = new Handler();
    Runnable to_exec;
    int compteur = 0;
    int compteur2 = 0;
    int i = 0;
    int random;
    String score = "2";
    int rst = 2;
    boolean good = false;
    boolean bad = false;
    private static final String MY_BACKUP_FILE = "MY_BACKUP_FILE";
    private static final String MY_BACKUP_FILE_NAME = "MY_BACKUP_FILE_NAME";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        list2 = new ArrayList<String>();
        list = new ArrayList<String>();
        list.add("1");
        list.add("2");
        list.add("3");
        bien = findViewById(R.id.bien);
        faux = findViewById(R.id.faux);
        replay = findViewById(R.id.replay);
        like = findViewById(R.id.like);
        dislike = findViewById(R.id.dislike);
        carer = findViewById(R.id.carer);
        traingleb = findViewById(R.id.triangle);
        rondb = findViewById(R.id.rond);
        square = findViewById(R.id.square);
        triangle = findViewById(R.id.triangle2);
        rond = findViewById(R.id.rond2);
        haut = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.haut);
        countdown_animation = findViewById(R.id.countdown_animation);
        countdown_animation.addAnimatorListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animator) {
                Log.e("Animation:","start");
                countdown_animation.setVisibility(View.VISIBLE);
            }
            @Override
            public void onAnimationEnd(Animator animator) {
                Log.e("Animation:","end");
                countdown_animation.setVisibility(View.GONE);
                state = true ;
            }
            @Override
            public void onAnimationCancel(Animator animator) {
            }
            @Override
            public void onAnimationRepeat(Animator animator) {
            }
        });
        int delay = 1; // 1000 milliseconds == 1 second
        to_exec =
                new Runnable() {
                    public void run() {
                        handler.postDelayed(this, delay);
                        afichage();
                        match();
                        replay();
                    }
                };
        handler.postDelayed(to_exec, delay);
    }

    private void saveScore() {
        getSharedPreferences(MY_BACKUP_FILE, MODE_PRIVATE)
                .edit()
                .putString(MY_BACKUP_FILE_NAME, score)
                .apply();
    }

    public void afichage(){
        if(state == true){
            int max = list.size();
            if(i<max){
                String x = list.get(i);
                if(compteur < 800) {
                    if (x == "1") {
                        square.setVisibility(View.VISIBLE);
                        triangle.setVisibility(View.INVISIBLE);
                        rond.setVisibility(View.INVISIBLE);
                        compteur++;
                    }
                    if (x == "2") {
                        triangle.setVisibility(View.VISIBLE);
                        square.setVisibility(View.INVISIBLE);
                        rond.setVisibility(View.INVISIBLE);
                        compteur++;
                    }
                    if (x == "3") {
                        rond.setVisibility(View.VISIBLE);
                        triangle.setVisibility(View.INVISIBLE);
                        square.setVisibility(View.INVISIBLE);
                        compteur++;
                    }
                }
                if(compteur >= 800){
                    triangle.setVisibility(View.INVISIBLE);
                    square.setVisibility(View.INVISIBLE);
                    rond.setVisibility(View.INVISIBLE);
                    compteur++;
                }
                if (compteur == 1000){
                    i++;
                    compteur = 0;
                }
            }
            else{
                clickstate = true;
            }
        }
    }
    public void match(){
        if(clickstate == true){
            int max = list.size();
            int max2 = list2.size();
            if(max == max2){
                if(list.equals(list2)) {
                    like.setVisibility(View.VISIBLE);
                    rst++;
                    good = true;
                }
                else{
                    dislike.setVisibility(View.VISIBLE);
                    bad = true;
                }
                clickstate = false;
            }
        }
    }

    public void replay(){
        if(good == true){
            if(compteur2 != 1000){
                compteur2++;
            }
            if(compteur2 == 1000){
                random = ThreadLocalRandom.current().nextInt(1, 4);
                good = false;
                like.setVisibility(View.INVISIBLE);
                bien.setVisibility(View.INVISIBLE);
                faux.setVisibility(View.INVISIBLE);
                list2.clear();
                if(random == 1){
                    list.add("1");
                }
                if(random == 2){
                    list.add("2");
                }
                if(random == 3){
                    list.add("3");
                }
                i = 0;
                compteur2 = 0;
            }
        }
        if(bad == true){
            if(compteur2 < 1000){
                compteur2++;
            }
            if(compteur2 == 1000){
                dislike.setVisibility(View.INVISIBLE);
                String best = getSharedPreferences(MY_BACKUP_FILE,MODE_PRIVATE).getString(MY_BACKUP_FILE_NAME,"unknown");
                if(best == "unknown"){
                    best = "2";
                }
                int besti = Integer.parseInt(best);
                if(besti < rst){
                    String temp = String.valueOf(rst);
                    score = temp;
                    saveScore();
                    bien.setVisibility(View.VISIBLE);
                }
                else{
                    faux.setVisibility(View.VISIBLE);
                }
                rst = 2;
                replay.setVisibility(View.VISIBLE);
            }
        }
    }

    public void add1(View view) {
        if(clickstate == true){
            list2.add("1");
        }
    }

    public void add2(View view) {
        if(clickstate == true){
            list2.add("2");
        }
    }

    public void add3(View view) {
        if(clickstate == true){
            list2.add("3");
        }
    }

    public void returnH(View view) {
        bad = false;
        list2.clear();
        list.clear();
        i = 0;
        compteur2 = 0;
        Intent MainActivityIntent = new Intent(MainActivity2.this,MainActivity.class);
        startActivity(MainActivityIntent);
    }
}