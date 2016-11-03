package edu.upc.broadcast;

import edu.upc.broadcast.receiver.TimeTickReceiver;
import edu.upc.broadcast.receiver.BroadcastReceiver2;
import edu.upc.broadcast.receiver.BroadcastReceiver1;
import android.app.Activity;
import android.app.AlarmManager;
import static android.app.AlarmManager.RTC;
import android.app.PendingIntent;
import android.content.*;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.SystemClock;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;
import edu.upc.broadcast.receiver.BootReceiver;
import java.util.Calendar;

public class Activity1 extends Activity implements View.OnClickListener
{
    TimeTickReceiver timeTick_Receiver;
    BroadcastReceiver2 broadcast_Receiver2;
    BroadcastReceiver1 broadcast_Receiver1;   
    AlarmManager alarmMgr;
    
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        
        ((Button)findViewById(R.id.registerTimeTick)).setOnClickListener(this);
        ((Button)findViewById(R.id.unregisterTimeTick)).setOnClickListener(this);
        ((Button)findViewById(R.id.unregisterTimeTick)).setEnabled(false);
        
        ((Button)findViewById(R.id.startBatteryService)).setOnClickListener(this);
        ((Button)findViewById(R.id.stopBatteryService)).setOnClickListener(this);
        
        ((Button)findViewById(R.id.broadcast)).setOnClickListener(this);
        ((Button)findViewById(R.id.stickybroadcast)).setOnClickListener(this);
        
        ((Button)findViewById(R.id.setalarm)).setOnClickListener(this);
        ((Button)findViewById(R.id.resetalarm)).setOnClickListener(this);
        
        ((Button)findViewById(R.id.enablebootreceiver)).setOnClickListener(this);
        ((Button)findViewById(R.id.disablebootreceiver)).setOnClickListener(this);
        
        ((Button)findViewById(R.id.activity2)).setOnClickListener(this);
        
        alarmMgr = (AlarmManager)getSystemService(Context.ALARM_SERVICE);
        
    }
    public void onClick(View arg0) {
        
        if(arg0==findViewById(R.id.registerTimeTick)){
            if(timeTick_Receiver==null){
                //...
                Toast.makeText(this, "time tick registered", Toast.LENGTH_SHORT).show();
                
                ((Button)findViewById(R.id.registerTimeTick)).setEnabled(false);
                ((Button)findViewById(R.id.unregisterTimeTick)).setEnabled(true);
            }
        }
        if(arg0==findViewById(R.id.unregisterTimeTick)){
            if(timeTick_Receiver!=null){
                //...
                timeTick_Receiver=null;
                Toast.makeText(this, "time tick unregistered", Toast.LENGTH_SHORT).show();
                
                ((Button)findViewById(R.id.unregisterTimeTick)).setEnabled(false);
                ((Button)findViewById(R.id.registerTimeTick)).setEnabled(true);
            }
        }
        
        if(arg0==findViewById(R.id.startBatteryService)){
            //...
        }
        if(arg0==findViewById(R.id.stopBatteryService)){
            //...
        }
        
        if(arg0==findViewById(R.id.broadcast)){
            //...
        }
        if(arg0==findViewById(R.id.stickybroadcast)){           
            //...
        }
        
        if (arg0 == findViewById(R.id.setalarm)) {
          
            Intent intent = new Intent("hello");
            int requestCode = 0;
            int flags = 0;
            PendingIntent pendingIntent = PendingIntent.getBroadcast(this, requestCode, intent, flags);

            Calendar calendar = Calendar.getInstance();
            calendar.setTimeInMillis(System.currentTimeMillis());
            
            // To set the alarm's trigger time to 8:30 a.m.
//            calendar.set(Calendar.HOUR_OF_DAY, 8);
//            calendar.set(Calendar.MINUTE, 30);
            
            alarmMgr.set(AlarmManager.RTC, calendar.getTimeInMillis()+10000, pendingIntent);

            
            Toast.makeText(this, "alarm set off", Toast.LENGTH_SHORT).show();
        }
        if (arg0 == findViewById(R.id.resetalarm)) {
            Intent intent = new Intent("hello");
            int requestCode = 0;
            int flags = 0;
            PendingIntent pendingIntent = PendingIntent.getBroadcast(this, requestCode, intent, flags);
            pendingIntent.cancel();
//            alarmMgr.cancel(pendingIntent);
            Toast.makeText(this, "alarm cancelled", Toast.LENGTH_SHORT).show();
        }
        
        if (arg0 == findViewById(R.id.enablebootreceiver)) {
            enableBootReceiver();
            Toast.makeText(this, "BootReceiver enabled", Toast.LENGTH_SHORT).show();
        }
        if (arg0 == findViewById(R.id.disablebootreceiver)) {
            disableBootReceiver();
            Toast.makeText(this, "BootReceiver disabled", Toast.LENGTH_SHORT).show();
        }
        
        if (arg0 == findViewById(R.id.activity2)) {
            //...
        }
        
    }
    
    @Override
    public void onResume(){
        super.onResume();
        
        //...
    }
    
    @Override
    public void onPause(){
        super.onPause();
        
        if(broadcast_Receiver1!=null)
            unregisterReceiver(broadcast_Receiver2);
        if(broadcast_Receiver2!=null)
            unregisterReceiver(broadcast_Receiver1);
    }
    
    private void enableBootReceiver(){
      ComponentName receiver = new ComponentName(this, BootReceiver.class);
      PackageManager pm = getPackageManager();

      pm.setComponentEnabledSetting(receiver,
        PackageManager.COMPONENT_ENABLED_STATE_ENABLED,
        PackageManager.DONT_KILL_APP);
    }
    
    private void disableBootReceiver(){
      ComponentName receiver = new ComponentName(this, BootReceiver.class);
      PackageManager pm = getPackageManager();

      pm.setComponentEnabledSetting(receiver,
        PackageManager.COMPONENT_ENABLED_STATE_DISABLED,
        PackageManager.DONT_KILL_APP);
    }

}