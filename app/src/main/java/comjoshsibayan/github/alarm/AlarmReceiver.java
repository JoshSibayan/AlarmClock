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

        // Create intent and start ringtone service
        Intent service_intent = new Intent(context, RingtonePlayingService.class);
        context.startService(service_intent);

    }
}
