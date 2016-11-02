package edu.upc.adapters;

import android.content.ContentResolver;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.provider.ContactsContract;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CursorAdapter;
import android.widget.FilterQueryProvider;
import android.widget.TextView;

/**
 *
 * @author upcnet
 */
public class ContactListAdapter extends CursorAdapter {

  private static final int COLUMN_DISPLAY_NAME = 1;
  public static final String[] CONTACT_PROJECTION = new String[]{
    ContactsContract.Contacts._ID,
    ContactsContract.Contacts.DISPLAY_NAME
  };
  private ContentResolver mContent;

  public ContactListAdapter(Context context, Cursor c) {
    super(context, c);
    mContent = context.getContentResolver();
  }

  @Override
  public View newView(Context context, Cursor cursor, ViewGroup parent) {
    LayoutInflater inflater = LayoutInflater.from(context);
    TextView view = (TextView) inflater.inflate(
      android.R.layout.simple_dropdown_item_1line, parent, false);
    view.setText(cursor.getString(COLUMN_DISPLAY_NAME));
    return view;
  }

  @Override
  public void bindView(View view, Context context, Cursor cursor) {
    ((TextView) view).setText(cursor.getString(COLUMN_DISPLAY_NAME));
  }

  @Override
  public String convertToString(Cursor cursor) {
    return cursor.getString(COLUMN_DISPLAY_NAME);
  }

  @Override
  public Cursor runQueryOnBackgroundThread(CharSequence constraint) {
    FilterQueryProvider filter = getFilterQueryProvider();
    if (filter != null) {
      return filter.runQuery(constraint);
    }

    Uri uri = Uri.withAppendedPath(
      ContactsContract.Contacts.CONTENT_FILTER_URI, Uri.encode(constraint.toString()));
    Log.e("contactlist",uri.toString());
    return mContent.query(uri, CONTACT_PROJECTION, null, null, null);
  }

}
