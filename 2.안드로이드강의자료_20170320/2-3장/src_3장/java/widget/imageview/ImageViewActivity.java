package androidbook.ch03.widget.imageview;

import java.util.ArrayList;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.AdapterView.OnItemClickListener;
import androidbook.ch03.R;
import androidbook.ch03.layout.LayoutActivity;
import androidbook.ch03.widget.button.ButtonActivity;

public class ImageViewActivity extends Activity implements OnItemClickListener{
	private ListView list;
	private ArrayList<String> activity;
	private ArrayAdapter<String> adapter;
	private Intent intent;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.imageview);
		list = (ListView)findViewById(R.id.list);
        activity = new ArrayList<String>();
        activity.add("BasicImageView");
        activity.add("ComplexImageView");
        adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, activity);
        list.setAdapter(adapter);
        list.setOnItemClickListener(this);
	}
	public void onItemClick(AdapterView<?> arg0, View arg1, int position, long id) {
		// TODO Auto-generated method stub
		switch(position)
		{
		case 0 :
			intent = new Intent(ImageViewActivity.this,BasicImageView.class);
			startActivity(intent);
			break;
		case 1 :
			intent = new Intent(ImageViewActivity.this,ComplexImageView.class);
			startActivity(intent);
			break;
		}
	}
}
