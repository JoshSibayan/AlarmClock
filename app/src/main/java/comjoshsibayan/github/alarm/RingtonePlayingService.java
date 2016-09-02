package comjoshsibayan.github.alarm;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.util.Log;

import java.util.Random;

/**
 * Created by Josh on 8/31/2016.
 */
public class RingtonePlayingService extends Service {

    MediaPlayer media_song;
    int startId;
    boolean isRunning;

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        //Log.i("LocalService", "Received start id " + startId + ": " + intent);

        // Fetch extra strings (on/off)
        String state = intent.getExtras().getString("extra");

        // Fetch sound_choice integer values
        int sound_id = intent.getExtras().getInt("sound_choice");

        // Log.e("Ringtone state: extra ", state);
        // Log.e("Sound choice is ", sound_id.toString());

        // Notification service setup, set intent to link to MainActivity
        NotificationManager notify_manager =
                (NotificationManager)getSystemService(NOTIFICATION_SERVICE);
        Intent intent_main_activity = new Intent(this.getApplicationContext(), MainActivity.class);

        // Create pending intent
        PendingIntent pending_intent_main_activity =
                PendingIntent.getActivity(this, 0, intent_main_activity, 0);

        // Set notification parameters
        Notification notify_popup =
                new Notification.Builder(this).setContentTitle("An alarm is going off!")
                        .setContentText("Click me!")
                        .setContentIntent(pending_intent_main_activity)
                        .setAutoCancel(true).build();



        // Converts extra strings from intent to start IDs
        assert state != null;
        switch (state) {
            case "alarm on":
                startId = 1;
                break;
            case "alarm off":
                startId = 0;
                Log.e("Start ID is ", state);
                break;
            default:
                startId = 0;
                break;
        }

        if(!this.isRunning && startId == 1) {
            // If no music playing and alarm on pressed, music plays
            // Log.e("there is no music, ", "and you want start");
            this.isRunning = true;
            this.startId = 0;

            // Set notification call commands
            notify_manager.notify(0, notify_popup);

            // Create instance of media player, start ringtone
            media_song = MediaPlayer.create(this, R.raw.kalimba);
            media_song.start();

            // Play sound depending on passed sound_id
            if(sound_id == 0) {
                // Play randomly selected audio
                int min = 1, max = 5;
                Random rand = new Random();
                int sound_number = rand.nextInt(max + min);
                // Log.e("Random number is ", String.valueOf(sound_number));

                if(sound_number == 1) {
                    media_song = MediaPlayer.create(this, R.raw.catalina_wine_mixer);
                    media_song.start();
                } else if(sound_id == 2) {
                    media_song = MediaPlayer.create(this, R.raw.inclement_weather);
                    media_song.start();
                } else if(sound_id == 3) {
                    media_song = MediaPlayer.create(this, R.raw.johnny_hopkins);
                    media_song.start();
                } else if(sound_id == 4) {
                    media_song = MediaPlayer.create(this, R.raw.lightning_bolt);
                    media_song.start();
                } else if(sound_id == 5) {
                    media_song = MediaPlayer.create(this, R.raw.robert_better_not_get_in_my_face);
                    media_song.start();
                } else {
                    media_song = MediaPlayer.create(this, R.raw.butt_buddy);
                    media_song.start();
                }
            } else if(sound_id == 1) {
                media_song = MediaPlayer.create(this, R.raw.catalina_wine_mixer);
                media_song.start();
            } else if(sound_id == 2) {
                media_song = MediaPlayer.create(this, R.raw.inclement_weather);
                media_song.start();
            } else if(sound_id == 3) {
                media_song = MediaPlayer.create(this, R.raw.johnny_hopkins);
                media_song.start();
            } else if(sound_id == 4) {
                media_song = MediaPlayer.create(this, R.raw.lightning_bolt);
                media_song.start();
            } else if(sound_id == 5) {
                media_song = MediaPlayer.create(this, R.raw.robert_better_not_get_in_my_face);
                media_song.start();
            } else {
                media_song = MediaPlayer.create(this, R.raw.butt_buddy);
                media_song.start();
            }


        } else if(this.isRunning && startId == 0) {
            // If music playing and alarm off pressed, music stops
            // Log.e("there is music, ", "and you want end");
            // Stop ringtone
            media_song.stop();
            media_song.reset();

            this.isRunning = false;
            this.startId = 0;

        } else if(!this.isRunning && startId == 0) {
            // Handles for when user presses buttons randomly
            // If no music playing and alarm off pressed, do nothing
            // Log.e("there is no music, ", "and you want end");
            this.isRunning = false;
            startId = 0;

        } else if(this.isRunning && startId == 1) {
            // If music playing and alarm on pressed, do nothing
            // Log.e("there is music, ", "and you want start");
            this.isRunning = true;
            this.startId = 1;

        } else {
            // Catch overlooked edge cases
            // Log.e("else, ", "somehow you reached this");
        }

        return START_NOT_STICKY;
    }

    @Override
    public void onDestroy() {
        // Tell user we stopped
        // Log.e("onDestroy called", "ta da");

        super.onDestroy();
        this.isRunning = false;
    }

}
