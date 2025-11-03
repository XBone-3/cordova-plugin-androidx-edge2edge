package com.xbone.cordova.edge2edge;

import org.apache.cordova.*;

import android.os.Build;
import android.view.View;
import android.view.Window;
import android.view.WindowInsetsController;


/**
 * This class echoes a string called from JavaScript.
 */
public class EdgeToEdge extends CordovaPlugin {

    @Override
    public boolean execute(String action, CordovaArgs args, CallbackContext callbackContext) throws JSONException {
        if (action.equals("enable")) {
            enableEdgeToEdge();
            callbackContext.success("Edge-to-edge enabled");
            return true;
        } else if (action.equals("disable")) {
            disableEdgeToEdge();
            callbackContext.success("Edge-to-edge disabled");
            return true;
        }
        return false;
    }

    private void enableEdgeToEdge() {
        cordova.getActivity().runOnUiThread(() -> {
            Window window = cordova.getActivity().getWindow();

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
                window.setDecorFitsSystemWindows(false);
                WindowInsetsController insetsController = window.getInsetsController();
                if (insetsController != null) {
                    insetsController.setSystemBarsBehavior(
                        WindowInsetsController.BEHAVIOR_SHOW_TRANSIENT_BARS_BY_SWIPE
                    );
                }
            } else {
                View decorView = window.getDecorView();
                decorView.setSystemUiVisibility(
                    View.SYSTEM_UI_FLAG_LAYOUT_STABLE |
                    View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN |
                    View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                );
            }
        });
    }

    private void disableEdgeToEdge() {
        cordova.getActivity().runOnUiThread(() -> {
            Window window = cordova.getActivity().getWindow();

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
                window.setDecorFitsSystemWindows(true);
            } else {
                View decorView = window.getDecorView();
                decorView.setSystemUiVisibility(View.SYSTEM_UI_FLAG_VISIBLE);
            }
        });
    }
}
