package br.com.alexpfx.irctest.app;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import br.com.alexpfx.irctest.app.receivers.WifiScanScheduleBroadcastReceiver;
import butterknife.ButterKnife;
import butterknife.OnClick;
import org.apache.log4j.BasicConfigurator;

import java.util.concurrent.TimeUnit;

public class MainActivity extends AppCompatActivity {

    public static final int DURATION = 60;
    private static final String TAG = MainActivity.class.getSimpleName();
    private AlarmManager alarmManager;
    private PendingIntent pendingIntent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ButterKnife.bind(this);

        /* Log4j */
        BasicConfigurator.configure();

        Intent intent = new Intent(getApplicationContext(), WifiScanScheduleBroadcastReceiver.class);
        pendingIntent = PendingIntent.getBroadcast(getApplicationContext(), 0, intent, 0);
        alarmManager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);

    }

    @OnClick(R.id.btnInitialize)
    void initializeApp() {
        setupScanAlarm();
    }

    void setupScanAlarm() {
        final long interval = TimeUnit.SECONDS.toMillis(DURATION);
        alarmManager.setInexactRepeating(AlarmManager.RTC_WAKEUP, System.currentTimeMillis(), interval, pendingIntent);
    }

    @Override
    protected void onDestroy() {
        alarmManager.cancel(pendingIntent);
        super.onDestroy();
    }
}
