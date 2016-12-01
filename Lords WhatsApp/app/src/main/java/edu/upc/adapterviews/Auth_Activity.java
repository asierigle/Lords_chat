package edu.upc.adapterviews;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Auth_Activity extends Activity implements View.OnClickListener {
  private EditText labelServer;
  private EditText labelUser;
  private EditText labelPort;
  private String server;
  private String username;
  private String port;

  @Override
  public void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.auth);
    ((Button) findViewById(R.id.mainButton)).setOnClickListener(this);
  }

  public void onClick(View arg0) {
    if (arg0 == findViewById(R.id.mainButton)) {

      labelServer = (EditText) findViewById(R.id.serverET);
      labelPort = (EditText) findViewById(R.id.portET);
      labelUser = (EditText) findViewById(R.id.usernameET);

      server = labelServer.getText().toString();
      port = labelPort.getText().toString();
      username = labelUser.getText().toString();

      if (!server.isEmpty()) {

        if (!port.isEmpty()) {

          if (!username.isEmpty()) {
            Intent intent = new Intent(this, Chat_Activity.class);

            intent.putExtra("Server", server);
            intent.putExtra("Port", port);
            intent.putExtra("Username", username);

            startActivity(intent);
          } else {
            Toast.makeText(this, "You cannot leave Username blank!", Toast.LENGTH_SHORT).show();
          }

        } else {
          Toast.makeText(this, "You cannot leave Port blank!", Toast.LENGTH_SHORT).show();
        }

      } else {
        Toast.makeText(this, "You cannot leave Server blank!", Toast.LENGTH_SHORT).show();
      }
    }
  }
}
