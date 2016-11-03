package edu.upc.adapterviews;

import edu.upc.adapters.MyAdapter_whatsapp;
import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Calendar;

public class Activity5 extends Activity {

  private ListView conversation;
  private MyAdapter_whatsapp adapter;
  private ArrayList<String> messages;
  private EditText input_text;
  private TextView title;
  private String user;

  //public Calendar calendar;
  //public TextView text_hour;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.main5_v0);

    conversation = (ListView) findViewById(R.id.conversation);
    messages = new ArrayList<String>();
    adapter = new MyAdapter_whatsapp(this, messages);
    conversation.setAdapter(adapter);
    input_text = (EditText) findViewById(R.id.input);
    title = (TextView) findViewById(R.id.title);
    //text_hour = (TextView) findViewById(R.id.right_hour);

    Bundle extras = getIntent().getExtras();

    String server = extras.getString("Server");
    String port = extras.getString("Port");
    user = extras.getString("Username");

    title.setText(user + "@" + server + ":" + port);

    //calendar = Calendar.getInstance();
  }

  public void addText(final View view) {

    //int hour = calendar.get(Calendar.HOUR);
    //int minute = calendar.get(Calendar.MINUTE);

    if(input_text.getEditableText().toString().equals("")) return;
    else
    {
      messages.add(user + " ha dicho:\n" + input_text.getEditableText().toString());
      //messages.add(hour + ":" + minute);
      //text_hour.setText(hour + ":" + minute);
    }
    //...
    
    conversation.post(new Runnable() {
        @Override
        public void run() {
            conversation.setSelection(conversation.getCount() - 1);
        }
    });
    
    adapter.notifyDataSetChanged();
  }

}