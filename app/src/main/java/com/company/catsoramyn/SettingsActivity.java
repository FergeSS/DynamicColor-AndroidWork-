package com.company.catsoramyn;

import static com.company.catsoramyn.Settings.action;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.CompoundButton;

import com.company.catsoramyn.databinding.ActivitySettingsBinding;

public class SettingsActivity extends Property {
    private ActivitySettingsBinding binding;
    public static boolean active = false;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivitySettingsBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        settingsSettings();
        active = true;
    }

    public void home(View v) {
        action(SettingsActivity.this);
        finish();
    }


    public void settingsSettings() {
        SharedPreferences sharedPreferences = getSharedPreferences("settings", MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();

        binding.soundSwitch.setChecked(sharedPreferences.getBoolean("sound_enabled", true));

        binding.soundSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                action(SettingsActivity.this);
                editor.putBoolean("sound_enabled", isChecked);
                editor.apply();
            }
        });

        binding.vibroSwitch.setChecked(sharedPreferences.getBoolean("vibro_enabled", true));

        binding.vibroSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                action(SettingsActivity.this);
                editor.putBoolean("vibro_enabled", isChecked);
                editor.apply();
            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        active = false;
    }
}
