/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.upc.broadcast.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

public class BroadcastReceiver1 extends BroadcastReceiver {

  static int instancesCounter = 0;
  int instance_id = ++instancesCounter;

  @Override
  public void onReceive(Context context, Intent intent) {
    Toast.makeText(context, "broadcastReceiver 1\ninstance_id "+instance_id, Toast.LENGTH_SHORT).show();

        //si no se ha recibido por sendOrderedBroadcast, esta instrucción genera excepción:
//        abortBroadcast();
    
        //datos al siguiente solo para sendOrderedBroadcast, 
    //por ejemplo cambiar el numero de un: Intent.ACTION_NEW_OUTGOING_CALL
//        String token = getResultData();
    String token = "from me the 1";
    setResultData("token to the next: " + token);
  }
}
