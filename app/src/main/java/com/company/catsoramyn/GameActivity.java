package com.company.catsoramyn;

import android.app.Dialog;
import android.os.Bundle;
import android.os.SystemClock;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Chronometer;

import com.company.catsoramyn.databinding.ActivityGameBinding;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class GameActivity extends Property {
    private ActivityGameBinding binding;
    public static boolean active = false;
    Dialog dialog;
    Integer[] colors = {R.drawable.green, R.drawable.red, R.drawable.blue, R.drawable.pink, R.drawable.white, R.drawable.black};
    List<Integer> colorsList = Arrays.asList(colors);
    int correctAnswer;
    long time = -10000;

    @Override
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);

        binding = ActivityGameBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        dialogSettings();
        active = true;
        binding.timer.setCountDown(true);
        binding.timer.setOnChronometerTickListener(new Chronometer.OnChronometerTickListener() {
            @Override
            public void onChronometerTick(Chronometer chronometer) {
                long elapsedMillis = SystemClock.elapsedRealtime() - binding.timer.getBase();

                if (elapsedMillis > 0) {
                    dialog.show();
                    binding.timer.stop();
                }
            }
        });
        setNewLevel();
    }

    private void setNewLevel() {
        binding.timer.stop();
        binding.timer.setBase(SystemClock.elapsedRealtime() + 11000);
        binding.timer.start();
        Collections.shuffle(colorsList);
        correctAnswer = new Random().nextInt(6);
        binding.upLine.setImageResource(colorsList.get(correctAnswer));
        binding.downLine.setImageResource(colorsList.get(correctAnswer));
        binding.but1.setBackgroundResource(colorsList.get(0));
        binding.but2.setBackgroundResource(colorsList.get(1));
        binding.but3.setBackgroundResource(colorsList.get(2));
        binding.but4.setBackgroundResource(colorsList.get(3));
        binding.but5.setBackgroundResource(colorsList.get(4));
        binding.but6.setBackgroundResource(colorsList.get(5));
    }

    public void gameButton1(View v) {
        Settings.action(GameActivity.this);
        if (correctAnswer != 0) {
            dialog.show();
            return;
        }
        setNewLevel();
    }

    public void gameButton2(View v) {
        Settings.action(GameActivity.this);
        if (correctAnswer != 1) {
            dialog.show();
            return;
        }
        setNewLevel();
    }

    public void gameButton3(View v) {
        Settings.action(GameActivity.this);
        if (correctAnswer != 2) {
            dialog.show();
            return;
        }
        setNewLevel();
    }

    public void gameButton4(View v) {
        Settings.action(GameActivity.this);
        if (correctAnswer != 3) {
            dialog.show();
            return;
        }
        setNewLevel();
    }

    public void gameButton5(View v) {
        Settings.action(GameActivity.this);
        if (correctAnswer != 4) {
            dialog.show();
            return;
        }
        setNewLevel();
    }

    public void gameButton6(View v) {
        Settings.action(GameActivity.this);
        if (correctAnswer != 5) {
            dialog.show();
            return;
        }
        setNewLevel();
    }

    public void home(View v) {
        Settings.action(GameActivity.this);
        finish();
    }

    public void replay(View v) {
        Settings.action(GameActivity.this);
        dialog.dismiss();
        setNewLevel();
    }

    private void dialogSettings() {
        dialog = new Dialog(GameActivity.this);

        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.getWindow().getDecorView().setSystemUiVisibility(
                View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                        | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                        | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                        | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                        | View.SYSTEM_UI_FLAG_FULLSCREEN
                        | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY);
        dialog.getWindow().setBackgroundDrawableResource(android.R.color.transparent);
        dialog.setCanceledOnTouchOutside(false);
        dialog.setCancelable(false);
        WindowManager.LayoutParams wlp = dialog.getWindow().getAttributes();
        wlp.dimAmount = 0.7f;
        dialog.getWindow().setAttributes(wlp);
        dialog.setContentView(R.layout.dialog);
    }

    @Override
    public void onPause() {
        super.onPause();
        binding.timer.stop();
        time = SystemClock.elapsedRealtime() - binding.timer.getBase();
    }

    @Override
    public void onResume() {
        super.onResume();
        if (dialog != null && !dialog.isShowing()) {
            binding.timer.setBase(SystemClock.elapsedRealtime() - time);
            binding.timer.start();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        active = false;
        if (dialog != null && dialog.isShowing()) {
            dialog.dismiss();
        }
    }

}
