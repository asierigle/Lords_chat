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
import android.widget.RelativeLayout;
import android.widget.TextView;
import edu.upc.adapterviews.R;
import java.util.ArrayList;

/**
 *
 * @author juanluis
 */
public class MyAdapter extends BaseAdapter {

  private Context mContext;
  private ArrayList<String> mStrings;
  private ArrayList<Drawable> mImageList;

  public MyAdapter(Context context, ArrayList<String> mStrings, ArrayList<Drawable> imageList) {
    mContext = context;
    this.mStrings = mStrings;
    mImageList = imageList;
  }

  public int getCount() {
    return mStrings.size();
  }

  public View getView(int position, View convertView, ViewGroup parent) {
    
    ViewHolder viewHolder;
    
    if(convertView==null){
      convertView = LayoutInflater.from(mContext).inflate(R.layout.row_customized_image_text_button, parent, false);
      viewHolder = new ViewHolder();
      viewHolder.image = (ImageView) convertView.findViewById(R.id.image);
      viewHolder.text  = (TextView)  convertView.findViewById(R.id.texto);
      viewHolder.button= (Button)    convertView.findViewById(R.id.button);
      convertView.setTag(viewHolder);
    }
    
    viewHolder = (ViewHolder)convertView.getTag();
    viewHolder.image.setImageDrawable(mImageList.get(position%4));
    viewHolder.text.setText(mStrings.get(position));
    viewHolder.button.setTag(position);
    
//    if(position%2==0){
//      RelativeLayout.LayoutParams rlp = (RelativeLayout.LayoutParams)viewHolder.text.getLayoutParams();
//      rlp.leftMargin = 0;
//      rlp.addRule(RelativeLayout.ALIGN_PARENT_LEFT);
//      rlp.addRule(RelativeLayout.BELOW, viewHolder.image.getId());
//    }
    
    return convertView;
  }
  
  public class ViewHolder{
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

}
