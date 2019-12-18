


package com.vehiclemind.AndroidNotchStatusBar;

import android.annotation.TargetApi;
import android.app.Activity;
import android.os.Build;
import android.view.Display;
import android.view.DisplayCutout;
import android.view.View;
import android.view.Window;
import android.view.WindowInsets;
import android.view.WindowManager;

import org.apache.cordova.CallbackContext;
import org.apache.cordova.CordovaArgs;
import org.apache.cordova.CordovaInterface;
import org.apache.cordova.CordovaPlugin;
import org.apache.cordova.CordovaWebView;
import org.apache.cordova.LOG;
import org.apache.cordova.PluginResult;
import org.json.JSONException;
import java.util.Arrays;


public class AndroidNotchStatusBar extends CordovaPlugin {
    private static final String TAG = "AndroidNotchStatusBar";

    /**
     * Executes the request and returns PluginResult.
     *
     * @param action            The action to execute.
     * @param args              JSONArry of arguments for the plugin.
     * @param callbackContext   The callback id used when calling back into JavaScript.
     * @return                  True if the action was valid, false otherwise.
     */
    @Override
    public boolean execute(final String action, final CordovaArgs args, final CallbackContext callbackContext) throws JSONException {
        final Activity activity = this.cordova.getActivity();
        final Window window = activity.getWindow();
        float density = activity.getResources().getDisplayMetrics().density;

        if ("setLayout".equals("action")) {
            this.webView.getView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN);
            return true;
        }

        // Getting the statusbar height, in case we can’t find a notch (which has a 24dp default value)
        float statusBarHeight = 24.0 / activity.getResources().getDisplayMetrics().density;
        int resourceId = activity.getResources().getIdentifier("status_bar_height", "dimen", activity.getPackageName());
        if (resourceId > 0) {
            statusBarHeight = (float) activity.getResources().getDimensionPixelSize(resourceId);
        }   

        if(Build.VERSION.SDK_INT < 28) {
            // DisplayCutout is not available on api < 28
            callbackContext.sendPluginResult(new PluginResult(PluginResult.Status.OK, statusBarHeight));
            return true;
        }

        final WindowInsets insets = getInsets();
        final DisplayCutout cutout = insets.getDisplayCutout();

        if ("hasCutout".equals(action)) {
            callbackContext.sendPluginResult(new PluginResult(PluginResult.Status.OK, cutout != null));
            return true;
        }

        if ("getInsetsTop".equals(action)) {
            callbackContext.sendPluginResult(new PluginResult(PluginResult.Status.OK, cutout != null ? (cutout.getSafeInsetTop() / density) : 0));
            return true;
        }
        
        if ("getInsetsRight".equals(action)) {
            callbackContext.sendPluginResult(new PluginResult(PluginResult.Status.OK, cutout != null ? (cutout.getSafeInsetRight() / density) : 0));
            return true;
        }

        if ("getInsetsBottom".equals(action)) {
            callbackContext.sendPluginResult(new PluginResult(PluginResult.Status.OK, cutout != null ? (cutout.getSafeInsetBottom() / density) : 0));
            return true;
        }

        if ("getInsetsLeft".equals(action)) {
            callbackContext.sendPluginResult(new PluginResult(PluginResult.Status.OK, cutout != null ? (cutout.getSafeInsetLeft() / density) : 0));
            return true;
        }


        return false;
    }

    @TargetApi(23)
    private WindowInsets getInsets() {
        return this.webView.getView().getRootWindowInsets();
    }
}
