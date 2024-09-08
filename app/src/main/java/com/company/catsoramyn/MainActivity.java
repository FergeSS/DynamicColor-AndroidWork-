package com.company.catsoramyn;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;

import com.company.catsoramyn.databinding.ActivityMainBinding;

public class MainActivity extends Property {
    private ActivityMainBinding binding;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        checkOpenActivities();
    }

    public void checkOpenActivities() {
        if (GameActivity.active) {
            Intent intent = new Intent(this, GameActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
            startActivity(intent);
            finish();
            return;
        }
        if (SettingsActivity.active) {
            Intent intent = new Intent(this, SettingsActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
            startActivity(intent);
            finish();
            return;
        }
        if (PolicyActivity.active) {
            Intent intent = new Intent(this, PolicyActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
            startActivity(intent);
            finish();
            return;
        }
    }

    public void settings(View v) {
        Settings.action(MainActivity.this);
        Intent intent = new Intent(this, SettingsActivity.class);
        if (SettingsActivity.active) {
            intent.setFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
        }
        startActivity(intent);
    }

    public void play(View v) {
        Settings.action(MainActivity.this);
        Intent intent = new Intent(this, GameActivity.class);
        if (GameActivity.active) {
            intent.setFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
        }
        startActivity(intent);
    }

    public void policy(View v) {
        Settings.action(MainActivity.this);
        Intent intent = new Intent(this, PolicyActivity.class);
        if (PolicyActivity.active) {
            intent.setFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
        }
        startActivity(intent);
    }

    public void close(View v) {
        Settings.action(MainActivity.this);
        finishAffinity();
    }

}