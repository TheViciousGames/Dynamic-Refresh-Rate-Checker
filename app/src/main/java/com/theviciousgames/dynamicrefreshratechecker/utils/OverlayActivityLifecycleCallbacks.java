package com.theviciousgames.dynamicrefreshratechecker.utils;

import android.app.Activity;
import android.app.Application;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.Settings;
import android.util.Log;

import com.theviciousgames.dynamicrefreshratechecker.services.OverlayService;


public final class OverlayActivityLifecycleCallbacks implements Application.ActivityLifecycleCallbacks {
    //private static final String a = OverlayActivityLifecycleCallbacks.class.getSimpleName();
    private static int code = 5469;

    public OverlayActivityLifecycleCallbacks() {
    }

    public void onActivityCreated(Activity activity, Bundle savedInstanceState) {
        if (Build.VERSION.SDK_INT >= 23 && !Settings.canDrawOverlays(activity)) {
            Intent permissionIntent = new Intent("android.settings.action.MANAGE_OVERLAY_PERMISSION", Uri.parse("package:" + activity.getPackageName()));
            activity.startActivityForResult(permissionIntent,code );
        }

    }

    public void onActivityStarted(Activity activity) {
    }

    public void onActivityResumed(Activity activity) {
        if (Build.VERSION.SDK_INT >= 23) {
            if (Settings.canDrawOverlays(activity)) {
                activity.startService(new Intent(activity, OverlayService.class));
            }
        } else {
            activity.startService(new Intent(activity, OverlayService.class));
        }

    }

    public void onActivityPaused(Activity activity) {
        //activity.stopService(new Intent(activity, TextOverlayService.class));
    }

    public void onActivityStopped(Activity activity) {

    }

    public void onActivitySaveInstanceState(Activity activity, Bundle outState) {
    }

    public void onActivityDestroyed(Activity activity) {
    }
}
