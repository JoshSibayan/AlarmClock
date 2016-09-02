package comjoshsibayan.github.alarm;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.icu.util.Calendar;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    AlarmManager alarm_manager;
    TimePicker alarm_timepicker;
    TextView alarm_state;
    Context context;
    PendingIntent pending_intent;
    int sound_select;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Initialize alarm manager, time picker, text update
        final AlarmManager alarm_manager = (AlarmManager) getSystemService(ALARM_SERVICE);
        final TimePicker alarm_timepicker = (TimePicker) findViewById(R.id.timePicker);
        final TextView alarm_state = (TextView) findViewById(R.id.alarm_state);

        // Create calendar instance
        final Calendar calendar = Calendar.getInstance();

        // Create intent for AlarmReceiver class, send only once
        final Intent my_intent = new Intent(MainActivity.this, AlarmReceiver.class);

        // Create spinner in main UI and corresponding ArrayAdapter
        Spinner spinner = (Spinner) findViewById(R.id.spinner);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource
                (this, R.array.stepbrothers_array, android.R.layout.simple_spinner_item);
        // Specify layout used for option list
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // Apply adapter to spinner
        spinner.setAdapter(adapter);
        // Set onClickListener for spinner
        spinner.setOnItemSelectedListener(this);


        // Initialize start button and create onClickListener to start alarm
        Button alarm_on = (Button) findViewById(R.id.alarm_on);
        alarm_on.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Set calendar based on user input
                calendar.set(Calendar.HOUR_OF_DAY, alarm_timepicker.getHour());
                calendar.set(Calendar.MINUTE, alarm_timepicker.getMinute());

                // Retrieve int values of hour and minute
                int hour = alarm_timepicker.getHour();
                int minute = alarm_timepicker.getMinute();

                // Convert ints into strings
                String hour_string = String.valueOf(hour);
                String minute_string = String.valueOf(minute);

                // Handles to format time data
                if(hour > 12) hour_string = String.valueOf(hour - 12);
                if(minute < 10) minute_string = "0" + String.valueOf(minute);

                alarm_state.setText("Alarm set to: " + hour_string + ":" + minute_string);

                // Put extra string into my_intent, indicates on button pressed
                my_intent.putExtra("extra", "alarm on");

                // Input extra long into my_intent
                // Requests specific value from spinner
                my_intent.putExtra("sound_choice", sound_select);


                // Pending intent to delay intent until specific calendar time
                pending_intent = PendingIntent.getBroadcast
                        (MainActivity.this, 0, my_intent, PendingIntent.FLAG_UPDATE_CURRENT);

                // Set alarm manager
                alarm_manager.set(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(), pending_intent);

            }
        });

        // Initialize stop button and create onClick listener to stop alarm
        Button alarm_off = (Button) findViewById(R.id.alarm_off);
        alarm_off.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                alarm_state.setText("Alarm Off!");

                // Cancel alarm
                alarm_manager.cancel(pending_intent);

                // Put extra string into my_intent, indicates off button pressed
                my_intent.putExtra("extra", "alarm off");

                // Also input extra long for alarm off to prevent null pointer exception
                my_intent.putExtra("sound_choice", sound_select);

                // Stop ringtone
                sendBroadcast(my_intent);
            }
        });
    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int position, long id) {
        // Output id which user selected
        //Toast.makeText(parent.getContext(), "Spinner item is " + id, Toast.LENGTH_SHORT).show();
        sound_select = (int) id;
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }
}
