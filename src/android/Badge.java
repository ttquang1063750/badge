package com.azitlab.cordova.plugin.badge;

import android.app.Notification;
import android.app.NotificationManager;
import android.content.Context;
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
            List<Notification> notifications = new ArrayList<>();
            StatusBarNotification[] listStatusBar = notificationManager.getActiveNotifications();
            for (StatusBarNotification statusBar : listStatusBar) {
                notifications.add(statusBar.getNotification());
            }

            callbackContext.success(new JSONArray(notifications));
            return true;
        }


        return false;
    }
}
