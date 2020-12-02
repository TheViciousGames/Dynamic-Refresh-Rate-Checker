package com.theviciousgames.dynamicrefreshratechecker.utils;

import android.app.Activity;
import android.content.Context;
import android.os.Build;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.WindowManager;

public class Tools{
    public static String getRefreshRate(Activity activity)
    {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R)
        {
            float rate=activity.getDisplay().getRefreshRate();
            return (int)rate+"";
        }
        else
        {
            Display display=((WindowManager)activity.getSystemService(Context.WINDOW_SERVICE)).getDefaultDisplay();
            return (int)display.getRefreshRate()+"";
        }


    }

}