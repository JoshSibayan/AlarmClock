package comjoshsibayan.github.alarm;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

/**
 * Created by Josh on 8/30/2016.
 */
public class AlarmReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        Log.e("In receiver", "Yay!");

        // Fetch extra strings from MainActivity on button intent
        String fetch_string = intent.getExtras().getString("extra");
        Log.e("What is the key?", fetch_string);

        // Create intent
        Intent service_intent = new Intent(context, RingtonePlayingService.class);

        // Pass extra string from MainActivity to RingtonePlayingService
        service_intent.putExtra("extra", fetch_string);

        // Start ringtone service
        context.startService(service_intent);

    }
}
