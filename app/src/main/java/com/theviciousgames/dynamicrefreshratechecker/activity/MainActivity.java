package com.theviciousgames.dynamicrefreshratechecker.activity;

import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.theviciousgames.dynamicrefreshratechecker.databinding.ActivityMainBinding;
import com.theviciousgames.dynamicrefreshratechecker.services.OverlayService;
import com.theviciousgames.dynamicrefreshratechecker.utils.Tools;

import java.util.Timer;
import java.util.TimerTask;


public final class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(declareVars());

    }

    protected View declareVars() {
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        buttonFunctions();
        updateRefreshRate();
        return binding.getRoot();
    }

    protected void buttonFunctions() {
        binding.exitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
                System.exit(0);
            }
        });
    }

    public void updateRefreshRate() {

        Timer timer = new Timer();
        timer.scheduleAtFixedRate(new TimerTask() {
            public void run() {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        OverlayService.setText(MainActivity.this, Tools.getRefreshRate(MainActivity.this));
                    }
                });
            }
        }, 0, 1000);
    }

}



