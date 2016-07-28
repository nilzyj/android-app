package com.zyj.myapplication;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;


public class MainActivity extends Activity {
    private Intent intent = new Intent("com.angel.Android.MUSIC");
    private Button btn_start, btn_end;
    private ImageView img_num, img_user, img_user1;
    private AnimationDrawable anim_num;
    private int count = 0;
    private int pos_x[] = {1050, 880, 762, 648, 552, 434, 320, 210, 210, 210, 210, 94, 94, 94, 200, 306, 306, 420, 530, 640, 750, 750, 750, 860, 976, 1086, 1086, 1086, 1086, 1086};
    private int pos_y[] = {480, 540, 540, 540, 540, 540, 540, 540, 456, 371, 285, 285, 192, 108, 108, 108, 194, 194, 194, 194, 194, 108, 18, 18, 18, 18, 104, 194, 282, 370};
    private int pos = 0, pos1 = 0;
    private int u = 0, end = 0, stop = 0;
    private TextView text;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btn_start = (Button) findViewById(R.id.btn);
        btn_end = (Button) findViewById(R.id.btn_end);
        img_num = (ImageView) findViewById(R.id.img_num);
        img_user = (ImageView) findViewById(R.id.user);
        img_user1 = (ImageView) findViewById(R.id.user1);
        text = (TextView) findViewById(R.id.text);
        startService(intent);
        anim_num = (AnimationDrawable) img_num.getBackground();
        btn_start.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View arg0) {
                img_num.setImageResource(R.drawable.num);
                anim_num.start();
            }
        });

        btn_end.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View arg0) {
                if (anim_num.isRunning()) {
                    anim_num.stop();
                    count = (int) (Math.random() * 6 + 1);
                    switch (count) {
                        case 1:
                            img_num.setImageResource(R.drawable.group1);
                            break;
                        case 2:
                            img_num.setImageResource(R.drawable.group3);
                            break;
                        case 3:
                            img_num.setImageResource(R.drawable.group2);
                            break;
                        case 4:
                            img_num.setImageResource(R.drawable.group4);
                            break;
                        case 5:
                            img_num.setImageResource(R.drawable.group5);
                            break;
                        case 6:
                            img_num.setImageResource(R.drawable.group6);
                            break;
                    }
                    if (stop == 0)
                        u = 1 - u;
                    else
                        stop--;
                    if (u == 0) {
                        move1();
                    } else {
                        move2();
                    }
                }
            }
        });
    }

    public void move1() {
        pos += count;
        text.setText(pos + "pos");
        if (pos > 29)
            pos = 58 - pos;
        switch (pos) {
            case 3:
                pos += 3;
                break;
            case 8:
                fun("瘦子");
                end = 1;
                break;
            case 10:
                pos = 0;
                break;
            case 13:
                pos = 13;
                stop = 2;
                u = 1;
                break;
            case 18:
                pos += 6;
                break;
            case 22:
                pos += 1;
                break;
            case 25:
                pos -= 5;
                break;
            case 26:
                pos = 29;
                break;
        }

        if (pos == 29) {
            fun1("瘦子");
            end = 1;
        }
        if (end == 1) {
            pos1 = 0;
            pos = 0;
            changePlace(img_user, pos, u);
            changePlace(img_user1, pos1, u);
            end--;
        } else
            changePlace(img_user, pos, u);
    }

    public void move2() {
        pos1 += count;
        text.setText(pos1 + "pos1");
        if (pos1 > 29)
            pos1 = 58 - pos1;

        switch (pos1) {
            case 3:
                pos1 += 3;
                break;
            case 8:
                fun("胖子");
                end = 1;
                break;
            case 10:
                pos1 = 0;
                break;
            case 13:
                pos1 = 13;
                stop = 2;
                u = 0;
                break;
            case 18:
                pos1 += 6;
                break;
            case 22:
                pos1 += 1;
                break;
            case 25:
                pos1 -= 5;
                break;
            case 26:
                pos1 = 29;
                break;
        }
        if (pos1 == 29) {
            fun1("胖子");
            end = 1;
        }
        if (end == 1) {
            pos1 = 0;
            pos = 0;
            changePlace(img_user, pos, u);
            changePlace(img_user1, pos1, u);
            end--;
        } else
            changePlace(img_user1, pos1, u);
    }

    public void changePlace(ImageView img, int pos, int u) {
        RelativeLayout.LayoutParams lp = new RelativeLayout.LayoutParams(img.getLayoutParams());

        if (u == 0)
            lp.setMargins(pos_x[pos] + 20, pos_y[pos], 0, 0);
        else
            lp.setMargins(pos_x[pos] - 20, pos_y[pos], 0, 0);
        img.setLayoutParams(lp);
    }

    public void fun(String a) {
        new AlertDialog.Builder(MainActivity.this).setTitle("游戏结束")
                .setMessage(a + "出局")
                .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface arg0, int arg1) {

                    }
                }).show();
    }

    public void fun1(String a) {
        new AlertDialog.Builder(MainActivity.this).setTitle("游戏结束")
                .setMessage(a + "win")
                .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface arg0, int arg1) {

                    }
                }).show();
    }
}
