package com.theviciousgames.dynamicrefreshratechecker.services;

import android.annotation.SuppressLint;
import android.app.Service;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Build.VERSION;
import android.os.IBinder;
import android.view.Gravity;
import android.view.WindowManager;
import android.view.WindowManager.LayoutParams;
import android.widget.TextView;

import androidx.core.content.ContextCompat;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;

import com.theviciousgames.dynamicrefreshratechecker.R;

public final class OverlayService extends Service {
    private static final String a = OverlayService.class.getSimpleName();
    private static String textToPut;
    private TextView refreshRateTextView;
    private final BroadcastReceiver d = new BroadcastReceiver() {
        public void onReceive(Context context, Intent intent) {
            OverlayService.this.a(intent);
        }
    };

    public OverlayService() {
    }

    public static void setText(Context context_p, String text_p) {
        Intent intent = new Intent("DynamicRefreshRate");
        intent.putExtra("DynamicRefreshRate.extra", text_p);
        LocalBroadcastManager.getInstance(context_p).sendBroadcast(intent);
        textToPut = text_p;
    }

    public int onStartCommand(Intent intent, int flags, int startId) {
        if (intent != null) {
            this.a(intent);
        }

        return super.onStartCommand(intent, flags, startId);
    }

    public void onCreate() {
        super.onCreate();
        //int var1 = this.getResources().getIdentifier("red", "color", this.getPackageName());
        this.refreshRateTextView = new TextView(this);
        this.refreshRateTextView.setTextColor(ContextCompat.getColor(this, R.color.coolYellow));
        this.refreshRateTextView.setTextSize(13);
        this.refreshRateTextView.setText(textToPut);
        short var2;
        if (VERSION.SDK_INT >= 26) {
            var2 = 2038;
        } else {
            var2 = 2006;
        }

        LayoutParams layoutParams = new LayoutParams(-2, -2, var2, 24, -3);
        layoutParams.gravity = Gravity.TOP;
        layoutParams.setTitle("Text Overlay");
        @SuppressLint("WrongConstant") WindowManager var4 = (WindowManager) this.getSystemService("window");
        if (var4 != null) {
            var4.addView(this.refreshRateTextView, layoutParams);
        }

        LocalBroadcastManager.getInstance(this).registerReceiver(this.d, new IntentFilter("DynamicRefreshRate"));
    }

    public void onDestroy() {
        super.onDestroy();
        LocalBroadcastManager.getInstance(this).unregisterReceiver(this.d);
       // @SuppressLint("WrongConstant") WindowManager var1 = (WindowManager) this.getSystemService("window");
        this.refreshRateTextView = null;
    }

    public IBinder onBind(Intent intent) {
        return null;
    }

    private void a(Intent var1) {
        String var2 = var1.getStringExtra("DynamicRefreshRate.extra");
        if (var2 != null) {
            this.refreshRateTextView.setText(var2);
        }

    }
}
