package edu.upc.broadcast.application;

import android.app.Application;
import android.widget.Toast;

/**
 *
 * @author juanluis
 */
public class _Application extends Application {
  
  private static int id;
    
    @Override
    public void onCreate(){
        super.onCreate();
        Toast.makeText(this, "Application created, id: "+(++id), Toast.LENGTH_SHORT).show();
    }
}
