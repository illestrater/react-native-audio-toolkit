package com.futurice.rctaudiotoolkit;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import com.facebook.react.bridge.Arguments;
import com.facebook.react.bridge.ReactContext;
import com.facebook.react.bridge.WritableMap;
import com.facebook.react.modules.core.DeviceEventManagerModule.RCTDeviceEventEmitter;

/**
 * @author Guichaguri
 */
public class AudioPlayerEvents extends BroadcastReceiver {
    // Playback Events
    public static final String PLAYBACK_ENDED = "ended";

    private final ReactContext reactContext;

    public AudioPlayerEvents(ReactContext reactContext) {
        this.reactContext = reactContext;
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        String event = intent.getStringExtra("event");
        Bundle data = intent.getBundleExtra("data");

        WritableMap map = data != null ? Arguments.fromBundle(data) : null;

        reactContext.getJSModule(RCTDeviceEventEmitter.class).emit(event, map);
    }

}