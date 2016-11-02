package edu.upc.adapterviews;

import edu.upc.adapters.MyAdapter_whatsapp;
import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

public class Activity5 extends Activity {

  private ListView conversation;
  private MyAdapter_whatsapp adapter;
  private ArrayList<String> messages;
  private EditText input_text;
  private TextView title;

  private String user;

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

    Bundle extras = getIntent().getExtras();

    String server = extras.getString("Server");
    String port = extras.getString("Port");
    user = extras.getString("Username");

    title.setText(user + "@" + server + ":" + port);
    
  }

  public void addText(final View view) {
    
    if(input_text.getEditableText().toString().equals("")) return;
    else
    {
      messages.add(user + " n?");
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