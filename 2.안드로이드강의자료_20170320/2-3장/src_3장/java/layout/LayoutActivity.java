package androidbook.ch03.layout;

import java.util.ArrayList;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import androidbook.ch03.R;
import androidbook.ch03.SampleActivity;
import androidbook.ch03.layout.framelayout.FrameLayoutActivity;
import androidbook.ch03.layout.linearlayout.LinearlayoutActivity;
import androidbook.ch03.layout.relativelayout.RelativeLayoutActivity;
import androidbook.ch03.layout.tablayout.TabLayoutActivity;
import androidbook.ch03.layout.tablelayout.TableLayoutActivity;

public class LayoutActivity extends Activity implements OnItemClickListener {
	private ListView list;
	private ArrayList<String> activity;
	private ArrayAdapter<String> adapter;
	private Intent intent;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.layout);
		list = (ListView)findViewById(R.id.list);
        activity = new ArrayList<String>();
        activity.add("SampleActivity");
        activity.add("LinearLayout");
        activity.add("FrameLayout");
        activity.add("RelativeLayout");
        activity.add("TableLayout");
        activity.add("TabLayout");
        adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, activity);
        list.setAdapter(adapter);
        list.setOnItemClickListener(this);
    }
    public void onItemClick(AdapterView<?> arg0, View arg1, int position, long id) {
		// TODO Auto-generated method stub
		switch(position)
		{
		case 0 :
			intent = new Intent(LayoutActivity.this,SampleActivity.class);
			startActivity(intent);
			break;
		case 1 :
			intent = new Intent(LayoutActivity.this,LinearlayoutActivity.class);
			startActivity(intent);
			break;
		case 2 :
			intent = new Intent(LayoutActivity.this,FrameLayoutActivity.class);
			startActivity(intent);
			break;
		case 3 :
			intent = new Intent(LayoutActivity.this,RelativeLayoutActivity.class);
			startActivity(intent);
			break;
		case 4 :
			intent = new Intent(LayoutActivity.this,TableLayoutActivity.class);
			startActivity(intent);
			break;
		case 5 :
			intent = new Intent(LayoutActivity.this,TabLayoutActivity.class);
			startActivity(intent);
			break;
		}
		
	}
}
