package edu.upc.broadcast.service;

import edu.upc.broadcast.receiver.BatteryReceiver;
import android.app.Service;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.IBinder;
import android.widget.Toast;

public class BatteryService extends Service {

    BatteryReceiver battery_receiver;
    
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }
    
    @Override
    //el servicio se crea una vez, aunque luego se quiera arrancar varias veces:
    public void onCreate() {
        super.onCreate();
        //...
        Toast.makeText(this, "batteryService created", Toast.LENGTH_SHORT).show();  
    }
    
    @Override
    //como el servicio no estará enlazado, al pararlo también se destruye:
    public void onDestroy() {
        super.onDestroy();
        //...
        Toast.makeText(this, "batteryService destroyed", Toast.LENGTH_SHORT).show();  
    }
    
}

