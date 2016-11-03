/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.upc.broadcast.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

public class BroadcastReceiver2 extends BroadcastReceiver {

  static int instancesCounter = 0;
  int instance_id = ++instancesCounter;

  @Override
  public void onReceive(Context context, Intent intent) {

    Toast.makeText(context, "broadcastReceiver 2\ninstance_id " + instance_id, Toast.LENGTH_SHORT).show();

    String token = getResultData();
    if (token != null) {
      Toast.makeText(context, "from previous BroadcastReceiver: " + token, Toast.LENGTH_SHORT).show();
    }
  }
}
