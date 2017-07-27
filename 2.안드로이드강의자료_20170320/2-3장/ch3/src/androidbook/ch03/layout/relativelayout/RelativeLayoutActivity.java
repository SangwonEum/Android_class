package androidbook.ch03.layout.relativelayout;

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

public class RelativeLayoutActivity extends Activity implements OnItemClickListener{
	private ListView list;
	private ArrayList<String> activity;
	private ArrayAdapter<String> adapter;
	private Intent intent;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.relativelayout);
        list = (ListView)findViewById(R.id.list);
        activity = new ArrayList<String>();
        activity.add("BasicRelativeLayout");
        activity.add("ComplexRelativeLayout");
        adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, activity);
        list.setAdapter(adapter);
        list.setOnItemClickListener(this);
    }
    public void onItemClick(AdapterView<?> arg0, View arg1, int position, long id) {
		// TODO Auto-generated method stub
		switch(position)
		{
		case 0 :
			intent = new Intent(RelativeLayoutActivity.this,BasicRelativeLayout.class);
			startActivity(intent);
			break;
		case 1 :
			intent = new Intent(RelativeLayoutActivity.this,ComplexRelativeLayout.class);
			startActivity(intent);
			break;
		}
	}
}
