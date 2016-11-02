package edu.upc.adapterviews;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.*;
import java.util.ArrayList;

public class Activity1 extends Activity implements ListView.OnItemClickListener {

  private ArrayList<String> nameList;
  private ListView listView;
  private ArrayAdapter<String> adapter;
  private int counter;

  @Override
  public void onCreate(Bundle icicle) {
    super.onCreate(icicle);

    setContentView(R.layout.main1_v0);

    nameList = new ArrayList<String>();
    nameList.add("john");
    nameList.add("peter");
    nameList.add("andrew");
    nameList.add("molly");
    nameList.add("steven");

    adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_single_choice, nameList);
    listView = (ListView) findViewById(R.id.main1ListView);
    listView.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE);
    listView.setAdapter(adapter);
    listView.setOnItemClickListener(this);

  }

  public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
    Toast.makeText(this, "item clicked: " + nameList.get(position), Toast.LENGTH_SHORT).show();
    nameList.remove(position);
    nameList.add(position, "" + (++counter));
    adapter.notifyDataSetChanged();
  }

}
