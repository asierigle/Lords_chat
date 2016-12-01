/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.upc.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import edu.upc.adapterviews.R;
import edu.upc.util.Message;

import java.util.ArrayList;
import java.util.Date;

/**
 * @author samuelns and asierigle
 */
public class MyAdapter_whatsapp extends BaseAdapter {

  private Context mContext;
  private ArrayList<Message> thisMessages;

  public MyAdapter_whatsapp(Context context, ArrayList<Message> messages) {
    mContext = context;
    this.thisMessages = messages;
  }

  public int getCount() {
    return thisMessages.size();
  }

  public View getView(int position, View convertView, ViewGroup parent) {
    
    ViewHolder viewHolder;
    
    if(convertView==null){
      if(getItemViewType(position) == 0)
      {
        convertView = LayoutInflater.from(mContext).inflate(R.layout.row_whatsapp_right, parent, false);

      }
      if(getItemViewType(position) == 1)
      {
        convertView = LayoutInflater.from(mContext).inflate(R.layout.row_whatsapp_left, parent, false);

      }
      if(getItemViewType(position) == 2)
      {
        convertView = LayoutInflater.from(mContext).inflate(R.layout.row_whatsapp_date, parent, false);

      }

      viewHolder = new ViewHolder();
      viewHolder.text  = (TextView)  convertView.findViewById(R.id.texto);
      convertView.setTag(viewHolder);
    }

    viewHolder = (ViewHolder)convertView.getTag();
    if(getItemViewType(position) == 0 || getItemViewType(position) == 1)
    {
      if(getItemViewType(position) == 0)
        viewHolder.hour_text = (TextView)  convertView.findViewById(R.id.right_hour);

      if(getItemViewType(position) == 1)
        viewHolder.hour_text = (TextView)  convertView.findViewById(R.id.left_hour);

      viewHolder.text.setText(thisMessages.get(position).content);
      viewHolder.hour_text.setText(thisMessages.get(position).realDate.getHours() + ":" + thisMessages.get(position).realDate.getMinutes());
    }

    if(getItemViewType(position) == 2)
    {
      Date date = new Date();
      viewHolder.text.setText(thisMessages.get(position).realDate.toString());

    }
    
    return convertView;
  }
  
  public class ViewHolder{
    TextView text;
    TextView hour_text;
  }

  public Object getItem(int arg0) {
    return thisMessages.get(arg0);
  }

  public long getItemId(int arg0) {
    return arg0;
  }
  
  @Override
  public int getItemViewType(int position) {
    return position % 3;
  }

  @Override
  public int getViewTypeCount() {
    return 3; // Count of different layouts
  }
  
  @Override
  public boolean isEnabled(int position) {
    return false;
  }

}
