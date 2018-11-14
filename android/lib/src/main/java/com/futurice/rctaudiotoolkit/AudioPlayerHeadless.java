package com.futurice.rctaudiotoolkit;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.content.LocalBroadcastManager;
import com.facebook.react.HeadlessJsTaskService;
import com.facebook.react.bridge.Arguments;
import com.facebook.react.jstasks.HeadlessJsTaskConfig;
import javax.annotation.Nullable;

public class AudioPlayerHeadless extends HeadlessJsTaskService {
  @Nullable
  @Override
  protected HeadlessJsTaskConfig getTaskConfig(Intent intent) {
      return new HeadlessJsTaskConfig("AudioPlayerHeadless", Arguments.createMap(), 0, true);
  }

  public void emit(String event, Bundle data) {
    Intent intent = new Intent("com.futurice.rctaudiotoolkit.event");

    intent.putExtra("event", event);
    if(data != null) intent.putExtra("data", data);

    LocalBroadcastManager.getInstance(this).sendBroadcast(intent);
  }
}