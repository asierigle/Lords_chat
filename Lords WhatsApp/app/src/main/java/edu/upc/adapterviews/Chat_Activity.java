package edu.upc.adapterviews;

import edu.upc.adapters.MyAdapter_whatsapp;
import edu.upc.util.LocalMessage;
import edu.upc.util.Message;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

public class Chat_Activity extends Activity {

  private ListView conversation;
  private MyAdapter_whatsapp adapter;
  private ArrayList<Message> messages;
  private EditText input_text;
  private TextView title;
  private String user;

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
    Date date = new Date();

    if(input_text.getEditableText().toString().equals("")) return;
    else
    {
      // Creates a new message and adds it into the array
      Message tmp = new LocalMessage();
      tmp.user = user;
      tmp.content = (input_text.getEditableText().toString());
      tmp.realDate = date;
      messages.add(tmp);
      input_text.setText("");
    }

    conversation.post(new Runnable() {
        @Override
        public void run() {
            conversation.setSelection(conversation.getCount() - 1);
        }
    });

    adapter.notifyDataSetChanged();
  }

}