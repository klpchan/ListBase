package com.klp.ListBase;

import java.util.ArrayList;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

public class ListBaseActivity extends Activity {
    /** Called when the activity is first created. */
	public static final String TAG = "ListBaseActivity";
	
	ListView mBaseList;
	BaseAdapter mBaseAdapter;
	SimpleAdapter mSimpleAdapter;
	
	ArrayList<Integer> mThemeImage;
	ArrayList<String> mThemeDetal;
	ArrayList<String> mThemeTitle;
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        
        init();
        bindData();
        showList();
    }
    
    private void init()
    {
      setContentView(R.layout.main);
      mBaseList = (ListView) findViewById(R.id.base_list);
      mBaseAdapter = new BaseListAdapter();
      mThemeImage = new ArrayList<Integer>();
      mThemeTitle = new ArrayList<String>();
      mThemeDetal = new ArrayList<String>();
    }
    
    private void bindData()
    {
      int[] arrayOfInt = getResources().getIntArray(R.array.ThemeImageArray);
      String[] arrayOfString1 = getResources().getStringArray(R.array.ThemeTitleArray);
      String[] arrayOfString2 = getResources().getStringArray(R.array.ThemeDescripArray);
      for (int i = 0; i < arrayOfInt.length; i++)
      {
        mThemeImage.add(i, Integer.valueOf(arrayOfInt[i]));
        mThemeTitle.add(i, arrayOfString1[i]);
        mThemeDetal.add(i, arrayOfString2[i]);
      }
    }
    
    private void showList(){
    	if (null != mBaseList && null != mBaseAdapter) {
    		mBaseList.setAdapter(mBaseAdapter);
		}
    	
    	mBaseList.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				// TODO Auto-generated method stub
				parent.getChildAt(position).setBackgroundColor(Color.RED);
				parent.getFirstVisiblePosition();
				Toast.makeText(getApplicationContext(), "position is " + position, Toast.LENGTH_SHORT).show();
			}
		});
    }
    
    private class BaseListAdapter extends BaseAdapter{

    	//确定需要显示多少条列表项信息
		public int getCount() {
			// TODO Auto-generated method stub
			return mThemeTitle.size();
		}
		//确定在Position位置处的列表项对象
		public Object getItem(int position) {
			// TODO Auto-generated method stub
			return position;
		}
		//确定在Position位置处的列表项对象ID
		public long getItemId(int position) {
			// TODO Auto-generated method stub
			return position;
		}
		/*
		 * 确定在position位置处的列表项显示格式及内容，可以手动创建一个视图对象或者从布局xml文件导入
		 */
		public View getView(int position, View convertView, ViewGroup parent) {
			// TODO Auto-generated method stub
		    ViewHolder localViewHolder;
		    if (convertView == null)
		    {
		        localViewHolder = new ViewHolder();
		        convertView = LayoutInflater.from(ListBaseActivity.this).inflate(R.layout.list_item, parent, false);
		        localViewHolder.image = ((ImageView)convertView.findViewById(R.id.preview_image));
		        localViewHolder.title = ((TextView)convertView.findViewById(R.id.theme_title));
		        localViewHolder.detail = ((TextView)convertView.findViewById(R.id.theme_detail));
		        convertView.setTag(localViewHolder);
		    } else {
		    	localViewHolder = (ViewHolder)convertView.getTag();
			}
//		    Toast.makeText(ListBaseActivity.this, "" + localViewHolder, Toast.LENGTH_SHORT).show();
		    Log.v(TAG, "localViewHolder is " + localViewHolder);
		    if ((mThemeImage != null) && (mThemeImage.size() > position))
		        localViewHolder.image.setBackgroundColor((Integer)mThemeImage.get(position));
		    if ((mThemeTitle != null) && (mThemeTitle.size() > position))
		        localViewHolder.title.setText((CharSequence)mThemeTitle.get(position));
		    if ((mThemeDetal != null) && (mThemeDetal.size() > position))
		        localViewHolder.detail.setText((CharSequence)mThemeDetal.get(position));
		        return convertView;
		}
		
		class ViewHolder
	    {
	      TextView detail;
	      ImageView image;
	      TextView title;
	    }
    }
}