package edu.upc.adapterviews;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class Activity0 extends Activity implements View.OnClickListener {
  private EditText labelServer;
  private EditText labelUser;
  private EditText labelPort;
  private String server;
  private String username;
  private String port;
  @Override
  public void onCreate(Bundle savedInstanceState)
  {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.main);
    ((Button) findViewById(R.id.mainButton)).setOnClickListener(this);
  }

  public void onClick(View arg0)
  {
    if (arg0 == findViewById(R.id.mainButton)) {
      Intent intent = new Intent(this, Activity5.class);

      labelServer = (EditText) findViewById(R.id.serverET);
      labelPort = (EditText) findViewById(R.id.portET);
      labelUser = (EditText) findViewById(R.id.usernameET);

      server = labelServer.getText().toString();
      port = labelPort.getText().toString();
      username = labelUser.getText().toString();

      intent.putExtra("Server", server);
      intent.putExtra("Port", port);
      intent.putExtra("Username", username);

      startActivity(intent);
    }
  }
}
