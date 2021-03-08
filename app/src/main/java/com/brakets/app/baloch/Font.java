package com.brakets.app.baloch;

import android.app.Application;

import uk.co.chrisjenx.calligraphy.CalligraphyConfig;

/**
 * Created by MoHseN.DeaDSpacE on 20/08/2016.
 */
public class Font extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        CalligraphyConfig.initDefault(new CalligraphyConfig.Builder()
                .setDefaultFontPath("font.ttf")
                .setFontAttrId(R.attr.fontPath)
                .build()
        );
    }
}
