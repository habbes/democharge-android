package xyz.habbes.democharge.activities.helpers;

import android.content.Context;
import android.widget.Toast;

/**
 * Created by Habbes on 12/10/2016.
 * helper for displaying toast messages in activities
 */
public class ToastMessage {

    /**
     * show a toast message
     * @param context
     * @param message message to show
     * @author Habbes
     * @added 12.10.2016
     * @version 1
     */
    public static void show(Context context, String message){
        Toast.makeText(context, message, Toast.LENGTH_LONG).show();
    }

    /**
     * show an error message in a toast
     * @param context
     * @param message error message to display
     * @author Habbes
     * @added 12.10.2016
     * @version 1
     */
    public static void showError(Context context, String message){
        show(context, "Error: " + message);
    }
}
