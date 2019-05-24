package com.azitlab.cordova.plugin.badge;

import android.app.NotificationManager;
import android.content.Context;

import org.apache.cordova.CordovaPlugin;
import org.apache.cordova.CallbackContext;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * This class echoes a string called from JavaScript.
 */
public class Badge extends CordovaPlugin {

    @Override
    public boolean execute(String action, JSONArray args, CallbackContext callbackContext) throws JSONException {
        if (action.equals("getBadge")) {
            String message = args.getString(0);
            this.getBadge(message, callbackContext);
            return true;
        }
        return false;
    }

    private void getBadge(String message, CallbackContext callbackContext) {
        int count = this.getCountBadge(this.cordova.getActivity());
        if (message != null && message.length() > 0) {
            callbackContext.success(count);
        } else {
            callbackContext.error("Expected one non-empty string argument.");
        }
    }

    private int getCountBadge(Context context) {
        NotificationManager notificationManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);

        int count = 0;
        if (notificationManager.getActiveNotifications() != null) {
            count = notificationManager.getActiveNotifications().length;
        }
        return count;
    }
}
