package edu.upc.adapterviews;

import android.app.Activity;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.widget.*;
import edu.upc.adapters.MyAdapter;
import edu.upc.adapters.MyAdapter2;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Activity3 extends Activity implements ListView.OnItemClickListener {

  private ArrayList<String> nameList;
  private ArrayList<Drawable> imageList;
  private ListView listView;
  private ArrayAdapter<String> adapter1;
  private BaseAdapter adapter2;
  private int counter;

  @Override
  public void onCreate(Bundle icicle) {
    super.onCreate(icicle);

    setContentView(R.layout.main3_v0);

    nameList = new ArrayList<String>();
    nameList.add("john");
    nameList.add("peter");
    nameList.add("andrew");
    nameList.add("molly");

    imageList = new ArrayList<Drawable>();
    imageList.add(getResources().getDrawable(R.drawable.kitchen1));
    imageList.add(getResources().getDrawable(R.drawable.kitchen2));
    imageList.add(getResources().getDrawable(R.drawable.kitchen3));
    imageList.add(getResources().getDrawable(R.drawable.kitchen4));
    
    List<Map<String, Object>> list_map = new ArrayList<Map<String, Object>>();
    List<Integer> drawableIdList = new ArrayList<Integer>();
    drawableIdList.add(R.drawable.kitchen1);
    drawableIdList.add(R.drawable.kitchen2);
    drawableIdList.add(R.drawable.kitchen3);
    drawableIdList.add(R.drawable.kitchen4);
    for (int i = 0; i < nameList.size(); i++) {
      Map<String, Object> map = new HashMap<String, Object>();
      map.put("name", nameList.get(i));
      map.put("image", drawableIdList.get(i));
      list_map.add(map);
    }

    adapter1 = new ArrayAdapter<String>(this, R.layout.row_customized_image_text_button, R.id.texto, nameList);
    listView = (ListView) findViewById(R.id.main3ListView);
    listView.setAdapter(adapter1);
    listView.setOnItemClickListener(this);

//    String[] from = {"name", "image"};
//    int[] to = {R.id.texto, R.id.image};
//    adapter2 = new SimpleAdapter(this, list_map, R.layout.row_customized_image_text_button, from, to);
//    listView = (ListView) findViewById(R.id.main3ListView);
//    listView.setAdapter(adapter2);
//    listView.setOnItemClickListener(this);
    
//    adapter2 = new MyAdapter(this, nameList, imageList);
//    listView = (ListView) findViewById(R.id.main3ListView);
//    listView.setAdapter(adapter2);
//    listView.setOnItemClickListener(this);
    
//    adapter2 = new MyAdapter2(this, nameList, imageList);
//    listView = (ListView) findViewById(R.id.main3ListView);
//    listView.setAdapter(adapter2);

  }

  public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
    Toast.makeText(this, "item clicked: " + nameList.get(position), Toast.LENGTH_SHORT).show();
  }

  public void Okay(final View view) {
    
    if(view.getTag()!=null){
      Toast.makeText(this, "Okay clicked at: " + view.getTag(), Toast.LENGTH_SHORT).show();
      int position = Integer.parseInt(view.getTag().toString());
      nameList.add(position, "" + (++counter));
      adapter2.notifyDataSetChanged();
    }
    else
      Toast.makeText(this, "Okay clicked!!!", Toast.LENGTH_SHORT).show();
  }

}
