package net.redlinesoft.androidwearvoiceinput;

import android.app.Notification;
import android.app.PendingIntent;


import android.content.Intent;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.NotificationManagerCompat;
import android.support.v4.app.RemoteInput;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;


public class MainActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void Notify () {
        Intent intent = new Intent(this,NotificationActivity.class);
        PendingIntent pendingIntent = PendingIntent.getActivity(this,0,intent,PendingIntent.FLAG_UPDATE_CURRENT);

        // First Notification
        NotificationCompat.Builder firstBuilder = new NotificationCompat.Builder(this);
        firstBuilder.setContentTitle("First Notification Title");
        firstBuilder.setContentText("First Notification Text");
        firstBuilder.setSmallIcon(R.drawable.ic_launcher);
        firstBuilder.setContentIntent(pendingIntent);

        // Second Notification
        NotificationCompat.Builder secondBuilder = new NotificationCompat.Builder(this);
        secondBuilder.setContentTitle("Second Notification Title");
        secondBuilder.setContentText("Second Notification Text");
        secondBuilder.setSmallIcon(R.drawable.ic_launcher);

        Notification secondNotification =  secondBuilder.build();

        // Extend Notification
        Notification extendNotification = firstBuilder.extend(new NotificationCompat.WearableExtender().addPage(secondNotification)).build();

        NotificationManagerCompat manager =  NotificationManagerCompat.from(this);
        manager.notify(1,extendNotification);

    }

    public void voiceInput (View view) {
        Intent intent = new Intent(this,NotificationActivity.class);
        PendingIntent pendingIntent = PendingIntent.getActivity(this,0,intent,PendingIntent.FLAG_UPDATE_CURRENT);

        String replayLabel = getResources().getString(R.string.label_reply);
        String[] replyChoices = getResources().getStringArray(R.array.reply_choices);

        RemoteInput remoteInput = new RemoteInput.Builder(NotificationUtils.EXTRA_VOICE_REPLY)
                .setLabel(replayLabel)
                .setChoices(replyChoices)
                .build();
        
        
        NotificationCompat.Action action = new NotificationCompat.Action.Builder(R.drawable.ic_reply,"Answer",pendingIntent)
                .addRemoteInput(remoteInput)
                .build();
        
        // First Notification
        Notification notification = new NotificationCompat.Builder(this)
                .setContentTitle("First Notification Title")
                .setContentText("First Notification Text")
                .setSmallIcon(R.drawable.ic_launcher)
                .setContentIntent(pendingIntent)
                .extend(new NotificationCompat.WearableExtender().addAction(action))
                .build();
                

        NotificationManagerCompat manager =  NotificationManagerCompat.from(this);
        manager.notify(1,notification);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
