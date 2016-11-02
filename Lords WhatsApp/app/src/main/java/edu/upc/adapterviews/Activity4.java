package edu.upc.adapterviews;

import android.app.Activity;
import android.content.ContentResolver;
import android.database.Cursor;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.widget.*;
import edu.upc.adapters.ContactListAdapter;
import static edu.upc.adapters.ContactListAdapter.CONTACT_PROJECTION;

public class Activity4 extends Activity {

  private ArrayAdapter<String> adapter1;
  private ContactListAdapter adapter2;
  
  static final String[] COLOURS = new String[]{
    "Red", "Black", "Yellow", "Yellow-blue", "Yellow-red", "Blue", "Green"};

  @Override
  public void onCreate(Bundle icicle) {
    super.onCreate(icicle);

    setContentView(R.layout.main4_v0);

        AutoCompleteTextView actv1 = ((AutoCompleteTextView)findViewById(R.id.main4AutoCompleteTextView));
        adapter1 = new ArrayAdapter<String>(this, android.R.layout.simple_dropdown_item_1line, COLOURS);
        actv1.setAdapter(adapter1);      
        
        MultiAutoCompleteTextView actv2 = ((MultiAutoCompleteTextView)findViewById(R.id.main4MultiAutoCompleteTextView));
        actv2.setAdapter(adapter1);
        actv2.setTokenizer(new MultiAutoCompleteTextView.CommaTokenizer());
        
        ContentResolver content = getContentResolver();
        Cursor cursor = content.query(ContactsContract.Contacts.CONTENT_URI, CONTACT_PROJECTION, null, null, null);
        adapter2 = new ContactListAdapter(this, cursor);
        AutoCompleteTextView textView = (AutoCompleteTextView)findViewById(R.id.main4AutoCompleteTextView2);
        textView.setAdapter(adapter2);

  }

}
