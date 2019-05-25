package com.azitlab.cordova.plugin.badge;

import android.app.NotificationManager;
import android.content.Context;
import android.os.Bundle;
import android.service.notification.StatusBarNotification;

import org.apache.cordova.CordovaPlugin;
import org.apache.cordova.CallbackContext;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * This class echoes a string called from JavaScript.
 */
public class Badge extends CordovaPlugin {

    @Override
    public boolean execute(String action, JSONArray args, CallbackContext callbackContext) throws JSONException {
        if (action.equals("getBadgeCount")) {
            Context context = this.cordova.getActivity();
            NotificationManager notificationManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
            int count = 0;
            if (notificationManager.getActiveNotifications() != null) {
                count = notificationManager.getActiveNotifications().length;
            }
            callbackContext.success(count);
            return true;
        }

        if (action.equals("getUnreadNotifications")) {
            Context context = this.cordova.getActivity();
            NotificationManager notificationManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
            List<JSONObject> notifications = new ArrayList<>();
            StatusBarNotification[] listStatusBar = notificationManager.getActiveNotifications();
            for (StatusBarNotification statusBar : listStatusBar) {
                Bundle bundle = statusBar.getNotification().extras;
                JSONObject result = new JSONObject();
                result.put("id", statusBar.getId());
                result.put("tag", statusBar.getTag());
                result.put("time", statusBar.getPostTime());
                result.put("title", bundle.getString("android.title"));
                result.put("text", bundle.getString("android.text"));
                notifications.add(result);
            }

            callbackContext.success(new JSONArray(notifications));
            return true;
        }


        return false;
    }
}
