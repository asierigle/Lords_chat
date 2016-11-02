/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.upc.adapters;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import edu.upc.adapterviews.R;
import java.util.ArrayList;
import java.util.Date;

/**
 *
 * @author juanluis
 */
public class MyAdapter2 extends BaseAdapter {

  private Context mContext;
  private ArrayList<String> mStrings;
  private ArrayList<Drawable> mImageList;

  public MyAdapter2(Context context, ArrayList<String> mStrings, ArrayList<Drawable> imageList) {
    mContext = context;
    this.mStrings = mStrings;
    mImageList = imageList;
  }

  public int getCount() {
    return mStrings.size();
  }

  public View getView(int position, View convertView, ViewGroup parent) {
    
    if(convertView==null){
      if(getItemViewType(position) == 0){
        convertView = LayoutInflater.from(mContext).inflate(R.layout.row_customized_date_hour, parent, false); 
        ViewHolder1 viewHolder = new ViewHolder1();
        viewHolder.date  = (TextView)  convertView.findViewById(R.id.date);
        viewHolder.hour  = (TextView)  convertView.findViewById(R.id.hour);
        convertView.setTag(viewHolder);
      }
      else{
        convertView = LayoutInflater.from(mContext).inflate(R.layout.row_customized_image_text_button, parent, false);
        ViewHolder2 viewHolder = new ViewHolder2();
        viewHolder.image = (ImageView) convertView.findViewById(R.id.image);
        viewHolder.text  = (TextView)  convertView.findViewById(R.id.texto);
        viewHolder.button= (Button)    convertView.findViewById(R.id.button);
        convertView.setTag(viewHolder);
      }
    }
    
    if(getItemViewType(position) == 0){
      Date date = new Date();
      ViewHolder1 viewHolder = (ViewHolder1)convertView.getTag();
      viewHolder.date.setText(date.toLocaleString());
      viewHolder.hour.setText(mStrings.get(position));
    }
    else{
      ViewHolder2 viewHolder = (ViewHolder2)convertView.getTag();
      viewHolder.image.setImageDrawable(mImageList.get(position%4));
      viewHolder.text.setText(mStrings.get(position));
      viewHolder.button.setTag(position); 
    }
    
    return convertView;
  }
  
  public class ViewHolder1{
    TextView date;
    TextView hour;
  }
  
  public class ViewHolder2{
    ImageView image;
    TextView text;
    Button button;
  }

  public Object getItem(int arg0) {
    return mStrings.get(arg0);
  }

  public long getItemId(int arg0) {
    return arg0;
  }
  
  @Override
  public int getItemViewType(int position) {
    // Define a way to determine which layout to use, here it's just evens and odds.
    int value = position % 4;
    int result;
    if(value!=0)
      result = 1;
    else
      result = 0;
    return result;
  }

  @Override
  public int getViewTypeCount() {
    return 2; // Count of different layouts
  }

}
