package edu.upc.broadcast;

import android.app.Activity;
import android.os.*;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import static java.lang.Thread.sleep;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

public class Activity2 extends Activity implements View.OnClickListener {
  
  TextView progress_textview;
  MyAsyncTask myAsyncTask;
  Timer timer;

  @Override
  public void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.main2);
    ((Button) findViewById(R.id.main2Button)).setOnClickListener(this);
    progress_textview = ((TextView) findViewById(R.id.progress));
    
    if(savedInstanceState!=null){
      myAsyncTask = (MyAsyncTask)getLastNonConfigurationInstance();
      if(myAsyncTask!=null){
        myAsyncTask.setTextView(progress_textview);
        progress_textview.setText(myAsyncTask.getProgress());
      }
    }
  }
  
  @Override
  public Object onRetainNonConfigurationInstance() {
    //util para devolver un objeto cualquiera
    return myAsyncTask;
  }
  
  @Override
  public void onRestoreInstanceState(Bundle savedInstanceState) {
    //solo util para recuperar tipos basicos
  }
  
  @Override
  public void onSaveInstanceState(Bundle savedInstanceState) {
    //solo util para guardar tipos basicos
  }
  
  @Override
    public void onPause(){
        super.onPause();
        //si no se cancela, el timer sigue trabajando porque
        //se trata de un hilo independiente
        if(timer!=null) timer.cancel();
    }

  public void onClick(View arg0) {

    //...
    
    Toast.makeText(Activity2.this, "new thread launched", Toast.LENGTH_SHORT).show();
  }

  private class OperationPerformer extends Thread {
    @Override
    public void run() {
      Toast.makeText(Activity2.this, "hello from OperationPerformer", Toast.LENGTH_SHORT).show();
    }
  }

  Handler handler = new Handler() {
    @Override
    public void handleMessage(Message msg) {

      switch (msg.getData().getInt("result")) {

        case 0:
          Toast.makeText(Activity2.this, msg.getData().getString("message"), Toast.LENGTH_SHORT).show();
          break;

        case -1:
          Toast.makeText(Activity2.this, msg.getData().getString("error"), Toast.LENGTH_SHORT).show();
          break;
      }
    }
  };

  private class OperationPerformer2 extends Thread {

    Handler handler;

    OperationPerformer2(Handler h) {
      handler = h;
    }

    @Override
    public void run() {
      Message msg = handler.obtainMessage();
      Bundle b = new Bundle();
      try{
          //here we are supposed to do something...
          //...
          b.putInt("result", 0);
          b.putString("message", "hello from OperationPerformer2");
      }
      catch(Exception e){
          b.putInt("result",-1);
          b.putString("error", e.getMessage());
      }
      msg.setData(b);
      handler.sendMessage(msg);

//      Runnable myRunnable = new Runnable() {
//        public void run() {
//          Toast.makeText(Activity2.this, "hello from OperationPerformer2", Toast.LENGTH_SHORT).show();
//        }
//      };
//      handler.post(myRunnable);
      
    }
  }

  //la alternativa a definir una clase interna estatica es definir
  //una clase convencional (no interna), para el caso es lo mismo.
  private static class MyAsyncTask extends AsyncTask<String, Integer, Integer> {
    
    List<String> tasks;
    //si esta clase interna accede a un atributo de su superclase,
    //si se recrea la superclase, la antigua referencia no es util, asi:
    TextView progress_textview_local;
    String last_message;
    
    public MyAsyncTask(){
      tasks = new ArrayList<String>();
    }
    
    public void setTextView(TextView textView){
      progress_textview_local = textView;
    }    
    public String getProgress(){
      return last_message;
    }

    @Override
    protected void onProgressUpdate(Integer... progress) {
      // Update progress bar, Notification, or other UI element
//      Toast.makeText(Activity2.this, "...onProgress of task: "+progress[0]+ " value: "+progress[1], Toast.LENGTH_SHORT).show();
      last_message = "...onProgress of task: "+progress[0]+ " value: "+progress[1];
      progress_textview_local.setText(last_message);
    }

    @Override
    protected void onPostExecute(Integer result) {
      // Report results via UI update, Dialog, or notification
//      Toast.makeText(Activity2.this, "MyAsyncTask finished: "+result, Toast.LENGTH_SHORT).show();
      last_message = "MyAsyncTask finished: "+result;
      progress_textview_local.setText(last_message);
    }

    @Override
    protected Integer doInBackground(String... parameter) {
      int task_progress = 0;
      int num_task = 0;
      for(String task : parameter){
        tasks.add(task);
        num_task++;
        // Perform background processing task, update myProgress
        for(int i=0; i<2; i++){
          task_progress++;
          try{sleep(5000);} 
          catch(Exception e){e.printStackTrace();}
          // Continue performing background processing task
          publishProgress(num_task,task_progress); 
        }
      }
      // Return the value to be passed to onPostExecute
			return task_progress;
    }
    
  }
  
  private class myTimerTask extends TimerTask {
    public void run() {
      //here yo do some work
      //...
      Runnable doUpdateGUI = new Runnable() {
        public void run() {
          Toast.makeText(Activity2.this, "myTimerTask has finished ", Toast.LENGTH_SHORT).show();
        }
      };
      handler.post(doUpdateGUI);
    }
  };
  
}
