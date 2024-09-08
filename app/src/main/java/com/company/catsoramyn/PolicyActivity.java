package com.company.catsoramyn;

import android.os.Bundle;
import android.view.View;

import com.company.catsoramyn.databinding.ActivityPolicyBinding;

public class PolicyActivity extends Property {
    private ActivityPolicyBinding binding;
    public static boolean active = false;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityPolicyBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.policy.loadUrl("https://telegra.ph/Fun-Laundry-08-30");

        active = true;
    }

    public void home(View v) {
        Settings.action(PolicyActivity.this);
        finish();
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        active = false;
    }
}
