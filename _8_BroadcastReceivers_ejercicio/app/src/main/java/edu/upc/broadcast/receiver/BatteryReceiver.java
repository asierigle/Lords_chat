

package edu.upc.broadcast.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

public class BatteryReceiver extends BroadcastReceiver{

    @Override
    public void onReceive(Context context, Intent intent) {
        
        int level = intent.getIntExtra("level", 0);
        Toast.makeText(context, "battery: "+level, Toast.LENGTH_SHORT).show();
        
    }
}
