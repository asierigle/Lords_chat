package edu.upc.adapterviews;

import edu.upc.adapters.MyAdapter_whatsapp;
import edu.upc.util.Message;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Calendar;

public class Chat_Activity extends Activity {

  private ListView conversation;
  private MyAdapter_whatsapp adapter;
  private ArrayList<Message> messages;
  private EditText input_text;
  private TextView title;
  private String user;
  private int currentTime;
  private Calendar calendar;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.chat);

    conversation = (ListView) findViewById(R.id.conversation);
    messages = new ArrayList<Message>();
    adapter = new MyAdapter_whatsapp(this, messages);
    conversation.setAdapter(adapter);
    input_text = (EditText) findViewById(R.id.input);
    title = (TextView) findViewById(R.id.title);

    Bundle extras = getIntent().getExtras();

    String server = extras.getString("Server");
    String port = extras.getString("Port");
    user = extras.getString("Username");

    title.setText(user + "@" + server + ":" + port);
  }

  public void addText(final View view) {

    // Gets the current time
    calendar = Calendar.getInstance();
    int hour = calendar.get(Calendar.HOUR);
    int minute = calendar.get(Calendar.MINUTE);

    if(input_text.getEditableText().toString().equals("")) return;
    else
    {
      // Creates a new message and adds it into the array
      Message tmp = new Message();
      tmp.content = (user + " ha dicho:\n" + input_text.getEditableText().toString());
      tmp.date = (hour + ":" + minute);
      messages.add(tmp);
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