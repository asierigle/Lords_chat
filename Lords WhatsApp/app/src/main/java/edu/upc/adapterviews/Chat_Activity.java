package edu.upc.adapterviews;

import edu.upc.adapters.MyAdapter_whatsapp;
import edu.upc.util.Message;

import android.app.Activity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Date;

import edu.upc.util.MessageTypes;
import edu.upc.entity.DMessages;
import edu.upc.entity.MessageREST;

public class Chat_Activity extends Activity {

  private ListView conversation;
  private MyAdapter_whatsapp adapter;
  private ArrayList<Message> messages;
  private EditText input_text;
  private TextView title;
  private String user;
  private String Server;
  private String Port;

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

    Server = extras.getString("Server");
    Port = extras.getString("Port");
    user = extras.getString("Username");

    title.setText(user + "@" + Server + ":" + Port);
  }

  public Date GetLastMessageDate()
  {
    if(messages.isEmpty())
    {
      return new Date(0);
    }
    else
    {
      return messages.get(messages.size() -1).realDate;
    }
  }
  public void NotifyMessageChanges()
  {
    conversation.post(new Runnable()
    {
      @Override
      public void run()
      {
        conversation.setSelection(conversation.getCount() - 1);
      }
    });

    adapter.notifyDataSetChanged();
  }

  public void AddSystemDate()
  {
    MessageTypes type = MessageTypes.System;
    Date date = new Date();
    String time;
    time = new String(date.toLocaleString());

    Message toAdd = new Message(input_text.getEditableText().toString(), type, time, date);
    messages.add(toAdd);

    conversation.post(new Runnable() {
      @Override
      public void run() {
        conversation.setSelection(conversation.getCount() - 1);
      }
    });
    NotifyMessageChanges();
  }
  public void addText(final View view) {

    // Gets the current time
    Date date = new Date();

    if (input_text.getEditableText().toString().equals("")) return;

    // Creates a new message and adds it into the array
    new RecieveMessages().execute(GetLastMessageDate());
    input_text.setText("");
  }

  class RecieveMessages extends AsyncTask<Date, Void, ArrayList<Message>> {
    @Override
    protected ArrayList<Message> doInBackground(Date... GetFromDate) {
      ArrayList<DMessages> mes = MessageREST.REST_retrieveFromDate(Server, Port, GetFromDate[0], user);
      ArrayList<Message> ret = new ArrayList<Message>();

      // ------------------------- Transforming from D_Messages (database format) to Message(App format) ----------------------------------------
      for (DMessages toTransform : mes) {

        MessageTypes type;
        if (toTransform.getUserSender() == user) {
          type = MessageTypes.local;
        } else {
          type = MessageTypes.Remote;
        }

        Date date = toTransform.getDate();
        String time;
        time = new String();
        time = time.concat(Integer.toString(date.getHours()));
        time = time.concat(":");
        int minutes = date.getMinutes();
        if (minutes < 10) {
          time = time.concat("0");
        }
        time = time.concat(Integer.toString(minutes));

        if (messages.size() > 1) {
          if (messages.get(messages.size() - 1).realDate.getDay() != date.getDay()) {
            AddSystemDate();
          }
        }

        Message toRecieve = new Message(toTransform.getContent(), type, time, date);
        ret.add(toRecieve);
      }
      return ret;
    }

    @Override
    protected void onPostExecute(ArrayList<Message> result) {
      for (Message m : result) {
        messages.add(m);
      }
      NotifyMessageChanges();
    }
  };

}
