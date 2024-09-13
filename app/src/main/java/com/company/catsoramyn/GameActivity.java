package com.company.catsoramyn;

import android.app.Dialog;
import android.os.Bundle;
import android.os.Handler;
import android.os.SystemClock;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Chronometer;

import com.company.catsoramyn.databinding.ActivityGameBinding;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Locale;
import java.util.Random;

public class GameActivity extends Property {
    private ActivityGameBinding binding;
    public static boolean active = false;
    Dialog dialog;
    Integer[] buttonColors = {R.drawable.color_button, R.drawable.color_button__1_, R.drawable.color_button__2_, R.drawable.color_button__3_, R.drawable.color_button__4_, R.drawable.color_button__5_,
            R.drawable.color_button__8_, R.drawable.color_button__9_, R.drawable.color_button__10_, R.drawable.color_button__11_, R.drawable.color_button__12_, R.drawable.color_button__13_, R.drawable.color_button__14_, R.drawable.color_button__15_, R.drawable.color_button__16_,
            R.drawable.color_button__17_, R.drawable.color_button__18_, R.drawable.color_button__19_, R.drawable.color_button__20_, R.drawable.color_button__21_, R.drawable.color_button__22_, R.drawable.color_button__23_};
    List<Integer> colorsList = Arrays.asList(buttonColors);
    int correctAnswer;
    long time = -10100;
    int score = -1;
    boolean clickable = true;

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

                int seconds = (int) (-elapsedMillis / 1000);
                chronometer.setText(String.format(Locale.getDefault(), "%d", seconds));

                if (elapsedMillis > 0) {
                    dialog.show();
                    binding.timer.stop();
                }
            }
        });
        setNewLevel(false);
    }

    private void setNewLevel(boolean notFirst) {
        if (notFirst) {
            clickable = false;
            if (correctAnswer == 0) {
                binding.but1Box.setImageResource(R.drawable.correct);
                binding.but1Box.setVisibility(View.VISIBLE);
            }
            else if (correctAnswer == 1) {
                binding.but2Box.setImageResource(R.drawable.correct);
                binding.but2Box.setVisibility(View.VISIBLE);
            }
            else if (correctAnswer == 2) {
                binding.but3Box.setImageResource(R.drawable.correct);
                binding.but3Box.setVisibility(View.VISIBLE);
            }
            else if (correctAnswer == 3) {
                binding.but4Box.setImageResource(R.drawable.correct);
                binding.but4Box.setVisibility(View.VISIBLE);
            }
            else if (correctAnswer == 4) {
                binding.but5Box.setImageResource(R.drawable.correct);
                binding.but5Box.setVisibility(View.VISIBLE);
            }
            else if (correctAnswer == 5) {
                binding.but6Box.setImageResource(R.drawable.correct);
                binding.but6Box.setVisibility(View.VISIBLE);
            }
            ++score;
            binding.score.setText(String.valueOf(score));
            binding.timer.stop();


            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    if (correctAnswer == 0) {
                        binding.but1Box.setVisibility(View.INVISIBLE);
                    }
                    else if (correctAnswer == 1) {
                        binding.but2Box.setVisibility(View.INVISIBLE);
                    }
                    else if (correctAnswer == 2) {
                        binding.but3Box.setVisibility(View.INVISIBLE);
                    }
                    else if (correctAnswer == 3) {
                        binding.but4Box.setVisibility(View.INVISIBLE);
                    }
                    else if (correctAnswer == 4) {
                        binding.but5Box.setVisibility(View.INVISIBLE);
                    }
                    else if (correctAnswer == 5) {
                        binding.but6Box.setVisibility(View.INVISIBLE);
                    }
                    binding.timer.setBase(SystemClock.elapsedRealtime() + 10010);
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

                    clickable = true;
                }
            }, 1000);
        } else {

            ++score;
            binding.score.setText(String.valueOf(score));
            binding.timer.stop();

            binding.timer.setBase(SystemClock.elapsedRealtime() + 10010);
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

    }

    public void gameButton1(View v) {
        if (!clickable) { return; }
        Settings.action(GameActivity.this);
        if (correctAnswer != 0) {
            binding.timer.stop();
            binding.but1Box.setVisibility(View.VISIBLE);
            binding.but1Box.setImageResource(R.drawable.incorrect);
            clickable = false;

            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    clickable = true;
                    binding.but1Box.setVisibility(View.INVISIBLE);
                    dialog.show();
                }
            }, 1000);

            return;
        }
        setNewLevel(true);
    }

    public void gameButton2(View v) {
        if (!clickable) { return; }
        Settings.action(GameActivity.this);
        if (correctAnswer != 1) {
            binding.timer.stop();
            binding.but2Box.setVisibility(View.VISIBLE);
            binding.but2Box.setImageResource(R.drawable.incorrect);
            clickable = false;

            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    clickable = true;
                    binding.but2Box.setVisibility(View.INVISIBLE);
                    dialog.show();
                }
            }, 1000);

            return;
        }
        setNewLevel(true);
    }

    public void gameButton3(View v) {
        if (!clickable) { return; }
        Settings.action(GameActivity.this);
        if (correctAnswer != 2) {
            binding.timer.stop();
            binding.but3Box.setVisibility(View.VISIBLE);
            binding.but3Box.setImageResource(R.drawable.incorrect);
            clickable = false;

            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    clickable = true;
                    binding.but3Box.setVisibility(View.INVISIBLE);
                    dialog.show();
                }
            }, 1000);

            return;
        }
        setNewLevel(true);
    }

    public void gameButton4(View v) {
        if (!clickable) { return; }
        Settings.action(GameActivity.this);
        if (correctAnswer != 3) {
            binding.timer.stop();
            binding.but4Box.setVisibility(View.VISIBLE);
            binding.but4Box.setImageResource(R.drawable.incorrect);
            clickable = false;

            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    clickable = true;
                    binding.but4Box.setVisibility(View.INVISIBLE);
                    dialog.show();
                }
            }, 1000);

            return;
        }
        setNewLevel(true);
    }

    public void gameButton5(View v) {
        if (!clickable) { return; }
        Settings.action(GameActivity.this);
        if (correctAnswer != 4) {
            binding.timer.stop();
            binding.but5Box.setVisibility(View.VISIBLE);
            binding.but5Box.setImageResource(R.drawable.incorrect);
            clickable = false;

            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    clickable = true;
                    binding.but5Box.setVisibility(View.INVISIBLE);
                    dialog.show();
                }
            }, 1000);

            return;
        }
        setNewLevel(true);
    }

    public void gameButton6(View v) {
        if (!clickable) { return; }
        Settings.action(GameActivity.this);
        if (correctAnswer != 5) {
            binding.timer.stop();
            binding.but6Box.setVisibility(View.VISIBLE);
            binding.but6Box.setImageResource(R.drawable.incorrect);
            clickable = false;

            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    clickable = true;
                    binding.but6Box.setVisibility(View.INVISIBLE);
                    dialog.show();
                }
            }, 1000);

            return;
        }
        setNewLevel(true);
    }

    public void home(View v) {
        Settings.action(GameActivity.this);
        finish();
    }

    public void replay(View v) {
        Settings.action(GameActivity.this);
        score = -1;
        dialog.dismiss();
        setNewLevel(false);
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
        wlp.dimAmount = 0.48f;
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
