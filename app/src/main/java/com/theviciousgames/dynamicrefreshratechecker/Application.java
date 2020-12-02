package com.theviciousgames.dynamicrefreshratechecker;

import android.content.Intent;

import com.theviciousgames.dynamicrefreshratechecker.services.OverlayService;
import com.theviciousgames.dynamicrefreshratechecker.utils.OverlayActivityLifecycleCallbacks;


public final class Application extends android.app.Application {
    @Override
    public void onCreate() {
        super.onCreate();
        registerActivityLifecycleCallbacks(new OverlayActivityLifecycleCallbacks());
        startService(new Intent(this, OverlayService.class));

    }
}
