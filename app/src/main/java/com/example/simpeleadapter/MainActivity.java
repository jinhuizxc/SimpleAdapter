package com.example.simpeleadapter;

import java.util.ArrayList;
import java.util.HashMap;


import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.webkit.WebSettings.TextSize;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity implements OnItemClickListener {

	ArrayList<HashMap<String, Object>> data;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		//图片数据
		int imageIds[] = {R.drawable.a01,R.drawable.a02,R.drawable.a03,R.drawable.a04,R.drawable.a05};
		//文字
		String texts[] = {"aa","bb","cc","dd","ee"};

		//链表 + hashMap保存文字,图片
		data = new ArrayList<HashMap<String,Object>>();
		for (int i = 0; i < texts.length; i++) {

			HashMap<String, Object> map = new HashMap<String, Object>();
			map.put("imageId", imageIds[i]);
			map.put("text", texts[i]);
			data.add(map);
		}

		//简单适配器
		SimpleAdapter adapter = new SimpleAdapter(this, data, R.layout.item,
				new String[]{"imageId","text"}, new int[]{R.id.imageView1,R.id.textView1});

		ListView listView = (ListView) findViewById(R.id.listView1);
		listView.setAdapter(adapter);

		//设置listview的监听
		listView.setOnItemClickListener(this);

	}
	//parent:listview;view:Linearlayout.
	@Override
	public void onItemClick(AdapterView<?> parent, View view, int position,
							long id) {
		// TODO Auto-generated method stub
		Log.d("Tag", "item click " + view);
		Log.d("Tag", "parent = " + parent);
		Log.d("Tag", "position = " + position);
		Log.d("Tag", "id = " + id);

		//获取用户点击的item项上的数据对象
		HashMap<String, Object> map = data.get(position);
		int imageId = (Integer) map.get("imageId");
		String text = (String) map.get("text");


		//自定义Toast
		Toast toast = Toast.makeText(this, "", Toast.LENGTH_SHORT);

		//自定义视图
		View tView = LayoutInflater.from(this).inflate(R.layout.toast_view, null);

		//设置数据
		TextView tv = (TextView) tView.findViewById(R.id.textView1);
		ImageView iv = (ImageView) tView.findViewById(R.id.imageView1);

		tv.setText(text);
		iv.setImageResource(imageId);


		toast.setView(tView);
		toast.setGravity(Gravity.CENTER, 0, 100);
		toast.show();
	}
}