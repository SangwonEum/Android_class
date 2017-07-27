package androidbook.ch03;

import java.util.ArrayList;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import androidbook.ch03.highrankwidget.HighRankWidget;
import androidbook.ch03.layout.LayoutActivity;
import androidbook.ch03.widget.WidgetActivity;

public class Main extends Activity implements OnItemClickListener{
	private ListView list;
	private ArrayList<String> activity;
	private ArrayAdapter<String> adapter;
	private Intent intent;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        list = (ListView)findViewById(R.id.list);
        activity = new ArrayList<String>();
        activity.add("Layout");
        activity.add("Widget");
        activity.add("High Rank Widget");
        adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, activity);
        list.setAdapter(adapter);
        list.setOnItemClickListener(this);
    }
	public void onItemClick(AdapterView<?> arg0, View arg1, int position, long id) {
		// TODO Auto-generated method stub
		switch(position)
		{
		case 0 :
			intent = new Intent(Main.this,LayoutActivity.class);
			startActivity(intent);
			break;
		case 1 :
			intent = new Intent(Main.this,WidgetActivity.class);
			startActivity(intent);
			break;
		case 2 :
			intent = new Intent(Main.this,HighRankWidget.class);
			startActivity(intent);
			break;
		}
	}
}
