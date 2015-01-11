package net.redlinesoft.androidwearvoiceinput;


import android.content.Intent;
import android.support.v4.app.RemoteInput;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.widget.Toast;


public class NotificationActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notification);
        
        CharSequence charSequence = getMessageText(getIntent());
        Toast.makeText(this, "Answer = "+ charSequence,Toast.LENGTH_LONG).show();
    
    }
    
    private  CharSequence getMessageText(Intent intent) {
        Bundle remoteInput = RemoteInput.getResultsFromIntent(intent);
        
        if (remoteInput!=null) {
            return remoteInput.getCharSequence(NotificationUtils.EXTRA_VOICE_REPLY);
        } 
        
        return null;   
    }
}
