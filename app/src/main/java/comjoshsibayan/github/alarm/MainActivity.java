package comjoshsibayan.github.alarm;

import android.app.AlarmManager;
import android.icu.util.Calendar;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.TimePicker;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Initialize alarm manager, time picker, text update
        AlarmManager alarm_manager = (AlarmManager) getSystemService(ALARM_SERVICE);
        final TimePicker alarm_timepicker = (TimePicker) findViewById(R.id.timePicker);
        final TextView alarm_state = (TextView) findViewById(R.id.alarm_state);

        // Create calendar instance
        final Calendar calendar = Calendar.getInstance();



        // Initialize start button and create onClick listener to start alarm
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


                alarm_state.setText("Alarm set to: " + hour_string + minute_string);
            }
        });

        // Initialize stop button and create onClick listener to stop alarm
        Button alarm_off = (Button) findViewById(R.id.alarm_off);
        alarm_off.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                alarm_state.setText("Alarm Off!");
            }
        });


    }


    @Override
    public void onClick(View view) {

    }
}
