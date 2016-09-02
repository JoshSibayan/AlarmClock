package comjoshsibayan.github.alarm;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

/**
 * Created by Josh on 8/30/2016.
 */
public class AlarmReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        // Log.e("In receiver", "Yay!");

        // Fetch extra strings from MainActivity on button intent
        // Determines whether user presses on or off
        String fetch_string = intent.getExtras().getString("extra");
        // Log.e("What is the key?", fetch_string);

        // Fetch extra longs from MainActivity intent
        // Tells which value user selects from spinner
        int get_sound_choice = intent.getExtras().getInt("sound_choice");
        //Log.e("Sound choice is ", get_sound_choice.toString());

        // Create intent
        Intent service_intent = new Intent(context, RingtonePlayingService.class);

        // Pass extra string from receiver to RingtonePlayingService
        service_intent.putExtra("extra", fetch_string);

        // Pass extra integer from receiver to RingtonePlayingService
        service_intent.putExtra("sound_choice", get_sound_choice);

        // Start ringtone service
        context.startService(service_intent);

    }
}
