package edu.upc.adapterviews;

import android.app.Activity;
import android.database.Cursor;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.util.Log;
import android.util.SparseBooleanArray;
import android.view.View;
import android.widget.*;

public class Activity2 extends Activity implements ListView.OnItemClickListener {

  private ListView listView;
  private SimpleCursorAdapter adapter;

  @Override
  public void onCreate(Bundle icicle) {
    super.onCreate(icicle);

    setContentView(R.layout.main2_v0);

    Cursor c = getContentResolver().query(ContactsContract.Contacts.CONTENT_URI, null, null, null, null);
    
    int item_view = android.R.layout.two_line_list_item;
//    int item_view = R.layout.row_twotextviews;
    
    adapter = new SimpleCursorAdapter(this, item_view, c,
      new String[]{ContactsContract.Contacts.DISPLAY_NAME, ContactsContract.Contacts.HAS_PHONE_NUMBER},
      new int[]{android.R.id.text1, android.R.id.text2}, SimpleCursorAdapter.FLAG_REGISTER_CONTENT_OBSERVER);
    
    listView = (ListView) findViewById(R.id.main2ListView1);
    listView.setAdapter(adapter);
	  listView.setCacheColorHint(0);
    //if we setChoiceMode the selected items are "activated"
//    listView.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE);
    listView.setOnItemClickListener(this);
  }

  public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
    Toast.makeText(this, "item clicked at: " + position, Toast.LENGTH_SHORT).show();
    
    //useful if we do not use setChoiceMode: 
    view.setSelected(true);
    
    //to printout the checked items up until now:
//    SparseBooleanArray checked = listView.getCheckedItemPositions();
//    Log.e("checked", "new print of checked items:");
//    for (int i = 0; i < listView.getCount(); i++) {
//      if (checked.get(i)) {
//        Log.e("checked", "checked i="+i);
//      }
//    }
  }

}
